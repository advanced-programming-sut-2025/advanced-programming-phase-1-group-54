package model.tools;

import model.Result;
import model.Tool;
import model.enums.toolsLevel.FishingPoleLevel;

public class FishingPole extends Tool {
    private FishingPoleLevel level;

    @Override
    public Result use() {
        return super.use();
    }


    public FishingPoleLevel getLevel() {
        return level;
    }

    public void setLevel(FishingPoleLevel level) {
        this.level = level;
    }
}
