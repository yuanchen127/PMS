package org.pms.javabase.pattern.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author: ike
 * @Date: 2019-02-18 09:48
 */
public class TestExceptionProxy {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Exception.class);
        enhancer.setCallback(new ExceptionHandler());
        Exception exception = (Exception) enhancer.create();
        exception.getClass().getConstructor().newInstance();
    }
}
