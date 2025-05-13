package model.items.tools;

import model.enums.SkillType;
import model.map.Tile;

public class Shear extends Tool {
    @Override
    public boolean checkSuccess(Tile tile) {
        return false;
    }

    @Override
    public void use(BackPack backPack, Tile tile) {
        return;
    }

    @Override
    public SkillType getSkillType() {
        return SkillType.FARMING;
    }

    @Override
    public int getEnergyNeededPerUse() {
        return 4;
    }
}
