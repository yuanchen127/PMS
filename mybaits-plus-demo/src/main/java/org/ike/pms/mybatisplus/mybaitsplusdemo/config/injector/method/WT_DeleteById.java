package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTSqlMethod;

public class WT_DeleteById extends AbstractMethod {
    public WT_DeleteById() {
    }

    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        WTSqlMethod wtSqlMethod = WTSqlMethod.LOGIC_DELETE_BY_ID;
        String sql;
        SqlSource sqlSource;
        if (tableInfo.isLogicDelete()) {
            sql = String.format(wtSqlMethod.getSql(), "%s", this.sqlLogicSet(tableInfo), tableInfo.getKeyColumn(), tableInfo.getKeyProperty(), tableInfo.getLogicDeleteSql(true, false));
            sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, Object.class);
            return this.addUpdateMappedStatement(mapperClass, modelClass, wtSqlMethod.getMethod(), sqlSource);
        } else {
            wtSqlMethod = WTSqlMethod.DELETE_BY_ID;
            sql = String.format(wtSqlMethod.getSql(), "%s", tableInfo.getKeyColumn(), tableInfo.getKeyProperty());
            sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, Object.class);
            return this.addDeleteMappedStatement(mapperClass, wtSqlMethod.getMethod(), sqlSource);
        }
    }
}
