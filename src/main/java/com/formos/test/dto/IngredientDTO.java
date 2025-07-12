package com.formos.test.dto;

/**
 *
 * DTO representing an ingredient used in drinks. Used for transferring
 * ingredient data between layers.
 */
public class IngredientDTO {

    public String name;       // Ingredient name
    public double quantity;   // Quantity in grams or ml
    public String type;       // Ingredient type (solid, liquid)
    public double unitCost;   // Cost per unit (gram or ml)
}
