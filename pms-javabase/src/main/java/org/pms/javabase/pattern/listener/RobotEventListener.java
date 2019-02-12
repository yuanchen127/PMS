package org.pms.javabase.pattern.listener;

public class RobotEventListener extends EventListener {
    @Override
    public void work() {
        System.out.println("robot start working...");
    }

    @Override
    public void dance() {
        System.out.println("robot start dancing...");
    }
}
