package org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
//@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    DataSourceEnum value() default DataSourceEnum.DB2;
}
