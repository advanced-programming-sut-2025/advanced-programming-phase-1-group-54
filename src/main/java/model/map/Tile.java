package model.map;

import model.Placeable;
import model.Walkable;

public class Tile {
    private Placeable thingOnTile;
    private boolean isProtected = false;

    public boolean isWalkable() {
        return thingOnTile == null || thingOnTile instanceof Walkable;
    }

    public Placeable getThingOnTile() {
        return thingOnTile;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setThingOnTile(Placeable thingOnTile) {
        this.thingOnTile = thingOnTile;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }
}
