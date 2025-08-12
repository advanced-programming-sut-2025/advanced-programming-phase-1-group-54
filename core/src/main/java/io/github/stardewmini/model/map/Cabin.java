package io.github.stardewmini.model.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.enums.Symbol;

public class Cabin extends Building {
    private final static int NUMBER_OF_ROWS = 4;
    private final static int NUMBER_OF_COLUMNS = 4;

    private final Refrigerator refrigerator = new Refrigerator();

    public Cabin(Location location) {
        super(location, new Map(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));

        this.getTileAt(getRandomLocation()).setThingOnTile(refrigerator);

        this.getSprite().setRegion(GameAssetManager.getInstance().getBuilding("House")); // TODO texture
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }
}
