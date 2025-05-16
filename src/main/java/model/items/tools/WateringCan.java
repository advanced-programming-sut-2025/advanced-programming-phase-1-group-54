package model.items.tools;

import model.enums.Feature;
import model.enums.SkillType;
import model.enums.ToolType;
import model.map.Tile;

public class WateringCan extends Tool {
    private int currentWater;

    public WateringCan() {
        super(ToolType.WATERING_CAN);
    }

    public int getCurrentWater() {
        return currentWater;
    }

    public void increaseWater() {
        currentWater++;
        if (currentWater > getCapacity())
            currentWater = getCapacity();
    }

    public void decreaseWater() {
        currentWater--;
        if (currentWater < 0)
            currentWater = 0;
    }

    public int getCapacity() {
        switch (getToolLevel()) {
            case NORMAL:
                return 40;
            case COPPER:
                return 55;
            case IRON:
                return 70;
            case GOLD:
                return 85;
            case IRIDIUM:
                return 100;
            default:
                return -1;
        }
    }

    @Override
    public SkillType getSkillType() {
        return SkillType.FARMING;
    }
}
