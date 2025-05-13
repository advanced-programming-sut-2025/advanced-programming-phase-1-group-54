package model.Building;

import model.Placeable;
import model.map.Location;
import model.map.Map;
import model.map.Tile;

public abstract class Building implements Placeable {
    private final Location location;
    private final Map map;

    public Building(Location location, Map map) {
        this.location = location;
        this.map = map;
    }

    public Location getLocation() {
        return location;
    }

    public Tile getTileAt(Location location) {
        return map.getTileAt(location);
    }

    public Location getRandomLocation() {
        return map.getRandomLocation();
    }

    public boolean canEnter() {
        return true;
    }
}
