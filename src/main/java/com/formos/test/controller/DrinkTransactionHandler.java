package com.formos.test.controller;

import java.util.Scanner;

import com.formos.test.model.Drink;
import com.formos.test.model.DrinkFlavor;
import com.formos.test.model.DrinkSize;
import com.formos.test.service.DrinkCalculator;
import com.formos.test.service.DrinkService;
import com.formos.test.service.Inventory;
import com.formos.test.service.SalesService;

/**
 * Handles selling logic for single and mixed drinks.
 */
public class DrinkTransactionHandler {

    private final Scanner scanner;
    private final Inventory inventory;
    private final SalesService salesService;

    public DrinkTransactionHandler(Scanner scanner, Inventory inventory, SalesService salesService) {
        this.scanner = scanner;
        this.inventory = inventory;
        this.salesService = salesService;
    }

    public void sellSingleDrink() {
        System.out.println("\nAvailable flavors:");
        for (DrinkFlavor flavor : DrinkFlavor.values()) {
            System.out.printf("- %s%n", flavor.name());
        }

        System.out.print("Enter flavor: ");
        String inputFlavor = scanner.nextLine().toUpperCase();

        System.out.println("\nSelect size:");
        for (DrinkSize size : DrinkSize.values()) {
            System.out.printf("- %s (%d ml)%n", size.name(), size.getVolumeMl());
        }

        System.out.print("Enter size: ");
        String inputSize = scanner.nextLine().toUpperCase();

        try {
            DrinkSize size = DrinkSize.valueOf(inputSize);
            DrinkFlavor flavor = DrinkFlavor.valueOf(inputFlavor);
            Drink drink = new Drink(flavor, size);
            Double profit = DrinkService.prepareDrink(flavor, size, inventory);

            if (profit != null) {
                double price = drink.getPrice(inventory);
                System.out.printf("✅ %s (%s) drink prepared!%n", flavor.name(), size.name());
                System.out.printf("💰 Price: $%.2f | Profit: $%.2f%n", price, profit);
                inventory.warnLowIngredients();
                salesService.record(flavor.name() + " (" + size.name() + ")", price, profit);
            } else {
                System.out.println("❌ Not enough ingredients for the drink.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("❗ Invalid flavor or size selected.");
        }
    }

    public void sellMixedDrink() {
        System.out.println("\nAvailable flavors:");
        for (DrinkFlavor flavor : DrinkFlavor.values()) {
            System.out.printf("- %s%n", flavor.name());
        }

        System.out.print("Enter first flavor: ");
        String input1 = scanner.nextLine().toUpperCase();
        System.out.print("Enter second flavor: ");
        String input2 = scanner.nextLine().toUpperCase();

        if (input1.equals(input2)) {
            System.out.println("⚠️ Both flavors are the same. Use single flavor option instead.");
            return;
        }

        System.out.println("\nSelect size:");
        for (DrinkSize size : DrinkSize.values()) {
            System.out.printf("- %s (%d ml)%n", size.name(), size.getVolumeMl());
        }

        System.out.print("Enter size: ");
        String inputSize = scanner.nextLine().toUpperCase();

        try {
            DrinkFlavor f1 = DrinkFlavor.valueOf(input1);
            DrinkFlavor f2 = DrinkFlavor.valueOf(input2);
            DrinkSize size = DrinkSize.valueOf(inputSize);
            Double profit = DrinkService.prepareMixedDrink(f1, f2, size, inventory);

            if (profit != null) {
                double cost = DrinkCalculator.calculateMixedCost(f1, f2, size, inventory);
                double price = Math.round((cost * 1.5) * 100.0) / 100.0;
                System.out.printf("✅ %s + %s (%s) drink prepared!%n", f1.name(), f2.name(), size.name());
                System.out.printf("💰 Price: $%.2f | Profit: $%.2f%n", price, profit);
                inventory.warnLowIngredients();
                salesService.record(f1.name() + " + " + f2.name() + " (" + size.name() + ")", price, profit);
            } else {
                System.out.println("❌ Not enough ingredients for a mixed drink.");
            }

        } catch (Exception e) {
            System.out.println("❗ Invalid input or size.");
        }
    }
}
