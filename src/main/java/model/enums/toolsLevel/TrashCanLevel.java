package model.enums.toolsLevel;

import model.enums.ToolLevel;

public enum TrashCanLevel implements ToolLevel {
    NORMAL(0),
    COPPER(15),
    IRON(30),
    GOLD(45),
    IRIDIUM(60);

    private final int percentage;

    TrashCanLevel(int percentage) {
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }
}
