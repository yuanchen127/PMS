package org.pms.javabase.pattern.template;

public class ModelB extends Model {
    @Override
    protected void start() {
        System.out.println("start B...");
    }

    @Override
    protected void stop() {
        System.out.println("stop B...");
    }
}
