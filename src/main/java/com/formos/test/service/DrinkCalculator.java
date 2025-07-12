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
     * fruit (50% of volume) + ice + condensed milk + sugar.
     */
    public static double calculateCost(DrinkFlavor flavor, DrinkSize size, Inventory inventory) {
        double volume = size.getVolumeMl();

        // Calculate ingredient quantities based on size
        double fruit = flavor.getRequiredGramsForJuiceVolume(volume);
        double ice = 30 * (volume / 100.0);      // 30ml per 100ml drink
        double milk = 20 * (volume / 100.0);     // 20ml per 100ml drink
        double sugar = 8 * (volume / 100.0);     // 8g per 100ml drink

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

        // Each fruit uses half the volume for mixed drinks
        double fruit1 = f1.getRequiredGramsForJuiceVolume(volume / 2.0);
        double fruit2 = f2.getRequiredGramsForJuiceVolume(volume / 2.0);
        double ice = 30 * (volume / 100.0);
        double milk = 20 * (volume / 100.0);
        double sugar = 8 * (volume / 100.0);

        return ingredientCost(f1.getIngredientKey(), fruit1, inventory)
                + ingredientCost(f2.getIngredientKey(), fruit2, inventory)
                + ingredientCost("Ice", ice, inventory)
                + ingredientCost("Condensed Milk", milk, inventory)
                + ingredientCost("Sugar", sugar, inventory);
    }
}
