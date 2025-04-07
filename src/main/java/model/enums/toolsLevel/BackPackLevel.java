package model.enums.toolsLevel;

import model.enums.ToolLevel;

public enum BackPackLevel implements ToolLevel {
    NORMAL(12),
    BIG(24),
    THELUX(-1);

    private final int size;

    BackPackLevel(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
