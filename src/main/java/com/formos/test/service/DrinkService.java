package com.formos.test.service;

import com.formos.test.model.DrinkFlavor;
import com.formos.test.model.DrinkSize;

public class DrinkService {

    public static Double prepareDrink(DrinkFlavor flavor, DrinkSize size, Inventory inventory) {
        double multiplier = size.getMultiplier();
        double fruit = flavor.getRequiredGrams() * multiplier;
        double iceQty = 90 * multiplier; // 90 grams of ice, adjusted for size
        double cMilkQty = 60 * multiplier; // 60 ml of condensed milk, adjusted for
        double sugarQty = 24 * multiplier; // 24 grams of sugar, adjusted for size

        boolean canMake = inventory.hasEnough(flavor.getIngredientKey(), fruit)
                && inventory.hasEnough("Ice", iceQty)
                && inventory.hasEnough("Condensed Milk", cMilkQty)
                && inventory.hasEnough("Sugar", sugarQty);

        if (!canMake) {
            return null;
        }

        double cost = DrinkCalculator.calculateCost(flavor, size, inventory);
        double price = Math.round((cost * 1.5) * 100.0) / 100.0;
        double profit = price - cost;

        inventory.use(flavor.getIngredientKey(), fruit);
        inventory.use("Ice", iceQty);
        inventory.use("Condensed Milk", cMilkQty);
        inventory.use("Sugar", sugarQty);

        return profit;
    }

    public static Double prepareMixedDrink(DrinkFlavor f1, DrinkFlavor f2, DrinkSize size, Inventory inventory) {
        double multiplier = size.getMultiplier();
        double fruit1 = f1.getRequiredGrams() * multiplier / 2.0;
        double fruit2 = f2.getRequiredGrams() * multiplier / 2.0;
        double iceQty = 90 * multiplier; // 90 grams of ice, adjusted for size
        double cMilkQty = 60 * multiplier; // 60 ml of condensed milk, adjusted for
        double sugarQty = 24 * multiplier; // 24 grams of sugar, adjusted for size

        boolean canMake = inventory.hasEnough(f1.getIngredientKey(), fruit1)
                && inventory.hasEnough(f2.getIngredientKey(), fruit2)
                && inventory.hasEnough("Ice", iceQty)
                && inventory.hasEnough("Condensed Milk", cMilkQty)
                && inventory.hasEnough("Sugar", sugarQty);

        if (!canMake) {
            return null;
        }

        double cost = DrinkCalculator.calculateMixedCost(f1, f2, size, inventory);
        double price = Math.round((cost * 1.5) * 100.0) / 100.0; // Profit margin of 50%
        double profit = price - cost;

        inventory.use(f1.getIngredientKey(), fruit1);
        inventory.use(f2.getIngredientKey(), fruit2);
        inventory.use("Ice", iceQty);
        inventory.use("Condensed Milk", cMilkQty);
        inventory.use("Sugar", sugarQty);

        return profit;
    }

}
