package model.items.tools;

import model.map.Tile;

public class Shear extends Tool {
    @Override
    public boolean use(BackPack backPack, Tile tile) {
        return false;
    }

    @Override
    public int getEnergyNeededPerUse() {
        return 4;
    }
}
