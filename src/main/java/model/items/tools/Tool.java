package model.items.tools;

import model.enums.SkillType;
import model.items.Item;
import model.enums.ToolLevel;
import model.map.Location;
import model.map.Node;
import model.map.Tile;

public abstract class Tool {
    private ToolLevel toolLevel = ToolLevel.NORMAL;

    public ToolLevel getToolLevel() {
        return toolLevel;
    }

    public void setToolLevel(ToolLevel toolLevel) {
        this.toolLevel = toolLevel;
    }

    abstract public boolean checkSuccess(Tile tile);

    abstract public void use(BackPack backPack, Tile tile);

    public int getEnergyNeededPerUse() {
        switch (toolLevel) {
            case NORMAL:
                return 5;
            case COPPER:
                return 4;
            case IRON:
                return 3;
            case GOLD:
                return 2;
            case IRIDIUM:
                return 1;
            default:
                return -1;
        }
    }

    public boolean upgrade() {
        if (toolLevel == ToolLevel.IRIDIUM)
            return false;

        toolLevel = ToolLevel.values()[toolLevel.ordinal() + 1];
        return true;
    }
}
