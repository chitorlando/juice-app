package com.formos.test.model;

/**
 * Concrete implementation of Ingredient for liquid ingredients. Represents
 * ingredients measured in milliliters (ml).
 */
public class LiquidIngredient extends Ingredient {

    public LiquidIngredient(String name, double quantity, double unitCost) {
        super(name, quantity, unitCost);
    }

    @Override
    public String getUnit() {
        return "ml";
    }
}
