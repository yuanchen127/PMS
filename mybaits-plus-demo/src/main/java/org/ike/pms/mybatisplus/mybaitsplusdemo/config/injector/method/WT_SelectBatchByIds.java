package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTSqlMethod;

public class WT_SelectBatchByIds extends AbstractMethod {
    public WT_SelectBatchByIds() {
    }

    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        WTSqlMethod wtSqlMethod = WTSqlMethod.LOGIC_SELECT_BATCH_BY_IDS;
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, String.format(wtSqlMethod.getSql(), this.sqlSelectColumns(tableInfo, false), "%s", tableInfo.getKeyColumn(), SqlScriptUtils.convertForeach("#{item}", "coll", (String)null, "item", ","), tableInfo.getLogicDeleteSql(true, false)), Object.class);
        return this.addSelectMappedStatementForTable(mapperClass, wtSqlMethod.getMethod(), sqlSource, tableInfo);
    }
}
