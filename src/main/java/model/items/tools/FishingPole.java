package model.items.tools;

import model.enums.FishingPoleType;

public class FishingPole {
    private final FishingPoleType type;

    public FishingPole(FishingPoleType type) {
        this.type = type;
    }

    public FishingPoleType getType() {
        return type;
    }

    public double getPoleFactor() {
        switch (getType()) {
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
        switch (getType()) {
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
