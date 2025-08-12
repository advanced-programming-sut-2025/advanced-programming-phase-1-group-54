package io.github.stardewmini.model.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.model.DailyUpdate;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.enums.Symbol;
import io.github.stardewmini.model.items.Material;

public class Quarry extends Building implements DailyUpdate {
    private static final int NUMBER_OF_FORAGING_MATERIALS = 3;

    public Quarry(Area area) {
        super(area.upperLeftLocation(), new Map(area.numberOfRows(), area.numberOfColumns()));

        this.getSprite().setRegion(GameAssetManager.getInstance().getBuilding("Quarry Floor"));
    }


    public void foragingMaterial() {
        for (int i = 0; i < NUMBER_OF_FORAGING_MATERIALS; i++) {
            Tile tile = getTileAt(getRandomLocation());
            if (tile.getThingOnTile() == null) {
                tile.setThingOnTile(Material.getForagingMaterial());
            }
        }
    }

    @Override
    public void nextDayUpdate() {
        foragingMaterial();
    }
}
