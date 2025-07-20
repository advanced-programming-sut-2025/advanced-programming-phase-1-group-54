package io.github.stardewmini.model.map;

/*
    each player has a farm, at the start player selects one of predefined farms;
 */

import io.github.stardewmini.model.DailyUpdate;
import io.github.stardewmini.model.enums.Feature;
import io.github.stardewmini.model.enums.Season;
import io.github.stardewmini.model.items.plants.Crop;
import io.github.stardewmini.model.items.plants.Fruit;
import io.github.stardewmini.model.items.plants.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Farm implements DailyUpdate {
    private static final int NUMBER_OF_ROWS = 35;
    private static final int NUMBER_OF_COLUMNS = 35;

    private final Location location;
    private final Map map;

    private final GreenHouse greenhouse;
    private final Cabin cabin;
    private final Quarry quarry;
    private final GenericWall[] lakes;

    private final ArrayList<AnimalHouse> animalHouses = new ArrayList<>();

    public static int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public static int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }

    public Farm(Location location, GreenHouse greenhouse, Cabin cabin, Quarry quarry, GenericWall[] lakes, Map map) {
        this.location = location;
        this.map = map;

        this.greenhouse = greenhouse;
        this.cabin = cabin;
        this.quarry = quarry;

        System.arraycopy(lakes, 0, this.lakes = new GenericWall[lakes.length], 0, lakes.length);
    }

    public Location getLocation() {
        return location;
    }

    public Tile getTileAt(Location location) {
        return map.getTileAt(location);
    }

    public GreenHouse getGreenhouse() {
        return greenhouse;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public Quarry getQuarry() {
        return quarry;
    }

    public GenericWall[] getLakes() {
        return lakes;
    }

    public ArrayList<AnimalHouse> getAnimalHouses() {
        return animalHouses;
    }

    public Location getRandomLocation() {
        return map.getRandomLocation();
    }

    public void thunderStrike(Location location) {
        Tile tile = getTileAt(location);
        if (tile == null || (tile.getThingOnTile() != null && tile.getThingOnTile() instanceof Building))
            return;

        tile.setThingOnTile(null);
    }

    public void foragingCrop(Season season) {
        for (int i = 0; i < Farm.getNumberOfRows(); i++) {
            for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                Tile tile = getTileAt(new Location(i, j));
                if (Math.random() <= 0.01 && tile.getThingOnTile() == null) {
                    tile.setThingOnTile(Fruit.getForagingCrop(season));
                }
            }
        }
    }

    public HashMap<Plant, Tile> getPlants() {
        HashMap<Plant, Tile> plants = new HashMap<>();
        for (int i = 0; i < Farm.getNumberOfRows(); i++) {
            for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                Tile tile = getTileAt(new Location(i, j)).getTop();
                if (tile.getThingOnTile() instanceof Plant plant)
                    plants.put(plant, tile);
            }
        }

        return plants;
    }

    public void crowAttack() {
        HashMap<Plant, Tile> plants = getPlants();

        Random rand = new Random();
        int numberOfCrows = plants.size() / 16;

        for (int i = 0; i < numberOfCrows; i++) {
            if (rand.nextInt(4) == 0) {
                List<Plant> keys = new ArrayList<>(plants.keySet());
                Plant plant = keys.get(rand.nextInt(keys.size()));
                Tile tile = plants.get(plant);

                if (!tile.getFeatures().contains(Feature.PROTECTED)) {
                    tile.setThingOnTile(null);
                    if (plant instanceof Crop crop && crop.getGiantDirection() != null) {
                        for (int k = 0; k < 3; k++) {
                            tile = getTileAt(tile.getLocation().
                                    getLocationAt(crop.getGiantDirection()));
                            crop = (Crop) tile.getThingOnTile();
                            tile.setThingOnTile(null);
                        }
                    }
                }
            }
        }
    }

    public void rain() {
        for (int i = 0; i < Farm.getNumberOfRows(); i++) {
            for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                Tile tile = getTileAt(new Location(i, j));
                if (tile.getThingOnTile() instanceof Plant plant)
                    plant.setWatered(true);
            }
        }
    }

    @Override
    public void nextDayUpdate() {
        crowAttack();
    }
}
