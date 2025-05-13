package model.items.tools;

import model.enums.Feature;
import model.enums.SkillType;
import model.map.Tile;

public class Hoe extends Tool {
    @Override
    public boolean checkSuccess(Tile tile) {
        if (tile.getThingOnTile() != null)
            return false;

        return !tile.hasFeature(Feature.PLOWED);
    }

    @Override
    public void use(BackPack backPack, Tile tile) {
        if (!checkSuccess(tile))
            return;

        tile.addFeature(Feature.PLOWED);
    }

    @Override
    public SkillType getSkillType() {
        return null;
    }
}
