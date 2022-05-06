package org.pms.javabase.pattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Handler implements InvocationHandler{
    private Object object;

    Handler(Object object) {
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke...");
        System.out.println("dynamicproxy :" + proxy.getClass().getName());
        System.out.println("method ："+method);
        method.invoke(object, args);
        System.out.println("after invoke...");
        return null;
    }
}
