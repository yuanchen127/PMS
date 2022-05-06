package org.pms.javabase.pattern.listener;

public class Robot {
    private EventListener listener;
    public Robot(EventListener listener) {
        this.listener = listener;
    }

    public void work() {
        this.listener.work();
    }
    public void dance() {
        this.listener.dance();
    }

}
