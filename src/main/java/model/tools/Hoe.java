package model.tools;

import model.Result;
import model.Tool;
import model.enums.toolsLevel.HoeLevel;

public class Hoe extends Tool {
    private HoeLevel level;

    @Override
    public void upgrade() {
        super.upgrade();
    }

    @Override
    public Result use() {
        return super.use();
    }

    public HoeLevel getLevel() {
        return level;
    }

    public void setLevel(HoeLevel level) {
        this.level = level;
    }
}
