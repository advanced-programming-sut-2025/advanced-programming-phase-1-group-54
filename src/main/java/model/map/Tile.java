package model.map;

import model.Placeable;

public class Tile {
    private Placeable thingOnTile;
    private boolean isProtected = false;

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
