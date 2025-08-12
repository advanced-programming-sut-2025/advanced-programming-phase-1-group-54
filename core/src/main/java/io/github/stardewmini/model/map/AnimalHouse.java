package io.github.stardewmini.model.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.enums.AnimalHouseType;
import io.github.stardewmini.model.enums.Symbol;

import java.util.ArrayList;

public class AnimalHouse extends Building {

    public static AnimalHouse BuildAnimalHouse(AnimalHousePrototype prototype, Location location) {
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        Tile[][] tiles = new Tile[prototype.getNumberOfRows()][prototype.getNumberOfColumns()];
        Map map = new Map(prototype.getNumberOfRows(), prototype.getNumberOfColumns(),tiles);
        AnimalHouse output = new AnimalHouse(prototype, location, map);
        for(int x = 0;x < prototype.getNumberOfRows(); x++) {
            for(int y = 0; y < prototype.getNumberOfColumns(); y++) {
                tiles[location.row() + x][location.column() + y].getTop().getSprite().
                    setRegion(gameAssetManager.getBuilding("floor"));
            }
        }
        return output;
    }

    private final AnimalHouseType animalHouseType;
    private final String name;
    private final ArrayList<String> animals;
    private final int size;
    private int numberOfAnimals;

    private AnimalHouse(AnimalHousePrototype prototype, Location location,Map map) {
        super(location, map);
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
