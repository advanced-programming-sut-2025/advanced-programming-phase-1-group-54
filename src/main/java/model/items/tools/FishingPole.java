package model.items.tools;

import model.enums.toolsLevel.FishingPoleLevel;

public class FishingPole extends Tool {
    private FishingPoleLevel level;


    public FishingPoleLevel getLevel() {
        return level;
    }

    public void setLevel(FishingPoleLevel level) {
        this.level = level;
    }
}
