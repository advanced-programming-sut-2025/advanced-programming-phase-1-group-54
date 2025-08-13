package io.github.stardewmini.model.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.Placeable;

public abstract class Building implements Placeable {
    private final Sprite sprite = new Sprite();

    private final Location location;
    private final Map map;

    public Building(Location location, Map map) {
        this.location = location;
        this.map = map;

        this.sprite.setSize(map.getNumberOfColumns() * Tile.getSize(), map.getNumberOfRows() * Tile.getSize());

        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        for(int x = 0; x < map.getNumberOfRows(); x++) {
            for(int y = 0; y < map.getNumberOfColumns(); y++) {
                map.getTileAt(new Location(x,y)).getSprite().setRegion(gameAssetManager.getBuilding("floor"));
            }
        }
    }

    public Location getLocation() {
        return location;
    }

    public int getNumberOfRows() {
        return map.getNumberOfRows();
    }

    public int getNumberOfColumns() {
        return map.getNumberOfColumns();
    }

    public Tile getTileAt(Location location) {
        return map.getTileAt(location);
    }

    public Location getRandomLocation() {
        return map.getRandomLocation();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean canEnter() {
        return true;
    }
}
