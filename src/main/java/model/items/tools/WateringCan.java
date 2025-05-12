package model.items.tools;

import model.map.Tile;

public class WateringCan extends Tool {
    @Override
    public boolean use(BackPack backPack, Tile tile) {
        return false;
    }
}
