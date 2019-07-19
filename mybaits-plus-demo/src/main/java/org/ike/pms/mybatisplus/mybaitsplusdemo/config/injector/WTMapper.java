package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.mapper.WTConstants;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface WTMapper<T> extends Mapper<T> {
    /**
     * 公共数据操作方法
     */
    int wt_insert(@Param(WTConstants.TABLE) String table, @Param("et") T entity);

    int wt_deleteById(@Param(WTConstants.TABLE) String table, @Param("id") Serializable id);

    int wt_deleteByMap(@Param(WTConstants.TABLE) String table, @Param("cm") Map<String, Object> columnMap);

    int wt_delete(@Param(WTConstants.TABLE) String table, @Param("ew") Wrapper<T> wrapper);

    int wt_deleteBatchIds(@Param(WTConstants.TABLE) String table, @Param("coll") Collection<? extends Serializable> idList);

    int wt_updateById(@Param(WTConstants.TABLE) String table, @Param("et") T entity);

    int wt_update(@Param(WTConstants.TABLE) String table, @Param("et") T entity, @Param("ew") Wrapper<T> updateWrapper);

    T wt_selectById(@Param(WTConstants.TABLE) String table, @Param("id") Serializable id);

    List<T> wt_selectBatchIds(@Param(WTConstants.TABLE) String table, @Param("coll") Collection<? extends Serializable> idList);

    List<T> wt_selectByMap(@Param(WTConstants.TABLE) String table, @Param("cm") Map<String, Object> columnMap);

    T wt_selectOne(@Param(WTConstants.TABLE) String table, @Param("ew") Wrapper<T> queryWrapper);

    Integer wt_selectCount(@Param(WTConstants.TABLE) String table, @Param("ew") Wrapper<T> queryWrapper);

    List<T> wt_selectList(@Param(WTConstants.TABLE) String table, @Param("ew") Wrapper<T> queryWrapper);

    List<Map<String, Object>> wt_selectMaps(@Param(WTConstants.TABLE) String table, @Param("ew") Wrapper<T> queryWrapper);

    List<Object> wt_selectObjs(@Param(WTConstants.TABLE) String table, @Param("ew") Wrapper<T> queryWrapper);

    IPage<T> wt_selectPage(IPage<T> page, @Param(WTConstants.TABLE) String table, @Param("ew") Wrapper<T> queryWrapper);

    IPage<Map<String, Object>> wt_selectMapsPage(IPage<T> page, @Param(WTConstants.TABLE) String table, @Param("ew") Wrapper<T> queryWrapper);

}
