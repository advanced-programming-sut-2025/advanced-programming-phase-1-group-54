package model.items.tools;

import model.map.Tile;

public class MilkPail extends Tool {

    @Override
    public boolean use(BackPack backPack, Tile tile) {
        return true;
    }

    @Override
    public int getEnergyNeededPerUse() {
        return 4;
    }
}
