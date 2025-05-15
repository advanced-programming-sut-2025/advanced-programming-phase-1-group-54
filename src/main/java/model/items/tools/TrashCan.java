package model.items.tools;

import model.enums.ToolLevel;
import model.enums.ToolType;
import model.items.Item;

public class TrashCan {
    ToolLevel toolLevel = ToolLevel.NORMAL;

    public ToolLevel getToolLevel() {
        return toolLevel;
    }

    public void setToolLevel(ToolLevel toolLevel) {
        this.toolLevel = toolLevel;
    }

    public int getMoneyFromTrashCan(Item item, int number) {
        return (getSellMoneyPercentage() * item.getBaseSellPrice() / 100) * number;
    }

    public int getSellMoneyPercentage() {
        switch (toolLevel) {
            case COPPER:
                return 15;
            case IRON:
                return 30;
            case GOLD:
                return 45;
            case IRIDIUM:
                return 60;
            default:
                return 0;
        }
    }

    public boolean isUpgradable() {
        return toolLevel != ToolLevel.IRIDIUM;
    }

    public void upgrade() {
        if (isUpgradable())
            toolLevel = ToolLevel.values()[toolLevel.ordinal() + 1];
    }
}
