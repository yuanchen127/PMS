package org.pms.javabase.pattern.template;

public class ModelA extends Model{
    @Override
    protected void start() {
        System.out.println("start A...");
    }

    @Override
    protected void stop() {
        System.out.println("stop A...");
    }
}
