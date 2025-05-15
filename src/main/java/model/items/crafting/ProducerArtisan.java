package model.items.crafting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.DailyUpdate;
import model.DateTime;
import model.HourUpdate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ProducerArtisan extends Artisan implements HourUpdate, DailyUpdate, Cloneable {

    private final static HashMap<String,ProducerArtisan> producerArtisans;

    static {
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("ProducerArtisans.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, ProducerArtisan>>(){}.getType();
        producerArtisans = gson.fromJson(file,type);
    }

    public static ProducerArtisan getProducerArtisan(String artisan){
        ProducerArtisan producerArtisan = producerArtisans.get(artisan);
        if(producerArtisan == null){
            return null;
        }
        else{
            return producerArtisan.clone();
        }
    }

    private final ArrayList<String> producesNames;
    private Produce processingProduce;
    private int remainingHours;
    private boolean produceIsReady;

    public ProducerArtisan(String name, String recipeName, ArrayList<String> producesNames) {
        super(name, recipeName);
        this.producesNames = producesNames;
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

    public boolean isProduceReady() {
        if (getProcessingProduce() == null)
            return false;
        return remainingHours <= 0;
    }

    public void setProcessingProduce(Produce processingProduce) {
        this.processingProduce = processingProduce;
    }

    public void setRemainingHours(int remainingHours) {
        this.remainingHours = remainingHours;
    }

    public void setProduceIsReady(boolean produceIsReady) {
        this.produceIsReady = produceIsReady;
    }

    @Override
    protected ProducerArtisan clone() {
        return (ProducerArtisan) super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ProducerArtisan producerArtisan) {
            return this.getName().equals(producerArtisan.getName());
        }
        return false;
    }

    public static void writeToJson(){

        HashMap<String,ProducerArtisan> producerArtisanType = new HashMap<>();

        ProducerArtisan producerArtisan;
        ArrayList<String> produces;

        produces = new ArrayList<>();

        produces.add("Coal");

        producerArtisan = new ProducerArtisan(
                "Charcoal Kiln",
                "Charcoal Kiln Recipe",
                produces
        );
        producerArtisanType.put(producerArtisan.getName(), producerArtisan);


        produces = new ArrayList<>();

        produces.add("Copper Bar");
        produces.add("Iron Bar");
        produces.add("Gold Bar");
        produces.add("Iridium Bar");

        producerArtisan = new ProducerArtisan(
                "Furnace",
                "Furnace Recipe",
                produces
        );
        producerArtisanType.put(producerArtisan.getName(), producerArtisan);


        produces = new ArrayList<>();

        produces.add("Honey");

        producerArtisan = new ProducerArtisan(
                "Bee House",
                "Bee House Recipe",
                produces
        );
        producerArtisanType.put(producerArtisan.getName(), producerArtisan);


        produces = new ArrayList<>();

        produces.add("Cheese");
        produces.add("Large Cheese");
        produces.add("Goat Cheese");
        produces.add("Large Goat Cheese");

        producerArtisan = new ProducerArtisan(
                "Cheese Press",
                "Cheese Press Recipe",
                produces
        );
        producerArtisanType.put(producerArtisan.getName(), producerArtisan);


        produces = new ArrayList<>();

        produces.add("Beer");
        produces.add("Vinegar");
        produces.add("Coffee");
        produces.add("Mead");
        produces.add("Pale Ale");

        produces.add("Amaranth Juice");
        produces.add("Artichoke Juice");
        produces.add("Beet Juice");
        produces.add("Bok Choy Juice");
        produces.add("Broccoli Juice");
        produces.add("Carrot Juice");
        produces.add("Cauliflower Juice");
        produces.add("Corn Juice");
        produces.add("Eggplant Juice");
        produces.add("Fiddlehead Fern Juice");
        produces.add("Garlic Juice");
        produces.add("Green Bean Juice");
        produces.add("Kale Juice");
        produces.add("Parsnip Juice");
        produces.add("Potato Juice");
        produces.add("Pumpkin Juice");
        produces.add("Radish Juice");
        produces.add("Red Cabbage Juice");
        produces.add("Summer Squash Juice");
        produces.add("Taro Root Juice");
        produces.add("Tea Leaves Juice");
        produces.add("Tomato Juice");
        produces.add("Unmilled Rice Juice");
        produces.add("Yam Juice");

        produces.add("Ancient Fruit Wine");
        produces.add("Apple Wine");
        produces.add("Apricot Wine");
        produces.add("Banana Wine");
        produces.add("Blackberry Wine");
        produces.add("Blueberry Wine");
        produces.add("Cactus Fruit Wine");
        produces.add("Cherry Wine");
        produces.add("Coconut Wine");
        produces.add("Cranberries Wine");
        produces.add("Crystal Fruit Wine");
        produces.add("Grape Wine");
        produces.add("Hot Pepper Wine");
        produces.add("Mango Wine");
        produces.add("Melon Wine");
        produces.add("Orange Wine");
        produces.add("Peach Wine");
        produces.add("Pineapple Wine");
        produces.add("Pomegranate Wine");
        produces.add("Powdermelon Wine");
        produces.add("Qi Fruit Wine");
        produces.add("Rhubarb Wine");
        produces.add("Salmonberry Wine");
        produces.add("Spice Berry Wine");
        produces.add("Starfruit Wine");
        produces.add("Strawberry Wine");
        produces.add("Wild Plum Wine");

        producerArtisan = new ProducerArtisan(
                "Keg",
                "Keg Recipe",
                produces
        );
        producerArtisanType.put(producerArtisan.getName(), producerArtisan);


        produces = new ArrayList<>();

        produces.add("Cloth");

        producerArtisan = new ProducerArtisan(
                "Loom",
                "Loom Recipe",
                produces
        );
        producerArtisanType.put(producerArtisan.getName(), producerArtisan);


        produces = new ArrayList<>();

        produces.add("Mayonnaise");
        produces.add("Large Mayonnaise");
        produces.add("Duck Mayonnaise");
        produces.add("Dinosaur Mayonnaise");

        producerArtisan = new ProducerArtisan(
                "Mayonnaise Machine",
                "Mayonnaise Machine Recipe",
                produces
        );
        producerArtisanType.put(producerArtisan.getName(), producerArtisan);


        produces = new ArrayList<>();

        produces.add("Truffle Oil");
        produces.add("Corn Oil");
        produces.add("Sunflower Seed Oil");
        produces.add("Sunflower Oil");

        producerArtisan = new ProducerArtisan(
                "Oil Maker",
                "Oil Maker Recipe",
                produces
        );
        producerArtisanType.put(producerArtisan.getName(), producerArtisan);


        produces = new ArrayList<>();

        produces.add("Amaranth Pickles");
        produces.add("Artichoke Pickles");
        produces.add("Beet Pickles");
        produces.add("Bok Choy Pickles");
        produces.add("Broccoli Pickles");
        produces.add("Carrot Pickles");
        produces.add("Cauliflower Pickles");
        produces.add("Corn Pickles");
        produces.add("Eggplant Pickles");
        produces.add("Fiddlehead Fern Pickles");
        produces.add("Garlic Pickles");
        produces.add("Green Bean Pickles");
        produces.add("Hops Pickles");
        produces.add("Kale Pickles");
        produces.add("Parsnip Pickles");
        produces.add("Potato Pickles");
        produces.add("Pumpkin Pickles");
        produces.add("Radish Pickles");
        produces.add("Red Cabbage Pickles");
        produces.add("Summer Squash Pickles");
        produces.add("Taro Root Pickles");
        produces.add("Tea Leaves Pickles");
        produces.add("Tomato Pickles");
        produces.add("Unmilled Rice Pickles");
        produces.add("Wheat Pickles");
        produces.add("Yam Pickles");

        produces.add("Ancient Fruit Jelly");
        produces.add("Apple Jelly");
        produces.add("Apricot Jelly");
        produces.add("Banana Jelly");
        produces.add("Blackberry Jelly");
        produces.add("Blueberry Jelly");
        produces.add("Cactus Fruit Jelly");
        produces.add("Cherry Jelly");
        produces.add("Coconut Jelly");
        produces.add("Cranberries Jelly");
        produces.add("Crystal Fruit Jelly");
        produces.add("Grape Jelly");
        produces.add("Hot Pepper Jelly");
        produces.add("Mango Jelly");
        produces.add("Melon Jelly");
        produces.add("Orange Jelly");
        produces.add("Peach Jelly");
        produces.add("Pineapple Jelly");
        produces.add("Pomegranate Jelly");
        produces.add("Powdermelon Jelly");
        produces.add("Qi Fruit Jelly");
        produces.add("Rhubarb Jelly");
        produces.add("Salmonberry Jelly");
        produces.add("Spice Berry Jelly");
        produces.add("Starfruit Jelly");
        produces.add("Strawberry Jelly");
        produces.add("Wild Plum Jelly");

        producerArtisan = new ProducerArtisan(
                "Preserves Jar",
                "Preserves Jar Recipe",
                produces
        );
        producerArtisanType.put(producerArtisan.getName(), producerArtisan);


        produces = new ArrayList<>();

        produces.add("Dried Chanterelle");
        produces.add("Dried Common Mushroom");
        produces.add("Dried Magma Cap");
        produces.add("Dried Morel");
        produces.add("Dried Purple Mushroom");
        produces.add("Dried Red Mushroom");

        produces.add("Dried Ancient Fruit");
        produces.add("Dried Apple");
        produces.add("Dried Apricot");
        produces.add("Dried Banana");
        produces.add("Dried Blackberry");
        produces.add("Dried Blueberry");
        produces.add("Dried Cactus Fruit");
        produces.add("Dried Cherry");
        produces.add("Dried Coconut");
        produces.add("Dried Cranberries");
        produces.add("Dried Crystal Fruit");
        produces.add("Dried Hot Pepper");
        produces.add("Dried Mango");
        produces.add("Dried Melon");
        produces.add("Dried Orange");
        produces.add("Dried Peach");
        produces.add("Dried Pineapple");
        produces.add("Dried pomegranate");
        produces.add("Dried Powdermelon");
        produces.add("Dried Qi Fruit");
        produces.add("Dried Rhubarb");
        produces.add("Dried Salmonberry");
        produces.add("Dried Spice Berry");
        produces.add("Dried Starfruit");
        produces.add("Dried Strawberry");
        produces.add("Dried Wild Plum");

        produces.add("Raisins");

        producerArtisan = new ProducerArtisan(
                "Dehydrator",
                "Dehydrator Recipe",
                produces
        );
        producerArtisanType.put(producerArtisan.getName(), producerArtisan);


        produces = new ArrayList<>();

        produces.add("Smoked Flounder");
        produces.add("Smoked Lionfish");
        produces.add("Smoked Herring");
        produces.add("Smoked Ghostfish");
        produces.add("Smoked Tilapia");
        produces.add("Smoked Dorado");
        produces.add("Smoked Sunfish");
        produces.add("Smoked Rainbow Trout");
        produces.add("Smoked Salmon");
        produces.add("Smoked Sardine");
        produces.add("Smoked Sardine");
        produces.add("Smoked Shad");
        produces.add("Smoked Blue Discus");
        produces.add("Smoked Midnight Carp");
        produces.add("Smoked Squid");
        produces.add("Smoked Tuna");
        produces.add("Smoked Perch");
        produces.add("Smoked Legend");
        produces.add("Smoked Glacierfish");
        produces.add("Smoked Angler");
        produces.add("Smoked Crimsonfish");


        producerArtisan = new ProducerArtisan(
                "Fish Smoker",
                "Fish Smoker Recipe",
                produces
        );
        producerArtisanType.put(producerArtisan.getName(), producerArtisan);



        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("producerArtisans.json")){
            gson.toJson(producerArtisanType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void nextHourUpdate() {
        if (this.getProcessingProduce() != null) {
           remainingHours--;
            if (remainingHours <= 0) {
                produceIsReady = true;
                remainingHours = 0;
            }
        }
    }

    @Override
    public void nextDayUpdate() {
        if (this.getProcessingProduce() != null) {
            remainingHours -= DateTime.getNightTime();
            if (remainingHours <= 0) {
                produceIsReady = true;
                remainingHours = 0;
            }
        }
    }
}
