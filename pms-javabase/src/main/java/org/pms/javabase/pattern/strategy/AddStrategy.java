package org.pms.javabase.pattern.strategy;

public class AddStrategy implements Strategy {
    @Override
    public int calc(int var1, int var2) {
        return var1 + var2;
    }
}
