package model.items.crafting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Placeable;
import model.items.Item;

import java.io.FileWriter;
import java.io.IOException;
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

        HashMap<String,Artisan> artisansType = new HashMap<>();

        Artisan artisan;
        ArrayList<String> produces;

        produces = new ArrayList<>();

        artisan = new Artisan(
                "Cherry Bomb",
                50,
                "Cherry Bomb Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);

        produces = new ArrayList<>();

        artisan = new Artisan(
                "Bomb",
                50,
                "Bomb Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);

        produces = new ArrayList<>();

        artisan = new Artisan(
                "Mega Bomb",
                50,
                "Mega Bomb Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        artisan = new Artisan(
                "Sprinkler",
                0,
                "Sprinkler Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        artisan = new Artisan(
                "Quality Sprinkler",
                0,
                "Quality Sprinkler Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        artisan = new Artisan(
                "Iridium Sprinkler",
                0,
                "Iridium Sprinkler Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Coal");

        artisan = new Artisan(
                "Charcoal Kiln",
                0,
                "Charcoal Kiln Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Copper Bar");
        produces.add("Iron Bar");
        produces.add("Gold Bar");
        produces.add("Iridium Bar");

        artisan = new Artisan(
                "Furnace",
                0,
                "Furnace Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        artisan = new Artisan(
                "Scarecrow",
                0,
                "Scarecrow Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        artisan = new Artisan(
                "Deluxe Scarecrow",
                0,
                "Deluxe Scarecrow Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Honey");

        artisan = new Artisan(
                "Bee House",
                0,
                "Bee House Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Cheese");
        produces.add("Large Cheese");
        produces.add("Goat Cheese");
        produces.add("Large Goat Cheese");

        artisan = new Artisan(
                "Cheese Press",
                0,
                "Cheese Press Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


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

        artisan = new Artisan(
                "Keg",
                0,
                "Keg Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Cloth");

        artisan = new Artisan(
                "Loom",
                0,
                "Loom Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Mayonnaise");
        produces.add("Large Mayonnaise");
        produces.add("Duck Mayonnaise");
        produces.add("Dinosaur Mayonnaise");

        artisan = new Artisan(
                "Mayonnaise Machine",
                0,
                "Mayonnaise Machine Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Truffle Oil");
        produces.add("Corn Oil");
        produces.add("Sunflower Seed Oil");
        produces.add("Sunflower Oil");

        artisan = new Artisan(
                "Oil Maker",
                0,
                "Oil Maker Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


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

        artisan = new Artisan(
                "Preserves Jar",
                0,
                "Preserves Jar Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


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

        artisan = new Artisan(
                "Dehydrator",
                0,
                "Dehydrator Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        artisan = new Artisan(
                "Grass Starter",
                0,
                "Grass Starter Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


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


        artisan = new Artisan(
                "Fish Smoker",
                0,
                "Fish Smoker Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        artisan = new Artisan(
                "Mystic Tree Seed",
                0,
                "Mystic Tree Seed Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("artisans.json")){
            gson.toJson(artisansType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
