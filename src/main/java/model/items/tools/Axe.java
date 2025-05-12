package model.items.tools;

import model.items.Material;
import model.items.plants.Seed;
import model.items.plants.Tree;
import model.map.Tile;

public class Axe extends Tool {
    @Override
    public boolean use(BackPack backpack, Tile tile) {
        if (tile.getThingOnTile() == null)
            return false;

        if (tile.getThingOnTile() instanceof Tree tree) {
            backpack.addItem(Material.getMaterial("Wood"), 1);
            backpack.addItem(Seed.getSeed(tree.getSource()), 2);
            return true;
        }
        return false;
    }
}
