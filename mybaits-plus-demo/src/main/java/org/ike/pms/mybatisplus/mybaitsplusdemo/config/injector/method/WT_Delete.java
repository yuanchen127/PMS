package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTSqlMethod;

public class WT_Delete extends AbstractMethod {
    public WT_Delete() {
    }

    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        WTSqlMethod wtSqlMethod = WTSqlMethod.LOGIC_DELETE;
        String sql;
        SqlSource sqlSource;
        if (tableInfo.isLogicDelete()) {
            sql = String.format(wtSqlMethod.getSql(), "%s", this.sqlLogicSet(tableInfo), this.sqlWhereEntityWrapper(true, tableInfo), this.sqlComment());
            sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
            return this.addUpdateMappedStatement(mapperClass, modelClass, wtSqlMethod.getMethod(), sqlSource);
        } else {
            wtSqlMethod = WTSqlMethod.DELETE;
            sql = String.format(wtSqlMethod.getSql(), "%s", this.sqlWhereEntityWrapper(true, tableInfo), this.sqlComment());
            sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
            return this.addDeleteMappedStatement(mapperClass, wtSqlMethod.getMethod(), sqlSource);
        }
    }
}
