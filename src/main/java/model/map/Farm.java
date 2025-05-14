package model.map;

/*
    each player has a farm, at the start player selects one of predefined farms;
 */

import model.DailyUpdate;
import model.enums.Feature;
import model.items.plants.Crop;
import model.items.plants.Fruit;
import model.items.plants.Seed;

public class Farm implements DailyUpdate {
    private static final int NUMBER_OF_ROWS = 25;
    private static final int NUMBER_OF_COLUMNS = 25;

    private final Location location;
    private final Map map;

    private final GreenHouse greenhouse;
    private final Cabin cabin;
    private final Quarry quarry;
    private final Lake[] lakes;

    public static int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public static int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }

    public Farm(Location location, GreenHouse greenhouse, Cabin cabin, Quarry quarry, Lake[] lakes, Map map) {
        this.location = location;
        this.map = map;

        this.greenhouse = greenhouse;
        this.cabin = cabin;
        this.quarry = quarry;
        this.lakes = lakes;
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

    public Lake[] getLakes() {
        return lakes;
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

    public void rain() {
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                Location location = new Location(i, j);
                Tile tile = getTileAt(location);

                if (tile.getThingOnTile() != null && tile.getThingOnTile() instanceof Building)
                    continue;

                tile.addFeature(Feature.WATERED);
            }
        }
    }

    public void dry() {
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                Location location = new Location(i, j);
                Tile tile = getTileAt(location);

                if (tile.getThingOnTile() != null && tile.getThingOnTile() instanceof Building)
                    continue;

                tile.removeFeature(Feature.WATERED);
            }
        }
    }

    @Override
    public void nextDayUpdate() {
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                Location location = new Location(i, j);
                Tile tile = getTileAt(location);
                tile.nextDayUpdate();
            }
        }
    }
}
