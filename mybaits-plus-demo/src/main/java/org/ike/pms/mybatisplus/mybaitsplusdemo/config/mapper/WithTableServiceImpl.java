package org.ike.pms.mybatisplus.mybaitsplusdemo.config.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class WithTableServiceImpl<M extends WithTableMapper<T>, T> extends ServiceImpl<M, T> implements WithTableService<T> {
    @Autowired
    protected M withTableMapper;

    public M getBaseMapper() {
        return this.withTableMapper;
    }

    protected String sqlStatement(WTSqlMethod wtsqlMethod) {
        return SqlHelper.table(this.currentModelClass()).getSqlStatement(wtsqlMethod.getMethod());
    }

    @Override
    public T getByIdWithTable(String table, Serializable id) {
        return this.withTableMapper.getByIdWithTable(table, id);
    }

    @Override
    public T getOneWithTable(String table, Wrapper<T> queryWrapper) {
        return this.withTableMapper.getOneWithTable(table, queryWrapper);
    }

    @Override
    public List<T> listWithTable(String table) {
        return this.withTableMapper.listWithTable(table);
    }

    @Override
    public List<T> listWithTable(String table, QueryWrapper<T> queryWrapper) {
        return this.withTableMapper.listWithTable(table, queryWrapper);
    }

    @Override
    public int countWithTable(String table, Wrapper<T> queryWrapper) {
        return this.withTableMapper.countWithTable(table, queryWrapper);
    }

    public boolean saveWithTable(String table, T entity) {

        return this.retBool(this.withTableMapper.saveWithTable(table, entity));
    }
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveBatchWithTable(String table, Collection<T> entityList, int batchSize) {
        String sqlStatement = this.sqlStatement(WTSqlMethod.WT_INSERT_ONE);
        SqlSession batchSqlSession = this.sqlSessionBatch();
        Throwable var5 = null;

        try {
            int i = 0;

            for(Iterator<T> var7 = entityList.iterator(); var7.hasNext(); ++i) {
                T anEntityList = var7.next();
                ParamMap<Object> param = new ParamMap();
                param.put(WTConstants.OBJ, anEntityList);
                param.put(WTConstants.TABLE, table);
                batchSqlSession.insert(sqlStatement, param);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }

            batchSqlSession.flushStatements();
            return true;
        } catch (Throwable var16) {
            var5 = var16;
            throw var16;
        } finally {
            if (batchSqlSession != null) {
                if (var5 != null) {
                    try {
                        batchSqlSession.close();
                    } catch (Throwable var15) {
                        var5.addSuppressed(var15);
                    }
                } else {
                    batchSqlSession.close();
                }
            }

        }
    }

    @Override
    public boolean removeByIdWithTable(String table, Serializable id) {
        return SqlHelper.retBool(this.withTableMapper.removeByIdWithTable(table, id));
    }

    @Override
    public boolean removeByIdsWithTable(String table, Collection<? extends Serializable> idList) {
        return SqlHelper.retBool(this.withTableMapper.removeByIdsWithTable(table, idList));
    }

    @Override
    public boolean removeWithTable(String table, QueryWrapper<T> queryWrapper) {
        return SqlHelper.retBool(this.withTableMapper.removeWithTable(table, queryWrapper));
    }

    @Override
    public boolean removeWithTable(String table, T entity) {
        return SqlHelper.retBool(this.withTableMapper.removeWithTable(table, entity));
    }

    @Override
    public boolean updateWithTable(String table, T entity) {
        return SqlHelper.retBool(this.withTableMapper.updateWithTable(table, entity));
    }

    @Override
    public boolean updateWithTable(String table, T entity, Wrapper<T> updateWrapper) {
        return SqlHelper.retBool(this.withTableMapper.updateWithTable(table, entity, updateWrapper));
    }

    @Override
    public boolean updateBatchByIdWithTable(String table, Collection<T> entityList, int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty", new Object[0]);
        String sqlStatement = this.sqlStatement(WTSqlMethod.WT_UPDATE_BY_ID);
        SqlSession batchSqlSession = this.sqlSessionBatch();
        Throwable var5 = null;

        try {
            int i = 0;

            for (Iterator<T> var7 = entityList.iterator(); var7.hasNext(); ++i) {
                T anEntityList = var7.next();
                ParamMap<Object> param = new ParamMap();
                param.put(WTConstants.OBJ, anEntityList);
                param.put(WTConstants.TABLE, table);
                batchSqlSession.update(sqlStatement, param);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }

            batchSqlSession.flushStatements();
            return true;
        } catch (Throwable var17) {
            var5 = var17;
            throw var17;
        } finally {
            if (batchSqlSession != null) {
                if (var5 != null) {
                    try {
                        batchSqlSession.close();
                    } catch (Throwable var16) {
                        var5.addSuppressed(var16);
                    }
                } else {
                    batchSqlSession.close();
                }
            }

        }
    }

    @Override
    public boolean saveOrUpdateByIdWithTable(String table, T entity) {
        if (null == entity) {
            return false;
        } else {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
            Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
            return !StringUtils.checkValNull(idVal) && !Objects.isNull(this.getByIdWithTable(table, (Serializable) idVal)) ? this.updateWithTable(table, entity) : this.saveWithTable(table, entity);
        }
    }

    @Override
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
    public boolean saveOrUpdateWithTable(String table, T entity, Collection<String> idList) {
        if (null == entity) {
            return false;
        } else {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
            QueryWrapper<T> queryWrapper = new QueryWrapper<>();
            tableInfo.getFieldList().stream().filter(fieldInfo -> idList.contains(fieldInfo.getColumn())).forEach(field -> {
                queryWrapper.eq(field.getColumn(),ReflectionKit.getMethodValue(cls, entity, field.getProperty()));
            });
            if (!idList.isEmpty() && idList.contains(tableInfo.getKeyColumn())) {
                queryWrapper.eq(tableInfo.getKeyColumn(),ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty()));
            }
            return queryWrapper.nonEmptyOfWhere() && this.countWithTable(table, queryWrapper)>0 ? this.updateWithTable(table, entity, queryWrapper) : this.saveWithTable(table, entity);
        }
    }

    @Override
    public boolean saveOrUpdateBatchByIdWithTable(String table, Collection<T> entityList, int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty", new Object[0]);
        Class<?> cls = this.currentModelClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
        SqlSession batchSqlSession = this.sqlSessionBatch();
        Throwable var7 = null;

        try {
            int i = 0;

            for(Iterator<T> var9 = entityList.iterator(); var9.hasNext(); ++i) {
                T entity = var9.next();
                Object idVal = ReflectionKit.getMethodValue(cls, entity, keyProperty);
                ParamMap<Object> param = new ParamMap();
                param.put(WTConstants.OBJ, entity);
                param.put(WTConstants.TABLE, table);
                if (!StringUtils.checkValNull(idVal) && !Objects.isNull(this.getById((Serializable)idVal))) {

                    batchSqlSession.update(this.sqlStatement(WTSqlMethod.WT_UPDATE_BY_ID), param);
                } else {
                    batchSqlSession.insert(this.sqlStatement(WTSqlMethod.WT_INSERT_ONE), param);
                }

                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }

            batchSqlSession.flushStatements();
            return true;
        } catch (Throwable var20) {
            var7 = var20;
            throw var20;
        } finally {
            if (batchSqlSession != null) {
                if (var7 != null) {
                    try {
                        batchSqlSession.close();
                    } catch (Throwable var19) {
                        var7.addSuppressed(var19);
                    }
                } else {
                    batchSqlSession.close();
                }
            }

        }
    }

    @Override
    public boolean saveOrUpdateBatchWithTable(String table, Collection<T> entityList, Collection<String> idList, int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty", new Object[0]);
        Class<?> cls = this.currentModelClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
        SqlSession batchSqlSession = this.sqlSessionBatch();
        Throwable var7 = null;

        try {
            int i = 0;

            for(Iterator<T> var9 = entityList.iterator(); var9.hasNext(); ++i) {
                T entity = var9.next();
                Object idVal = ReflectionKit.getMethodValue(cls, entity, keyProperty);

                QueryWrapper<T> queryWrapper = new QueryWrapper<>();
                tableInfo.getFieldList().stream().filter(fieldInfo -> idList.contains(fieldInfo.getColumn())).forEach(field -> {
                    queryWrapper.eq(field.getColumn(),ReflectionKit.getMethodValue(cls, entity, field.getProperty()));
                });
                if (!idList.isEmpty() && idList.contains(tableInfo.getKeyColumn())) {
                    queryWrapper.eq(tableInfo.getKeyColumn(),ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty()));
                }
                ParamMap<Object> param = new ParamMap<>();
                param.put(WTConstants.OBJ, entity);
                param.put(WTConstants.TABLE, table);
                if (!StringUtils.checkValNull(idVal) && this.countWithTable(table, queryWrapper)>0) {
                    param.put(WTConstants.WRAPPER, queryWrapper);
                    batchSqlSession.update(this.sqlStatement(WTSqlMethod.WT_UPDATE_BY_ID), param);
                } else {
                    batchSqlSession.insert(this.sqlStatement(WTSqlMethod.WT_INSERT_ONE), param);
                }

                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }

            batchSqlSession.flushStatements();
            return true;
        } catch (Throwable var20) {
            var7 = var20;
            throw var20;
        } finally {
            if (batchSqlSession != null) {
                if (var7 != null) {
                    try {
                        batchSqlSession.close();
                    } catch (Throwable var19) {
                        var7.addSuppressed(var19);
                    }
                } else {
                    batchSqlSession.close();
                }
            }

        }
    }
}
