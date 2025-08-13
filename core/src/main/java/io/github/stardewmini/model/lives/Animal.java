package io.github.stardewmini.model.lives;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.stardewmini.model.DailyUpdate;
import io.github.stardewmini.model.enums.ProduceQuality;
import io.github.stardewmini.model.enums.Symbol;
import io.github.stardewmini.model.items.AnimalProduce;
import io.github.stardewmini.model.map.AnimalHouse;
import io.github.stardewmini.model.map.Farm;
import io.github.stardewmini.model.map.Location;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class Animal extends Live implements Cloneable,DailyUpdate {

    private static final HashMap<String,Animal> animals;


    static {
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader(Objects.requireNonNull(Animal.class.getClassLoader().getResource("animals.json")).getFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, Animal>>(){}.getType();
        animals = gson.fromJson(file,type);
        for(Animal animal : animals.values()){
            animal.sprite = new Sprite();
        }
    }

    private static final int MAX_FRIENDSHIP = 1000;

    public static Animal getAnimal(String name) {
        Animal animal = animals.get(name);
        if(animal == null){
            return null;
        }
        else{
            animal.setSprite(new Sprite());
            return animal.clone();
        }
    }

    public static Set<String> getAnimalsList(){
        return new HashSet<>(animals.keySet());
    }


    private final String animalName;
    protected final int sellPrice;
    private final int numberOfProducingDays;
    private int daysAfterProducing;

    private final ArrayList<String> animalProducesNames;

    private Player owner;

    private int friendshipLevel;
    private boolean fed;
    private boolean caressed;
    private boolean goneOut;
    private AnimalProduce produce;
    protected Location location;
    private Sprite sprite;
    private float eatTime;
    private float walkTime;
    private float petTime;

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

    public boolean isFed() {
        return fed;
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

    public float getEatTime() {
        return eatTime;
    }

    public float getWalkTime() {
        return walkTime;
    }

    public float getPetTime() {
        return petTime;
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

    public void setFed(boolean fed) {
        this.fed = fed;
    }

    public void setCaressed(boolean caressed) {
        this.caressed = caressed;
    }

    public void setGoneOut(boolean goneOut) {
        this.goneOut = goneOut;
        if(goneOut){
            this.fed = true;
        }
    }

    public void setProduce(AnimalProduce produce) {
        this.produce = produce;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setEatTime(float eatTime) {
        this.eatTime = eatTime;
    }

    public void setWalkTime(float walkTime) {
        this.walkTime = walkTime;
    }

    public void setPetTime(float petTime) {
        this.petTime = petTime;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void nextDayUpdate(){
        if(this.isGoneOut()){
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

        if(this.isFed()){
            this.increaseDaysAfterProducing(1);
        }
        else {
            this.decreaseFriendshipLevel(20);
            this.setDaysAfterProducing(0);
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

        this.fed = false;
        this.caressed = false;
        this.goneOut = false;

    }

    private AnimalProduce producing(){

        int friendship = this.getFriendshipLevel();
        if(this.getAnimalProducesNames().size() > 1 && friendship > 100){
            double probability = (friendship + (150  * (Math.random() + 0.5)))/1500;
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
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public String toString() {
        return getAnimalName() + " " + getName();
    }
}
