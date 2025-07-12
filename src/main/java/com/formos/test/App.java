package com.formos.test;

import java.util.Scanner;

import com.formos.test.model.Drink;
import com.formos.test.model.DrinkFlavor;
import com.formos.test.model.DrinkSize;
import com.formos.test.service.DrinkCalculator;
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

            System.out.println("🍹 Welcome to the Drink Shop Management System!");
            System.out.println("==============================================");

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
                            Drink drink = new Drink(selectedFlavor, size);
                            Double profit = DrinkService.prepareDrink(selectedFlavor, size, inventory);

                            if (profit != null) {
                                double price = drink.getPrice(inventory);
                                System.out.printf("✅ %s (%s) drink prepared!%n", selectedFlavor, size.name());
                                System.out.printf("💰 Price: $%.2f | Profit: $%.2f%n", price, profit);
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
                            double cost = DrinkCalculator.calculateMixedCost(flavor1, flavor2, size, inventory);
                            double price = Math.round((cost * 1.5) * 100.0) / 100.0;

                            if (profit != null) {
                                System.out.printf("✅ %s + %s (%s) drink prepared!%n", flavor1, flavor2, size.name());
                                System.out.printf("💰 Price: $%.2f | Profit: $%.2f%n", price, profit);
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

///**
/// !Solucion anteriormente eliminada
/// package com.formos.test;

// import java.util.Scanner;

// import com.formos.test.model.Drink;
// import com.formos.test.model.DrinkFlavor;
// import com.formos.test.model.DrinkSize;
// import com.formos.test.service.DrinkCalculator;
// import com.formos.test.service.DrinkService;

// import com.formos.test.service.Inventory;

// /**
//  * Main application class for the Drink Shop Management System. Provides a
//  * console-based interface for managing inventory and selling drinks. Features
//  * include inventory listing, single flavor drinks, mixed drinks, and exit
//  * option.
//  */
// public class App {

//     private static final Scanner scanner = new Scanner(System.in);
//     private static final Inventory inventory = new Inventory();

//     public static void main(String[] args) {
//         System.out.println("==============================================");
//         System.out.println("🍹 Welcome to the Drink Shop Management System!");
//         System.out.println("==============================================");

//         boolean running = true;

//         while (running) {
//             showMenu();
//             int choice = getMenuChoice();

//             switch (choice) {
//                 case 1:
//                     listInventory();
//                     break;
//                 case 2:
//                     sellSingleDrink();
//                     break;
//                 case 3:
//                     sellMixedDrink();
//                     break;
//                 case 4:
//                     System.out.println("👋 Thank you for using the Drink Shop System!");
//                     running = false;
//                     break;
//                 default:
//                     System.out.println("❌ Invalid option. Please try again.");
//             }
//         }

//         scanner.close();
//     }

//     /**
//      * Displays the main menu options.
//      */
//     private static void showMenu() {
//         System.out.println("\n📋 Main Menu:");
//         System.out.println("1. 📦 List Inventory");
//         System.out.println("2. 🥤 Sell Single Flavor Drink");
//         System.out.println("3. 🍹 Sell Mixed Flavor Drink");
//         System.out.println("4. 🚪 Exit");
//         System.out.print("Select an option (1-4): ");
//     }

//     /**
//      * Gets and validates menu choice from user input.
//      *
//      * @return Valid menu choice (1-4)
//      */
//     private static int getMenuChoice() {
//         try {
//             return Integer.parseInt(scanner.nextLine().trim());
//         } catch (NumberFormatException e) {
//             return -1; // Invalid choice
//         }
//     }

//     /**
//      * Lists current inventory and checks for low stock warnings.
//      */
//     private static void listInventory() {
//         inventory.printInventory();
//         inventory.warnLowIngredients();
//     }

//     /**
//      * Handles selling a single flavor drink. Prompts for flavor and size, then
//      * processes the sale.
//      */
//     private static void sellSingleDrink() {
//         System.out.println("\nAvailable flavors:");
//         for (DrinkFlavor flavor : DrinkFlavor.values()) {
//             System.out.printf("- %s%n", flavor.name());
//         }

//         System.out.print("Enter flavor: ");
//         String inputFlavor = scanner.nextLine().toUpperCase();
//         System.out.println("\nSelect size:");
//         for (DrinkSize size : DrinkSize.values()) {
//             System.out.printf("- %s (%d ml)%n", size.name(), size.getVolumeMl());
//         }
//         System.out.print("Enter size: ");
//         String inputSize = scanner.nextLine().toUpperCase();
//         try {
//             DrinkSize size = DrinkSize.valueOf(inputSize);
//             DrinkFlavor selectedFlavor = DrinkFlavor.valueOf(inputFlavor);
//             Drink drink = new Drink(selectedFlavor, size);
//             Double profit = DrinkService.prepareDrink(selectedFlavor, size, inventory);
//             if (profit != null) {
//                 double price = drink.getPrice(inventory);
//                 System.out.printf("✅ %s (%s) drink prepared!%n", selectedFlavor, size.name());
//                 System.out.printf("💰 Price: $%.2f | Profit: $%.2f%n", price, profit);
//                 inventory.warnLowIngredients();
//             } else {
//                 System.out.println("❌ Not enough ingredients for the drink.");
//             }
//         } catch (IllegalArgumentException e) {
//             System.out.println("❗ Invalid size selected. Please try again.");
//         }
//     }

//     /**
//      * Handles selling a mixed flavor drink (two flavors). Prompts for both
//      * flavors and size, then processes the sale.
//      */
//     private static void sellMixedDrink() {
//         System.out.println("\nAvailable flavors");
//         for (DrinkFlavor flavor : DrinkFlavor.values()) {
//             System.out.printf("- %s%n", flavor.name());
//         }
//         System.out.print("Enter first flavor: ");
//         String input1 = scanner.nextLine().toUpperCase();
//         System.out.print("Enter second flavor: ");
//         String input2 = scanner.nextLine().toUpperCase();

//         if (input1.equals(input2)) {
//             System.out.println("⚠️ Both flavors are the same. Use single flavor option instead.");
//             return;
//         }

//         System.out.println("\nSelect size:");
//         for (DrinkSize size : DrinkSize.values()) {
//             System.out.printf("- %s (%d ml)%n", size.name(), size.getVolumeMl());
//         }

//         System.out.print("Enter size: ");
//         String inputMixSize = scanner.nextLine().toUpperCase();
//         try {
//             DrinkFlavor flavor1 = DrinkFlavor.valueOf(input1);
//             DrinkFlavor flavor2 = DrinkFlavor.valueOf(input2);
//             DrinkSize size = DrinkSize.valueOf(inputMixSize);
//             Double profit = DrinkService.prepareMixedDrink(flavor1, flavor2, size, inventory);
//             double cost = DrinkCalculator.calculateMixedCost(flavor1, flavor2, size, inventory);
//             double price = Math.round((cost * 1.5) * 100.0) / 100.0;
//             if (profit != null) {
//                 System.out.printf("✅ %s + %s (%s) drink prepared!%n", flavor1, flavor2, size.name());
//                 System.out.printf("💰 Price: $%.2f | Profit: $%.2f%n", price, profit);
//                 inventory.warnLowIngredients();
//             } else {
//                 System.out.println("❌ Not enough ingredients for a mixed drink.");
//             }
//         } catch (Exception e) {
//             System.out.println("❗ One or both flavors are invalid or invalid size selected, or not enough ingredients.");
//         }
//     }
// }

///  */
