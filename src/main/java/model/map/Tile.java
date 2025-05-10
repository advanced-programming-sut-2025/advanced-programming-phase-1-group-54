package model.map;

import model.Placeable;
import model.Walkable;
import model.enums.Feature;

import java.util.ArrayList;

public class Tile {
    private Placeable thingOnTile;
    private ArrayList<Feature> features;

    public boolean isWalkable() {
        return thingOnTile == null || thingOnTile instanceof Walkable;
    }

    public Placeable getThingOnTile() {
        return thingOnTile;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setThingOnTile(Placeable thingOnTile) {
        this.thingOnTile = thingOnTile;
    }

}
