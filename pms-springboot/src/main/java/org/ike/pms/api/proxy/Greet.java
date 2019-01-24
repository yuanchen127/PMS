package org.ike.pms.api.proxy;

public class Greet implements Common{
    @Override
    public void hello() {
        System.out.println("hello...");
    }

    @Override
    public void bye() {
        System.out.println("good bye...");
    }
}
