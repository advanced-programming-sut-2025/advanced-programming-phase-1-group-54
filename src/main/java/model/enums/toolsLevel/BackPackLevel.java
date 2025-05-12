package model.enums.toolsLevel;

import model.enums.ToolLevel;

public enum BackPackLevel implements ToolLevel {
    NORMAL(12),
    LARGE(24),
    DELUX(Integer.MAX_VALUE);
    private final int size;

    BackPackLevel(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int getEnergyNeededPerUse() {
        return 0;
    }
}
