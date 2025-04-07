package model.enums.toolsLevel;

import model.enums.ToolLevel;

import java.lang.annotation.Target;

public enum HoeLevel implements ToolLevel {
    NORMAL(5),
    COPPER(4),
    IRON(3),
    GOLD(2),
    IRIDIUM(1);

    private final int energyNeededPerUse;

    HoeLevel(int energyNeededPerUse) {
        this.energyNeededPerUse = energyNeededPerUse;
    }

    public int getEnergyNeededPerUse() {
        return energyNeededPerUse;
    }
}
