package model;

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

public class AnimalHouse implements Cloneable{

    private static final HashMap<String, AnimalHouse> animalHouses;

    static {
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("animalHouses.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, AnimalHouse>>(){}.getType();
        animalHouses = gson.fromJson(file,type);
    }

    public static AnimalHouse getAnimalHouse(String ItemName){
        AnimalHouse animalHouse = animalHouses.get(ItemName);
        if(animalHouse == null){
            return null;
        }
        else{
            return animalHouse.clone();
        }
    }

    private final String Name;
    private final ArrayList<String> animals;
    private final int size;
    private int numberOfAnimals;


    public AnimalHouse(String name, ArrayList<String> animals, int size) {
        Name = name;
        this.animals = animals;
        this.size = size;
        this.numberOfAnimals = 0;
    }

    public String getName() {
        return Name;
    }

    public ArrayList<String> getAnimals() {
        return animals;
    }

    public int getSize() {
        return size;
    }

    public int getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public void increaseNumberOfAnimals(int numberOfAnimals) {
        this.numberOfAnimals += numberOfAnimals;
    }

    public void decreaseNumberOfAnimals(int numberOfAnimals) {
        this.numberOfAnimals -= numberOfAnimals;
    }

    @Override
    protected AnimalHouse clone(){
        try {
            return (AnimalHouse) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void writeToJson(){

        HashMap<String,AnimalHouse> animalHousesType = new HashMap<String,AnimalHouse>();

        AnimalHouse animalHouse;

        animalHouse = new AnimalHouse("Coop",new ArrayList<>(){{
            add("Hen");
        }},4);
        animalHousesType.put(animalHouse.getName(), animalHouse);

        animalHouse = new AnimalHouse("Big Coop",new ArrayList<>(){{
            add("Hen");
            add("Duck");
            add("Dinosaur");
        }},8);
        animalHousesType.put(animalHouse.getName(), animalHouse);

        animalHouse = new AnimalHouse("Deluxe Coop",new ArrayList<>(){{
            add("Hen");
            add("Duck");
            add("Rabbit");
        }},12);
        animalHousesType.put(animalHouse.getName(), animalHouse);

        animalHouse = new AnimalHouse("Barn",new ArrayList<>(){{
            add("Cow");
        }},4);
        animalHousesType.put(animalHouse.getName(), animalHouse);

        animalHouse = new AnimalHouse("Big Barn",new ArrayList<>(){{
            add("Cow");
            add("Goat");
        }},8);
        animalHousesType.put(animalHouse.getName(), animalHouse);

        animalHouse = new AnimalHouse("Deluxe Barn",new ArrayList<>(){{
            add("Cow");
            add("Goat");
            add("Sheep");
            add("Pig");
        }},12);
        animalHousesType.put(animalHouse.getName(), animalHouse);



        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("animalHouses.json")){
            gson.toJson(animalHousesType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
