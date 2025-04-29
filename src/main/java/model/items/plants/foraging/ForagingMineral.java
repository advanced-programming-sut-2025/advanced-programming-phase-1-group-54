package model.items.plants.foraging;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Placeable;
import model.items.Item;
import model.items.plants.Tree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ForagingMineral extends Item implements Placeable {

    static HashMap<String ,ForagingMineral> foragingMinerals;

    static{
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("foragingMinerals.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, ForagingMineral>>(){}.getType();
        foragingMinerals = gson.fromJson(file,type);
        System.out.println(foragingMinerals.size());
    }


    private final String name;
    private final int sellPrice;

    public ForagingMineral(String name, int sellPrice) {
        this.name = name;
        this.sellPrice = sellPrice;
    }

    public String getName() {
        return name;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public static void writeToJson(){

        HashMap<String,ForagingMineral> foragingMineralTypes = new HashMap<String,ForagingMineral>();

        ForagingMineral foragingMineral;

        foragingMineral = new ForagingMineral("Quartz", 25);
        foragingMineralTypes.put("Quartz", foragingMineral);

        foragingMineral = new ForagingMineral("Earth Crystal", 50);
        foragingMineralTypes.put("Earth Crystal", foragingMineral);

        foragingMineral = new ForagingMineral("Frozen Tear", 75);
        foragingMineralTypes.put("Frozen Tear", foragingMineral);

        foragingMineral = new ForagingMineral("Fire Quartz", 100);
        foragingMineralTypes.put("Fire Quartz", foragingMineral);

        foragingMineral = new ForagingMineral("Emerald", 250);
        foragingMineralTypes.put("Emerald", foragingMineral);

        foragingMineral = new ForagingMineral("Aquamarine", 180);
        foragingMineralTypes.put("Aquamarine", foragingMineral);

        foragingMineral = new ForagingMineral("Ruby", 250);
        foragingMineralTypes.put("Ruby", foragingMineral);

        foragingMineral = new ForagingMineral("Amethyst", 100);
        foragingMineralTypes.put("Amethyst", foragingMineral);

        foragingMineral = new ForagingMineral("Topaz", 80);
        foragingMineralTypes.put("Topaz", foragingMineral);

        foragingMineral = new ForagingMineral("Jade", 200);
        foragingMineralTypes.put("Jade", foragingMineral);

        foragingMineral = new ForagingMineral("Diamond", 750);
        foragingMineralTypes.put("Diamond", foragingMineral);

        foragingMineral = new ForagingMineral("Prismatic Shard", 2000);
        foragingMineralTypes.put("Prismatic Shard", foragingMineral);

        foragingMineral = new ForagingMineral("Copper", 5);
        foragingMineralTypes.put("Copper", foragingMineral);

        foragingMineral = new ForagingMineral("Iron", 10);
        foragingMineralTypes.put("Iron", foragingMineral);

        foragingMineral = new ForagingMineral("Gold", 25);
        foragingMineralTypes.put("Gold", foragingMineral);

        foragingMineral = new ForagingMineral("Iriduim", 100);
        foragingMineralTypes.put("Iriduim", foragingMineral);

        foragingMineral = new ForagingMineral("Coal", 15);
        foragingMineralTypes.put("Coal", foragingMineral);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("foragingMinerals.json")){
            gson.toJson(foragingMineralTypes, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
