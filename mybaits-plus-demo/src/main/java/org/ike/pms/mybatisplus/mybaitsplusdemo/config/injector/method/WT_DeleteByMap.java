package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTSqlMethod;

import java.util.Map;

public class WT_DeleteByMap extends AbstractMethod {
    public WT_DeleteByMap() {
    }

    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        WTSqlMethod wtSqlMethod = WTSqlMethod.LOGIC_DELETE_BY_MAP;
        String sql;
        SqlSource sqlSource;
        if (tableInfo.isLogicDelete()) {
            sql = String.format(wtSqlMethod.getSql(), "%s", this.sqlLogicSet(tableInfo), this.sqlWhereByMap(tableInfo));
            sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, Map.class);
            return this.addUpdateMappedStatement(mapperClass, Map.class, wtSqlMethod.getMethod(), sqlSource);
        } else {
            wtSqlMethod = WTSqlMethod.DELETE_BY_MAP;
            sql = String.format(wtSqlMethod.getSql(), "%s", this.sqlWhereByMap(tableInfo));
            sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, Map.class);
            return this.addDeleteMappedStatement(mapperClass, wtSqlMethod.getMethod(), sqlSource);
        }
    }
}
