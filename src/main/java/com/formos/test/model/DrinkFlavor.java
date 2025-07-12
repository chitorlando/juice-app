package com.formos.test.model;

/**
 *
 * Enum defining available drink flavors with their ingredient requirements.
 * Each flavor specifies the grams of ingredient needed per 100ml of drink.
 */
public enum DrinkFlavor {
    STRAWBERRY("Strawberry", 100), // 100g per 100ml
    BANANA("Banana", 120), // 120g per 100ml
    MANGO("Mango", 140); // 140g per 100ml
    private final String ingredientKey;
    private final double requiredGramsPer100ml;

    DrinkFlavor(String ingredientKey, double requiredGramsPer100ml) {
        this.ingredientKey = ingredientKey;
        this.requiredGramsPer100ml = requiredGramsPer100ml;
    }

    public String getIngredientKey() {
        return ingredientKey;
    }
// Calculates required grams for a specific volume

    public double getRequiredGramsForVolume(double volumeInMl) {
        return (requiredGramsPer100ml / 100.0) * volumeInMl;
    }

    public double getRequiredGrams() {
        return requiredGramsPer100ml;
    }
}
