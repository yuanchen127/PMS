package org.ike.pms.mybatisplus.mybaitsplusdemo.provider;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.jdbc.SQL;

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

    public String selectProvider(Page page, String name,String password) {
        String sql = "select * from t_user where user_name='"+name+"' and password='"+password+"'";
//        return new SQL(){{
//            SELECT("*");
//            FROM("t_user");
//            WHERE("user_name='"+name+"' and password='"+password+"'");
//        }}.toString();
        return sql;
    }
}
