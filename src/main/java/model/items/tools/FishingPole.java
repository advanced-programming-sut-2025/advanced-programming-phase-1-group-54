package model.items.tools;

import model.enums.FishingPoleLevel;
import model.enums.ToolLevel;
import model.items.Fish;
import model.items.Item;
import model.map.Lake;
import model.map.Tile;

public class FishingPole {
    FishingPoleLevel toolLevel = FishingPoleLevel.TRAINING;

    public FishingPoleLevel getToolLevel() {
        return toolLevel;
    }

    public double getPoleFactor() {
        switch (getToolLevel()) {
            case TRAINING:
                return 0.1;
            case BAMBOO:
                return 0.5;
            case FIBERGLASS:
                return 0.9;
            case IRIDIUM:
                return 1.2;
            default:
                return 0;
        }
    }

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
