package com.formos.test.model;

public class LiquidIngredient extends Ingredient {
    public LiquidIngredient( String name, double quantity) {
        super(name, quantity);
    }

    @Override
    public String getUnit() {
        return "ml";
    }
}
