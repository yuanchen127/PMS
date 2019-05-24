package org.ike.pms.mybatisplus.mybaitsplusdemo.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;

import java.util.Map;

public class UserProvider {

    public String selectUserById(String id) {
        return new SQL(){
            {
                SELECT("*");
                FROM("t_user");
                WHERE("user_id=#{id}");
            }
        }.toString();
    }

    public String selectUserByName(String name) {
        return new SQL(){{
            SELECT("*");
            FROM("t_user");
            WHERE("user_name=#{name}");
        }}.toString();
    }

    public String selectProvider(Map map) {
//        String sql = "select * from t_user where user_name='"+name+"' and password='"+password+"'";
        String base = "select * from pms.t_user ";
//        System.out.println(wrapper.getSqlSegment());

        return new SQL(){{
            SELECT("*");
            FROM("pms.t_user");
        }}.toString()+" ${ew.customSqlSegment}";
//        return new SQL(){{
//            SELECT("*");
//            FROM("t_user");
//            WHERE("user_name='"+name+"' and password='"+password+"'");
//        }}.toString();
//        return sql;
    }
}
