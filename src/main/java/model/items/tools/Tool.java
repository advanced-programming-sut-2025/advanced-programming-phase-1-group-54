package model.items.tools;

import model.enums.SkillType;
import model.enums.ToolType;
import model.items.Item;
import model.enums.ToolLevel;
import model.map.Location;
import model.map.Node;
import model.map.Tile;

public class Tool {
    private ToolLevel toolLevel = ToolLevel.NORMAL;
    private final ToolType toolType;

    public Tool(ToolType toolType) {
        this.toolType = toolType;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public ToolLevel getToolLevel() {
        return toolLevel;
    }

    public void setToolLevel(ToolLevel toolLevel) {
        this.toolLevel = toolLevel;
    }

    public SkillType getSkillType() {
        switch (toolType) {
            case AXE:
                return SkillType.FORAGING;
            case PICKAXE:
                return SkillType.MINING;
            case HOE, WATERING_CAN:
                return SkillType.FARMING;
            default:
                return null;
        }
    }

    public int getEnergyNeededPerUse() {
        if (toolType == ToolType.SCYTHE)
            return 2;

        if (toolType == ToolType.MILK_PAIL || toolType == ToolType.SHEAR)
            return 4;

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

    public boolean isUpgradable() {
        if (toolType == ToolType.SCYTHE || toolType == ToolType.MILK_PAIL || toolType == ToolType.SHEAR)
            return false;

        return toolLevel != ToolLevel.IRIDIUM;
    }

    public void upgrade() {
        if (isUpgradable())
            toolLevel = ToolLevel.values()[toolLevel.ordinal() + 1];
    }

    @Override
    public String toString() {
        return toolLevel.toString() + " " + toolType.toString();
    }
}
