package model.items.plants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.enums.Season;
import model.items.Item;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Seed extends Item {

    public static HashMap<String,Seed> seeds;
    public static HashMap<Season,ArrayList<String>> foragingSeeds;
    public static HashMap<Season,ArrayList<String>> mixedSeeds;
    public static ArrayList<String> foragingTrees;

    static{
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("seeds.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String,Seed>>(){}.getType();
        seeds = gson.fromJson(file,type);
        System.out.println(seeds.size());

        try {
            file = new FileReader("foragingSeeds.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        type = new TypeToken<HashMap<Season,ArrayList<String>>>(){}.getType();
        foragingSeeds = gson.fromJson(file,type);

        try {
            file = new FileReader("mixedSeeds.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        type = new TypeToken<HashMap<Season,ArrayList<String>>>(){}.getType();
        mixedSeeds = gson.fromJson(file,type);

        try {
            file = new FileReader("foragingTrees.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        type = new TypeToken<ArrayList<String>>(){}.getType();
        foragingTrees = gson.fromJson(file,type);

    }

    private final String plant;

    public Seed(String name, String plant) {
        super(name);
        this.plant = plant;
    }

    public String getPlant() {
        return plant;
    }

    public static void writeToJson(){
        HashMap<String ,Seed> seedTypes = new HashMap<String,Seed>();

        HashMap<Season, ArrayList<String>> foragingSeedsType = new HashMap<Season, ArrayList<String>>();

        ArrayList<String> springSeeds = new ArrayList<>();
        ArrayList<String> summerSeeds = new ArrayList<>();
        ArrayList<String> fallSeeds = new ArrayList<>();
        ArrayList<String> winterSeeds = new ArrayList<>();

        HashMap<Season,ArrayList<String>> mixedSeedsType = new HashMap<>();

        ArrayList<String> springMixedSeeds = new ArrayList<>();
        ArrayList<String> summerMixedSeeds = new ArrayList<>();
        ArrayList<String> fallMixedSeeds = new ArrayList<>();
        ArrayList<String> winterMixedSeeds = new ArrayList<>();

        ArrayList<String> foragingTreesType = new ArrayList<>();

        Seed seed;

        // spring Crop's Seeds

        seed = new Seed("Jazz Seeds","Jazz Crop");
        seedTypes.put("Jazz Seeds",seed);
        springSeeds.add(seed.getName());
        springMixedSeeds.add(seed.getName());

        seed = new Seed("Carrot Seeds","Carrot Crop");
        seedTypes.put("Carrot Seeds",seed);
        springSeeds.add(seed.getName());

        seed = new Seed("Cauliflower Seeds","Cauliflower Crop");
        seedTypes.put("Cauliflower Seeds",seed);
        springSeeds.add(seed.getName());
        springMixedSeeds.add(seed.getName());

        seed = new Seed("Coffee Bean","Coffee Bean Crop");
        seedTypes.put("Coffee Bean",seed);
        springSeeds.add(seed.getName());

        seed = new Seed("Garlic Seeds","Garlic Crop");
        seedTypes.put("Garlic Seeds",seed);
        springSeeds.add(seed.getName());

        seed = new Seed("Bean Starter","Green Bean Crop");
        seedTypes.put("Bean Starter",seed);
        springSeeds.add(seed.getName());

        seed = new Seed("Kale Seeds","Kale Crop");
        seedTypes.put("Kale Seeds",seed);
        springSeeds.add(seed.getName());

        seed = new Seed("Parsnip Seeds","Parsnip Crop");
        seedTypes.put("Parsnip Seeds",seed);
        springSeeds.add(seed.getName());
        springMixedSeeds.add(seed.getName());

        seed = new Seed("Potato Seeds","Potato Crop");
        seedTypes.put("Potato Seeds",seed);
        springSeeds.add(seed.getName());
        springMixedSeeds.add(seed.getName());

        seed = new Seed("Rhubarb Seeds","Rhubarb Crop");
        seedTypes.put("Rhubarb Seeds",seed);
        springSeeds.add(seed.getName());

        seed = new Seed("Strawberry Seeds","Strawberry Crop");
        seedTypes.put("Strawberry Seeds",seed);
        springSeeds.add(seed.getName());

        seed = new Seed("Tulip Bulb","Tulip Crop");
        seedTypes.put("Tulip Bulb",seed);
        springSeeds.add(seed.getName());
        springMixedSeeds.add(seed.getName());

        seed = new Seed("Rice Shoot","Unmilled Rice Crop");
        seedTypes.put("Rice Shoot",seed);
        springSeeds.add(seed.getName());

        // summer Crop's Seeds

        seed = new Seed("Blueberry Seeds","Blueberry Crop");
        seedTypes.put("Blueberry Seeds",seed);
        summerSeeds.add(seed.getName());

        seed = new Seed("Corn Seeds","Corn Crop");
        seedTypes.put("Corn Seeds",seed);
        summerSeeds.add(seed.getName());
        summerMixedSeeds.add(seed.getName());
        fallMixedSeeds.add(seed.getName());

        seed = new Seed("Hops Starter","Hops Crop");
        seedTypes.put("Hops Starter",seed);
        summerSeeds.add(seed.getName());

        seed = new Seed("Pepper Seeds","Hot Pepper Crop");
        seedTypes.put("Pepper Seeds",seed);
        summerSeeds.add(seed.getName());
        summerMixedSeeds.add(seed.getName());

        seed = new Seed("Melon Seeds","Melon Crop");
        seedTypes.put("Melon Seeds",seed);
        summerSeeds.add(seed.getName());

        seed = new Seed("Poppy Seeds","Poppy Crop");
        seedTypes.put("Poppy Seeds",seed);
        summerSeeds.add(seed.getName());
        summerMixedSeeds.add(seed.getName());

        seed = new Seed("Radish Seeds","Radish Crop");
        seedTypes.put("Radish Seeds",seed);
        summerSeeds.add(seed.getName());
        summerMixedSeeds.add(seed.getName());

        seed = new Seed("Red Cabbage Seeds","Red Cabbage Crop");
        seedTypes.put("Red Cabbage Seeds",seed);
        summerSeeds.add(seed.getName());

        seed = new Seed("Starfruit Seeds","Starfruit Crop");
        seedTypes.put("Starfruit Seeds",seed);
        summerSeeds.add(seed.getName());

        seed = new Seed("Spangle Seeds","Spangle Crop");
        seedTypes.put("Spangle Seeds",seed);
        summerSeeds.add(seed.getName());
        summerMixedSeeds.add(seed.getName());

        seed = new Seed("Summer Squash Seeds","Summer Squash Crop");
        seedTypes.put("Summer Squash Seeds",seed);
        summerSeeds.add(seed.getName());

        seed = new Seed("Sunflower Seeds","Sunflower Crop");
        seedTypes.put("Sunflower Seeds",seed);
        summerSeeds.add(seed.getName());
        summerMixedSeeds.add(seed.getName());
        fallMixedSeeds.add(seed.getName());

        seed = new Seed("Tomato Seeds","Tomato Crop");
        seedTypes.put("Tomato Seeds",seed);
        summerSeeds.add(seed.getName());

        seed = new Seed("Wheat Seeds","Wheat Crop");
        seedTypes.put("Wheat Seeds",seed);
        summerSeeds.add(seed.getName());
        summerMixedSeeds.add(seed.getName());

        //fall Crop's Seeds

        seed = new Seed("Amaranth Seeds","Amaranth Crop");
        seedTypes.put("Amaranth Seeds",seed);
        fallSeeds.add(seed.getName());

        seed = new Seed("Artichoke Seeds","Artichoke Seeds");
        seedTypes.put("Artichoke Seeds",seed);
        fallSeeds.add(seed.getName());
        fallMixedSeeds.add(seed.getName());

        seed = new Seed("Beet Seeds","Beet Crop");
        seedTypes.put("Beet Seeds",seed);
        fallSeeds.add(seed.getName());

        seed = new Seed("Bok Choy Seeds","Bok Choy Crop");
        seedTypes.put("Bok Choy Seeds",seed);
        fallSeeds.add(seed.getName());

        seed = new Seed("Broccoli Seeds","Broccoli Crop");
        seedTypes.put("Broccoli Seeds",seed);
        fallSeeds.add(seed.getName());

        seed = new Seed("Cranberry Seeds","Cranberry Crop");
        seedTypes.put("Cranberry Seeds",seed);
        fallSeeds.add(seed.getName());

        seed = new Seed("Eggplant Seeds","Eggplant Crop");
        seedTypes.put("Eggplant Seeds",seed);
        fallSeeds.add(seed.getName());
        fallMixedSeeds.add(seed.getName());

        seed = new Seed("Fairy Seeds","Fairy Rose Crop");
        seedTypes.put("Fairy Seeds",seed);
        fallSeeds.add(seed.getName());
        fallMixedSeeds.add(seed.getName());

        seed = new Seed("Grape Starter","Grape Crop");
        seedTypes.put("Grape Starter",seed);
        fallSeeds.add(seed.getName());

        seed = new Seed("Pumpkin Seeds","Pumpkin Crop");
        seedTypes.put("Pumpkin Seeds",seed);
        fallSeeds.add(seed.getName());
        fallMixedSeeds.add(seed.getName());

        seed = new Seed("Yam Seeds","Yam Crop");
        seedTypes.put("Yam Seeds",seed);
        fallSeeds.add(seed.getName());

        seed = new Seed("Rare Seed","Sweet Gem Crop");
        seedTypes.put("Rare Seed",seed);
        fallSeeds.add(seed.getName());

        // winter Crop's Seeds

        seed = new Seed("Powdermelon Seeds","Powdermelon Crop");
        seedTypes.put("Powdermelon Seeds",seed);
        winterSeeds.add(seed.getName());
        winterMixedSeeds.add(seed.getName());

        seed = new Seed("Ancient Seeds","Ancient Fruit Crop");
        seedTypes.put("Ancient Seeds",seed);
        springSeeds.add(seed.getName());
        summerSeeds.add(seed.getName());
        fallSeeds.add(seed.getName());
        winterSeeds.add(seed.getName());

        seed = new Seed("Mixed Seeds",null);
        seedTypes.put("Mixed Seeds",seed);

        // tree's Seeds

        seed = new Seed("Apricot Sapling","Apricot Tree");
        seedTypes.put("Apricot Sapling",seed);

        seed = new Seed("Cherry Sapling","Cherry Tree");
        seedTypes.put("Cherry Sapling",seed);

        seed = new Seed("Banana Sapling","Banana Tree");
        seedTypes.put("Banana Sapling",seed);

        seed = new Seed("Mango Sapling","Mango Tree");
        seedTypes.put("Mango Sapling",seed);

        seed = new Seed("Orange Sapling","Orange Tree");
        seedTypes.put("Orange Sapling",seed);

        seed = new Seed("Peach Sapling","Peach Tree");
        seedTypes.put("Peach Sapling",seed);

        seed = new Seed("Apple Sapling","Apple Tree");
        seedTypes.put("Apple Sapling",seed);

        seed = new Seed("Pomegranate Sapling","Pomegranate Tree");
        seedTypes.put("Pomegranate Sapling",seed);

        seed = new Seed("Acorns","Oak Tree");
        seedTypes.put("Acorns",seed);
        foragingTreesType.add(seed.getName());

        seed = new Seed("Maple Seeds","Maple Tree");
        seedTypes.put("Maple Seeds",seed);
        foragingTreesType.add(seed.getName());

        seed = new Seed("Pine Cones","Pine Tree");
        seedTypes.put("Pine Cones",seed);
        foragingTreesType.add(seed.getName());

        seed = new Seed("Mahogany Seeds","Mahogany Tree");
        seedTypes.put("Mahogany Seeds",seed);
        foragingTreesType.add(seed.getName());

        seed = new Seed("Mushroom Tree Seeds","Mushroom Tree");
        seedTypes.put("Mushroom Tree Seeds",seed);
        foragingTreesType.add(seed.getName());

        seed = new Seed("Mystic Tree Seeds","Mystic Tree");
        seedTypes.put("Mystic Tree Seeds",seed);



        foragingSeedsType.put(Season.SPRING,springSeeds);
        foragingSeedsType.put(Season.SUMMER,summerSeeds);
        foragingSeedsType.put(Season.FALL,fallSeeds);
        foragingSeedsType.put(Season.WINTER,winterSeeds);

        mixedSeedsType.put(Season.SPRING,springSeeds);
        mixedSeedsType.put(Season.SUMMER,summerSeeds);
        mixedSeedsType.put(Season.FALL,fallSeeds);
        mixedSeedsType.put(Season.WINTER,winterSeeds);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("seeds.json")){
            gson.toJson(seedTypes, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter file = new FileWriter("foragingSeeds.json")){
            gson.toJson(foragingSeedsType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter file = new FileWriter("mixedSeeds.json")){
            gson.toJson(mixedSeedsType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter file = new FileWriter("foragingTrees.json")){
            gson.toJson(foragingTreesType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
