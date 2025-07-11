package com.formos.test;

import java.util.Scanner;

import com.formos.test.model.DrinkFlavor;
import com.formos.test.model.DrinkSize;
import com.formos.test.service.DrinkService;
import com.formos.test.service.Inventory;

/**
 * Main app!
 *
 */
public class App {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            System.out.println("🍹 Welcome to the Juice Stand!");

            while (running) {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Show Inventory");
                System.out.println("2. Sell a Drink");
                System.out.println("3. Sell mixed drinks");
                System.out.println("4. Exit");
                System.out.print("Select an option: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        inventory.printInventory();
                        break;

                    case "2":
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
                            DrinkFlavor selectedFlavor = DrinkFlavor.valueOf(inputFlavor);
                            Double profit = DrinkService.prepareDrink(selectedFlavor, size, inventory);

                            if (profit != null) {
                                System.out.printf("✅ %s (%s) drink prepared!%n", selectedFlavor, size.name());
                                System.out.printf("💰 Price: $%.2f | Profit: $%.2f%n", size.getPrice(), profit);
                                inventory.warnLowIngredients();
                            } else {
                                System.out.println("❌ Not enough ingredients for the drink.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("❗ Invalid size selected. Please try again.");
                        }
                        break;

                    case "3":
                        System.out.println("\nAvailable flavors");
                        for (DrinkFlavor flavor : DrinkFlavor.values()) {
                            System.out.printf("- %s%n", flavor.name());
                        }
                        System.out.print("Enter first flavor: ");
                        String input1 = scanner.nextLine().toUpperCase();
                        System.out.print("Enter second flavor: ");
                        String input2 = scanner.nextLine().toUpperCase();

                        System.out.println("\nSelect size:");
                        for (DrinkSize size : DrinkSize.values()) {
                            System.out.printf("- %s (%d ml)%n", size.name(), size.getVolumeMl());
                        }
                        System.out.print("Enter size: ");
                        String inputMixSize = scanner.nextLine().toUpperCase();

                        try {
                            DrinkFlavor flavor1 = DrinkFlavor.valueOf(input1);
                            DrinkFlavor flavor2 = DrinkFlavor.valueOf(input2);
                            DrinkSize size = DrinkSize.valueOf(inputMixSize);
                            Double profit = DrinkService.prepareMixedDrink(flavor1, flavor2, size, inventory);

                            if (profit != null) {
                                System.out.printf("✅ %s + %s (%s) drink prepared!%n", flavor1, flavor2, size.name());
                                System.out.printf("💰 Price: $%.2f | Profit: $%.2f%n", size.getPrice(), profit);
                                inventory.warnLowIngredients();
                            } else {
                                System.out.println("❌ Not enough ingredients for a mixed drink.");
                            }

                        } catch (Exception e) {
                            System.out.println("❗ One or both flavors are invalid or invalid size selected, or not enough ingredients.");
                        }
                        break;

                    case "4":
                        running = false;
                        System.out.println("👋 Goodbye!");
                        break;

                    default:
                        System.out.println("❗ Invalid option. Try again.");
                        break;
                }
            }
        }
    }

}
