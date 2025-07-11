package com.formos.test.service;

import com.formos.test.model.DrinkFlavor;
import com.formos.test.model.DrinkSize;
import com.formos.test.model.Ingredient;

public class DrinkCalculator {

    private static double ingredientCost(String key, double quantity, Inventory inventory) {
        Ingredient ingredient = inventory.get(key);
        return ingredient.getCostFor(quantity);
    }

    public static double calculateCost(DrinkFlavor flavor, DrinkSize size, Inventory inventory) {
        double multiplier = size.getMultiplier();
        double fruit = flavor.getRequiredGrams() * multiplier;
        double ice = 90 * multiplier;
        double milk = 60 * multiplier;
        double sugar = 24 * multiplier;

        return ingredientCost(flavor.getIngredientKey(), fruit, inventory)
                + ingredientCost("Ice", ice, inventory)
                + ingredientCost("Condensed Milk", milk, inventory)
                + ingredientCost("Sugar", sugar, inventory);
    }

    public static double calculateMixedCost(DrinkFlavor f1, DrinkFlavor f2, DrinkSize size, Inventory inventory) {
        double multiplier = size.getMultiplier();
        double fruit1 = f1.getRequiredGrams() * multiplier / 2.0;
        double fruit2 = f2.getRequiredGrams() * multiplier / 2.0;
        double ice = 90 * multiplier;
        double milk = 60 * multiplier;
        double sugar = 24 * multiplier;

        return ingredientCost(f1.getIngredientKey(), fruit1, inventory)
                + ingredientCost(f2.getIngredientKey(), fruit2, inventory)
                + ingredientCost("Ice", ice, inventory)
                + ingredientCost("Condensed Milk", milk, inventory)
                + ingredientCost("Sugar", sugar, inventory);
    }

}
