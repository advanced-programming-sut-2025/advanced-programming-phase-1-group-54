package model;

public class Tile {
    private final Location location;

    private Placeable thingOnTile;

    public Tile(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public Placeable getThingOnTile() {
        return thingOnTile;
    }

    public void setThingOnTile(Placeable thingOnTile) {
        this.thingOnTile = thingOnTile;
    }
}
