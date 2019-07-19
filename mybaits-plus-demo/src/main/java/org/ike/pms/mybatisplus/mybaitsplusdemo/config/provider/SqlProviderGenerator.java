package org.ike.pms.mybatisplus.mybaitsplusdemo.config.provider;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;

@Slf4j
public class SqlProviderGenerator {

    public String pt_insert(String table, Class clazz, Object entity) {
        Object obj = clazz.cast(entity);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        ArrayList<String> keyList = new ArrayList<>();
        ArrayList<Object> valueList = new ArrayList<>();
        keyList.add(tableInfo.getKeyColumn());
        valueList.add("'"+ReflectionKit.getMethodValue(clazz, obj, tableInfo.getKeyProperty())+"'");
        tableInfo.getFieldList().stream().forEach(field->{
            String property = field.getProperty();
            String column = field.getColumn();
            Object pValue = ReflectionKit.getMethodValue(clazz, obj, property);
            if (pValue != null) {
                keyList.add(column);
                valueList.add("'"+pValue+"'");
            }
        });
        return String.format(StringUtils.replaceAll(SqlMethod.INSERT_ONE.getSql(),"<script>|</script>",""), table, "(" + StringUtils.join(keyList, ",") + ")", "(" + StringUtils.join(valueList, ",") + ")");
    }

    public String list(@Param("table") String table, @Param("ew") Wrapper wrapper) {
        String sql = new SQL() {{
            SELECT(CURDUtil.getSelectFields(wrapper));
            FROM(table);
            WHERE(CURDUtil.getCondition(wrapper));
        }}.toString();
        log.info(sql);
        return sql;
    }

    public String getOne(@Param("table") String table, @Param("ew") Wrapper wrapper) {
        return new SQL() {{
            SELECT("top 1 *");
        }}.toString();
    }
}
