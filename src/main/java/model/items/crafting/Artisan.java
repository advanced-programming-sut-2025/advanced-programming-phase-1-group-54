package model.items.crafting;

import model.Placeable;
import model.items.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class Artisan extends Item implements Placeable {

    static HashMap<String,Artisan> Artisans ;

    static {
        Artisans = new HashMap<>();
    }

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


    public static void writeToJson(){

        HashMap<String,Artisan> artisans = new HashMap<>();

        Artisan artisan;
        ArrayList<String> produces;

        produces = new ArrayList<>();

        artisan = new Artisan(
                "Cherry Bomb",
                50,
                "Cherry Bomb Recipe",
                produces
        );
        artisans.put(artisan.getName(), artisan);

        artisan = new Artisan(
                "Bomb",
                50,
                "Bomb Recipe",
                produces
        );
        artisans.put(artisan.getName(), artisan);

        artisan = new Artisan(
                "Mega Bomb",
                50,
                "Mega Bomb Recipe",
                produces
        );
        artisans.put(artisan.getName(), artisan);


    }

}
