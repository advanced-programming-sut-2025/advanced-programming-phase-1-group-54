package model.items.tools;

import model.enums.Feature;
import model.map.Tile;

public class Hoe extends Tool {

    @Override
    public boolean use(BackPack backPack, Tile tile) {
        if (tile.getThingOnTile() != null) {
            return false;
        }

        if (tile.hasFeature(Feature.PLOWED)) {
            return false;
        }

        tile.addFeature(Feature.PLOWED);
        return true;
    }
}
