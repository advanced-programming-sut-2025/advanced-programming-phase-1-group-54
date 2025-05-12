package model.map;

/*
    each player has a farm, at the start player selects one of predefined farms;
 */

import model.Building.Building;
import model.Building.Cabin;
import model.Building.GreenHouse;
import model.DailyUpdate;
import model.alive.Player;

public class Farm implements DailyUpdate {
    private static final int NUMBER_OF_ROWS = 25;
    private static final int NUMBER_OF_COLUMNS = 25;
    private static final int NUMBER_OF_THUNDER = 3;

    private final Location location;
    private final Map map;

    private final Player owner;

    private final GreenHouse greenhouse;
    private final Cabin cabin;
    private final Quarry quarry;
    private final Lake lake;

    public static int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public static int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }

    public Farm(Player owner, Location location, GreenHouse greenhouse, Cabin cabin, Quarry quarry, Lake lake, Map map) {
        this.owner = owner;
        this.location = location;
        this.map = map;

        this.greenhouse = greenhouse;
        this.cabin = cabin;
        this.quarry = quarry;
        this.lake = lake;
    }

    public Player getOwner() {
        return owner;
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

    public Lake getLake() {
        return lake;
    }

    public Location getRandomLocation() {
        return new Location((int)(Math.random() * getNumberOfRows()), (int) (Math.random() * getNumberOfColumns()));
    }

    public void destroy(Location location) {
        Tile tile = getTileAt(location);
        if (tile == null || (tile.getThingOnTile() != null && tile.getThingOnTile() instanceof Building))
            return;

        tile.setThingOnTile(null);
    }

    @Override
    public void nextDayUpdate() {
        for (int i = 0; i < NUMBER_OF_THUNDER; i++) {
            destroy(location);
        }
    }
}
