package model.enums.toolsLevel;

import model.enums.ToolLevel;

public enum WateringCanLevel implements ToolLevel {
    NORMAL(40,5),
    COPPER(55,4),
    IRON(70,3),
    GOLD(85,2),
    IRIDIUM(100,1);

    private final int numberOfTiles;
    private final int energyNeededPerUse;

    WateringCanLevel(int numberOfTiles, int energyNeededPerUse) {
        this.numberOfTiles = numberOfTiles;
        this.energyNeededPerUse = energyNeededPerUse;
    }

    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    public int getEnergyNeededPerUse() {
        return energyNeededPerUse;
    }
}
