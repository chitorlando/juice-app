package com.formos.test.model;

/**
 * Abstract base class for all ingredients in the inventory system. Provides
 * common functionality for ingredient management, cost calculation, and
 * quantity tracking with validation.
 */
public abstract class Ingredient {

    protected String name;
    protected double quantity;
    protected double unitCost;

    public Ingredient(String name, double quantity, double unitCost) {
        this.name = name;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    // Calculates cost for a specific amount
    public double getCostFor(double amount) {
        return unitCost * amount;
    }

    // Reduces quantity with validation
    public void reduce(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to reduce cannot be negative");
        }
        if (amount > quantity) {
            throw new IllegalArgumentException("Cannot reduce more than the current quantity inventory");
        }
        this.quantity -= amount;
    }

    // Increases quantity with validation
    public void increase(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to increase cannot be negative");
        }
        this.quantity += amount;
    }

    // Abstract method to be implemented by concrete ingredient types
    public abstract String getUnit();
}
