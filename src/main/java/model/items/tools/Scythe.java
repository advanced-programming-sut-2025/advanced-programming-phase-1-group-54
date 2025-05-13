package model.items.tools;

import model.enums.SkillType;
import model.items.plants.Crop;
import model.items.plants.Fruit;
import model.items.plants.Plant;
import model.items.plants.Tree;
import model.map.Tile;

public class Scythe extends Tool {

    @Override
    public boolean checkSuccess(Tile tile) {
        if (tile.getThingOnTile() == null)
            return false;

        return (tile.getThingOnTile() instanceof Plant);
    }

    @Override
    public void use(BackPack backPack, Tile tile) {
        if (!checkSuccess(tile))
            return;

        if (tile.getThingOnTile() instanceof Plant plant) {
            if (plant.isFruitIsRipen()) {
                backPack.addItem(Fruit.getFruit(plant.getFruit()), 1);

                if (plant instanceof Crop crop && crop.getGiantDirection() != null) {
                    backPack.addItem(Fruit.getFruit(plant.getFruit()), 9);
                }
            }
        }
    }

    @Override
    public SkillType getSkillType() {
        return SkillType.FARMING;
    }

    @Override
    public int getEnergyNeededPerUse() {
        return 2;
    }

    @Override
    public boolean upgrade() {
        return false;
    }
}
