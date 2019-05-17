package org.ike.pms.mybatisplus.mybaitsplusdemo.dao;

import org.ike.pms.mybatisplus.mybaitsplusdemo.config.mapper.WithTableMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.springframework.stereotype.Component;

@Component
public interface TestMapper extends WithTableMapper<TUser> {

    /**
     * 测试模板sql
     */
//    List<TUser> listWithTable(@Param("table")String table);
//
//    List<TUser> listWithTable(@Param("table") String table, @Param(Constants.WRAPPER) QueryWrapper<TUser> queryWrapper);
//
//    int saveWithTable(@Param("table")String table,@Param("obj") TUser tUser);
//
//    int saveBatchWithTable(@Param("table")String table,@Param("list") Collection<TUser> list);
//
//    int removeByIdWithTable(@Param("table")String table, @Param("id")Serializable id);
//
//    int removeByIdsWithTable(@Param("table")String table,@Param("list")Collection<? extends Serializable> list);
//
//    int removeWithTable(@Param("table")String table, @Param(Constants.WRAPPER) QueryWrapper<TUser> queryWrapper);
//
//    int updateWithTable(@Param("table")String table, @Param("obj")TUser tUser);
//
//    int updateWithTable(@Param("table")String table, @Param("obj")TUser tUser, @Param(Constants.WRAPPER) UpdateWrapper<TUser> updateWrapper);
//
//    int updateBatchByIdWithTable(@Param("table")String table, @Param("list")Collection<TUser> list);
//
//    int saveOrUpdateWithTable(@Param("table")String table, @Param("obj")TUser tUser);
//
//    int saveOrUpdateBatchWithTable(@Param("table")String table, @Param("list")Collection<TUser> list);

}
