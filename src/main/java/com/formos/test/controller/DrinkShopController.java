package com.formos.test.controller;

import java.util.Scanner;

import com.formos.test.service.Inventory;
import com.formos.test.service.SalesService;
import com.formos.test.utils.MenuPrinter;

/**
 * Controller class for managing the Drink Shop user interface.
 * Handles user input and coordinates sales and inventory logic.
 */
public class DrinkShopController {

    private final Scanner scanner = new Scanner(System.in);
    private final Inventory inventory = new Inventory();
    private final SalesService salesService = new SalesService();
    private final DrinkTransactionHandler transactionHandler = new DrinkTransactionHandler(scanner, inventory, salesService);

    public void start() {
        System.out.println("==============================================");
        System.out.println("🍹 Welcome to the Drink Shop Management System!");
        System.out.println("==============================================");

        boolean running = true;

        while (running) {
            MenuPrinter.printMainMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    inventory.printInventory();
                    inventory.warnLowIngredients();
                    break;
                case 2:
                    transactionHandler.sellSingleDrink();
                    break;
                case 3:
                    transactionHandler.sellMixedDrink();
                    break;
                case 4:
                    salesService.printReport();
                    break;
                case 5:
                    System.out.println("\n🔒 Final daily report before exit:");
                    salesService.printReport();
                    System.out.println("👋 Thank you for using the Drink Shop System!");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
