package model.lives;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controller.Game.CommonGameController;
import model.DailyUpdate;
import model.map.AnimalHouse;
import model.enums.ProduceQuality;
import model.enums.Symbol;
import model.items.AnimalProduce;
import model.map.Farm;
import model.map.Location;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Animal extends Live implements Cloneable,DailyUpdate {

    private static final HashMap<String,Animal> animals;


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

    private static final int MAX_FRIENDSHIP = 1000;

    public static Animal getAnimal(String name) {
        Animal animal = animals.get(name);
        if(animal == null){
            return null;
        }
        else{
            return animal.clone();
        }
    }


    private final String animalName;
    protected final int sellPrice;
    private final int numberOfProducingDays;
    private int daysAfterProducing;

    private final ArrayList<String> animalProducesNames;

    private Player owner;

    private int friendshipLevel;
    private boolean hungry;
    private boolean caressed;
    private boolean goneOut;
    private AnimalProduce produce;
    protected Location location;

    public Animal(String animalName, int sellPrice, int numberOfProducingDays, ArrayList<String> animalProducesNames) {
        this.animalName = animalName;
        this.sellPrice = sellPrice;
        this.numberOfProducingDays = numberOfProducingDays;
        this.animalProducesNames = animalProducesNames;
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

    public boolean isGoneOut() {
        return goneOut;
    }

    public AnimalProduce getProduce() {
        return produce;
    }

    public Location getLocation() {
        return location;
    }

    public void increaseDaysAfterProducing(int daysAfterProducing) {
        this.daysAfterProducing += daysAfterProducing;
    }

    public void setDaysAfterProducing(int daysAfterProducing) {
        this.daysAfterProducing = daysAfterProducing;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel = Math.min(friendshipLevel, MAX_FRIENDSHIP);
    }

    public void increaseFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel += friendshipLevel;
        if(this.friendshipLevel > MAX_FRIENDSHIP){
            this.friendshipLevel = MAX_FRIENDSHIP;
        }
    }

    public void decreaseFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel -= friendshipLevel;
        if(this.friendshipLevel < 0){
            this.friendshipLevel = 0;
        }
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public void setCaressed(boolean caressed) {
        this.caressed = caressed;
    }

    public void setGoneOut(boolean goneOut) {
        this.goneOut = goneOut;
    }

    public void setProduce(AnimalProduce produce) {
        this.produce = produce;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void nextDayUpdate(){
        if(this.isGoneOut()){
            this.setHungry(false);
            this.increaseFriendshipLevel(8);
        }

        if(! this.isCaressed() && this.getFriendshipLevel() > 200){
            this.decreaseFriendshipLevel(10);
        }

        Farm farm = getOwner().getFarm();
        Location location = this.getLocation();
        if(location == null){
            this.decreaseFriendshipLevel(20);
        }
        else if(! (farm.getTileAt(location.delta(farm.getLocation())).getThingOnTile() instanceof AnimalHouse)){
            this.decreaseFriendshipLevel(20);
        }

        if(this.isHungry()){
            this.decreaseFriendshipLevel(20);
            this.setDaysAfterProducing(0);
        }
        else {
            this.increaseDaysAfterProducing(1);
        }

        if(this.getAnimalName().equals("Pig")){
            if(this.isGoneOut()){
                AnimalProduce animalProduce = producing();
                setProduceQuality(animalProduce);
                this.setProduce(animalProduce);
            }
        }
        else if(this.getDaysAfterProducing() == this.getNumberOfProducingDays()){
            this.setDaysAfterProducing(0);

            AnimalProduce animalProduce = producing();
            setProduceQuality(animalProduce);
            this.setProduce(animalProduce);
        }
    }

    private AnimalProduce producing(){

        int friendship = this.getFriendshipLevel();
        if(this.getAnimalProducesNames().size() > 1 && friendship > 100){
            double probability = (this.getFriendshipLevel() + (150  * (Math.random() + 0.5)))/1500;
            if(Math.random() <= probability){
                return AnimalProduce.getAnimalProduce(this.getAnimalProducesNames().get(1));
            }
            else {
                return AnimalProduce.getAnimalProduce(this.getAnimalProducesNames().getFirst());
            }
        }
        else {
            return AnimalProduce.getAnimalProduce(this.getAnimalProducesNames().getFirst());
        }

    }

    private void setProduceQuality(AnimalProduce produce){
        double qualityNumber = ((double) this.getFriendshipLevel() / 1000) * (0.5 + 0.5 * Math.random());
        ProduceQuality quality = ProduceQuality.giveQuality(qualityNumber);
        produce.setQuality(quality);
    }


    public static void writeToJson(){

        HashMap<String,Animal> animalsType = new HashMap<>();

        Animal animal;


        animal = new Animal("Hen",800,1,new ArrayList<>(){{
            add("Egg");
            add("Large Egg");
        }});
        animalsType.put(animal.getAnimalName(), animal);

        animal = new Animal("Duck",1200,2,new ArrayList<>(){{
            add("Duck Egg");
        }});
        animalsType.put(animal.getAnimalName(), animal);

        animal = new Animal("Rabbit",8000,4,new ArrayList<>(){{
            add("Wool");
            add("Rabbit Leg");
        }});
        animalsType.put(animal.getAnimalName(), animal);

        animal = new Animal("Dinosaur",14000,7,new ArrayList<>(){{
            add("Dinosaur Egg");
        }});
        animalsType.put(animal.getAnimalName(), animal);

        animal = new Animal("Cow",1500,1,new ArrayList<>(){{
            add("Milk");
            add("Large Milk");
        }});
        animalsType.put(animal.getAnimalName(), animal);

        animal = new Animal("Goat",4000,2,new ArrayList<>(){{
            add("Goat Milk");
            add("Large Goat Milk");
        }});
        animalsType.put(animal.getAnimalName(), animal);

        animal = new Animal("Sheep",8000,3,new ArrayList<>(){{
            add("Wool");
        }});
        animalsType.put(animal.getAnimalName(), animal);

        animal = new Animal("Pig",16000,-1,new ArrayList<>(){{
            add("Truffle");
        }});
        animalsType.put(animal.getAnimalName(), animal);




        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("animals.json")){
            gson.toJson(animalsType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    protected Animal clone() {
        try {
            return (Animal) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.ANIMAL;
    }

    @Override
    public String toString() {
        return getAnimalName() + " " + getName();
    }
}
