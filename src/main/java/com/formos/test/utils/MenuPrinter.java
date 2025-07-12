package com.formos.test.utils;

/**
 * Utility class for printing the main menu.
 */
public class MenuPrinter {

    public static void printMainMenu() {
        System.out.println("\n📋 Main Menu:");
        System.out.println("1. 📦 List Inventory");
        System.out.println("2. 🥤 Sell Single Flavor Drink");
        System.out.println("3. 🍹 Sell Mixed Flavor Drink");
        System.out.println("4. 🧾 Show Daily Sales Report");
        System.out.println("5. 🚪 Exit");
        System.out.print("Select an option (1-5): ");
    }
}
