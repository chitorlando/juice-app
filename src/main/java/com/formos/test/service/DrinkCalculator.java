package com.formos.test.service;

import com.formos.test.model.DrinkFlavor;
import com.formos.test.model.DrinkSize;
import com.formos.test.model.Ingredient;

/**
 * Service class for calculating drink costs based on ingredients and recipes.
 * Handles both single-flavor and mixed-flavor drinks with standard recipe
 * proportions.
 */
public class DrinkCalculator {

    // Helper method to calculate cost for a specific ingredient
    private static double ingredientCost(String key, double quantity, Inventory inventory) {
        Ingredient ingredient = inventory.get(key);
        return ingredient.getCostFor(quantity);
    }

    /**
     * Calculates the total cost of a single-flavor drink. Uses standard recipe:
     * fruit + ice + condensed milk + sugar.
     */
    public static double calculateCost(DrinkFlavor flavor, DrinkSize size, Inventory inventory) {
        double volume = size.getVolumeMl();
        double multiplier = size.getMultiplier();

        // Calculate ingredient quantities based on size
        double fruit = flavor.getRequiredGramsForVolume(volume);
        double ice = 90 * multiplier;           // 90g base amount
        double milk = 60 * multiplier;          // 60ml base amount
        double sugar = 24 * multiplier;         // 24g base amount

        return ingredientCost(flavor.getIngredientKey(), fruit, inventory)
                + ingredientCost("Ice", ice, inventory)
                + ingredientCost("Condensed Milk", milk, inventory)
                + ingredientCost("Sugar", sugar, inventory);
    }

    /**
     * Calculates the total cost of a mixed-flavor drink. Uses half volume for
     * each fruit flavor, same other ingredients.
     */
    public static double calculateMixedCost(DrinkFlavor f1, DrinkFlavor f2, DrinkSize size, Inventory inventory) {
        double volume = size.getVolumeMl();
        double multiplier = size.getMultiplier();

        // Each fruit uses half the volume for mixed drinks
        double fruit1 = f1.getRequiredGramsForVolume(volume / 2.0);
        double fruit2 = f2.getRequiredGramsForVolume(volume / 2.0);
        double ice = 90 * multiplier;           // Same base ingredients
        double milk = 60 * multiplier;
        double sugar = 24 * multiplier;

        return ingredientCost(f1.getIngredientKey(), fruit1, inventory)
                + ingredientCost(f2.getIngredientKey(), fruit2, inventory)
                + ingredientCost("Ice", ice, inventory)
                + ingredientCost("Condensed Milk", milk, inventory)
                + ingredientCost("Sugar", sugar, inventory);
    }
}
