package com.formos.test.model;

/**
 *
 * Enum defining available drink sizes with volume and pricing multipliers. Each
 * size has a specific volume in ml and a multiplier for cost calculations.
 */
public enum DrinkSize {
    SMALL(200), // 200ml 
    MEDIUM(300), // 300ml 
    LARGE(500);    // 500ml 
    private final int ml; // Volume in milliliters

    DrinkSize(int ml) {
        this.ml = ml;

    }

    public int getVolumeMl() {
        return ml;
    }
}
