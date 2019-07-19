package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTSqlMethod;

public class WT_DeleteBatchByIds extends AbstractMethod {
    public WT_DeleteBatchByIds() {
    }

    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        WTSqlMethod wtSqlMethod = WTSqlMethod.LOGIC_DELETE_BATCH_BY_IDS;
        String sql;
        SqlSource sqlSource;
        if (tableInfo.isLogicDelete()) {
            sql = String.format(wtSqlMethod.getSql(), "%s", this.sqlLogicSet(tableInfo), tableInfo.getKeyColumn(), SqlScriptUtils.convertForeach("#{item}", "coll", (String)null, "item", ","), tableInfo.getLogicDeleteSql(true, false));
            sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, Object.class);
            return this.addUpdateMappedStatement(mapperClass, modelClass, wtSqlMethod.getMethod(), sqlSource);
        } else {
            wtSqlMethod = WTSqlMethod.DELETE_BATCH_BY_IDS;
            sql = String.format(wtSqlMethod.getSql(), "%s", tableInfo.getKeyColumn(), SqlScriptUtils.convertForeach("#{item}", "coll", (String)null, "item", ","));
            sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, Object.class);
            return this.addDeleteMappedStatement(mapperClass, wtSqlMethod.getMethod(), sqlSource);
        }
    }
}
