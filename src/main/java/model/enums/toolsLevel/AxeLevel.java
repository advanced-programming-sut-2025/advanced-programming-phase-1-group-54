package model.enums.toolsLevel;

import model.enums.ToolLevel;

public enum AxeLevel implements ToolLevel {
    NORMAL(5),
    COPPER(4),
    IRON(3),
    GOLD(2),
    IRIDIUM(1);

    private final int EnergyNeededPerUse;

    AxeLevel(int energyNeededPerUse) {
        EnergyNeededPerUse = energyNeededPerUse;
    }

    public int getEnergyNeededPerUse() {
        return EnergyNeededPerUse;
    }
}
