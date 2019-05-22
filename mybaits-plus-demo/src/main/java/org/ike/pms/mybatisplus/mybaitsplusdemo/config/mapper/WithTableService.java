package org.ike.pms.mybatisplus.mybaitsplusdemo.config.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface WithTableService<T> extends IService<T> {
    T getByIdWithTable(String table, Serializable id);

    default T getOneWithTable(String table) {
        return getOneWithTable(table, new QueryWrapper<>());
    }

    T getOneWithTable(String table, Wrapper<T> queryWrapper);

    List<T> listWithTable(String table);

    List<T> listWithTable(String table, QueryWrapper<T> queryWrapper);

    default int countWithTable(String table) {
        return countWithTable(table, new QueryWrapper<>());
    }

    int countWithTable(String table, QueryWrapper<T> queryWrapper);

    boolean saveWithTable(String table, T entity);

    default boolean saveBatchWithTable(String table, Collection<T> list) {
        return saveBatchWithTable(table, list);
    }
    boolean saveBatchWithTable(String table, Collection<T> list, int batchSize);

    boolean removeByIdWithTable(String table, Serializable id);

    boolean removeByIdsWithTable(String table, Collection<? extends Serializable> idList);

    boolean removeWithTable(String table, QueryWrapper<T> queryWrapper);

    boolean removeWithTable(String table, T entity);

    boolean updateWithTable(String table, T entity);

    boolean updateWithTable(String table, T entity, Wrapper<T> updateWrapper);

    default boolean updateBatchByIdWithTable(String table, Collection<T> entityList) {
        return updateBatchByIdWithTable(table, entityList, 1000);
    }

    boolean updateBatchByIdWithTable(String table, Collection<T> entityList, int batchSize);

    boolean saveOrUpdateByIdWithTable(String table, T entity);

    boolean saveOrUpdateWithTable(String table, T entity, Wrapper<T> updateWrapper);

    default boolean saveOrUpdateBatchByIdWithTable(String table, Collection<T> entityList) {
        return this.saveOrUpdateBatchByIdWithTable(table, entityList, 1000);
    }

    boolean saveOrUpdateBatchByIdWithTable(String table, Collection<T> entityList, int batchSize);

    default boolean saveOrUpdateBatchWithTable(String table, Collection<T> entityList,Collection<String> columnsList) {
        return saveOrUpdateBatchWithTable(table, entityList, columnsList,1000);
    }

    boolean saveOrUpdateBatchWithTable(String table, Collection<T> entityList,Collection<String> columnsList, int batchSize);
}
