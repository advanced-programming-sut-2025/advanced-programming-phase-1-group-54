package model.enums.toolsLevel;

import model.enums.ToolLevel;

public enum FishingPoleLevel implements ToolLevel {
    TRAINING(8),
    BOMBO(8),
    FIBERGLASS(6),
    IRIDIUM(4);

    private final int energyNeededPerUse;

    FishingPoleLevel(int energyNeededPerUse) {
        this.energyNeededPerUse = energyNeededPerUse;
    }

    public int getEnergyNeededPerUse() {
        return energyNeededPerUse;
    }

}
