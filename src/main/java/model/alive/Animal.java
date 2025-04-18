package model.alive;

import model.enums.AnimalProduce;
import model.map.Location;
import model.Result;
import model.enums.AnimalType;

public class Animal extends Character {
    protected String name;

    protected Player owner;

    protected AnimalType animalType;

    protected int friendshipLevel;

    protected boolean hungry;

    protected Location location;

    private AnimalProduce produces;

    protected int cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public int getFriendshipLevel() {
        return friendshipLevel;
    }

    public void setFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel = friendshipLevel;
    }

    public boolean isHungry() {
        return hungry;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void makeProduce() {

    }

    public AnimalProduce getProduce() {
        return null;
    }
}
