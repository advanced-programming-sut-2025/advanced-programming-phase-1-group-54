package model.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Placeable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Material extends Item implements Placeable {

    public static HashMap<String, Material> materials;
    public static ArrayList<String> foragingMaterials;

    static {
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("materials.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, Material>>(){}.getType();
        materials = gson.fromJson(file,type);

        try {
            file = new FileReader("foragingMaterials.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        type = new TypeToken<ArrayList<String>>(){}.getType();
        foragingMaterials = gson.fromJson(file,type);
    }

    private final int baseSellPrice;

    public Material(String name, int baseSellPrice) {
        super(name,false);
        this.baseSellPrice = baseSellPrice;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public static void writeToJson(){

        HashMap<String, Material> materialsType = new HashMap<String, Material>();
        ArrayList<String> foragingMaterialsType = new ArrayList<>();

        Material material;

        material = new Material("Wood", 0);
        materialsType.put(material.getName(), material);

        material = new Material("Stone", 0);
        materialsType.put(material.getName(), material);

        material = new Material("Quartz", 25);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Earth Crystal", 50);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Frozen Tear", 75);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Fire Quartz", 100);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Emerald", 250);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Aquamarine", 180);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Ruby", 250);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Amethyst", 100);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Topaz", 80);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Jade", 200);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Diamond", 750);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Prismatic Shard", 2000);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Copper Ore", 0);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Iron Ore", 0);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Gold Ore", 0);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Iridium Ore", 0);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        material = new Material("Coal", 0);
        materialsType.put(material.getName(), material);
        foragingMaterialsType.add(material.getName());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("materials.json")){
            gson.toJson(materialsType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter file = new FileWriter("foragingMaterials.json")){
            gson.toJson(foragingMaterialsType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
