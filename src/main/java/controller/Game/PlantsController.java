package controller.Game;

import model.App;
import model.Result;
import model.enums.Direction;
import model.items.plants.*;
import model.map.Tile;

import java.sql.SQLOutput;

public class PlantsController {

    public String showInfo(String plantName) {

        Crop crop = Crop.crops.get(plantName);
        if(crop != null){
            return crop.toString();
        }

        Tree tree = Tree.trees.get(plantName);
        if(tree != null){
            return tree.toString();
        }

        Fruit fruit = Fruit.fruits.get(plantName);
        if(fruit != null){
            return fruit.toString();
        }

        Seed seed = Seed.seeds.get(plantName);
        if(seed != null){
            return seed.toString();
        }

        return "Plant does not exist";

    }

    public Result planting(String seedName,String directionString){

        Seed seed = Seed.seeds.get(seedName);
        if(seed == null){
            return new Result(-1,"seed does not exist");
        }

        // Todo
//        if(inventory does not contain Seed){
//            return new Result(-1,"You do not have Seed");
//        }

        Direction direction = Direction.valueOf(directionString);
        // Todo
//        Tile tile = App.getCurrentGame().getCurrentTile();
//
//        if(tile.getThingOnTile() != null){
//            return new Result(-1,"tile already is full");
//        }

        Tree tree = Tree.trees.get(seed.getPlant());
        if(tree != null){
//            tile.setThingOnTile(tree.clone());
        }

//        Crop crop = Crop.crops.get(seed.getPlant());
//        if(crop != null){
//            tile.setThingOnTile(crop.clone());
//        }

        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        return null;
    }

}
