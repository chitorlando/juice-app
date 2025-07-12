package com.formos.test.service;

import com.formos.test.model.Sale;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for recording and reporting drink sales over time.
 * Allows registering individual sales and generating a daily report.
 */
public class SalesService {

    private final List<Sale> sales = new ArrayList<>();

    /**
     * Records a new sale with a description, price, and profit.
     *
     * @param description Description of the sold drink (e.g., "MANGO (LARGE)")
     * @param price       Final sale price
     * @param profit      Profit made from the sale
     */
    public void record(String description, double price, double profit) {
        sales.add(new Sale(description, price, profit));
    }

    /**
     * Prints a summary report of all sales for the current session.
     * Includes number of drinks sold, total revenue, and total profit.
     */
    public void printReport() {
        if (sales.isEmpty()) {
            System.out.println("📊 No sales recorded today.");
            return;
        }

        System.out.println("\n📊 Daily Sales Report");
        System.out.printf("Total drinks sold: %d%n", sales.size());

        double totalRevenue = 0;
        double totalProfit = 0;

        for (Sale sale : sales) {
            totalRevenue += sale.getPrice();
            totalProfit += sale.getProfit();
        }

        System.out.printf("Total revenue: $%.2f%n", totalRevenue);
        System.out.printf("Total profit: $%.2f%n", totalProfit);
    }
}
