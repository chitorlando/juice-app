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

public class Inventory {

    private Map<String, Ingredient> ingredients = new HashMap<>();

    public Inventory() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ingredients.json");
            List<IngredientDTO> ingredientList = mapper.readValue(inputStream, new TypeReference<List<IngredientDTO>>() {
            });

            for (IngredientDTO dto : ingredientList) {
                Ingredient ingredient;
                if (dto.type.equalsIgnoreCase("liquid")) {
                    ingredient = new LiquidIngredient(dto.name, dto.quantity);
                } else {
                    ingredient = new SolidIngredient(dto.name, dto.quantity);
                }
                ingredients.put(dto.name, ingredient);
            }
        } catch (IOException e) {
            // Handle any exceptions that may occur during initialization
            System.err.println("Error initializing inventory: " + e.getMessage());
        }
    }

    public boolean hasEnough(String name, double required) {
        return ingredients.containsKey(name) && ingredients.get(name).getQuantity() >= required;
    }

    public void use(String name, double amount) {
        if (hasEnough(name, amount)) {
            ingredients.get(name).reduce(amount);
        }
    }

    public Ingredient get(String name) {
        return ingredients.get(name);
    }

    public void printInventory() {
        System.out.println("\n📦 Current Inventory:");
        for (Ingredient i : ingredients.values()) {
            System.out.printf("- %s: %.2f %s%n", i.getName(), i.getQuantity(), i.getUnit());
        }
        System.out.println();
    }

    public void warnLowIngredients() {
        System.out.println("\n🔎 Checking for low ingredients...");

        boolean warningShown = false;

        for (Ingredient ingredient : ingredients.values()) {
            double threshold;

            if (ingredient.getName().equalsIgnoreCase("Ice")) {
                threshold = 360;
            } else if (ingredient.getName().equalsIgnoreCase("Condensed Milk")) {
                threshold = 240;
            } else if (ingredient.getName().equalsIgnoreCase("Sugar")) {
                threshold = 96;
            } else {
                // fruta: asumimos máximo 210g por bebida × 4
                threshold = 840;
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
