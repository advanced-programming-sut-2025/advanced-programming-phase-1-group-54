package model.items.plants;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;

public class Fruit {

    public static HashMap<String, Fruit> fruits;

    static{
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("fruits.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String,Fruit>>(){}.getType();
        fruits = gson.fromJson(file,type);
        System.out.println(fruits.size());
    }

    private final String name;
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energy;

    public Fruit(String name, int baseSellPrice, boolean isEdible, int energy) {
        this.name = name;
        this.baseSellPrice = baseSellPrice;
        this.isEdible = isEdible;
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public int getEnergy() {
        return energy;
    }
}
