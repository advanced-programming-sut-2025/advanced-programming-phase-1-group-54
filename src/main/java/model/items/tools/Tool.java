package model.items.tools;

import model.items.Item;
import model.enums.ToolLevel;

public abstract class Tool extends Item {
    protected ToolLevel level;

    public void use() {
    }

    public void upgrade() {
    }

    public int getEnergyNeededPerUse() {
        return level.getEnergyNeededPerUse();
    }
}
