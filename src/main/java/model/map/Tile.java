package model.map;

import controller.Game.PlantsController;
import model.DailyUpdate;
import model.Placeable;
import model.enums.Color;
import model.enums.Symbol;
import model.enums.Feature;
import model.items.plants.Plant;

import java.util.ArrayList;

public class Tile implements DailyUpdate {
    private Location location;

    private Placeable thingOnTile = null;
    private final ArrayList<Feature> features = new ArrayList<>();

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public boolean isWalkable() {
        if (thingOnTile == null)
            return true;

        if (thingOnTile instanceof Building building)
            return building.canEnter() && getTop().isWalkable();

        return false;
    }

    public Tile getTop() {
        if (thingOnTile instanceof Building building)
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
        if (!hasFeature(feature))
            features.add(feature);
    }

    public void removeFeature(Feature feature) {
        features.remove(feature);
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

        if (features.contains(Feature.WATER))
            out = Symbol.LAKE.toString();

        else if (features.contains(Feature.PROTECTED))
            out = Color.YELLOW_BACKGROUND + out + Color.DEFAULT;

        return out;
    }

    @Override
    public void nextDayUpdate() {
        if (thingOnTile instanceof Building) {
            return;
        }

        if (thingOnTile instanceof Plant plant) {
            if (this.hasFeature(Feature.AUTO_WATER))
                PlantsController.giveWater(this.getLocation());

            if (plant.isDead()) {
                setThingOnTile(null);
            }
            else {
                plant.nextDayUpdate();
            }
        }
        else if (thingOnTile instanceof DailyUpdate updating) {
            updating.nextDayUpdate();
        }
    }
}
