package model.items.plants;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.enums.Season;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;


public class Tree extends Plant implements Cloneable{

    public Tree(String name, String source, String fruit, int[] stages, int totalHarvestTime,
                int regrowthTime, Season[] seasons) {
        super(name, source, fruit, stages, totalHarvestTime, regrowthTime, seasons);
    }

    public static HashMap<String,Tree> trees;

    static{
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("trees.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String,Tree>>(){}.getType();
        trees = gson.fromJson(file,type);
    }

    @Override
    public String toString() {
        return
                "name : " + name +
                "\nsource : " + source +
                "\nfruit : " + fruit  +
                "\nstages : " + Arrays.toString(stages) +
                "\ntotalHarvestTime : " + totalHarvestTime +
                "\nregrowthTime : " + regrowthTime +
                "\nseasons : " + Arrays.toString(seasons);
    }

    @Override
    public Tree clone()  {
        try {
            return (Tree) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }

    }

    public static void writeToJson(){
        HashMap<String,Tree> treeTypes = new HashMap<String,Tree>();

        Tree tree;

        // میوه‌های فصل بهار
        tree = new Tree(
                "Apricot Tree",
                "Apricot Sapling",
                "Apricot",
                new int[]{7, 7, 7, 7},
                28,
                1,
                new Season[]{Season.SPRING}
        );
        treeTypes.put("Apricot Tree", tree);

        tree = new Tree(
                "Cherry Tree",
                "Cherry Sapling",
                "Cherry",
                new int[]{7, 7, 7, 7},
                28,
                1,
                new Season[]{Season.SPRING}
        );
        treeTypes.put("Cherry Tree", tree);

// میوه‌های فصل تابستان
        tree = new Tree(
                "Banana Tree",
                "Banana Sapling",
                "Banana",
                new int[]{7, 7, 7, 7},
                28,
                1,
                new Season[]{Season.SUMMER}
        );
        treeTypes.put("Banana Tree", tree);

        tree = new Tree(
                "Mango Tree",
                "Mango Sapling",
                "Mango",
                new int[]{7, 7, 7, 7},
                28,
                1,
                new Season[]{Season.SUMMER}
        );
        treeTypes.put("Mango Tree", tree);

        tree = new Tree(
                "Orange Tree",
                "Orange Sapling",
                "Orange",
                new int[]{7, 7, 7, 7},
                28,
                1,
                new Season[]{Season.SUMMER}
        );
        treeTypes.put("Orange Tree", tree);

        tree = new Tree(
                "Peach Tree",
                "Peach Sapling",
                "Peach",
                new int[]{7, 7, 7, 7},
                28,
                1,
                new Season[]{Season.SUMMER}
        );
        treeTypes.put("Peach Tree", tree);

// میوه‌های فصل پاییز
        tree = new Tree(
                "Apple Tree",
                "Apple Sapling",
                "Apple",
                new int[]{7, 7, 7, 7},
                28,
                1,
                new Season[]{Season.FALL}
        );
        treeTypes.put("Apple Tree", tree);

        tree = new Tree(
                "Pomegranate Tree",
                "Pomegranate Sapling",
                "Pomegranate",
                new int[]{7, 7, 7, 7},
                28,
                1,
                new Season[]{Season.FALL}
        );
        treeTypes.put("Pomegranate Tree", tree);

// محصولات خاص (همه فصل‌ها)
        tree = new Tree(
                "Oak Tree",
                "Oak Seed",
                "Oak Resin",
                new int[]{7, 7, 7, 7},
                28,
                7,
                new Season[]{Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER}
        );
        treeTypes.put("Oak Tree", tree);

        tree = new Tree(
                "Maple Tree",
                "Maple Seed",
                "Maple Syrup",
                new int[]{7, 7, 7, 7},
                28,
                9,
                new Season[]{Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER}
        );
        treeTypes.put("Maple Tree", tree);

        tree = new Tree(
                "Pine Tree",
                "Pine Cone",
                "Pine Tar",
                new int[]{7, 7, 7, 7},
                28,
                5,
                new Season[]{Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER}
        );
        treeTypes.put("Pine Tree", tree);

        tree = new Tree(
                "Sap Tree",
                "Sap Source",
                "Sap",
                new int[]{7, 7, 7, 7},
                28,
                1,
                new Season[]{Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER}
        );
        treeTypes.put("Sap Tree", tree);

        tree = new Tree(
                "Mushroom Tree",
                "Mushroom Spore",
                "Common Mushroom",
                new int[]{7, 7, 7, 7},
                28,
                1,
                new Season[]{Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER}
        );
        treeTypes.put("Mushroom Tree", tree);

        tree = new Tree(
                "Mystic Tree",
                "Mystic Seed",
                "Mystic Syrup",
                new int[]{7, 7, 7, 7},
                28,
                7,
                new Season[]{Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER}
        );
        treeTypes.put("Mystic Tree", tree);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("trees.json")){
            gson.toJson(treeTypes, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//    public Tree clone() {
//        Tree newTree = new Tree();
//
//        //newTree.name = this.name;
//        newTree.source = this.source;
//        newTree.stages = this.stages;
//        newTree.totalHarvestTime = this.totalHarvestTime;
//
//        return newTree;
//    }
}
