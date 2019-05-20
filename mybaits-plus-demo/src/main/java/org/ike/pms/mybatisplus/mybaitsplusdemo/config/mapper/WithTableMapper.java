package org.ike.pms.mybatisplus.mybaitsplusdemo.config.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface WithTableMapper<T> extends BaseMapper<T> {
    T getOneWithTable(@Param(WTConstants.TABLE) String table);

    T getOneWithTable(@Param(WTConstants.TABLE) String table, @Param(WTConstants.WRAPPER) QueryWrapper<T> queryWrapper);

    List<T> listWithTable(@Param(WTConstants.TABLE)String table);

    List<T> listWithTable(@Param(WTConstants.TABLE) String table, @Param(WTConstants.WRAPPER) QueryWrapper<T> queryWrapper);

    int countWithTable(@Param(WTConstants.TABLE) String table);

    int countWithTable(@Param(WTConstants.TABLE) String table, @Param(WTConstants.WRAPPER) QueryWrapper<T> queryWrapper);

    int saveWithTable(@Param(WTConstants.TABLE)String table,@Param(WTConstants.OBJ) T entity);

    int saveBatchWithTable(@Param(WTConstants.TABLE)String table,@Param(WTConstants.COLLECTION) Collection<T> list);

    int removeByIdWithTable(@Param(WTConstants.TABLE)String table, @Param(WTConstants.ID) Serializable id);

    int removeByIdsWithTable(@Param(WTConstants.TABLE)String table,@Param(WTConstants.COLLECTION)Collection<? extends Serializable> list);

    int removeWithTable(@Param(WTConstants.TABLE)String table, @Param(WTConstants.WRAPPER) QueryWrapper<T> queryWrapper);

    int updateWithTable(@Param(WTConstants.TABLE)String table, @Param(WTConstants.OBJ)T entity);

    int updateWithTable(@Param(WTConstants.TABLE)String table, @Param(WTConstants.OBJ)T entity, @Param(WTConstants.WRAPPER) UpdateWrapper<T> updateWrapper);

    int updateBatchByIdWithTable(@Param(WTConstants.TABLE)String table, @Param(WTConstants.COLLECTION)Collection<T> list);

    int saveOrUpdateWithTable(@Param(WTConstants.TABLE)String table, @Param(WTConstants.OBJ)T entity);

    int saveOrUpdateBatchWithTable(@Param(WTConstants.TABLE)String table, @Param(WTConstants.COLLECTION)Collection<T> list);
}
