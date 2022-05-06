package org.pms.javabase.pattern.template;

public class TestTemplate {
    public static void main(String[] args) {
        Model m1 = new ModelA();
        m1.exec();
        Model m2 = new ModelB();
        m2.exec();
    }
}
