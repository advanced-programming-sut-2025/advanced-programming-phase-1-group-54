package model.items.tools;

import model.items.plants.Crop;
import model.items.plants.Fruit;
import model.items.plants.Plant;
import model.items.plants.Tree;
import model.map.Tile;

public class Scythe extends Tool {

    @Override
    public boolean use(BackPack backPack, Tile tile) {
        if (tile.getThingOnTile() == null)
            return false;

        if (tile.getThingOnTile() instanceof Plant plant) {
            if (plant.isFruitIsRipen()) {
                backPack.addItem(Fruit.getFruit(plant.getFruit()), 1);

                if (plant instanceof Crop crop && crop.getGiantDirection() != null) {
                    backPack.addItem(Fruit.getFruit(plant.getFruit()), 9);
                }

                return true;
            }
        }

        return false;
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
