package model.alive;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.items.AnimalProduce;
import model.items.Food;
import model.items.Item;
import model.map.Location;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Animal {

    static final HashMap<String,Animal> animals;


    static {
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("animals.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, Animal>>(){}.getType();
        animals = gson.fromJson(file,type);
    }

    static final int maxFriendship = 1000;

    private final String name;
    private String animalName;
    protected final int sellPrice;
    private final int numberOfProducingDays;
    private int daysAfterProducing;

    private final ArrayList<String> animalProducesNames;

    private Player owner;

    private int friendshipLevel;
    private boolean hungry;
    private boolean caressed;
    private AnimalProduce produce;
    protected Location location;

    public Animal(String name, int sellPrice, int numberOfProducingDays, ArrayList<String> animalProducesNames) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.numberOfProducingDays = numberOfProducingDays;
        this.animalProducesNames = animalProducesNames;
    }

    public String getName() {
        return name;
    }

    public String getAnimalName() {
        return animalName;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getNumberOfProducingDays() {
        return numberOfProducingDays;
    }

    public int getDaysAfterProducing() {
        return daysAfterProducing;
    }

    public ArrayList<String> getAnimalProducesNames() {
        return animalProducesNames;
    }

    public Player getOwner() {
        return owner;
    }

    public int getFriendshipLevel() {
        return friendshipLevel;
    }

    public boolean isHungry() {
        return hungry;
    }

    public boolean isCaressed() {
        return caressed;
    }

    public AnimalProduce getProduce() {
        return produce;
    }

    public Location getLocation() {
        return location;
    }



    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel = Math.min(friendshipLevel,maxFriendship);
    }

    public void increaseFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel += friendshipLevel;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public void setCaressed(boolean caressed) {
        this.caressed = caressed;
    }

    public void setProduce(AnimalProduce produce) {
        this.produce = produce;
    }

    public void setLocation(Location location) {
        this.location = location;
    }




    public static void writeToJson(){

        HashMap<String,Animal> animalsType = new HashMap<>();

        Animal animal;

        // TODO

        animal = new Animal("Hen",800,1,new ArrayList<>(){{
            add("Egg");
            add("Large Egg");
        }});
        animalsType.put(animal.getName(), animal);

        animal = new Animal("Duck",1200,2,new ArrayList<>(){{
            add("Duck Egg");
        }});
        animalsType.put(animal.getName(), animal);

        animal = new Animal("Rabbit",8000,4,new ArrayList<>(){{
            add("Wool");
            add("Rabbit Leg");
        }});
        animalsType.put(animal.getName(), animal);

        animal = new Animal("Dinosaur",14000,7,new ArrayList<>(){{
            add("Dinosaur Egg");
        }});
        animalsType.put(animal.getName(), animal);

        animal = new Animal("Cow",1500,1,new ArrayList<>(){{
            add("Milk");
            add("Large Milk");
        }});
        animalsType.put(animal.getName(), animal);

        animal = new Animal("Goat",4000,2,new ArrayList<>(){{
            add("Goat Milk");
            add("Large Goat Milk");
        }});
        animalsType.put(animal.getName(), animal);

        animal = new Animal("Sheep",8000,3,new ArrayList<>(){{
            add("Wool");
        }});
        animalsType.put(animal.getName(), animal);

        animal = new Animal("Pig",16000,-1,new ArrayList<>(){{
            add("Truffle");
        }});
        animalsType.put(animal.getName(), animal);




        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("animals.json")){
            gson.toJson(animalsType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
