package model.items.tools;

import model.enums.Feature;
import model.enums.SkillType;
import model.map.Lake;
import model.map.Tile;

public class WateringCan extends Tool {
    private int currentWater;

    public int getCurrentWater() {
        return currentWater;
    }

    public void increaseWater() {
        currentWater++;
        if (currentWater > getCapacity())
            currentWater = getCapacity();
    }

    public void decreaseWater() {
        currentWater--;
        if (currentWater < 0)
            currentWater = 0;
    }

    @Override
    public boolean checkSuccess(Tile tile) {
        if (tile.hasFeature(Feature.WATERED)) {
            return true;
        }

        return currentWater > 0;
    }

    @Override
    public void use(BackPack backPack, Tile tile) {
        if (!checkSuccess(tile))
            return;

        if (tile.hasFeature(Feature.WATERED)) {
            increaseWater();
            if (tile.getThingOnTile() == null || !(tile.getThingOnTile() instanceof Lake))
                // TODO rain?
                tile.removeFeature(Feature.WATERED);
        }

        else {
            decreaseWater();
            tile.addFeature(Feature.WATERED);
        }
    }

    public int getCapacity() {
        switch (getToolLevel()) {
            case NORMAL:
                return 40;
            case COPPER:
                return 55;
            case IRON:
                return 70;
            case GOLD:
                return 85;
            case IRIDIUM:
                return 100;
            default:
                return -1;
        }
    }

    @Override
    public SkillType getSkillType() {
        return null;
    }
}
