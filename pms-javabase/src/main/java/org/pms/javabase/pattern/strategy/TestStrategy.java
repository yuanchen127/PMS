package org.pms.javabase.pattern.strategy;

public class TestStrategy {
    public static void main(String[] args) {
        Environment en1 = new Environment(new AddStrategy());
        Environment en2 = new Environment(new SubtractStrategy());
        System.out.println(en1.calc(1, 2));
        System.out.println(en2.calc(1, 2));
    }
}
