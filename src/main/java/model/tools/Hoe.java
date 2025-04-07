package model.tools;

import model.Result;
import model.enums.toolsLevel.HoeLevel;

public class Hoe extends Tool {
    private HoeLevel level;

    public HoeLevel getLevel() {
        return level;
    }

    public void setLevel(HoeLevel level) {
        this.level = level;
    }
}
