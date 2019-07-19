package org.ike.pms.mybatisplus.mybaitsplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Slf4j
public class Test {
    public static void main(String[] args) {
        testIn();
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

    static String compressString(String str) {
        try {
            if (str == null || str.length() == 0) {
                return str;
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
            gzip.close();
            return out.toString("ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
    static String unCompressString(String str) {
        try {
            if (str == null || str.length() == 0) {
                return str;
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(str
                    .getBytes("ISO-8859-1"));
            GZIPInputStream gunzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gunzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    static String testRegex() {
        String str = "abc";
        return StringUtils.replaceAll(str, "a|b", "");
    }

    static void testIn() {
        String sql = "update t_user\n" +
                "         SET user_id=?,\n" +
                "            \n" +
                "            \n" +
                "                user_name=?,\n" +
                "            \n" +
                "            \n" +
                "                password=? \n" +
                "         \n" +
                "                WHERE user_id = ?";

        sql = sql.replaceAll("\n", "");
//        sql = sql.replaceAll("  ", " ");
        sql = sql.replaceAll("    ", " ");
        log.info("sql: {}", sql);
        String rgex = "SET( .*?)WHERE";
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(sql);
//        log.info("m.find(): {}", String.valueOf(m.find()));
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        String listStr = StringUtils.join(list, ",");
        log.info("listStr: {}", listStr);
    }

    static void addFieldDynamic() {
        TUser user = new TUser()
                .setUserName("qwe")
                .setPassword("123");
//        new ClassUtil
    }

}
