package com.formos.test.model;

/**
 * Concrete implementation of Ingredient for solid ingredients. Represents
 * ingredients measured in grams (g).
 */
public class SolidIngredient extends Ingredient {

    public SolidIngredient(String name, double quantity, double unitCost) {
        // Call the constructor of the parent class Ingredient
        // to initialize name, quantity, and unitCost
        super(name, quantity, unitCost);
    }

    @Override
    public String getUnit() {
        return "g";
    }
}
