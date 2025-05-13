package model.items.tools;

import model.enums.SkillType;
import model.items.Material;
import model.items.plants.Seed;
import model.items.plants.Tree;
import model.map.Tile;

public class Axe extends Tool {
    @Override
    public boolean checkSuccess(Tile tile) {
        if (tile.getThingOnTile() == null)
            return false;

        return tile.getThingOnTile() instanceof Tree;
    }

    @Override
    public void use(BackPack backpack, Tile tile) {
        if (!checkSuccess(tile))
            return;

        if (tile.getThingOnTile() instanceof Tree tree) {
            backpack.addItem(Material.getMaterial("Wood"), 1);
            backpack.addItem(Seed.getSeed(tree.getSource()), 2);
        }
    }

    @Override
    public SkillType getSkillType() {
        return SkillType.FORAGING;
    }
}
