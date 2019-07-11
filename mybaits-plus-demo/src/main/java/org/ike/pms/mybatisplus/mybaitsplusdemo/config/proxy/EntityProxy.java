package org.ike.pms.mybatisplus.mybaitsplusdemo.config.proxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class EntityProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        String methodName = method.getName();
        if (methodName.startsWith("set")) {
            log.error("开始set咯");
            Object var1 = methodProxy.invokeSuper(o, args);
            log.error("结束set咯");
            return var1;
        }
        return o;
    }
}
