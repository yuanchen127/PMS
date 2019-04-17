package org.ike.pms.mybatisplus.mybaitsplusdemo.provider;

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
}
