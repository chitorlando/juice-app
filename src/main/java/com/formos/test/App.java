package com.formos.test;

import java.util.Scanner;

import com.formos.test.model.Drink;
import com.formos.test.model.DrinkFlavor;
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

                        try {
                            DrinkFlavor selectedFlavor = DrinkFlavor.valueOf(inputFlavor);
                            Drink drink = new Drink(selectedFlavor);

                            if (drink.make(inventory)) {
                                System.out.printf("✅ %s drink prepared successfully!%n", selectedFlavor.name());
                                inventory.warnLowIngredients();
                            } else {
                                System.out.println("❌ Not enough ingredients to make this drink.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("❗ Invalid flavor. Please try again.");
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

                        try {
                            DrinkFlavor flavor1 = DrinkFlavor.valueOf(input1);
                            DrinkFlavor flavor2 = DrinkFlavor.valueOf(input2);

                            boolean success = makeMixedDrink(flavor1, flavor2, inventory);

                            if (success) {
                                System.out.printf("✅ %s + %s drink prepared successfully!%n", flavor1, flavor2);
                                inventory.warnLowIngredients();
                            } else {
                                System.out.println("❌ Not enough ingredients for a mixed drink.");
                            }
                        } catch (Exception e) {
                            System.out.println("❗ One or both flavors are invalid.");
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

    private static boolean makeMixedDrink(DrinkFlavor f1, DrinkFlavor f2, Inventory inventory) {
        double fruit1 = f1.getRequiredGrams() / 2.0;
        double fruit2 = f2.getRequiredGrams() / 2.0;
        double ice = 90;
        double milk = 60;
        double sugar = 24;

        boolean canMake = inventory.hasEnough(f1.getIngredientKey(), fruit1)
                && inventory.hasEnough(f2.getIngredientKey(), fruit2)
                && inventory.hasEnough("Ice", ice)
                && inventory.hasEnough("Condensed Milk", milk)
                && inventory.hasEnough("Sugar", sugar);

        if (!canMake) {
            return false;
        }

        inventory.use(f1.getIngredientKey(), fruit1);
        inventory.use(f2.getIngredientKey(), fruit2);
        inventory.use("Ice", ice);
        inventory.use("Condensed Milk", milk);
        inventory.use("Sugar", sugar);

        return true;
    }

}
