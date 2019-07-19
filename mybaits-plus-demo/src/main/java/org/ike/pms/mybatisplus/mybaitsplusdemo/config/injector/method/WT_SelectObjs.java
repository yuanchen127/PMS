package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTSqlMethod;

public class WT_SelectObjs extends AbstractMethod {
    public WT_SelectObjs() {
    }

    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        WTSqlMethod wtSqlMethod = WTSqlMethod.SELECT_OBJS;
        String sql = String.format(wtSqlMethod.getSql(), this.sqlSelectObjsColumns(tableInfo), "%s", this.sqlWhereEntityWrapper(true, tableInfo), this.sqlComment());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
        return this.addSelectMappedStatementForOther(mapperClass, wtSqlMethod.getMethod(), sqlSource, Object.class);
    }
}
