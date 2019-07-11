package org.ike.pms.mybatisplus.mybaitsplusdemo.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(12)
@Slf4j
public class EntityAspect {
    public EntityAspect() {
        log.error("创建EntityAspect");
    }
//    org.ike.pms.mybatisplus.mybaitsplusdemo.entity.*.
//    org.ike.pms.mybatisplus.mybaitsplusdemo.controller.*.
    @Pointcut("execution(* org.ike.pms.mybatisplus.mybaitsplusdemo.vo.*.*(..))")
//    @Pointcut("@within(org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource.Ike) || @annotation(org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource.Ike)")
    public void setValue() {
        log.error("开始set咯");
    }

    @Before("setValue()")
    public void doBefore() {
        log.error("set之前");
    }

    @After("setValue()")
    public void doAfter() {
        log.error("set之后");
    }
}
