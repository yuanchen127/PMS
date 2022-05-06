package org.pms.javabase.pattern.strategy;

public class Environment implements Strategy{
    private Strategy strategy;
    public Environment(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public int calc(int var1, int var2) {
        return strategy.calc(var1, var2);
    }

}
