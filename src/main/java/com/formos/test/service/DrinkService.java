package com.formos.test.service;

import com.formos.test.model.DrinkFlavor;
import com.formos.test.model.DrinkSize;

/**
 * Service class for drink preparation and inventory management. Handles
 * ingredient availability checks, inventory consumption, and profit
 * calculation. Returns null if ingredients are insufficient, otherwise returns
 * the profit made.
 */
public class DrinkService {

    /**
     * Prepares a single-flavor drink if ingredients are available.
     *
     * @return Profit made from the drink, or null if cannot be prepared
     */
    public static Double prepareDrink(DrinkFlavor flavor, DrinkSize size, Inventory inventory) {
        double volume = size.getVolumeMl();

        // Calculate required quantities
        double fruit = flavor.getRequiredGramsForJuiceVolume(volume); // 50% of volume, converted to grams
        double iceQty = 30 * (volume / 100.0);      // 30ml per 100ml
        double cMilkQty = 20 * (volume / 100.0);    // 20ml per 100ml
        double sugarQty = 8 * (volume / 100.0);     // 8g per 100ml

        // Check if all ingredients are available
        boolean canMake = inventory.hasEnough(flavor.getIngredientKey(), fruit)
                && inventory.hasEnough("Ice", iceQty)
                && inventory.hasEnough("Condensed Milk", cMilkQty)
                && inventory.hasEnough("Sugar", sugarQty);

        if (!canMake) {
            return null;
        }

        // Calculate cost and profit
        double cost = DrinkCalculator.calculateCost(flavor, size, inventory);
        double price = Math.round((cost * 1.5) * 100.0) / 100.0;
        double profit = price - cost;

        // Consume ingredients from inventory
        inventory.use(flavor.getIngredientKey(), fruit);
        inventory.use("Ice", iceQty);
        inventory.use("Condensed Milk", cMilkQty);
        inventory.use("Sugar", sugarQty);

        return profit;
    }

    /**
     * Prepares a mixed-flavor drink if ingredients are available.
     *
     * @return Profit made from the drink, or null if cannot be prepared
     */
    public static Double prepareMixedDrink(DrinkFlavor f1, DrinkFlavor f2, DrinkSize size, Inventory inventory) {
        double volume = size.getVolumeMl();

        // Each fruit uses half the volume for mixed drinks
        double fruit1 = f1.getRequiredGramsForJuiceVolume(volume / 2.0);
        double fruit2 = f2.getRequiredGramsForJuiceVolume(volume / 2.0);
        double iceQty = 30 * (volume / 100.0);
        double cMilkQty = 20 * (volume / 100.0);
        double sugarQty = 8 * (volume / 100.0);

        // Check if all ingredients are available
        boolean canMake = inventory.hasEnough(f1.getIngredientKey(), fruit1)
                && inventory.hasEnough(f2.getIngredientKey(), fruit2)
                && inventory.hasEnough("Ice", iceQty)
                && inventory.hasEnough("Condensed Milk", cMilkQty)
                && inventory.hasEnough("Sugar", sugarQty);

        if (!canMake) {
            return null;
        }

        // Calculate cost and profit
        double cost = DrinkCalculator.calculateMixedCost(f1, f2, size, inventory);
        double price = Math.round((cost * 1.5) * 100.0) / 100.0; // Profit margin of 50%
        double profit = price - cost;

        // Consume ingredients from inventory
        inventory.use(f1.getIngredientKey(), fruit1);
        inventory.use(f2.getIngredientKey(), fruit2);
        inventory.use("Ice", iceQty);
        inventory.use("Condensed Milk", cMilkQty);
        inventory.use("Sugar", sugarQty);

        return profit;
    }
}
