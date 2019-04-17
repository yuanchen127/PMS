package org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(-100)
public class IkeAspect {

    @Pointcut("@within(Ike) || @annotation(Ike)")
    public void pointCut1(){

    }

    @Before("pointCut1()")
    public void doBefore(){
        log.info("IKE-name success");
    }

    @After("pointCut1()")
    public void doAfter(){
        log.info("IKE-name end");
    }
}
