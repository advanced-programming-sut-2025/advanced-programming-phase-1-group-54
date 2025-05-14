package model.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class AnimalHousePrototype implements Cloneable{
    // Needed to add location to AnimalHouse
    private static final HashMap<String, AnimalHousePrototype> animalHouses;

    static {
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("animalHouses.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, AnimalHousePrototype>>(){}.getType();
        animalHouses = gson.fromJson(file,type);
    }

    public static AnimalHousePrototype getAnimalHousePrototype(String ItemName){
        AnimalHousePrototype animalHousePrototype = animalHouses.get(ItemName);
        if(animalHousePrototype == null){
            return null;
        }
        else{
            return animalHousePrototype.clone();
        }
    }

    private final String name;
    private final ArrayList<String> animals;
    private final int size;


    public AnimalHousePrototype(String name, ArrayList<String> animals, int size) {
        this.name = name;
        this.animals = animals;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAnimals() {
        return animals;
    }

    public int getSize() {
        return size;
    }

    @Override
    protected AnimalHousePrototype clone(){
        try {
            return (AnimalHousePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void writeToJson(){

        HashMap<String, AnimalHousePrototype> animalHousesType = new HashMap<String, AnimalHousePrototype>();

        AnimalHousePrototype animalHousePrototype;

        animalHousePrototype = new AnimalHousePrototype("Coop",new ArrayList<>(){{
            add("Hen");
        }},4);
        animalHousesType.put(animalHousePrototype.getName(), animalHousePrototype);

        animalHousePrototype = new AnimalHousePrototype("Big Coop",new ArrayList<>(){{
            add("Hen");
            add("Duck");
            add("Dinosaur");
        }},8);
        animalHousesType.put(animalHousePrototype.getName(), animalHousePrototype);

        animalHousePrototype = new AnimalHousePrototype("Deluxe Coop",new ArrayList<>(){{
            add("Hen");
            add("Duck");
            add("Rabbit");
        }},12);
        animalHousesType.put(animalHousePrototype.getName(), animalHousePrototype);

        animalHousePrototype = new AnimalHousePrototype("Barn",new ArrayList<>(){{
            add("Cow");
        }},4);
        animalHousesType.put(animalHousePrototype.getName(), animalHousePrototype);

        animalHousePrototype = new AnimalHousePrototype("Big Barn",new ArrayList<>(){{
            add("Cow");
            add("Goat");
        }},8);
        animalHousesType.put(animalHousePrototype.getName(), animalHousePrototype);

        animalHousePrototype = new AnimalHousePrototype("Deluxe Barn",new ArrayList<>(){{
            add("Cow");
            add("Goat");
            add("Sheep");
            add("Pig");
        }},12);
        animalHousesType.put(animalHousePrototype.getName(), animalHousePrototype);



        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("animalHouses.json")){
            gson.toJson(animalHousesType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
