package model.map;

import model.enums.AnimalHouseType;
import model.enums.Symbol;

import java.util.ArrayList;

public class AnimalHouse extends Building {
    private static AnimalHouseType getAnimalHouseType(AnimalHousePrototype prototype) {
        return prototype.getName().contains("Barn") ? AnimalHouseType.BARN : AnimalHouseType.COOP;
    }

    private static int getNumberOfRows(AnimalHouseType animalHouseType) {
        return animalHouseType.equals(AnimalHouseType.BARN) ? 4 : 3;
    }

    private static int getNumberOfColumns(AnimalHouseType animalHouseType) {
        return animalHouseType.equals(AnimalHouseType.BARN) ? 7 : 6;
    }


    private final AnimalHouseType animalHouseType;
    private final String name;
    private final ArrayList<String> animals;
    private final int size;
    private int numberOfAnimals;

    public AnimalHouse(AnimalHousePrototype prototype, Location location) {
        super(location, new Map(getNumberOfRows(getAnimalHouseType(prototype)),
                        getNumberOfColumns(getAnimalHouseType(prototype))));

        this.animalHouseType = getAnimalHouseType(prototype);
        this.name = prototype.getName();
        this.animals = prototype.getAnimals();
        this.size = prototype.getSize();
        this.numberOfAnimals = 0;
    }


    public int getNumberOfRows() {
        return getNumberOfRows(animalHouseType);
    }

    public int getNumberOfColumns() {
        return getNumberOfColumns(animalHouseType);
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

    @Override
    public Symbol getSymbol() {
        return (animalHouseType.equals(AnimalHouseType.BARN) ? Symbol.BARN : Symbol.COOP);
    }


}
