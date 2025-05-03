package controller.Game;

import model.App;
import model.Result;
import model.alive.Player;
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

//    public Result planting(String seedName,String directionString){
//
//        Seed seed = Seed.seeds.get(seedName);
//        if(seed == null){
//            return new Result(-1,"seed does not exist");
//        }
//
//        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
//
//        Integer numberOfSeeds = currentPlayer.getBackpack().getNumberOfItemInBackPack().get(seed);
//
//        if(numberOfSeeds == null || numberOfSeeds < 1){
//            return new Result(-1,"You do not have Seed");
//        }
//
//        Direction direction = Direction.valueOf(directionString);
//        // Todo
//        Tile tile /*= findTileInDirection(currentPlayer.getCurrentTile(),direction) */;
//
//        if(tile.getThingOnTile() != null){
//            return new Result(-1,"tile already is full");
//        }
//
//        Tree tree = Tree.trees.get(seed.getPlant());
//        if(tree != null){
//            tile.setThingOnTile(tree.clone());
//        }
//
//        Crop crop = Crop.crops.get(seed.getPlant());
//        if(crop != null){
//            cropCanBeGiant(crop,tile);
//            tile.setThingOnTile(crop.clone());
//        }
//
//        System.out.println("EROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR");
//        return null;
//    }
//
//    public void cropCanBeGiant(Crop crop,Tile tile){
//
//        Tile upTile /* = findTileInDirection(tile,Direction.UP) */;
//        Tile downTile /* = findTileInDirection(tile,Direction.DOWN) */;
//        Tile leftTile /* = findTileInDirection(tile,Direction.LEFT) */;
//        Tile rightTile /* = findTileInDirection(tile,Direction.RIGHT) */;
//        Tile up_LeftTile /* = findTileInDirection(tile,Direction.UP_LEFT) */;
//        Tile down_LeftTile /* = findTileInDirection(tile,Direction.DOWN_LEFT) */;
//        Tile up_RightTile /* = findTileInDirection(tile,Direction.UP_RIGHT) */;
//        Tile down_RightTile /* = findTileInDirection(tile,Direction.DOWN_RIGHT) */;
//
//
//        if(upTile.getThingOnTile() instanceof Crop upCrop && upCrop.getName().equals(crop.getName())){
//
//            if(leftTile.getThingOnTile() instanceof Crop leftCrop && leftCrop.getName().equals(crop.getName())){
//
//                if(up_LeftTile.getThingOnTile() instanceof Crop upLeftCrop && upLeftCrop.getName().equals(crop.getName())){
//
//                    crop = compareCropsGrowth(crop,upCrop,leftCrop,upLeftCrop).clone();
//
//
//                    crop.setGiantDirection(Direction.LEFT);
//                    upCrop.setGiantDirection(Direction.DOWN);
//                    leftCrop.setGiantDirection(Direction.UP);
//                    upLeftCrop.setGiantDirection(Direction.RIGHT);
//
//                }
//
//            }
//            else if(rightTile.getThingOnTile() instanceof Crop rightCrop && rightCrop.getName().equals(crop.getName())){
//
//                if(up_RightTile.getThingOnTile() instanceof Crop upRightCrop && upRightCrop.getName().equals(crop.getName())){
//
//                    crop.setGiantDirection(Direction.UP);
//                    upCrop.setGiantDirection(Direction.RIGHT);
//                    rightCrop.setGiantDirection(Direction.LEFT);
//                    upRightCrop.setGiantDirection(Direction.DOWN);
//
//                }
//
//            }
//
//        }
//        else if(downTile.getThingOnTile() instanceof Crop downCrop && downCrop.getName().equals(crop.getName())){
//
//            if(leftTile.getThingOnTile() instanceof Crop leftCrop && leftCrop.getName().equals(crop.getName())){
//
//                if(down_LeftTile.getThingOnTile() instanceof Crop downLeftCrop && downLeftCrop.getName().equals(crop.getName())){
//
//                    crop.setGiantDirection(Direction.DOWN);
//                    downCrop.setGiantDirection(Direction.LEFT);
//                    leftCrop.setGiantDirection(Direction.RIGHT);
//                    downLeftCrop.setGiantDirection(Direction.UP);
//
//                }
//
//            }
//            else if(rightTile.getThingOnTile() instanceof Crop rightCrop && rightCrop.getName().equals(crop.getName())){
//
//                if(down_RightTile.getThingOnTile() instanceof Crop downRightCrop && downRightCrop.getName().equals(crop.getName())){
//
//
//                    crop.setGiantDirection(Direction.RIGHT);
//                    downCrop.setGiantDirection(Direction.UP);
//                    rightCrop.setGiantDirection(Direction.DOWN);
//                    downRightCrop.setGiantDirection(Direction.LEFT);
//
//                }
//
//            }
//
//        }
//
//    }
//
//    public Crop compareCropsGrowth(Crop crop1, Crop crop2,Crop crop3,Crop crop4) {
//
//        Crop [] crops = {crop1, crop2, crop3, crop4};
//        int[] growth = {0,0,0,0};
//        for (int j = 0; j < 4;j++){
//            for(int i = 0; i <= crops[j].getCurrentStage();i++){
//                growth[j] += crop1.getStages()[i];
//            }
//            growth[j] += crop1.getDaysInCurrentStage();
//        }
//
//        int [] indexes = {0,0,0,0};
//        for(int i = 0 ; i < crops.length; i++){
//            for(int j = 0 ; j < crops.length ; j++){
//                if(growth[i] < growth[j]){
//                    indexes[i]++;
//                }
//            }
//        }
//
//        for(int i = 0 ; i < indexes.length ; i++){
//            if(indexes[i] == 0){
//                return crops[i];
//            }
//        }
//
//        return null;
//
//    }

}
