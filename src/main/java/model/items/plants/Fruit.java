package model.items.plants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.App;
import model.Placeable;
import model.enums.ProduceQuality;
import model.enums.Season;
import model.enums.Symbol;
import model.items.Fish;
import model.items.Item;
import model.items.crafting.UnProducerArtisan;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Fruit extends Item implements Cloneable, Placeable {

    private final static HashMap<String, Fruit> fruits;
    private final static HashMap<Season, ArrayList<String>> foragingCrops;


    static {
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("fruits.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, Fruit>>() {
        }.getType();
        fruits = gson.fromJson(file, type);
        System.out.println(fruits.size());

        try {
            file = new FileReader("foragingCrops.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        type = new TypeToken<HashMap<Season, ArrayList<String>>>() {
        }.getType();
        foragingCrops = gson.fromJson(file, type);
        System.out.println(foragingCrops.size());

    }

    public static Fruit getFruit(String name) {
        Fruit fruit = fruits.get(name);
        if (fruit == null) {
            return null;
        } else {
            return fruit.clone();
        }
    }

    public static Fruit getForagingCrop() {
        Season season = App.getCurrentGame().getDateTime().getSeason();
        Random rand = new Random();
        String foragingCropName = foragingCrops.get(season).get(rand.nextInt(foragingCrops.size()));
        return Fruit.getFruit(foragingCropName);
    }


    private final int energy;
    private ProduceQuality quality;

    public Fruit(String name, int sellPrice, boolean isEdible, int energy) {
        super(name, isEdible, sellPrice);
        this.energy = energy;
        this.quality = ProduceQuality.NORMAL;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public int getEnergy() {
        return energy;
    }

    public ProduceQuality getQuality() {
        return quality;
    }

    public void setQuality(ProduceQuality quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "Name : " + name +
                "\nbaseSellPrice : " + baseSellPrice +
                "\nisEdible : " + isEdible +
                "\nenergy : " + energy;
    }

    @Override
    protected Fruit clone() {
        try {
            return (Fruit) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fruit fruit) {
            return this.getName().equals(fruit.getName()) && this.getQuality().equals(fruit.getQuality());
        }
        return false;
    }

    public static void writeToJson() {

        HashMap<String, Fruit> fruitTypes = new HashMap<String, Fruit>();

        HashMap<Season, ArrayList<String>> foragingCropsType = new HashMap<>();

        ArrayList<String> springForagingCrops = new ArrayList<>();
        ArrayList<String> summerForagingCrops = new ArrayList<>();
        ArrayList<String> fallForagingCrops = new ArrayList<>();
        ArrayList<String> winterForagingCrops = new ArrayList<>();

        Fruit fruit;

        // Tree fruits

        fruit = new Fruit("Apricot", 59, true, 38);
        fruitTypes.put("Apricot", fruit);

        fruit = new Fruit("Cherry", 80, true, 38);
        fruitTypes.put("Cherry", fruit);

        fruit = new Fruit("Banana", 150, true, 75);
        fruitTypes.put("Banana", fruit);

        fruit = new Fruit("Mango", 130, true, 100);
        fruitTypes.put("Mango", fruit);

        fruit = new Fruit("Orange", 100, true, 38);
        fruitTypes.put("Orange", fruit);

        fruit = new Fruit("Peach", 140, true, 38);
        fruitTypes.put("Peach", fruit);

        fruit = new Fruit("Apple", 100, true, 38);
        fruitTypes.put("Apple", fruit);

        fruit = new Fruit("Pomegranate", 140, true, 38);
        fruitTypes.put("Pomegranate", fruit);

        fruit = new Fruit("Oak Resin ", 150, false, 0);
        fruitTypes.put("Oak Resin ", fruit);

        fruit = new Fruit("Maple Syrup", 200, false, 0);
        fruitTypes.put("Maple Syrup", fruit);

        fruit = new Fruit("Pine Tar", 100, false, 0);
        fruitTypes.put("Pine Tar", fruit);

        fruit = new Fruit("Sap", 2, true, -2);
        fruitTypes.put("Sap", fruit);

        fruit = new Fruit("Common Mushroom", 40, false, 38);
        fruitTypes.put("Common Mushroom", fruit);
        summerForagingCrops.add(fruit.getName());

        fruit = new Fruit("Mystic Syrup", 1000, false, 500);
        fruitTypes.put("Mystic Syrup", fruit);

        // Crop fruits

        fruit = new Fruit("Blue Jazz", 50, true, 45);
        fruitTypes.put("Blue Jazz", fruit);

        fruit = new Fruit("Carrot", 35, true, 75);
        fruitTypes.put("Carrot", fruit);

        fruit = new Fruit("Cauliflower", 175, true, 75);
        fruitTypes.put("Cauliflower", fruit);

        fruit = new Fruit("Coffee Bean", 15, false, 0);
        fruitTypes.put("Coffee Bean", fruit);

        fruit = new Fruit("Garlic", 60, true, 20);
        fruitTypes.put("Garlic", fruit);

        fruit = new Fruit("Green Bean", 40, true, 25);
        fruitTypes.put("Green Bean", fruit);

        fruit = new Fruit("Kale", 110, true, 50);
        fruitTypes.put("Kale", fruit);

        fruit = new Fruit("Parsnip", 35, true, 25);
        fruitTypes.put("Parsnip", fruit);

        fruit = new Fruit("Potato", 80, true, 25);
        fruitTypes.put("Potato", fruit);

        fruit = new Fruit("Rhubarb", 220, false, 0);
        fruitTypes.put("Rhubarb", fruit);

        fruit = new Fruit("Strawberry", 120, true, 50);
        fruitTypes.put("Strawberry", fruit);

        fruit = new Fruit("Tulip", 30, true, 45);
        fruitTypes.put("Tulip", fruit);

        fruit = new Fruit("Unmilled Rice", 30, true, 3);
        fruitTypes.put("Unmilled Rice", fruit);

        fruit = new Fruit("Blueberry", 50, true, 25);
        fruitTypes.put("Blueberry", fruit);

        fruit = new Fruit("Corn", 50, true, 25);
        fruitTypes.put("Corn", fruit);

        fruit = new Fruit("Hops", 25, true, 45);
        fruitTypes.put("Hops", fruit);

        fruit = new Fruit("Hot Pepper", 40, true, 13);
        fruitTypes.put("Hot Pepper", fruit);

        fruit = new Fruit("Melon", 250, true, 113);
        fruitTypes.put("Melon", fruit);

        fruit = new Fruit("Poppy", 140, true, 45);
        fruitTypes.put("Poppy", fruit);

        fruit = new Fruit("Radish", 90, true, 45);
        fruitTypes.put("Radish", fruit);

        fruit = new Fruit("Red Cabbage", 260, true, 75);
        fruitTypes.put("Red Cabbage", fruit);

        fruit = new Fruit("Starfruit", 750, true, 125);
        fruitTypes.put("Starfruit", fruit);

        fruit = new Fruit("Summer Spangle", 90, true, 45);
        fruitTypes.put("Summer Spangle", fruit);

        fruit = new Fruit("Summer Squash", 45, true, 63);
        fruitTypes.put("Summer Squash", fruit);

        fruit = new Fruit("Sunflower", 80, true, 45);
        fruitTypes.put("Sunflower", fruit);

        fruit = new Fruit("Tomato", 60, true, 20);
        fruitTypes.put("Tomato", fruit);

        fruit = new Fruit("Wheat", 25, false, 0);
        fruitTypes.put("Wheat", fruit);

        fruit = new Fruit("Amaranth", 150, true, 50);
        fruitTypes.put("Amaranth", fruit);

        fruit = new Fruit("Artichoke", 160, true, 30);
        fruitTypes.put("Artichoke", fruit);

        fruit = new Fruit("Beet", 100, true, 30);
        fruitTypes.put("Beet", fruit);

        fruit = new Fruit("Bok Choy", 80, true, 25);
        fruitTypes.put("Bok Choy", fruit);

        fruit = new Fruit("Broccoli", 70, true, 63);
        fruitTypes.put("Broccoli", fruit);

        fruit = new Fruit("Cranberries", 75, true, 38);
        fruitTypes.put("Cranberries", fruit);

        fruit = new Fruit("Eggplant", 60, true, 20);
        fruitTypes.put("Eggplant", fruit);

        fruit = new Fruit("Fairy Rose", 290, true, 45);
        fruitTypes.put("Fairy Rose", fruit);

        fruit = new Fruit("Grape", 80, true, 38);
        fruitTypes.put("Grape", fruit);
        summerForagingCrops.add(fruit.getName());

        fruit = new Fruit("Pumpkin", 320, false, 0);
        fruitTypes.put("Pumpkin", fruit);

        fruit = new Fruit("Yam", 160, true, 45);
        fruitTypes.put("Yam", fruit);

        fruit = new Fruit("Sweet Gem Berry", 3000, false, 0);
        fruitTypes.put("Sweet Gem Berry", fruit);

        fruit = new Fruit("Powdermelon", 60, true, 63);
        fruitTypes.put("Powdermelon", fruit);

        fruit = new Fruit("Ancient Fruit", 550, false, 0);
        fruitTypes.put("Ancient Fruit", fruit);

        // foraging Crops

        fruit = new Fruit("Daffodil", 40, false, 0);
        fruitTypes.put(fruit.getName(), fruit);
        springForagingCrops.add(fruit.getName());

        fruit = new Fruit("Dandelion", 40, true, 25);
        fruitTypes.put(fruit.getName(), fruit);
        springForagingCrops.add(fruit.getName());

        fruit = new Fruit("Leek", 60, true, 40);
        fruitTypes.put(fruit.getName(), fruit);
        springForagingCrops.add(fruit.getName());

        fruit = new Fruit("Morel", 150, true, 20);
        fruitTypes.put(fruit.getName(), fruit);
        springForagingCrops.add(fruit.getName());

        fruit = new Fruit("Salmonberry", 5, true, 25);
        fruitTypes.put(fruit.getName(), fruit);
        springForagingCrops.add(fruit.getName());

        fruit = new Fruit("Spring Onion", 8, true, 13);
        fruitTypes.put(fruit.getName(), fruit);
        springForagingCrops.add(fruit.getName());

        fruit = new Fruit("Wild Horseradish", 50, true, 13);
        fruitTypes.put(fruit.getName(), fruit);
        springForagingCrops.add(fruit.getName());

        fruit = new Fruit("Fiddlehead Fern", 90, true, 25);
        fruitTypes.put(fruit.getName(), fruit);
        summerForagingCrops.add(fruit.getName());

        fruit = new Fruit("Red Mushroom", 75, false, -50);
        fruitTypes.put(fruit.getName(), fruit);
        summerForagingCrops.add(fruit.getName());

        fruit = new Fruit("Spice Berry", 80, true, 25);
        fruitTypes.put(fruit.getName(), fruit);
        summerForagingCrops.add(fruit.getName());

        fruit = new Fruit("Sweet Pea", 50, false, 0);
        fruitTypes.put(fruit.getName(), fruit);
        summerForagingCrops.add(fruit.getName());

        fruit = new Fruit("Blackberry", 25, true, 25);
        fruitTypes.put(fruit.getName(), fruit);
        fallForagingCrops.add(fruit.getName());

        fruit = new Fruit("Chanterelle", 160, true, 75);
        fruitTypes.put(fruit.getName(), fruit);
        fallForagingCrops.add(fruit.getName());

        fruit = new Fruit("Hazelnut", 40, true, 38);
        fruitTypes.put(fruit.getName(), fruit);
        fallForagingCrops.add(fruit.getName());

        fruit = new Fruit("Purple Mushroom", 90, true, 30);
        fruitTypes.put(fruit.getName(), fruit);
        fallForagingCrops.add(fruit.getName());

        fruit = new Fruit("Wild Plum", 80, true, 25);
        fruitTypes.put(fruit.getName(), fruit);
        fallForagingCrops.add(fruit.getName());

        fruit = new Fruit("Crocus", 60, false, 0);
        fruitTypes.put(fruit.getName(), fruit);
        winterForagingCrops.add(fruit.getName());

        fruit = new Fruit("Crystal Fruit", 150, true, 63);
        fruitTypes.put(fruit.getName(), fruit);
        winterForagingCrops.add(fruit.getName());

        fruit = new Fruit("Holly", 80, true, -37);
        fruitTypes.put(fruit.getName(), fruit);
        winterForagingCrops.add(fruit.getName());

        fruit = new Fruit("Snow Yam", 100, true, 30);
        fruitTypes.put(fruit.getName(), fruit);
        winterForagingCrops.add(fruit.getName());

        fruit = new Fruit("Winter Root", 70, true, 25);
        fruitTypes.put(fruit.getName(), fruit);
        winterForagingCrops.add(fruit.getName());

        foragingCropsType.put(Season.SPRING, springForagingCrops);
        foragingCropsType.put(Season.SUMMER, summerForagingCrops);
        foragingCropsType.put(Season.FALL, fallForagingCrops);
        foragingCropsType.put(Season.WINTER, winterForagingCrops);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("fruits.json")) {
            gson.toJson(fruitTypes, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter file = new FileWriter("foragingCrops.json")) {
            gson.toJson(foragingCropsType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Symbol getSymbol() {
        return null;
    }
}
