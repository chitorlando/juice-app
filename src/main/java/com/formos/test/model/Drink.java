package com.formos.test.model;

import com.formos.test.service.DrinkCalculator;
import com.formos.test.service.Inventory;

public class Drink {

    private final DrinkFlavor flavor;
    private final DrinkSize size;

    public Drink(DrinkFlavor flavor, DrinkSize size) {
        this.flavor = flavor;
        this.size = size;
    }

    public double calculateCost(Inventory inventory) {
        return DrinkCalculator.calculateCost(flavor, size, inventory);
    }

    public double calculateMixedCost(DrinkFlavor f1, DrinkFlavor f2, Inventory inventory) {
        return DrinkCalculator.calculateMixedCost(f1, f2, size, inventory);
    }

    public double getPrice() {
        return size.getPrice();
    }

    public double getProfit(Inventory inventory) {
        return getPrice() - calculateCost(inventory);
    }


    public String getFlavorName() {
        return flavor.name();
    }

    public DrinkSize getSize() {
        return size;
    }
}
