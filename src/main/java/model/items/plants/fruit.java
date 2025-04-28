package model.items.plants;

import java.util.HashMap;

public class fruit {

    public static HashMap<String,fruit> fruits;

    private String name;
    private int baseSellPrice;
    private boolean isEdible;
    private int energy;

    public String getName() {
        return name;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public int getEnergy() {
        return energy;
    }
}
