package controller.Game;

import model.App;
import model.Building.GreenHouse;
import model.Game;
import model.Placeable;
import model.Result;
import model.alive.Player;
import model.enums.Direction;
import model.enums.Feature;
import model.items.Fertilize;
import model.items.plants.*;
import model.items.tools.Scythe;
import model.map.Farm;
import model.map.Location;
import model.map.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlantsController {

    public String showInfo(String plantName) {

        Crop crop = Crop.getCrop(plantName);
        if(crop != null){
            return crop.toString();
        }

        Tree tree = Tree.getTree(plantName);
        if(tree != null){
            return tree.toString();
        }

        Fruit fruit = Fruit.getFruit(plantName);
        if(fruit != null){
            return fruit.toString();
        }

        Seed seed = Seed.getSeed(plantName);
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
            return plantingSeeds(Seed.getMixedSeed(App.getCurrentGame().getDateTime().getSeason()),direction);
        }
        else{
            seed = Seed.getSeed(seedName);
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
                if(! tile.getFeatures().contains(Feature.PROTECTED)) {
                    player.getPlants().remove(plant);
                    tile.setThingOnTile(null);
                    if (plant instanceof Crop crop && crop.getGiantDirection() != null){
                        for (int k = 0 ; k < 3;k++){
                            tile = App.getCurrentGame().getWorld().getFarm(player).getTileAt(tile.getLocation().
                                    getLocationAt(crop.getGiantDirection()));
                            crop = (Crop) tile.getThingOnTile();
                            player.getPlants().remove(crop);
                            tile.setThingOnTile(null);
                        }
                    }
                }
            }
        }
    }

    public Result showPlant(int x, int y){

        Location location = new Location(x, y);
        Player player = App.getCurrentGame().getCurrentPlayer();
        Tile tile = App.getCurrentGame().getWorld().getFarm(player).getTileAt(location);

        if(tile == null){
            return new Result(-1,"tile is not in your farm");
        }

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

    //TODO
    public Result harvestPlant(String directionString){

        // Todo check energy

        if(! (App.getCurrentGame().getCurrentPlayer().getEquippedTool() instanceof Scythe)){
            return new Result(-1,"You don't have scythe in your hand");
        }


        Direction direction;
        try{
            direction = Direction.valueOf(directionString);
        }catch (IllegalArgumentException e){
            return new Result(-1,"invalid direction");
        }

        Game game = App.getCurrentGame();
        Location location = game.getCurrentPlayer().getCurrentLocation().getLocationAt(direction);
        Farm farm = game.getWorld().getFarm(game.getCurrentPlayer());
        Tile tile = farm.getTileAt(location.delta(farm.getLocation()));

        if(tile == null){
            return new Result(-1,"tile is not in your farm");
        }

        if(tile.getThingOnTile() != null && tile.getThingOnTile() instanceof GreenHouse){
            tile = tile.getTop();
        }

        Placeable placeable = tile.getThingOnTile();
        if(placeable == null){
            return new Result(-1,"Does not exist any plant on the tile");
        }
        if(placeable instanceof Tree tree){
            if(tree.isFruitIsRipen()){
                game.getCurrentPlayer().getBackpack().addItem(Fruit.getFruit(tree.getFruit()),1);
                tree.setFruitIsRipen(false);
                return new Result(1,"You get 1 " + tree.getFruit());
            }
            return new Result(-1,"fruit has not ripen");

        }
        else if(placeable instanceof Crop crop){
            if(crop.isFruitIsRipen()){
                if(crop.getGiantDirection() != null){
                    game.getCurrentPlayer().getBackpack().addItem(Fruit.getFruit(crop.getFruit()),10);
                    crop.setFruitIsRipen(false);
                    crop = (Crop) game.getWorld().getTileAt(location.getLocationAt(crop.getGiantDirection())).getThingOnTile();
                    crop.setFruitIsRipen(false);
                    crop = (Crop) game.getWorld().getTileAt(location.getLocationAt(crop.getGiantDirection())).getThingOnTile();
                    crop.setFruitIsRipen(false);
                    crop = (Crop) game.getWorld().getTileAt(location.getLocationAt(crop.getGiantDirection())).getThingOnTile();
                    crop.setFruitIsRipen(false);
                }
                else{
                    game.getCurrentPlayer().getBackpack().addItem(Fruit.getFruit(crop.getFruit()),1);
                    crop.setFruitIsRipen(false);
                }
            }
            return new Result(1,"fruit has not ripen");
        }
        else{
            return new Result(-1,"Does not exist any plant on the tile");
        }
    }

    public Result fertilizePlant(String fertilizeName,String directionString){

        Direction direction;
        try{
            direction = Direction.valueOf(directionString);
        } catch (IllegalArgumentException e){
            return new Result(-1,"invalid direction");
        }

        Player player = App.getCurrentGame().getCurrentPlayer();
        Farm farm = App.getCurrentGame().getWorld().getFarm(player);
        Tile tile = farm.getTileAt(player.getCurrentLocation().delta(farm.getLocation()).getLocationAt(direction));
        if(tile == null){
            return new Result(-1,"tile is not in your farm");
        }

        if(tile.getThingOnTile() instanceof GreenHouse){
            tile = tile.getTop();
        }

        if(tile.getThingOnTile() != null){
            return new Result(-1,"tile is full");
        }

        if(! tile.getFeatures().contains(Feature.PLOWED)){
            return new Result(-1,"tile has not plowed");
        }

        Fertilize fertilize = Fertilize.getFertilizer(fertilizeName);
        if(fertilize == null){
            return new Result(-1,"fertilize does not exist");
        }

        return new Result(1,"Tile fertilized successfully");
    }

    public Result foragingCrop(Player player){

        for (int i = 0 ; i < Farm.getNumberOfRows();i++){
            for(int j = 0;j < Farm.getNumberOfColumns();j++){
                Tile tile = App.getCurrentGame().getWorld().getFarm(player).getTileAt(new Location(i,j));
                if(Math.random() <= 0.01 && tile.getThingOnTile() == null){
                    tile.setThingOnTile(Fruit.getForagingCrop());
                }
            }
        }

        return new Result(1,"foragingCrop finished");
    }

    //TODO
    public Result foragingMaterial(){


        return null;
    }

    public Result foragingSeed(Player player){

        for (int i = 0 ; i < Farm.getNumberOfRows();i++){
            for(int j = 0;j < Farm.getNumberOfColumns();j++){
                Tile tile = App.getCurrentGame().getWorld().getFarm(player).getTileAt(new Location(i,j));
                if(Math.random() <= 0.01 && tile.getThingOnTile() == null && tile.getFeatures().contains(Feature.PLOWED)){
                    Crop crop = Crop.getCrop(Seed.getForagingSeed().getPlant());
                    if(!cropCanBeGiant(crop,tile.getLocation())){
                        player.getPlants().put(crop,tile);
                        tile.setThingOnTile(crop);
                    }
                }
            }
        }

        return new Result(1,"foragingSeed finished");
    }





    private Result plantingSeeds(Seed seed,Direction direction){

        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Farm farm = App.getCurrentGame().getWorld().getFarm(currentPlayer);
        Location location = currentPlayer.getCurrentLocation().getLocationAt(direction).delta(farm.getLocation());
        Tile tile = farm.getTileAt(location);

        if(tile == null){
            return new Result(-1,"tile is not in your farm");
        }

        Integer numberOfSeeds = currentPlayer.getBackpack().getNumberOfItemInBackPack().get(seed);

        if(numberOfSeeds == null || numberOfSeeds < 1){
            return new Result(-1,"You do not have Seed");
        }

        if(tile.getThingOnTile() instanceof GreenHouse greenHouse){
         tile = tile.getTop();
        }

        if(! tile.getFeatures().contains(Feature.PLOWED)){
            return new Result(-1,"tile has not plowed");
        }

        if(tile.getThingOnTile() != null){
            return new Result(-1,"tile already is full");
        }

        currentPlayer.getBackpack().removeItem(seed,1);

        Tree tree = Tree.getTree(seed.getPlant());
        if(tree != null){
            currentPlayer.getPlants().put(tree,tile);
            tile.setThingOnTile(tree);
        }

        Crop crop = Crop.getCrop(seed.getPlant());
        if(crop != null){
            if(!cropCanBeGiant(crop,location)){
                currentPlayer.getPlants().put(crop,tile);
                tile.setThingOnTile(crop);
            }
        }

        System.out.println("EROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR");
        return null;
    }

    private boolean cropCanBeGiant(Crop crop, Location location){

        Player player = App.getCurrentGame().getCurrentPlayer();
        Farm farm = App.getCurrentGame().getWorld().getFarm(player);

        Tile tile = farm.getTileAt(location);
        Tile upTile = farm.getTileAt(location.getLocationAt(Direction.UP));
        Tile downTile = farm.getTileAt(location.getLocationAt(Direction.DOWN));
        Tile leftTile = farm.getTileAt(location.getLocationAt(Direction.LEFT));
        Tile rightTile = farm.getTileAt(location.getLocationAt(Direction.RIGHT));
        Tile up_LeftTile = farm.getTileAt(location.getLocationAt(Direction.UP_LEFT));
        Tile down_LeftTile = farm.getTileAt(location.getLocationAt(Direction.DOWN_LEFT));
        Tile up_RightTile = farm.getTileAt(location.getLocationAt(Direction.UP_RIGHT));
        Tile down_RightTile = farm.getTileAt(location.getLocationAt(Direction.DOWN_RIGHT));

        Crop upCrop;
        Crop downCrop;
        Crop leftCrop;
        Crop rightCrop;
        Crop upLeftCrop;
        Crop downLeftCrop;
        Crop upRightCrop;
        Crop downRightCrop;

        if((upCrop = checkCropOnTile(crop,upTile)) != null){

            if(( leftCrop = checkCropOnTile(crop,leftTile)) != null){

                if(( upLeftCrop = checkCropOnTile(crop,up_LeftTile)) != null){

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
            else if((rightCrop = checkCropOnTile(crop,rightTile)) != null){

                if((upRightCrop = checkCropOnTile(crop,up_RightTile)) != null){

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
        else if((downCrop = checkCropOnTile(crop,downTile)) != null){

            if((leftCrop = checkCropOnTile(crop,leftTile)) != null){

                if((downLeftCrop = checkCropOnTile(crop,down_LeftTile)) != null){

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
            else if((rightCrop = checkCropOnTile(crop,rightTile)) != null){

                if((downRightCrop = checkCropOnTile(crop,down_RightTile)) != null){

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

    private Crop checkCropOnTile(Crop crop, Tile tile){
        if( tile != null && tile.getThingOnTile() != null &&
                tile.getThingOnTile() instanceof Crop upCrop && upCrop.getName().equals(crop.getName())){
            return upCrop;
        }
        return null;
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
