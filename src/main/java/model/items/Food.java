package model.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.enums.SkillType;
import model.items.plants.Tree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class Food extends Item implements Cloneable {

    private final static HashMap<String, Food> foods;

    static {
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("foods.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, Food>>() {
        }.getType();
        foods = gson.fromJson(file, type);
    }

    public static Food getFood(String ItemName) {
        Food food = foods.get(ItemName);
        if (food == null) {
            return null;
        } else {
            return food.clone();
        }
    }

    private final int energy;
    private final SkillType skillType;
    private final int energyBuff;
    private final int buffHours;

    public Food(String name, int energy, int sellPrice, SkillType skillType, int energyBuff, int buffHours) {
        super(name, true, sellPrice);
        this.energy = energy;
        this.skillType = skillType;
        this.energyBuff = energyBuff;
        this.buffHours = buffHours;
    }

    public int getEnergy() {
        return energy;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public int getEnergyBuff() {
        return energyBuff;
    }

    public int getBuffHours() {
        return buffHours;
    }


    @Override
    protected Food clone() {
        try {
            return (Food) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Food food) {
            return this.getName().equals(food.getName());
        }
        return false;
    }

    public static void writeToJson() {

        HashMap<String, Food> foodsType = new HashMap<>();

        Food food;

        food = new Food("Fried Egg"
                , 50
                , 35
                , null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Baked Fish"
                , 75
                , 100
                , null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Salad"
                , 113
                , 110
                , null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Omelet"
                , 100
                , 125
                , null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Pumpkin Pie"
                , 225
                , 385
                , null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Spaghetti"
                , 75
                , 120
                , null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Pizza"
                , 150
                , 300
                , null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Tortilla"
                , 50
                , 50
                , null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Maki Roll"
                , 100
                , 220
                , null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Triple Shot Espresso"
                , 200
                , 450
                , null,
                100,
                5);
        foodsType.put(food.getName(), food);

        food = new Food("Cookie"
                , 90
                , 140
                , null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Hash Browns",
                90,
                120,
                SkillType.FARMING,
                0,
                5);
        foodsType.put(food.getName(), food);

        food = new Food("Pancakes",
                90,
                80,
                SkillType.FORAGING,
                0,
                11);
        foodsType.put(food.getName(), food);

        food = new Food("Fruit Salad",
                263,
                450,
                null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Red Plate",
                240,
                400,
                null,
                50,
                3);
        foodsType.put(food.getName(), food);

        food = new Food("Bread",
                50,
                60,
                null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Salmon Dinner",
                125,
                300,
                null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Vegetable Medley",
                165,
                120,
                null,
                0,
                0);
        foodsType.put(food.getName(), food);

        food = new Food("Farmer's Lunch",
                200,
                150,
                SkillType.FARMING,
                0,
                5);
        foodsType.put(food.getName(), food);


        food = new Food("Survival Burger",
                125,
                180,
                SkillType.FORAGING,
                0,
                5);
        foodsType.put(food.getName(), food);

        food = new Food("Dish O' The Sea",
                150,
                220,
                SkillType.FISHING,
                0,
                5);
        foodsType.put(food.getName(), food);

        food = new Food("Seaform Pudding",
                175,
                300,
                SkillType.FISHING,
                0,
                10);
        foodsType.put(food.getName(), food);

        food = new Food("Miner's Treat",
                125,
                200,
                SkillType.MINING,
                0,
                5);
        foodsType.put(food.getName(), food);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("foods.json")) {
            gson.toJson(foodsType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
