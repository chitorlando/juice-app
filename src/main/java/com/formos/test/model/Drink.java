package com.formos.test.model;

import com.formos.test.service.DrinkCalculator;
import com.formos.test.service.Inventory;

/**
 *
 * Model representing a drink with specific flavor and size. Handles cost
 * calculation, pricing and profit using a fixed 50% margin.
 */
public class Drink {

    private final DrinkFlavor flavor;
    private final DrinkSize size;
    private static final double PROFIT_MARGIN = 1.5; // 50% profit margin

    public Drink(DrinkFlavor flavor, DrinkSize size) {
        this.flavor = flavor;
        this.size = size;
    }
// Calculates the base cost of the drink

    public double calculateCost(Inventory inventory) {
        return DrinkCalculator.calculateCost(flavor, size, inventory);
    }
// Calculates the cost of a mixed drink (two flavors)

    public double calculateMixedCost(DrinkFlavor f1, DrinkFlavor f2, Inventory inventory) {
        return DrinkCalculator.calculateMixedCost(f1, f2, size, inventory);
    }
// Gets the sale price (cost + margin)

    public double getPrice(Inventory inventory) {
        double cost = calculateCost(inventory);
        return Math.round((cost * PROFIT_MARGIN) * 100.0) / 100.0;
    }
// Calculates the profit obtained

    public double getProfit(Inventory inventory) {
        return getPrice(inventory) - calculateCost(inventory);
    }

    public String getFlavorName() {
        return flavor.name();
    }

    public DrinkSize getSize() {
        return size;
    }
}
