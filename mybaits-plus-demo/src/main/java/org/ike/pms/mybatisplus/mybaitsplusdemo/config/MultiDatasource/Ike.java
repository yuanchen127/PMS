package org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ike {
    String value();
}
