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

public class FeatureArtisan extends Artisan implements Cloneable{

    private final static HashMap<String, FeatureArtisan> unProducerArtisans;

    static{
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("unProducerArtisans.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, FeatureArtisan>>(){}.getType();
         unProducerArtisans = gson.fromJson(file,type);
    }

    public static FeatureArtisan getFeatureArtisan(String Name) {
        FeatureArtisan featureArtisan = unProducerArtisans.get(Name);
        if (featureArtisan == null) {
            return null;
        }
        else {
            return featureArtisan.clone();
        }
    }

    private final int sellPrice;
    private final int radius;
    private final Feature feature;

    public FeatureArtisan(String name, String recipeName, int sellPrice, int radius, Feature feature) {
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
    protected FeatureArtisan clone() {
        return (FeatureArtisan) super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FeatureArtisan featureArtisan) {
            return this.getName().equals(featureArtisan.getName());
        }
        return false;
    }

    public static void writeToJson(){

        HashMap<String, FeatureArtisan> UnProducerArtisansType = new HashMap<>();

        FeatureArtisan featureArtisan;


        featureArtisan = new FeatureArtisan(
                "Cherry Bomb",
                "Cherry Bomb Recipe",
                50,
                3,
                Feature.DESTROYED
        );
        UnProducerArtisansType.put(featureArtisan.getName(), featureArtisan);


        featureArtisan = new FeatureArtisan(
                "Bomb",
                "Bomb Recipe",
                50,
                5,
                Feature.DESTROYED
        );
        UnProducerArtisansType.put(featureArtisan.getName(), featureArtisan);


        featureArtisan = new FeatureArtisan(
                "Mega Bomb",
                "Mega Bomb Recipe",
                50,
                7,
                Feature.DESTROYED
        );
        UnProducerArtisansType.put(featureArtisan.getName(), featureArtisan);


        featureArtisan = new FeatureArtisan(
                "Sprinkler",
                "Sprinkler Recipe",
                0,
                4,
                Feature.AUTO_WATER
        );
        UnProducerArtisansType.put(featureArtisan.getName(), featureArtisan);


        featureArtisan = new FeatureArtisan(
                "Quality Sprinkler",
                "Quality Sprinkler Recipe",
                0,
                8,
                Feature.AUTO_WATER
        );
        UnProducerArtisansType.put(featureArtisan.getName(), featureArtisan);


        featureArtisan = new FeatureArtisan(
                "Iridium Sprinkler",
                "Iridium Sprinkler Recipe",
                0,
                24,
                Feature.AUTO_WATER
        );
        UnProducerArtisansType.put(featureArtisan.getName(), featureArtisan);


        featureArtisan = new FeatureArtisan(
                "Scarecrow",
                "Scarecrow Recipe",
                0,
                8,
                Feature.PROTECTED
        );
        UnProducerArtisansType.put(featureArtisan.getName(), featureArtisan);


        featureArtisan = new FeatureArtisan(
                "Deluxe Scarecrow",
                "Deluxe Scarecrow Recipe",
                0,
                12,
                Feature.PROTECTED
        );
        UnProducerArtisansType.put(featureArtisan.getName(), featureArtisan);


        featureArtisan = new FeatureArtisan(
                "Grass Starter",
                "Grass Starter Recipe",
                0,
                0,
                Feature.GRASS
        );
        UnProducerArtisansType.put(featureArtisan.getName(), featureArtisan);


        featureArtisan = new FeatureArtisan(
                "Mystic Tree Seeds",
                "Mystic Tree Seeds Recipe",
                0,
                0,
                null
        );
        UnProducerArtisansType.put(featureArtisan.getName(), featureArtisan);



        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("unProducerArtisans.json")){
            gson.toJson(UnProducerArtisansType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
