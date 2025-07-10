package com.formos.test.model;

public class SolidIngredient extends Ingredient {
     public SolidIngredient( String name, double quantity) {
        super(name, quantity);
    }

    @Override
    public String getUnit() {
        return "g";
    }
}
