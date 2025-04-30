package model.items.crafting;

import model.Placeable;
import model.items.Item;

import java.util.ArrayList;

public class Artisan extends Item implements Placeable {

    private final String recipeName;
    private final int sellPrice;
    private final ArrayList<String> producesNames;
    private Produce processingProduce;
    private int remainingHours;
    private boolean produceIsReady;

    public Artisan(String name, int sellPrice, String recipeName, ArrayList<String> producesNames) {
        super(name);
        this.sellPrice = sellPrice;
        this.recipeName = recipeName;
        this.producesNames = producesNames;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public ArrayList<String> getProducesNames() {
        return producesNames;
    }

    public Produce getProcessingProduce() {
        return processingProduce;
    }

    public int getRemainingHours() {
        return remainingHours;
    }

    public boolean isProduceIsReady() {
        return produceIsReady;
    }

}
