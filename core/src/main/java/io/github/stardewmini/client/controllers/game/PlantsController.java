package io.github.stardewmini.client.controllers.game;

import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.items.plants.Crop;
import io.github.stardewmini.common.model.items.plants.Fruit;
import io.github.stardewmini.common.model.items.plants.Seed;
import io.github.stardewmini.common.model.items.plants.Tree;

public class PlantsController {
    public static Result showInfo(String plantName) {
        Crop crop = Crop.getCrop(plantName);
        if (crop != null) {
            return new Result(true, crop.toString());
        }

        Tree tree = Tree.getTree(plantName);
        if (tree != null) {
            return new Result(true, tree.toString());
        }

        Fruit fruit = Fruit.getFruit(plantName);
        if (fruit != null) {
            return new Result(true, fruit.toString());
        }

        Seed seed = Seed.getSeed(plantName);
        if (seed != null) {
            return new Result(true, seed.toString());
        }

        return new Result(false, "Plant does not exist");

    }

}
