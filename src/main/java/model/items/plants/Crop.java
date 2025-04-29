package model.items.plants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.enums.Season;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class Crop extends Plant {
    public static HashMap<String,Crop> crops;

    static{
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("crops.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String,Crop>>(){}.getType();
        crops = gson.fromJson(file,type);
        System.out.println(crops.size());
    }

    private final boolean oneTime;
    private final boolean canBecomeGiant;

    public Crop(String name, String source, String fruit, int[] stages, int totalHarvestTime, int regrowthTime,
                Season[] seasons, boolean oneTime, boolean canBecomeGiant) {
        super(name, source, fruit, stages, totalHarvestTime, regrowthTime, seasons);
        this.oneTime = oneTime;
        this.canBecomeGiant = canBecomeGiant;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public boolean isCanBecomeGiant() {
        return canBecomeGiant;
    }

    public static void writeToJson(){
        HashMap<String ,Crop> cropsType = new HashMap<String,Crop>();

        Crop crop;

        crop = new Crop(
                "Blue Jazz Crop",
                "Jazz Seeds",
                "Blue Jazz",
                new int[] {1, 2, 2, 2},
                7,
                0,
                new Season[]{Season.SPRING},
                true,
                false
        );
        cropsType.put("Blue Jazz Crop", crop);

        crop = new Crop(
                "Carrot Crop",
                "Carrot Seeds",
                "Carrot",
                new int[] {1, 2, 2},
                10,
                0,
                new Season[]{Season.SPRING},
                true,
                false
        );
        cropsType.put("Carrot Crop", crop);

        crop = new Crop(
                "Cauliflower Crop",
                "Cauliflower Seeds",
                "Cauliflower",
                new int[] {1, 2, 4, 4, 1},
                12,
                2,
                new Season[]{Season.SPRING},
                true,
                false
        );
        cropsType.put("Cauliflower Crop", crop);

        crop = new Crop(
                "Coffee Bean Crop",
                "Coffee Bean",
                "Coffee Bean",
                new int[] {1, 2, 2, 3, 2},
                10,
                2,
                new Season[]{Season.SPRING, Season.SUMMER},
                false,
                false
        );
        cropsType.put("Coffee Bean Crop", crop);

        crop = new Crop(
                "Garlic Crop",
                "Garlic Seeds",
                "Garlic",
                new int[] {1, 2, 2},
                4,
                3,
                new Season[]{Season.SPRING},
                true,
                false
        );
        cropsType.put("Garlic Crop", crop);

        crop = new Crop(
                "Green Bean Crop",
                "Bean Starter",
                "Green Bean",
                new int[] {1, 1, 1, 3, 2},
                10,
                3,
                new Season[]{Season.SPRING},
                false,
                true
        );
        cropsType.put("Green Bean Crop", crop);

        crop = new Crop(
                "Kale Crop",
                "Kale Seeds",
                "Kale",
                new int[] {1, 2, 2, 2},
                6,
                3,
                new Season[]{Season.SPRING},
                true,
                false
        );
        cropsType.put("Kale Crop", crop);

        crop = new Crop(
                "Parsnip Crop",
                "Parsnip Seeds",
                "Parsnip",
                new int[] {1, 2, 2},
                4,
                0,
                new Season[]{Season.SPRING},
                true,
                false
        );
        cropsType.put("Parsnip Crop", crop);

        crop = new Crop(
                "Potato Crop",
                "Potato Seeds",
                "Potato",
                new int[] {1, 2, 2},
                6,
                0,
                new Season[]{Season.SPRING},
                true,
                false
        );
        cropsType.put("Potato Crop", crop);

        crop = new Crop(
                "Rhubarb Crop",
                "Rhubarb Seeds",
                "Rhubarb",
                new int[] {1, 2, 2},
                13,
                0,
                new Season[]{Season.SPRING},
                true,
                false
        );
        cropsType.put("Rhubarb Crop", crop);

        crop = new Crop(
                "Strawberry Crop",
                "Strawberry Seeds",
                "Strawberry",
                new int[] {1, 2, 2},
                8,
                0,
                new Season[]{Season.SPRING},
                true,
                false
        );
        cropsType.put("Strawberry Crop", crop);

        crop = new Crop(
                "Tulip Crop",
                "Tulip Bulb",
                "Tulip",
                new int[] {1, 2, 2},
                6,
                0,
                new Season[]{Season.SPRING},
                true,
                false
        );
        cropsType.put("Tulip Crop", crop);

        crop = new Crop(
                "Unmilled Rice Crop",
                "Rice Shoot",
                "Unmilled Rice",
                new int[] {1, 2, 2},
                6,
                0,
                new Season[]{Season.SPRING},
                true,
                false
        );
        cropsType.put("Unmilled Rice Crop", crop);

        crop = new Crop(
                "Blueberry Crop",
                "Blueberry Seeds",
                "Blueberry",
                new int[] {1, 2, 3, 3},
                13,
                0,
                new Season[]{Season.SUMMER},
                true,
                false
        );
        cropsType.put("Blueberry Crop", crop);

        crop = new Crop(
                "Corn Crop",
                "Corn Seeds",
                "Corn",
                new int[] {1, 2, 2, 3},
                14,
                0,
                new Season[]{Season.SUMMER, Season.FALL},
                true,
                false
        );
        cropsType.put("Corn Crop", crop);

        crop = new Crop(
                "Hops Crop",
                "Hops Starter",
                "Hops",
                new int[] {1, 2, 3},
                11,
                3,
                new Season[]{Season.SUMMER},
                false,
                true
        );
        cropsType.put("Hops Crop", crop);

        crop = new Crop(
                "Hot Pepper Crop",
                "Pepper Seeds",
                "Hot Pepper",
                new int[] {1, 2, 2, 2},
                5,
                3,
                new Season[]{Season.SUMMER},
                true,
                false
        );
        cropsType.put("Hot Pepper Crop", crop);

        crop = new Crop(
                "Melon Crop",
                "Melon Seeds",
                "Melon",
                new int[] {1, 2, 3, 4},
                12,
                0,
                new Season[]{Season.SUMMER},
                true,
                false
        );
        cropsType.put("Melon Crop", crop);

        crop = new Crop(
                "Poppy Crop",
                "Poppy Seeds",
                "Poppy",
                new int[] {1, 2, 2, 3},
                7,
                0,
                new Season[]{Season.SUMMER},
                true,
                false
        );
        cropsType.put("Poppy Crop", crop);

        crop = new Crop(
                "Radish Crop",
                "Radish Seeds",
                "Radish",
                new int[] {1, 2, 2},
                6,
                0,
                new Season[]{Season.SUMMER},
                true,
                false
        );
        cropsType.put("Radish Crop", crop);

        crop = new Crop(
                "Red Cabbage Crop",
                "Red Cabbage Seeds",
                "Red Cabbage",
                new int[] {1, 2, 3, 3},
                9,
                0,
                new Season[]{Season.SUMMER},
                true,
                false
        );
        cropsType.put("Red Cabbage Crop", crop);

        crop = new Crop(
                "Starfruit Crop",
                "Starfruit Seeds",
                "Starfruit",
                new int[] {1, 2, 3, 3},
                13,
                0,
                new Season[]{Season.SUMMER},
                true,
                false
        );
        cropsType.put("Starfruit Crop", crop);

        crop = new Crop(
                "Spangle Crop",
                "Spangle Seeds",
                "Summer Spangle",
                new int[] {1, 2, 3},
                8,
                0,
                new Season[]{Season.SUMMER},
                true,
                false
        );
        cropsType.put("Spangle Crop", crop);

        crop = new Crop(
                "Summer Squash Crop",
                "Squash Seeds",
                "Summer Squash",
                new int[] {1, 2, 2, 2},
                7,
                0,
                new Season[]{Season.SUMMER, Season.FALL},
                true,
                false
        );
        cropsType.put("Summer Squash Crop", crop);

        crop = new Crop(
                "Sunflower Crop",
                "Sunflower Seeds",
                "Sunflower",
                new int[] {1, 2, 3},
                8,
                0,
                new Season[]{Season.SUMMER, Season.FALL},
                true,
                false
        );
        cropsType.put("Sunflower Crop", crop);

        crop = new Crop(
                "Tomato Crop",
                "Tomato Seeds",
                "Tomato",
                new int[] {1, 2, 2, 2},
                11,
                3,
                new Season[]{Season.SUMMER},
                true,
                false
        );
        cropsType.put("Tomato Crop", crop);

        crop = new Crop(
                "Wheat Crop",
                "Wheat Seeds",
                "Wheat",
                new int[] {1, 1, 2},
                4,
                0,
                new Season[]{Season.SUMMER, Season.FALL},
                true,
                false
        );
        cropsType.put("Wheat Crop", crop);

        crop = new Crop(
                "Amaranth Crop",
                "Amaranth Seeds",
                "Amaranth",
                new int[] {1, 2, 2},
                7,
                0,
                new Season[]{Season.FALL},
                true,
                false
        );
        cropsType.put("Amaranth Crop", crop);

        crop = new Crop(
                "Artichoke Crop",
                "Artichoke Seeds",
                "Artichoke",
                new int[] {1, 2, 2, 2},
                8,
                0,
                new Season[]{Season.FALL},
                true,
                false
        );
        cropsType.put("Artichoke Crop", crop);

        crop = new Crop(
                "Beet Crop",
                "Beet Seeds",
                "Beet",
                new int[] {1, 2, 2},
                6,
                0,
                new Season[]{Season.FALL},
                true,
                false
        );
        cropsType.put("Beet Crop", crop);

        crop = new Crop(
                "Bok Choy Crop",
                "Bok Choy Seeds",
                "Bok Choy",
                new int[] {1, 2, 2},
                4,
                0,
                new Season[]{Season.FALL},
                true,
                false
        );
        cropsType.put("Bok Choy Crop", crop);

        crop = new Crop(
                "Broccoli Crop",
                "Broccoli Seeds",
                "Broccoli",
                new int[] {1, 2, 2},
                6,
                0,
                new Season[]{Season.FALL},
                true,
                false
        );
        cropsType.put("Broccoli Crop", crop);

        crop = new Crop(
                "Cranberries Crop",
                "Cranberry Seeds",
                "Cranberries",
                new int[] {1, 2, 2, 1},
                7,
                5,
                new Season[]{Season.FALL},
                false,
                false
        );
        cropsType.put("Cranberries Crop", crop);

        crop = new Crop(
                "Eggplant Crop",
                "Eggplant Seeds",
                "Eggplant",
                new int[] {1, 2, 2, 2},
                5,
                3,
                new Season[]{Season.FALL},
                false,
                false
        );
        cropsType.put("Eggplant Crop", crop);

        crop = new Crop(
                "Fairy Rose Crop",
                "Fairy Seeds",
                "Fairy Rose",
                new int[] {1, 2, 3, 3},
                12,
                0,
                new Season[]{Season.FALL},
                true,
                false
        );
        cropsType.put("Fairy Rose Crop", crop);

        crop = new Crop(
                "Grape Crop",
                "Grape Starter",
                "Grape",
                new int[] {1, 2, 2, 2},
                10,
                3,
                new Season[]{Season.FALL},
                false,
                true
        );
        cropsType.put("Grape Crop", crop);

        crop = new Crop(
                "Pumpkin Crop",
                "Pumpkin Seeds",
                "Pumpkin",
                new int[] {1, 2, 3, 3},
                13,
                0,
                new Season[]{Season.FALL},
                true,
                false
        );
        cropsType.put("Pumpkin Crop", crop);

        crop = new Crop(
                "Yam Crop",
                "Yam Seeds",
                "Yam",
                new int[] {1, 2, 3, 2},
                10,
                0,
                new Season[]{Season.FALL},
                true,
                false
        );
        cropsType.put("Yam Crop", crop);

        crop = new Crop(
                "Sweet Gem Crop",
                "Rare Seed",
                "Sweet Gem Berry",
                new int[] {1, 2, 4, 3},
                24,
                0,
                new Season[]{Season.FALL},
                true,
                false
        );
        cropsType.put("Sweet Gem Crop", crop);

        crop = new Crop(
                "Powdermelon Crop",
                "Powdermelon Seeds",
                "Powdermelon",
                new int[] {1,2,1,2,1},
                7,
                0,
                new Season[]{Season.WINTER},
                true,
                true
        );
        cropsType.put("Powdermelon Crop", crop);

        crop = new Crop(
                "Ancient Fruit Crop",
                "Ancient Seeds",
                "Ancient Fruit",
                new int[] {2, 2, 2, 2, 5},
                28,
                7,
                new Season[]{Season.SPRING, Season.SUMMER, Season.FALL},
                false,
                true
        );
        cropsType.put("Ancient Fruit Crop", crop);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("crops.json")){
            gson.toJson(cropsType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
