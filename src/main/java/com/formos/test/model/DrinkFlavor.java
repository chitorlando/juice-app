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
    private final double gramsPer100mlBlendedFruit;

    DrinkFlavor(String ingredientKey, double gramsPer100mlBlendedFruit) {
        this.ingredientKey = ingredientKey;
        this.gramsPer100mlBlendedFruit = gramsPer100mlBlendedFruit;
    }

    public String getIngredientKey() {
        return ingredientKey;
    }

    public double getRequiredGramsPer100mlBlendedFruit() {
        return gramsPer100mlBlendedFruit;
    }

    /**
     * Calculates how many grams of raw fruit are needed to produce the required
     * blended fruit for a given volume of final drink.
     *
     * The drink consists of 50% blended fruit, so we first compute how much
     * blended fruit is needed, and then calculate how many grams of raw fruit
     * are required to produce it.
     *
     * @param drinkVolumeInMl The total final volume of the drink (e.g., 300ml)
     * @return grams of raw fruit needed
     */
    public double getRequiredGramsForJuiceVolume(double drinkVolumeInMl) {
        double blendedFruitVolume = drinkVolumeInMl * 0.5; // 50% of the drink is blended fruit
        return (gramsPer100mlBlendedFruit / 100.0) * blendedFruitVolume;
    }
}
