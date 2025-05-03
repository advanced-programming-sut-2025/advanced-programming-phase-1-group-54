package controller.Game;

import model.App;
import model.Result;
import model.alive.Player;
import model.enums.Direction;
import model.items.plants.*;
import model.map.Location;
import model.map.Tile;
import model.map.World;

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

        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();

        Integer numberOfSeeds = currentPlayer.getBackpack().getNumberOfItemInBackPack().get(seed);

        if(numberOfSeeds == null || numberOfSeeds < 1){
            return new Result(-1,"You do not have Seed");
        }

        Direction direction = Direction.valueOf(directionString);
        Location location = currentPlayer.getCurrentLocation().getLocationAt(direction);
        // Todo
        Tile tile = App.getCurrentGame().getWorld().getTileAt(location);

        if(tile.getThingOnTile() != null){
            return new Result(-1,"tile already is full");
        }

        Tree tree = Tree.trees.get(seed.getPlant());
        if(tree != null){
            tree = tree.clone();
            currentPlayer.getPlants().add(tree);
            tile.setThingOnTile(tree);
        }

        Crop crop = Crop.crops.get(seed.getPlant());
        if(crop != null){
            crop = crop.clone();
            if(!cropCanBeGiant(crop,location)){
                currentPlayer.getPlants().add(crop);
                tile.setThingOnTile(crop);
            }
        }

        System.out.println("EROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR");
        return null;
    }

    private boolean cropCanBeGiant(Crop crop, Location location){

        World world = App.getCurrentGame().getWorld();
        Player player = App.getCurrentGame().getCurrentPlayer();

        Tile tile = world.getTileAt(location);
        Tile upTile = world.getTileAt(location.getLocationAt(Direction.UP));
        Tile downTile = world.getTileAt(location.getLocationAt(Direction.DOWN));
        Tile leftTile = world.getTileAt(location.getLocationAt(Direction.LEFT));
        Tile rightTile = world.getTileAt(location.getLocationAt(Direction.RIGHT));
        Tile up_LeftTile = world.getTileAt(location.getLocationAt(Direction.UP_LEFT));
        Tile down_LeftTile = world.getTileAt(location.getLocationAt(Direction.DOWN_LEFT));
        Tile up_RightTile = world.getTileAt(location.getLocationAt(Direction.UP_RIGHT));
        Tile down_RightTile = world.getTileAt(location.getLocationAt(Direction.DOWN_RIGHT));


        if(upTile.getThingOnTile() instanceof Crop upCrop && upCrop.getName().equals(crop.getName())){

            if(leftTile.getThingOnTile() instanceof Crop leftCrop && leftCrop.getName().equals(crop.getName())){

                if(up_LeftTile.getThingOnTile() instanceof Crop upLeftCrop && upLeftCrop.getName().equals(crop.getName())){

                    player.getPlants().remove(upCrop);
                    player.getPlants().remove(leftCrop);
                    player.getPlants().remove(upLeftCrop);

                    crop = compareCropsGrowth(crop,upCrop,leftCrop,upLeftCrop).clone();
                    upCrop = crop.clone();
                    leftCrop = crop.clone();
                    upLeftCrop = crop.clone();

                    crop.setGiantDirection(Direction.LEFT);
                    upCrop.setGiantDirection(Direction.DOWN);
                    leftCrop.setGiantDirection(Direction.UP);
                    upLeftCrop.setGiantDirection(Direction.RIGHT);

                    player.getPlants().add(crop);
                    player.getPlants().add(upCrop);
                    player.getPlants().add(leftCrop);
                    player.getPlants().add(upLeftCrop);

                    tile.setThingOnTile(crop);
                    upTile.setThingOnTile(upCrop);
                    leftTile.setThingOnTile(leftCrop);
                    up_LeftTile.setThingOnTile(upLeftCrop);

                    return true;

                }

            }
            else if(rightTile.getThingOnTile() instanceof Crop rightCrop && rightCrop.getName().equals(crop.getName())){

                if(up_RightTile.getThingOnTile() instanceof Crop upRightCrop && upRightCrop.getName().equals(crop.getName())){

                    player.getPlants().remove(upCrop);
                    player.getPlants().remove(rightCrop);
                    player.getPlants().remove(upRightCrop);

                    crop = compareCropsGrowth(crop,upCrop,rightCrop,upRightCrop).clone();
                    upCrop = crop.clone();
                    rightCrop = crop.clone();
                    upRightCrop = crop.clone();

                    crop.setGiantDirection(Direction.UP);
                    upCrop.setGiantDirection(Direction.RIGHT);
                    rightCrop.setGiantDirection(Direction.LEFT);
                    upRightCrop.setGiantDirection(Direction.DOWN);

                    player.getPlants().add(crop);
                    player.getPlants().add(upCrop);
                    player.getPlants().add(rightCrop);
                    player.getPlants().add(upRightCrop);

                    tile.setThingOnTile(crop);
                    upTile.setThingOnTile(upCrop);
                    rightTile.setThingOnTile(rightCrop);
                    up_RightTile.setThingOnTile(upRightCrop);

                    return true;
                }

            }

        }
        else if(downTile.getThingOnTile() instanceof Crop downCrop && downCrop.getName().equals(crop.getName())){

            if(leftTile.getThingOnTile() instanceof Crop leftCrop && leftCrop.getName().equals(crop.getName())){

                if(down_LeftTile.getThingOnTile() instanceof Crop downLeftCrop && downLeftCrop.getName().equals(crop.getName())){

                    player.getPlants().remove(downCrop);
                    player.getPlants().remove(leftCrop);
                    player.getPlants().remove(downLeftCrop);

                    crop = compareCropsGrowth(crop,downCrop,leftCrop,downLeftCrop).clone();
                    downCrop = crop.clone();
                    leftCrop = crop.clone();
                    downLeftCrop = crop.clone();

                    crop.setGiantDirection(Direction.DOWN);
                    downCrop.setGiantDirection(Direction.LEFT);
                    leftCrop.setGiantDirection(Direction.RIGHT);
                    downLeftCrop.setGiantDirection(Direction.UP);

                    player.getPlants().add(crop);
                    player.getPlants().add(downCrop);
                    player.getPlants().add(leftCrop);
                    player.getPlants().add(downLeftCrop);

                    tile.setThingOnTile(crop);
                    downTile.setThingOnTile(downCrop);
                    leftTile.setThingOnTile(leftCrop);
                    down_LeftTile.setThingOnTile(downLeftCrop);

                    return true;
                }

            }
            else if(rightTile.getThingOnTile() instanceof Crop rightCrop && rightCrop.getName().equals(crop.getName())){

                if(down_RightTile.getThingOnTile() instanceof Crop downRightCrop && downRightCrop.getName().equals(crop.getName())){

                    player.getPlants().remove(downCrop);
                    player.getPlants().remove(rightCrop);
                    player.getPlants().remove(downRightCrop);

                    crop = compareCropsGrowth(crop,downCrop,rightCrop,downRightCrop).clone();
                    downCrop = crop.clone();
                    rightCrop = crop.clone();
                    downRightCrop = crop.clone();

                    crop.setGiantDirection(Direction.RIGHT);
                    downCrop.setGiantDirection(Direction.UP);
                    rightCrop.setGiantDirection(Direction.DOWN);
                    downRightCrop.setGiantDirection(Direction.LEFT);

                    player.getPlants().add(crop);
                    player.getPlants().add(downCrop);
                    player.getPlants().add(rightCrop);
                    player.getPlants().add(downRightCrop);

                    tile.setThingOnTile(crop);
                    downTile.setThingOnTile(downCrop);
                    rightTile.setThingOnTile(rightCrop);
                    down_RightTile.setThingOnTile(downRightCrop);

                    return true;
                }

            }

        }
        return false;

    }

    private Crop compareCropsGrowth(Crop crop1, Crop crop2,Crop crop3,Crop crop4) {

        Crop [] crops = {crop1, crop2, crop3, crop4};
        int[] growth = {0,0,0,0};
        for (int j = 0; j < 4;j++){
            for(int i = 0; i <= crops[j].getCurrentStage();i++){
                growth[j] += crop1.getStages()[i];
            }
            growth[j] += crop1.getDaysInCurrentStage();
        }

        int [] indexes = {0,0,0,0};
        for(int i = 0 ; i < crops.length; i++){
            for(int j = 0 ; j < crops.length ; j++){
                if(growth[i] < growth[j]){
                    indexes[i]++;
                }
            }
        }

        for(int i = 0 ; i < indexes.length ; i++){
            if(indexes[i] == 0){
                return crops[i];
            }
        }

        return null;

    }

}
