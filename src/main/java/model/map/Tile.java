package model.map;

import model.Building.Building;
import model.Placeable;
import model.enums.Color;
import model.enums.Symbol;
import model.enums.Feature;

import java.util.ArrayList;

public class Tile {
    private Location location;

    private Placeable thingOnTile = null;
    private final ArrayList<Feature> features = new ArrayList<>();



    public boolean isWalkable() {
        if (thingOnTile == null)
            return true;

        if (thingOnTile instanceof Building building)
            return building.canEnter() && getTop().isWalkable();

        return false;
    }

    public Tile getTop() {
        if (thingOnTile != null && thingOnTile instanceof Building building)
            return building.getTileAt(location.delta(building.getLocation()));

        return this;
    }

    public Placeable getThingOnTile() {
        return thingOnTile;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void addFeature(Feature feature) {
        features.add(feature);
    }

    public boolean hasFeature(Feature feature) {
        return features.contains(feature);
    }

    public void setThingOnTile(Placeable thingOnTile) {
        this.thingOnTile = thingOnTile;
    }

    @Override
    public String toString() {
        String out;
        if (thingOnTile == null)
            out = Symbol.EMPTY.toString();
        else
            out = getThingOnTile().getSymbol().toString();

        if (features.contains(Feature.WATERED))
            out = Color.BLUE_BACKGROUND + out + Color.DEFAULT;

        else if (features.contains(Feature.PROTECTED))
            out = Color.YELLOW_BACKGROUND + out + Color.DEFAULT;

        return out;
    }
}
