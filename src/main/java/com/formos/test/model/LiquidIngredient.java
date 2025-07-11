package com.formos.test.model;

public class LiquidIngredient extends Ingredient {
    public LiquidIngredient( String name, double quantity, double unitCost) {
        super(name, quantity, unitCost);
    }

    @Override
    public String getUnit() {
        return "ml";
    }

}
