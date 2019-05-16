package org.ike.pms.mybatisplus.mybaitsplusdemo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public interface TestMapper extends BaseMapper<TUser> {

    /**
     * 测试模板sql
     */
    List<TUser> listWithTable(@Param("table")String table);

    List<TUser> listWithTable(@Param("table") String table, @Param(Constants.WRAPPER) QueryWrapper<TUser> queryWrapper);

    List<TUser> listByWrapperWithTable(@Param("table") String table, @Param(Constants.WRAPPER) QueryWrapper<TUser> queryWrapper);

    int saveWithTable(@Param("table")String table,@Param("param") TUser tUser);

    int saveWithTable(@Param("table")String table, @Param("param")Map map);

    int saveBatchWithTable(@Param("table")String table,@Param("list") Collection<TUser> list);

    int removeByIdWithTable(@Param("table")String table, @Param("id")String id);

    int removeByIdsWithTable(@Param("table")String table,@Param("list")Collection<? extends Serializable> list);
}
