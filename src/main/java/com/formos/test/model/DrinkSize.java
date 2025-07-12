package com.formos.test.model;

/**
 *
 * Enum defining available drink sizes with volume and pricing multipliers. Each
 * size has a specific volume in ml and a multiplier for cost calculations.
 */
public enum DrinkSize {
    SMALL(200, 0.66), // 200ml with 0.66x multiplier
    MEDIUM(300, 1.0), // 300ml with 1.0x multiplier (base)
    LARGE(500, 1.66);    // 500ml with 1.66x multiplier
    private final int ml; // Volume in milliliters
    private final double multiplier; // Price multiplier for calculations

    DrinkSize(int ml, double multiplier) {
        this.ml = ml;
        this.multiplier = multiplier;
    }

    public int getVolumeMl() {
        return ml;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
