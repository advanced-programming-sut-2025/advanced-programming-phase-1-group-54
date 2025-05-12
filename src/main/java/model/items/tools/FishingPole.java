package model.items.tools;

import model.enums.ToolLevel;
import model.items.Fish;
import model.items.Item;
import model.map.Lake;
import model.map.Tile;

public class FishingPole extends Tool {

    public FishingPole() {
        setToolLevel(ToolLevel.TRAINING);
    }

    @Override
    public boolean use(BackPack backPack, Tile tile) {
        if (tile.getThingOnTile() == null)
            return false;

        if (tile.getThingOnTile() instanceof Lake) {
           if (getToolLevel() == ToolLevel.TRAINING)
               backPack.addItem(Fish.getSeasonFish(), 1);
           // TODO get cheapest season fish

           else
               backPack.addItem(Fish.getSeasonFish(), 1);

           return true;
        }

        return false;
    }

    @Override
    public int getEnergyNeededPerUse() {
        switch (getToolLevel()) {
            case TRAINING, BAMBOO:
                return 8;
            case FIBERGLASS:
                return 6;
            case IRIDIUM:
                return 4;
            default:
                return -1;
        }
    }
}
