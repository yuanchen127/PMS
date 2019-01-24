package org.pms.javabase.pattern.template;

public abstract class Model {

    protected abstract void start();

    protected  abstract void stop();

    final public void exec() {
        System.out.println("================");
        start();
        stop();
    }

}
