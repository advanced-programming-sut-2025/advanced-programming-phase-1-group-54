package io.github.stardewmini.common.model.map;

import io.github.stardewmini.client.Renderers.GameAssetManager;
import io.github.stardewmini.common.model.enums.AnimalHouseType;

import java.util.ArrayList;

public class AnimalHouse extends Building {

    private final AnimalHouseType animalHouseType;
    private final String name;
    private final ArrayList<String> animals;
    private final int size;
    private int numberOfAnimals;

    public AnimalHouse(AnimalHousePrototype prototype, Location location) {
        super(location, new Map(prototype.getNumberOfRows(), prototype.getNumberOfColumns()));
        this.animalHouseType = prototype.getAnimalHouseType();
        this.name = prototype.getName();
        this.animals = prototype.getAnimals();
        this.size = prototype.getSize();
        this.numberOfAnimals = 0;

        this.getSprite().setRegion(GameAssetManager.getInstance().getBuilding(prototype.getName()));
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

    public int getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public void increaseNumberOfAnimals(int numberOfAnimals) {
        this.numberOfAnimals += numberOfAnimals;
    }

    public void decreaseNumberOfAnimals(int numberOfAnimals) {
        this.numberOfAnimals -= numberOfAnimals;
    }

}
