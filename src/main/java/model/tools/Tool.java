package model.tools;

import model.Item;
import model.Result;
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
