package org.ike.pms.api.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestProxy {
    public static void main(String[] args) {
        Common greet = new Greet();
        InvocationHandler handler =  new Handler(greet);
        Common common = (Common) Proxy.newProxyInstance(handler.getClass().getClassLoader(), greet.getClass().getInterfaces(),handler);
        System.out.println("common :" + common.getClass().getName());
        common.hello();
        System.out.println("===========================");
        common.bye();
    }
}
