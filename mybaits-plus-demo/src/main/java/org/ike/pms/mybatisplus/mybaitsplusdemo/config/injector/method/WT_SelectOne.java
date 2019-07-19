package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTSqlMethod;

public class WT_SelectOne extends AbstractMethod {
    public WT_SelectOne() {
    }

    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        WTSqlMethod wtSqlMethod = WTSqlMethod.SELECT_ONE;
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, String.format(wtSqlMethod.getSql(), this.sqlSelectColumns(tableInfo, true), "%s", this.sqlWhereEntityWrapper(true, tableInfo), this.sqlComment()), modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, wtSqlMethod.getMethod(), sqlSource, tableInfo);
    }
}
