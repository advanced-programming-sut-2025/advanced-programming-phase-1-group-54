package model.items.crafting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.enums.Feature;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class UnProducerArtisan extends Artisan implements Cloneable{

    private final static HashMap<String, UnProducerArtisan> unProducerArtisans;

    static{
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("unProducerArtisans.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, UnProducerArtisan>>(){}.getType();
         unProducerArtisans = gson.fromJson(file,type);
    }

    public static UnProducerArtisan getUnProducerArtisan(String Name) {
        UnProducerArtisan unProducerArtisan = unProducerArtisans.get(Name);
        if (unProducerArtisan == null) {
            return null;
        }
        else {
            return unProducerArtisan.clone();
        }
    }

    private final int sellPrice;
    private final int radius;
    private final Feature feature;

    public UnProducerArtisan(String name, String recipeName, int sellPrice, int radius, Feature feature) {
        super(name, recipeName);
        this.sellPrice = sellPrice;
        this.radius = radius;
        this.feature = feature;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getRadius() {
        return radius;
    }

    public Feature getFeature() {
        return feature;
    }

    @Override
    protected UnProducerArtisan clone() {
        return (UnProducerArtisan) super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnProducerArtisan unProducerArtisan) {
            return this.getName().equals(unProducerArtisan.getName());
        }
        return false;
    }

    public static void writeToJson(){

        HashMap<String,UnProducerArtisan> UnProducerArtisansType = new HashMap<>();

        UnProducerArtisan unProducerArtisan;


        unProducerArtisan = new UnProducerArtisan(
                "Cherry Bomb",
                "Cherry Bomb Recipe",
                50,
                3,
                Feature.DESTROYED
        );
        UnProducerArtisansType.put(unProducerArtisan.getName(), unProducerArtisan);


        unProducerArtisan = new UnProducerArtisan(
                "Bomb",
                "Bomb Recipe",
                50,
                5,
                Feature.DESTROYED
        );
        UnProducerArtisansType.put(unProducerArtisan.getName(), unProducerArtisan);


        unProducerArtisan = new UnProducerArtisan(
                "Mega Bomb",
                "Mega Bomb Recipe",
                50,
                7,
                Feature.DESTROYED
        );
        UnProducerArtisansType.put(unProducerArtisan.getName(), unProducerArtisan);


        unProducerArtisan = new UnProducerArtisan(
                "Sprinkler",
                "Sprinkler Recipe",
                0,
                4,
                Feature.WATER
        );
        UnProducerArtisansType.put(unProducerArtisan.getName(), unProducerArtisan);


        unProducerArtisan = new UnProducerArtisan(
                "Quality Sprinkler",
                "Quality Sprinkler Recipe",
                0,
                8,
                Feature.WATER
        );
        UnProducerArtisansType.put(unProducerArtisan.getName(), unProducerArtisan);


        unProducerArtisan = new UnProducerArtisan(
                "Iridium Sprinkler",
                "Iridium Sprinkler Recipe",
                0,
                24,
                Feature.WATER
        );
        UnProducerArtisansType.put(unProducerArtisan.getName(), unProducerArtisan);


        unProducerArtisan = new UnProducerArtisan(
                "Scarecrow",
                "Scarecrow Recipe",
                0,
                8,
                Feature.PROTECTED
        );
        UnProducerArtisansType.put(unProducerArtisan.getName(), unProducerArtisan);


        unProducerArtisan = new UnProducerArtisan(
                "Deluxe Scarecrow",
                "Deluxe Scarecrow Recipe",
                0,
                12,
                Feature.PROTECTED
        );
        UnProducerArtisansType.put(unProducerArtisan.getName(), unProducerArtisan);


        unProducerArtisan = new UnProducerArtisan(
                "Grass Starter",
                "Grass Starter Recipe",
                0,
                0,
                Feature.GRASS
        );
        UnProducerArtisansType.put(unProducerArtisan.getName(), unProducerArtisan);


        unProducerArtisan = new UnProducerArtisan(
                "Mystic Tree Seeds",
                "Mystic Tree Seeds Recipe",
                0,
                0,
                null
        );
        UnProducerArtisansType.put(unProducerArtisan.getName(), unProducerArtisan);



        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("unProducerArtisans.json")){
            gson.toJson(UnProducerArtisansType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
