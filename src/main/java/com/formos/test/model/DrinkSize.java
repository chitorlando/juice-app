package com.formos.test.model;

public enum DrinkSize {
    SMALL(200, 0.66),
    MEDIUM(300, 1.0),
    LARGE(500, 1.66);

    private final int volumeMl; // in milliliters
    private final double multiplier; // multiplier for price calculation
    private final double costPerMl = 0.05; // example cost per ml

    DrinkSize(int volumeMl, double multiplier) {
        this.volumeMl = volumeMl;
        this.multiplier = multiplier;
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public double getPrice() {
        return volumeMl * costPerMl * multiplier;
    }
}
