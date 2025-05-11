package model.map;

/*
    each player has a farm, at the start player selects one of predefined farms;
 */

import model.Building.Cabin;
import model.Building.GreenHouse;
import model.alive.Player;

public class Farm {
    private static final int NUMBER_OF_ROWS = 25;
    private static final int NUMBER_OF_COLUMNS = 25;

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
}
