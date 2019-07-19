package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.mapper.WTConstants;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

public class WTServiceImpl<M extends WTMapper<T>, T> implements WTService<T> {

    @Autowired
    protected M wtMapper;

    @Override
    public WTMapper<T> getWTMapper() {
        return wtMapper;
    }

    /**
     * 判断数据库操作是否成功
     *
     * @param result 数据库操作返回影响条数
     * @return boolean
     */
    protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    /**
     * 批量操作 SqlSession
     */
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(currentModelClass());
    }

    /**
     * 释放sqlSession
     *
     * @param sqlSession session
     */
    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(currentModelClass()));
    }

    /**
     * 获取 SqlStatement
     *
     * @param sqlMethod ignore
     * @return ignore
     */
    protected String sqlStatement(WTSqlMethod sqlMethod) {
        return SqlHelper.table(currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }

    @Override
    public boolean saveWithTable(String table, T entity) {
        return retBool(wtMapper.wt_insert(table, entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatchWithTable(String table, Collection<T> entityList, int batchSize) {
        String sqlStatement = sqlStatement(WTSqlMethod.INSERT_ONE);
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int i = 0;
            for (T anEntityList : entityList) {
                batchSqlSession.insert(sqlStatement, anEntityList);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateWithTable(String table, T entity) {
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
            Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
            return StringUtils.checkValNull(idVal) || Objects.isNull(getByIdWithTable(table, (Serializable) idVal)) ? saveWithTable(table, entity) : updateWithTable(table, entity);
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateWithTable(String table, T entity, Wrapper<T> updateWrapper) {
        if (null == entity) {
            return false;
        } else {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
            return updateWrapper.nonEmptyOfWhere() && this.countWithTable(table, updateWrapper)>0 ? this.updateWithTable(table, entity, updateWrapper) : this.saveWithTable(table, entity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateWithTable(String table, T entity, Collection<String> idColumns) {
        if (null == entity) {
            return false;
        } else {
            Class<?> clazz = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
            QueryWrapper<T> queryWrapper = new QueryWrapper<>();
            tableInfo.getFieldList().stream().filter(fieldInfo -> idColumns.contains(fieldInfo.getColumn())).forEach(field -> {
                queryWrapper.eq(field.getColumn(),ReflectionKit.getMethodValue(clazz, entity, field.getProperty()));
            });
            if (!idColumns.isEmpty() && idColumns.contains(tableInfo.getKeyColumn())) {
                queryWrapper.eq(tableInfo.getKeyColumn(),ReflectionKit.getMethodValue(clazz, entity, tableInfo.getKeyProperty()));
            }
            return queryWrapper.nonEmptyOfWhere() && this.countWithTable(table, queryWrapper)>0 ? this.updateWithTable(table, entity, queryWrapper) : this.saveWithTable(table, entity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateBatchWithTable(String table, Collection<T> entityList, int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty");
        Class<?> cls = currentModelClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int i = 0;
            for (T entity : entityList) {
                Object idVal = ReflectionKit.getMethodValue(cls, entity, keyProperty);
                MapperMethod.ParamMap<Object> param = new MapperMethod.ParamMap<>();
                param.put(Constants.ENTITY, entity);
                param.put(WTConstants.TABLE, table);
                if (StringUtils.checkValNull(idVal) || Objects.isNull(getByIdWithTable(table, (Serializable) idVal))) {
                    batchSqlSession.insert(sqlStatement(WTSqlMethod.INSERT_ONE), param);
                } else {
                    batchSqlSession.update(sqlStatement(WTSqlMethod.UPDATE_BY_ID), param);
                }
                // 不知道以后会不会有人说更新失败了还要执行插入 😂😂😂
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateBatchWithTable(String table, Collection<T> entityList, Collection<String> idColumns, int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty", new Object[0]);
        Class<?> cls = this.currentModelClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
        try (SqlSession batchSqlSession = sqlSessionBatch()){
            int i = 0;
            for (Iterator<T> var9 = entityList.iterator(); var9.hasNext(); ++i) {
                T entity = var9.next();
                Object idVal = ReflectionKit.getMethodValue(cls, entity, keyProperty);

                QueryWrapper<T> queryWrapper = new QueryWrapper<>();
                tableInfo.getFieldList().stream().filter(fieldInfo -> idColumns.contains(fieldInfo.getColumn())).forEach(field -> {
                    queryWrapper.eq(field.getColumn(), ReflectionKit.getMethodValue(cls, entity, field.getProperty()));
                });
                if (!idColumns.isEmpty() && idColumns.contains(tableInfo.getKeyColumn())) {
                    queryWrapper.eq(tableInfo.getKeyColumn(), ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty()));
                }
                MapperMethod.ParamMap<Object> param = new MapperMethod.ParamMap<>();
                param.put(WTConstants.OBJ, entity);
                param.put(WTConstants.TABLE, table);
                if (!StringUtils.checkValNull(idVal) && this.countWithTable(table, queryWrapper) > 0) {
                    param.put(WTConstants.WRAPPER, queryWrapper);
                    batchSqlSession.update(this.sqlStatement(WTSqlMethod.UPDATE_BY_ID), param);
                } else {
                    batchSqlSession.insert(this.sqlStatement(WTSqlMethod.INSERT_ONE), param);
                }

                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }
            batchSqlSession.flushStatements();
        }
        return true;
    }

    @Override
    public T getByIdWithTable(String table, Serializable id) {
        return wtMapper.wt_selectById(table, id);
    }

    @Override
    public T getOneWithTable(String table, Wrapper<T> queryWrapper) {
        return wtMapper.wt_selectOne(table, queryWrapper);
    }

    @Override
    public List<T> listWithTable(String table, Map<String, Object> columnMap) {
        return wtMapper.wt_selectByMap(table, columnMap);
    }

    @Override
    public List<Map<String, Object>> listMapsWithTable(String table, Wrapper<T> queryWrapper) {
        return wtMapper.wt_selectMaps(table, queryWrapper);
    }

    @Override
    public List<Object> listObjWithTable(String table, Wrapper<T> queryWrapper) {
        return wtMapper.wt_selectObjs(table, queryWrapper);
    }

    @Override
    public IPage<T> pageWithTable(IPage<T> page, String table, Wrapper<T> queryWrapper) {
        return wtMapper.wt_selectPage(page, table, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> pageMapsWithTable(IPage<T> page, String table, Wrapper<T> queryWrapper) {
        return wtMapper.wt_selectMapsPage(page, table, queryWrapper);
    }

    @Override
    public List<T> listWithTable(String table, Wrapper<T> queryWrapper) {
        return wtMapper.wt_selectList(table, queryWrapper);
    }

    @Override
    public List<T> listWithTable(String table, Collection<? extends Serializable> idList) {
        return wtMapper.wt_selectBatchIds(table, idList);
    }

    @Override
    public int countWithTable(String table, Wrapper<T> queryWrapper) {
        return wtMapper.wt_selectCount(table, queryWrapper);
    }

    @Override
    public boolean removeByIdWithTable(String table, Serializable id) {
        return retBool(wtMapper.wt_deleteById(table, id));
    }

    @Override
    public boolean removeBatchIdsWithTable(String table, Collection<? extends Serializable> idList) {
        return retBool(wtMapper.wt_deleteBatchIds(table, idList));
    }

    @Override
    public boolean removeWithTable(String table, Wrapper<T> queryWrapper) {
        return retBool(wtMapper.wt_delete(table, queryWrapper));
    }

    @Override
    public boolean removeWithTable(String table, T entity) {
        Assert.notNull(entity, "error: can not execute. because can not find entity");
        Class<?> clazz = entity.getClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(tableInfo.getKeyColumn(),ReflectionKit.getMethodValue(clazz, entity, tableInfo.getKeyProperty()));
        return retBool(wtMapper.wt_deleteById(table, queryWrapper));
    }

    @Override
    public boolean removeWithTable(String table, Map<String, Object> columnMap) {
        return retBool(wtMapper.wt_deleteByMap(table, columnMap));
    }

    @Override
    public boolean updateWithTable(String table, T entity) {
        return retBool(wtMapper.wt_updateById(table, entity));
    }

    @Override
    public boolean updateWithTable(String table, T entity, Wrapper<T> updateWrapper) {
        return retBool(wtMapper.wt_update(table, entity, updateWrapper));
    }

    @Override
    public boolean updateBatchByIdWithTable(String table, Collection<T> entityList, int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty");
        String sqlStatement = sqlStatement(WTSqlMethod.UPDATE_BY_ID);
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int i = 0;
            for (T anEntityList : entityList) {
                MapperMethod.ParamMap<Object> param = new MapperMethod.ParamMap<>();
                param.put(Constants.ENTITY, anEntityList);
                param.put(WTConstants.TABLE, table);
                batchSqlSession.update(sqlStatement, param);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        }
        return true;
    }
}
