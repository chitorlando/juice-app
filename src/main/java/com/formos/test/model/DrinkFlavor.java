package com.formos.test.model;

public enum  DrinkFlavor {
    STRAWBERRY("Strawberry",150),
    BANANA("Banana",180),
    MANGO("Mango",210);

    private final String ingredientKey;
    private final double requiredGrams;

    DrinkFlavor(String ingredientKey, double requiredGrams){
        this.ingredientKey = ingredientKey;
        this.requiredGrams = requiredGrams;
    }

    public String getIngredientKey() {
        return ingredientKey;
    }
    
    public double getRequiredGrams() {
        return requiredGrams;   
    }
}
