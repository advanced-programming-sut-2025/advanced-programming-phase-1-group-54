package model.alive;

import model.items.Item;
import model.map.Location;

import java.util.ArrayList;

public class Animal extends Item {
    private String animalName;
    protected final int sellPrice;
    private final int numberOfProducingDays;
    private int daysAfterProducing;

    private final ArrayList<String> animalProducesNames;

    protected Player owner;

    protected int friendshipLevel;

    private boolean hungry;
    private boolean caressed;
    private boolean produced;
    protected Location location;

    public Animal(String name, boolean isEdible, int sellPrice, int numberOfProducingDays,
                  ArrayList<String> animalProducesNames) {
        super(name, isEdible);
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

    public boolean isProduced() {
        return produced;
    }

    public Location getLocation() {
        return location;
    }



    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void increaseNumberOfDaysAfterProducing(int numberOfDaysAfterProducing) {
        this.daysAfterProducing += numberOfDaysAfterProducing;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel = friendshipLevel;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public void setCaressed(boolean caressed) {
        this.caressed = caressed;
    }

    public void setProduced(boolean produced) {
        this.produced = produced;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


}
