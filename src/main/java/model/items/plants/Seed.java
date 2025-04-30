package model.items.plants;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.enums.Season;
import model.items.Item;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
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

        HashMap<Season, ArrayList<Seed>> foragingSeeds = new HashMap<Season, ArrayList<Seed>>();

        ArrayList<Seed> springSeeds = new ArrayList<Seed>();
        ArrayList<Seed> summerSeeds = new ArrayList<Seed>();
        ArrayList<Seed> fallSeeds = new ArrayList<Seed>();
        ArrayList<Seed> winterSeeds = new ArrayList<Seed>();

        Seed seed;

        seed = new Seed("","");
        seedTypes.put("",seed);

    }

}
