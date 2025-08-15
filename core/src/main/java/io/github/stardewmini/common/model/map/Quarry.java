package io.github.stardewmini.common.model.map;

import io.github.stardewmini.common.model.DailyUpdate;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.items.Material;

import java.util.Random;

public class Quarry extends Building implements DailyUpdate {
    private static final int NUMBER_OF_FORAGING_MATERIALS = 3;

    public Quarry(Area area) {
        super(area.upperLeftLocation(), new Map(area.numberOfRows(), area.numberOfColumns()));

        this.getSprite().setRegion(GameAssetManager.getInstance().getBuilding("Quarry Floor"));
    }


    public void foragingMaterial(Random rand) {
        for (int i = 0; i < NUMBER_OF_FORAGING_MATERIALS; i++) {
            Tile tile = getTileAt(getRandomLocation());
            if (tile.getThingOnTile() == null) {
                tile.setThingOnTile(Material.getForagingMaterial(rand));
            }
        }
    }

    @Override
    public void nextDayUpdate() {
//        foragingMaterial();
    }
}
