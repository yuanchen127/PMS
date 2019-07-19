package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTSqlMethod;

import java.util.Map;

public class WT_SelectByMap extends AbstractMethod {
    public WT_SelectByMap() {
    }

    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        WTSqlMethod wtSqlMethod = WTSqlMethod.SELECT_BY_MAP;
        String sql = String.format(wtSqlMethod.getSql(), this.sqlSelectColumns(tableInfo, false), "%s", this.sqlWhereByMap(tableInfo));
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, Map.class);
        return this.addSelectMappedStatementForTable(mapperClass, wtSqlMethod.getMethod(), sqlSource, tableInfo);
    }
}
