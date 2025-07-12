package com.formos.test.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formos.test.dto.IngredientDTO;
import com.formos.test.model.Ingredient;
import com.formos.test.model.LiquidIngredient;
import com.formos.test.model.SolidIngredient;

/**
 * Service class for managing ingredient inventory. Loads ingredients from JSON
 * file and provides methods for checking availability, consuming ingredients,
 * and monitoring stock levels.
 */
public class Inventory {

    private Map<String, Ingredient> ingredients = new HashMap<>();

    /**
     * Constructor that loads ingredients from ingredients.json file. Creates
     * appropriate ingredient objects based on type (liquid/solid).
     */
    public Inventory() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ingredients.json");
            List<IngredientDTO> ingredientList = mapper.readValue(inputStream, new TypeReference<List<IngredientDTO>>() {
            });

            // Convert DTOs to appropriate ingredient objects
            for (IngredientDTO dto : ingredientList) {
                Ingredient ingredient;
                if (dto.type.equalsIgnoreCase("liquid")) {
                    ingredient = new LiquidIngredient(dto.name, dto.quantity, dto.unitCost);
                } else {
                    ingredient = new SolidIngredient(dto.name, dto.quantity, dto.unitCost);
                }
                ingredients.put(dto.name, ingredient);
            }
        } catch (IOException e) {
            // Handle any exceptions that may occur during initialization
            System.err.println("Error initializing inventory: " + e.getMessage());
        }
    }

    // Checks if there's enough quantity of an ingredient available
    public boolean hasEnough(String name, double required) {
        return ingredients.containsKey(name) && ingredients.get(name).getQuantity() >= required;
    }

    // Consumes ingredients from inventory if available
    public void use(String name, double amount) {
        if (hasEnough(name, amount)) {
            ingredients.get(name).reduce(amount);
        }
    }

    // Retrieves ingredient by name
    public Ingredient get(String name) {
        return ingredients.get(name);
    }

    // Displays current inventory status
    public void printInventory() {
        System.out.println("\n📦 Current Inventory:");
        for (Ingredient i : ingredients.values()) {
            System.out.printf("- %s: %.2f %s%n", i.getName(), i.getQuantity(), i.getUnit());
        }
        System.out.println();
    }

    // Warns about low ingredient levels based on usage thresholds
    public void warnLowIngredients() {
        System.out.println("\n🔎 Checking for low ingredients...");

        boolean warningShown = false;

        for (Ingredient ingredient : ingredients.values()) {
            double threshold;

            // Set thresholds based on ingredient type (enough for ~4 drinks)
            if (ingredient.getName().equalsIgnoreCase("Ice")) {
                threshold = 360;        // 90g * 4 drinks
            } else if (ingredient.getName().equalsIgnoreCase("Condensed Milk")) {
                threshold = 240;        // 60ml * 4 drinks
            } else if (ingredient.getName().equalsIgnoreCase("Sugar")) {
                threshold = 96;         // 24g * 4 drinks
            } else {
                // Fruits: assume max 210g per drink × 4
                threshold = 840;        // Max fruit usage * 4 drinks
            }

            if (ingredient.getQuantity() < threshold) {
                System.out.printf("🚨 %s is low (%.2f %s left)%n",
                        ingredient.getName(), ingredient.getQuantity(), ingredient.getUnit());
                warningShown = true;
            }
        }

        if (!warningShown) {
            System.out.println("✅ All ingredients are above safe thresholds.");
        }
    }
}
