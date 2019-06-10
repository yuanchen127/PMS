package org.ike.pms.mybatisplus.mybaitsplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class Test {
    public static void main(String[] args) {
        testWrapper();
    }

    public static void testWrapper() {
        TUser user = new TUser();
        QueryWrapper<TUser> wrapper = new QueryWrapper<TUser>();
        wrapper.select("user_id,user_name");
        wrapper.setEntity(user);
        wrapper.eq("user_id","tt");
        wrapper.orderByAsc("age");
        wrapper.groupBy("user_id");
        log.info(wrapper.getCustomSqlSegment());
        log.info(wrapper.getSqlSelect());
        log.info(wrapper.getSqlSegment());
        log.info(wrapper.getParamAlias());
        log.info(wrapper.getSqlSet());
        log.info("size {}",wrapper.getParamNameValuePairs());
    }

    public static void testStream() {
        List<String> list1 = new ArrayList<String>(){{
            add("1");
            add("21");
            add("12");
        }};

        List<String> list2 = new ArrayList<String>(){{
            add("A");
            add("B");
            add("C");
        }};
        ArrayList<Object> list = new ArrayList<>();
//        Map<String, String> data = list1.stream().filter(str -> str.length() > 0).collect(Collectors.toMap(k -> k, v -> list2.get(Integer.parseInt(v))));
        list1.stream().filter(str -> str.length() > 1).forEach(x -> {
            System.out.println(x);
            System.out.println(x);
        });

        System.out.println(list);
    }

}
