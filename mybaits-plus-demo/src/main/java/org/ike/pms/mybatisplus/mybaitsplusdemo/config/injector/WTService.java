package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 顶级service
 *
 * @author ike
 * @since 2019/7/18
 */
public interface WTService<T> {
    /**
     * 获取对应 entity 的 WTMapper
     *
     * @return WTMapper
     */
    WTMapper<T> getWTMapper();

    boolean saveWithTable(String table, T entity);

    @Transactional(rollbackFor = Exception.class)
    default boolean saveBatchWithTable(String table, Collection<T> entityList) {
        return this.saveBatchWithTable(table, entityList, 1000);
    }

    boolean saveBatchWithTable(String table, Collection<T> entityList, int batchSize);

    @Transactional(rollbackFor = Exception.class)
    boolean saveOrUpdateWithTable(String table, T entity);

    @Transactional(rollbackFor = Exception.class)
    boolean saveOrUpdateWithTable(String table, T entity, Wrapper<T> updateWrapper);

    @Transactional(rollbackFor = Exception.class)
    boolean saveOrUpdateWithTable(String table, T entity, Collection<String> idList);

    @Transactional(rollbackFor = Exception.class)
    default boolean saveOrUpdateBatchWithTable(String table, Collection<T> entityList) {
        return this.saveOrUpdateBatchWithTable(table, entityList, 1000);
    }

    boolean saveOrUpdateBatchWithTable(String table, Collection<T> entityList, int batchSize);

    @Transactional(rollbackFor = Exception.class)
    default boolean saveOrUpdateBatchWithTable(String table, Collection<T> entityList, Collection<String> idColumns) {
        return saveOrUpdateBatchWithTable(table, entityList, idColumns, 1000);
    }

    boolean saveOrUpdateBatchWithTable(String table, Collection<T> entityList,Collection<String> idColumns, int batchSize);

    T getByIdWithTable(String table, Serializable id);

    T getOneWithTable(String table, Wrapper<T> queryWrapper);

    default List<T> listWithTable(String table) {
        return listWithTable(table, Wrappers.emptyWrapper());
    }

    List<T> listWithTable(String table, Wrapper<T> queryWrapper);

    List<T> listWithTable(String table, Collection<? extends Serializable> idList);

    List<T> listWithTable(String table, Map<String, Object> columnMap);

    List<Map<String, Object>> listMapsWithTable(String table, Wrapper<T> queryWrapper);

    List<Object> listObjWithTable(String table, Wrapper<T> queryWrapper);

    default IPage<T> pageWithTable(IPage<T> page, String table) {
        return pageWithTable(page, table, Wrappers.emptyWrapper());
    }

    IPage<T> pageWithTable(IPage<T> page, String table, Wrapper<T> queryWrapper);

    default IPage<Map<String, Object>> pageMapsWithTable(IPage<T> page, String table) {
        return pageMapsWithTable(page, table, Wrappers.emptyWrapper());
    }

    IPage<Map<String, Object>> pageMapsWithTable(IPage<T> page, String table, Wrapper<T> queryWrapper);

    default int countWithTable(String table) {
        return countWithTable(table, new QueryWrapper<>());
    }

    int countWithTable(String table, Wrapper<T> queryWrapper);

    boolean removeByIdWithTable(String table, Serializable id);

    boolean removeBatchIdsWithTable(String table, Collection<? extends Serializable> idList);

    boolean removeWithTable(String table, Wrapper<T> queryWrapper);

    boolean removeWithTable(String table, T entity);

    boolean removeWithTable(String table, Map<String, Object> columnMap);

    boolean updateWithTable(String table, T entity);

    boolean updateWithTable(String table, T entity, Wrapper<T> updateWrapper);

    default boolean updateBatchByIdWithTable(String table, Collection<T> entityList) {
        return updateBatchByIdWithTable(table, entityList, 1000);
    }

    boolean updateBatchByIdWithTable(String table, Collection<T> entityList, int batchSize);

}
