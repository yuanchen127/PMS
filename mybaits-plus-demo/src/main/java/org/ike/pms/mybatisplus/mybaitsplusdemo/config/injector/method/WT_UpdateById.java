package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTSqlMethod;

public class WT_UpdateById extends AbstractMethod {
    public WT_UpdateById() {
    }

    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        boolean logicDelete = tableInfo.isLogicDelete();
        WTSqlMethod wtSqlMethod = WTSqlMethod.UPDATE_BY_ID;
        String additional = this.optlockVersion() + tableInfo.getLogicDeleteSql(true, false);
        String sql = String.format(wtSqlMethod.getSql(), "%s", this.sqlSet(logicDelete, false, tableInfo, false, "et", "et."), tableInfo.getKeyColumn(), "et." + tableInfo.getKeyProperty(), additional);
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, wtSqlMethod.getMethod(), sqlSource);
    }
}
