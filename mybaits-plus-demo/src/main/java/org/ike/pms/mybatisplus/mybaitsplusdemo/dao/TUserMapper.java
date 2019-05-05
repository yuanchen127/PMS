package org.ike.pms.mybatisplus.mybaitsplusdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.provider.UserProvider;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ike
 * @since 2019-04-11
 */
public interface TUserMapper extends BaseMapper<TUser> {

    @Select("select * from t_user where user_name=#{user_name} and password=#{password}")
    List<TUser> testSelect(@Param("user_name") String user_name, @Param("password") String password);

    @SelectProvider(type = UserProvider.class, method = "selectProvider")
    List<TUser> testProviderSelect(Page page, String name, String password);

    @Select("select * from t_user where user_name=#{user_name} and password=#{password}")
    List<Map<String, Object>> testMapSelect(Page page, @Param("user_name") String user_name, @Param("password") String password);

    @SelectProvider(type = UserProvider.class,method = "selectUserById")
    TUser selectUserById(String id);

    @SelectProvider(type = UserProvider.class,method = "selectUserByName")
    List<TUser> selectUserByName(String name);
}
