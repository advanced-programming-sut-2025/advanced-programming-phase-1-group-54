package model.items.tools;

import model.enums.Feature;
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
    public boolean use(BackPack backPack, Tile tile) {
        if (tile.hasFeature(Feature.WATERED)) {
            increaseWater();
            if (tile.getThingOnTile() == null || !(tile.getThingOnTile() instanceof Lake))
                tile.removeFeature(Feature.WATERED);
            return true;
        }

        if (currentWater > 0) {
            decreaseWater();
            tile.addFeature(Feature.WATERED);
            return true;
        }

        return false;
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
}
