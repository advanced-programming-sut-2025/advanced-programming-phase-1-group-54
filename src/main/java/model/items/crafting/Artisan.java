package model.items.crafting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Placeable;
import model.enums.Feature;
import model.enums.Symbol;
import model.items.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Artisan extends Item implements Placeable,Cloneable{

//    private final static HashMap<String,Artisan> artisans;
//
//    static {
//        artisans = new HashMap<>();
//        Gson gson = new Gson();
//        FileReader file = null;
//        try {
//            file = new FileReader("artisans.json");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        Type type = new TypeToken<HashMap<String, Artisan>>(){}.getType();
//        artisans = gson.fromJson(file,type);
//        System.out.println(artisans.size());
//    }
//
//    public static Artisan getArtisan(String Name) {
//        return artisans.get(Name).clone();
//    }



    private final String recipeName;



    public Artisan(String name, String recipeName) {
        super(name,false,0);
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    @Override
    protected Artisan clone() {
        try {
            return (Artisan) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void writeToJson(){

        HashMap<String,Artisan> artisansType = new HashMap<>();

        Artisan artisan;
        ArrayList<String> produces;


        artisan = new UnProducerArtisan(
                "Cherry Bomb",
                "Cherry Bomb Recipe",
                50,
                3,
                Feature.DESTROYED
        );
        artisansType.put(artisan.getName(), artisan);


        artisan = new UnProducerArtisan(
                "Bomb",
                "Bomb Recipe",
                50,
                5,
                Feature.DESTROYED
        );
        artisansType.put(artisan.getName(), artisan);


        artisan = new UnProducerArtisan(
                "Mega Bomb",
                "Mega Bomb Recipe",
                50,
                7,
                Feature.DESTROYED
        );
        artisansType.put(artisan.getName(), artisan);


        artisan = new UnProducerArtisan(
                "Sprinkler",
                "Sprinkler Recipe",
                0,
                4,
                Feature.WATERED
        );
        artisansType.put(artisan.getName(), artisan);


        artisan = new UnProducerArtisan(
                "Quality Sprinkler",
                "Quality Sprinkler Recipe",
                0,
                8,
                Feature.WATERED
        );
        artisansType.put(artisan.getName(), artisan);


        artisan = new UnProducerArtisan(
                "Iridium Sprinkler",
                "Iridium Sprinkler Recipe",
                0,
                24,
                Feature.WATERED
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        artisan = new UnProducerArtisan(
                "Scarecrow",
                "Scarecrow Recipe",
                0,
                8,
                Feature.PROTECTED
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        artisan = new UnProducerArtisan(
                "Deluxe Scarecrow",
                "Deluxe Scarecrow Recipe",
                0,
                12,
                Feature.PROTECTED
        );
        artisansType.put(artisan.getName(), artisan);


        artisan = new UnProducerArtisan(
                "Grass Starter",
                "Grass Starter Recipe",
                0,
                0,
                Feature.GRASS
        );
        artisansType.put(artisan.getName(), artisan);

        // Producer Artisans

        produces = new ArrayList<>();

        produces.add("Coal");

        artisan = new ProducerArtisan(
                "Charcoal Kiln",
                "Charcoal Kiln Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Copper Bar");
        produces.add("Iron Bar");
        produces.add("Gold Bar");
        produces.add("Iridium Bar");

        artisan = new ProducerArtisan(
                "Furnace",
                "Furnace Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Honey");

        artisan = new ProducerArtisan(
                "Bee House",
                "Bee House Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Cheese");
        produces.add("Large Cheese");
        produces.add("Goat Cheese");
        produces.add("Large Goat Cheese");

        artisan = new ProducerArtisan(
                "Cheese Press",
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

        artisan = new ProducerArtisan(
                "Keg",
                "Keg Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Cloth");

        artisan = new ProducerArtisan(
                "Loom",
                "Loom Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Mayonnaise");
        produces.add("Large Mayonnaise");
        produces.add("Duck Mayonnaise");
        produces.add("Dinosaur Mayonnaise");

        artisan = new ProducerArtisan(
                "Mayonnaise Machine",
                "Mayonnaise Machine Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);


        produces = new ArrayList<>();

        produces.add("Truffle Oil");
        produces.add("Corn Oil");
        produces.add("Sunflower Seed Oil");
        produces.add("Sunflower Oil");

        artisan = new ProducerArtisan(
                "Oil Maker",
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

        artisan = new ProducerArtisan(
                "Preserves Jar",
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

        artisan = new ProducerArtisan(
                "Dehydrator",
                "Dehydrator Recipe",
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


        artisan = new ProducerArtisan(
                "Fish Smoker",
                "Fish Smoker Recipe",
                produces
        );
        artisansType.put(artisan.getName(), artisan);



        artisan = new UnProducerArtisan(
                "Mystic Tree Seeds",
                "Mystic Tree Seeds Recipe",
                0,
                0,
                null
        );
        artisansType.put(artisan.getName(), artisan);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("artisans.json")){
            gson.toJson(artisansType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Symbol getSymbol() {
        return Symbol.ARTISAN;
    }
}
