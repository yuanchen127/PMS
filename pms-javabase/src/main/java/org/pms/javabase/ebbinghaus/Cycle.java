package org.pms.javabase.ebbinghaus;

public class Cycle {
    private int unit;
    private int increment;
    private double capacity;

    public Cycle(int unit, int increment, double capacity) {
        this.unit = unit;
        this.increment = increment;
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
