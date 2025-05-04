package controller.Game;

import model.App;
import model.Game;
import model.Placeable;
import model.Result;
import model.alive.Player;
import model.enums.Direction;
import model.items.plants.*;
import model.map.Location;
import model.map.Tile;
import model.map.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        Direction direction;
        try{
            direction = Direction.valueOf(directionString);
        }catch (IllegalArgumentException e){
            return new Result(-1,"invalid direction");
        }

        Seed seed;
        if(seedName.equals("Mixed Seeds")){
            Random rand = new Random();
            ArrayList<String> mixedSeeds = Seed.mixedSeeds.get(App.getCurrentGame().getDateTime().getSeason());
            seed = Seed.seeds.get(mixedSeeds.get(rand.nextInt(mixedSeeds.size())));
            return plantingSeeds(seed,direction);
        }
        else{
            seed = Seed.seeds.get(seedName);
            if(seed == null){
                return new Result(-1,"seed does not exist");
            }
            return plantingSeeds(seed,direction);
        }

    }

    public void crowAttack(){

        Player player = App.getCurrentGame().getCurrentPlayer();

        Random rand = new Random();
        int numberOfPlayerPlants = player.getPlants().size()/16;

        for(int i = 0; i < numberOfPlayerPlants; i++){
            if(rand.nextInt(4) == 0) {
                List<Plant> keys = new ArrayList<>(player.getPlants().keySet());
                Plant plant = keys.get(rand.nextInt(keys.size()));
                Tile tile = player.getPlants().get(plant);
                if(!tile.isProtected()) {
                    player.getPlants().remove(plant);
                    tile.setThingOnTile(null);
                }
            }
        }

    }

    public Result showPlant(int x, int y){

        Location location = new Location(x, y);
        Tile tile = App.getCurrentGame().getWorld().getTileAt(location);

        Placeable placeable = tile.getThingOnTile();
        if(placeable instanceof Plant plant){
            StringBuilder output = new StringBuilder();
            output.append(plant.toString());
            output.append("\ndays until harvest : ");
            if(plant.isFruitIsRipen()){
                output.append("0");
            }
            else if(plant.getCurrentStage() > plant.getMaxStages()){
                output.append(plant.getRegrowthTime() - plant.getCurrentStage());
            }
            else{
                output.append(plant.getTotalHarvestTime() - plant.getCurrentStage());
            }
            output.append("\nCurrent stage : ").append(plant.getCurrentStage());
            output.append("\nWatered today : ").append(plant.isWatered());
            output.append("\nFertilized : ").append(plant.isFertilized());

            return new Result(1,output.toString());

        }
        else{
            return new Result(-1,"Does not exist any plant on the tile");
        }

    }

    public Result harvestPlant(String directionString){

        Direction direction;
        try{
            direction = Direction.valueOf(directionString);
        }catch (IllegalArgumentException e){
            return new Result(-1,"invalid direction");
        }

        Game game = App.getCurrentGame();
        Location location = game.getCurrentPlayer().getCurrentLocation().getLocationAt(direction);
        Tile tile = game.getWorld().getTileAt(location);

        Placeable placeable = tile.getThingOnTile();
        if(placeable instanceof Tree tree){
            if(tree.isFruitIsRipen()){
                game.getCurrentPlayer().getBackpack().addItem(Fruit.fruits.get(tree.getFruit()),1);
                tree.setFruitIsRipen(false);
                return new Result(1,"You get 1 " + tree.getFruit());
            }
            return new Result(-1,"fruit has not ripen");

        }
        else if(placeable instanceof Crop crop){
            if(crop.isFruitIsRipen()){
                if(crop.getGiantDirection() != null){
                    game.getCurrentPlayer().getBackpack().addItem(Fruit.fruits.get(crop.getFruit()),10);
                    crop.setFruitIsRipen(false);
                    crop = (Crop) game.getWorld().getTileAt(location.getLocationAt(crop.getGiantDirection())).getThingOnTile();
                    crop.setFruitIsRipen(false);
                    crop = (Crop) game.getWorld().getTileAt(location.getLocationAt(crop.getGiantDirection())).getThingOnTile();
                    crop.setFruitIsRipen(false);
                    crop = (Crop) game.getWorld().getTileAt(location.getLocationAt(crop.getGiantDirection())).getThingOnTile();
                    crop.setFruitIsRipen(false);



                }
                else{



                }
            }
            return new Result(1,"fruit has not ripen");
        }
        else{
            return new Result(-1,"Does not exist any plant on the tile");
        }

    }



    private Result plantingSeeds(Seed seed,Direction direction){

        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Location location = currentPlayer.getCurrentLocation().getLocationAt(direction);
        Tile tile = App.getCurrentGame().getWorld().getTileAt(location);

        Integer numberOfSeeds = currentPlayer.getBackpack().getNumberOfItemInBackPack().get(seed);

        if(numberOfSeeds == null || numberOfSeeds < 1){
            return new Result(-1,"You do not have Seed");
        }


        if(tile.getThingOnTile() != null){
            return new Result(-1,"tile already is full");
        }

        currentPlayer.getBackpack().removeItem(seed,1);

        Tree tree = Tree.trees.get(seed.getPlant());
        if(tree != null){
            tree = tree.clone();
            currentPlayer.getPlants().put(tree,tile);
            tile.setThingOnTile(tree);
        }

        Crop crop = Crop.crops.get(seed.getPlant());
        if(crop != null){
            crop = crop.clone();
            if(!cropCanBeGiant(crop,location)){
                currentPlayer.getPlants().put(crop,tile);
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

                    player.getPlants().put(crop,tile);
                    player.getPlants().put(upCrop,upTile);
                    player.getPlants().put(leftCrop,leftTile);
                    player.getPlants().put(upLeftCrop,up_LeftTile);

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

                    player.getPlants().put(crop,tile);
                    player.getPlants().put(upCrop,upTile);
                    player.getPlants().put(rightCrop,rightTile);
                    player.getPlants().put(upRightCrop,up_RightTile);

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

                    player.getPlants().put(crop,tile);
                    player.getPlants().put(downCrop,downTile);
                    player.getPlants().put(leftCrop,leftTile);
                    player.getPlants().put(downLeftCrop,down_LeftTile);

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

                    player.getPlants().put(crop,tile);
                    player.getPlants().put(downCrop,downTile);
                    player.getPlants().put(rightCrop,rightTile);
                    player.getPlants().put(downRightCrop,down_RightTile);

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
