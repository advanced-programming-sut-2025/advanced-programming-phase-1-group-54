package model.enums.toolsLevel;

import model.enums.ToolLevel;

public enum PickAxeLevel implements ToolLevel {
    NORMAL(5),
    COPPER(4),
    IRON(3),
    GOLD(2),
    IRIDIUM(1);

    private final int energyNeededPerUse;

    PickAxeLevel(int energyNeededPerUse) {
        this.energyNeededPerUse = energyNeededPerUse;
    }

    public int getEnergyNeededPerUse() {
        return energyNeededPerUse;
    }
}
