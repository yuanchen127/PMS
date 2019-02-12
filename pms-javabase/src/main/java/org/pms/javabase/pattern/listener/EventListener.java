package org.pms.javabase.pattern.listener;

public class EventListener implements Event {

    @Override
    public void work() {
        System.out.println("init work");
    }

    @Override
    public void dance() {
        System.out.println("init dance");
    }
}
