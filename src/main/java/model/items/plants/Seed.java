package model.items.plants;

import model.items.Item;

import java.util.HashMap;

public class Seed extends Item {

    public static HashMap<String,Seed> seeds;

    private String name;
    private String plant;

    public String getName() {
        return name;
    }

    public String getPlant() {
        return plant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }
}
