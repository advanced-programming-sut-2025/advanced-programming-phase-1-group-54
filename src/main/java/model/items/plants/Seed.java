package model.items.plants;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.items.Item;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;

public class Seed extends Item {

    public static HashMap<String,Seed> seeds;

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
    }

    private final String name;
    private final String plant;

    public Seed(String name, String plant) {
        this.name = name;
        this.plant = plant;
    }

    public String getName() {
        return name;
    }

    public String getPlant() {
        return plant;
    }

}
