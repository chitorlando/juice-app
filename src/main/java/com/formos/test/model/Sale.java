package com.formos.test.model;

import java.time.LocalDateTime;

/**
 * Represents a single drink sale.
 * Stores a description, price, profit, and timestamp of the sale.
 */
public class Sale {

    private final String description;
    private final double price;
    private final double profit;
    private final LocalDateTime timestamp;

    /**
     * Constructs a new Sale record with the given data and current timestamp.
     *
     * @param description Human-readable name of the drink sold
     * @param price       Final price charged for the drink
     * @param profit      Profit made from the sale (price - cost)
     */
    public Sale(String description, double price, double profit) {
        this.description = description;
        this.price = price;
        this.profit = profit;
        this.timestamp = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getProfit() {
        return profit;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
