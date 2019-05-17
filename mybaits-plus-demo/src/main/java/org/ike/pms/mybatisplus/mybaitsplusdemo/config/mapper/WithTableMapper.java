package org.ike.pms.mybatisplus.mybaitsplusdemo.config.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface WithTableMapper<T> extends BaseMapper<T> {
    List<T> listWithTable(@Param(WTConstants.TABLE)String table);

    List<T> listWithTable(@Param(WTConstants.TABLE) String table, @Param(Constants.WRAPPER) QueryWrapper<T> queryWrapper);

    int saveWithTable(@Param(WTConstants.TABLE)String table,@Param(WTConstants.OBJ) T entity);

    int saveBatchWithTable(@Param(WTConstants.TABLE)String table,@Param(Constants.COLLECTION) Collection<T> list);

    int removeByIdWithTable(@Param(WTConstants.TABLE)String table, @Param(WTConstants.ID) Serializable id);

    int removeByIdsWithTable(@Param(WTConstants.TABLE)String table,@Param(Constants.COLLECTION)Collection<? extends Serializable> list);

    int removeWithTable(@Param(WTConstants.TABLE)String table, @Param(Constants.WRAPPER) QueryWrapper<T> queryWrapper);

    int updateWithTable(@Param(WTConstants.TABLE)String table, @Param(WTConstants.OBJ)T entity);

    int updateWithTable(@Param(WTConstants.TABLE)String table, @Param(WTConstants.OBJ)T entity, @Param(Constants.WRAPPER) UpdateWrapper<T> updateWrapper);

    int updateBatchByIdWithTable(@Param(WTConstants.TABLE)String table, @Param(Constants.COLLECTION)Collection<T> list);

    int saveOrUpdateWithTable(@Param(WTConstants.TABLE)String table, @Param(WTConstants.OBJ)T entity);

    int saveOrUpdateBatchWithTable(@Param(WTConstants.TABLE)String table, @Param(Constants.COLLECTION)Collection<T> list);
}
