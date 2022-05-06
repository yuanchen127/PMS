package org.pms.javabase.pattern.dynamicproxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: ike
 * @Date: 2019-02-18 09:45
 */

public class ExceptionHandler implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("invoke before...");
        System.out.println("intercept method :"+method.getName());
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("intercept object :"+ object.getClass().getName());
        System.out.println(object instanceof Exception);
        System.out.println("invoke after...");
        return object;
    }
}
