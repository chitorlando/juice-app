package com.formos.test.model;

import com.formos.test.service.Inventory;

public class Drink {

    private final DrinkFlavor flavor;

    public Drink(DrinkFlavor flavor) {
        this.flavor = flavor;
    }

    public boolean make(Inventory inventory) {
        double fruit = flavor.getRequiredGrams();
        double ice = 90; // 90 grams of ice
        double milk = 150; // 150 ml of milk    
        double sugar = 24; // 30 grams of sugar

        boolean canMake = inventory.hasEnough(flavor.getIngredientKey(), fruit)
                && inventory.hasEnough("Ice", ice)
                && inventory.hasEnough("Condensed Milk", milk)
                && inventory.hasEnough("Sugar", sugar);

        if (!canMake) {
            return false;
        }

        inventory.use(flavor.getIngredientKey(), fruit);
        inventory.use("Ice", ice);
        inventory.use("Condensed Milk", milk);
        inventory.use("Sugar", sugar);

        return true;
    }

    public String getFlavorName() {
        return flavor.name();
    }
}
