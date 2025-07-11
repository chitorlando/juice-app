package com.formos.test.model;

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

    public double getCostFor(double amount) {
        return unitCost * amount;
    }

    public void reduce(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to reduce cannot be negative");
        }
        if (amount > quantity) {
            throw new IllegalArgumentException("Cannot reduce more than the current quantity inventory");
        }
        this.quantity -= amount;
    }

    public void increase(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to increase cannot be negative");
        }
        this.quantity += amount;
    }

    public abstract String getUnit();
}
