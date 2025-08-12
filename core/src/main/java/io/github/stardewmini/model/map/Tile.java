package io.github.stardewmini.model.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.controller.game.PlantsController;
import io.github.stardewmini.model.DailyUpdate;
import io.github.stardewmini.model.Placeable;
import io.github.stardewmini.model.enums.Color;
import io.github.stardewmini.model.enums.Feature;
import io.github.stardewmini.model.enums.Symbol;
import io.github.stardewmini.model.items.plants.Plant;

import java.util.ArrayList;

public class Tile implements DailyUpdate {
    private static final int SIZE = 60;

    private final Location location;

    private final Sprite sprite = new Sprite();

    private Placeable thingOnTile = null;
    private final ArrayList<Feature> features = new ArrayList<>();

    public static int getSize() {
        return SIZE;
    }

    public Tile(Location location) {
        this.location = location;

        sprite.setSize(SIZE, SIZE);
    }

    public Location getLocation() {
        return location;
    }

    public boolean isWalkable() {
        if (thingOnTile == null)
            return true;

        if (thingOnTile instanceof Building building)
            return building.canEnter() && getTop().isWalkable();

        return false;
    }

    public Tile getTop() {
        if (thingOnTile instanceof Building building) {
            Tile out = building.getTileAt(location.delta(building.getLocation()));
            return out;
        }
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
            out = getThingOnTile().getSprite().toString();

        if (features.contains(Feature.WATER))
            out = Symbol.LAKE.toString();

        else if (features.contains(Feature.PROTECTED))
            out = Color.YELLOW_BACKGROUND + out + Color.DEFAULT;

        return out;
    }

    @Override
    public void nextDayUpdate() {
        if (thingOnTile instanceof Plant plant) {
            if (this.hasFeature(Feature.AUTO_WATER))
                PlantsController.giveWater(this.getLocation());

            if (plant.isDead()) {
                setThingOnTile(null);
            }
        }
    }

    public void setSpritePosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
