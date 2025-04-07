package model;

import model.enums.ToolLevel;

public abstract class Tool extends Item {

    protected ToolLevel level;
    protected int energyNeededPerUse;

    public Result use(){
        return null;
    }

    public Result upgrade() {
        return null;
    }
}
