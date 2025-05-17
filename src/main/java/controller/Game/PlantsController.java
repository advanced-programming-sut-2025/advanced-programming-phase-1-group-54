package controller.Game;

import model.App;
import model.enums.Season;
import model.enums.SkillType;
import model.map.*;
import model.Game;
import model.Placeable;
import model.Result;
import model.lives.Player;
import model.enums.Direction;
import model.enums.Feature;
import model.items.Fertilize;
import model.items.plants.*;

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

    public static Result planting(String seedName, Direction direction) {
        if (direction == null)
            return new Result(false, "invalid direction");

        Seed seed;
        if (seedName.equals("Mixed Seeds")) {
            Result result = plantingSeeds(Seed.getMixedSeed(App.getCurrentGame().getDateTime().getSeason()), direction);
            if (result.success()) {
                if (!App.getCurrentGame().getCurrentPlayer().getBackpack().
                        removeItem(Seed.getSeed("Mixed Seeds"), 1)) {
                    return new Result(-1, "you don't enough " + seedName);
                }
            }
            return result;
        } else {
            seed = Seed.getSeed(seedName);
            if (seed == null) {
                return new Result(-1, "seed does not exist");
            }
            Result result = plantingSeeds(seed, direction);
            if (result.success()) {
                if (!App.getCurrentGame().getCurrentPlayer().getBackpack().removeItem(seed, 1)) {
                    return new Result(-1, "you don't enough " + seedName);
                }
            }
            return result;
        }

    }

    public static Result showPlant(Location location) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Tile tile = App.getCurrentGame().getWorld().getFarmAt(player.getCurrentLocation()).getTileAt(location);

        if (tile == null) {
            return new Result(-1, "tile is not in your farm");
        }

        Placeable placeable = tile.getThingOnTile();
        if (placeable instanceof Plant plant) {
            StringBuilder output = new StringBuilder();
            output.append(plant.toString());
            output.append("\ndays until harvest : ");
            if (plant.isFruitIsRipen()) {
                output.append("0");
            } else if (plant.getCurrentStage() > plant.getMaxStages()) {
                output.append(plant.getRegrowthTime() - plant.getCurrentStage());
            } else {
                output.append(plant.getTotalHarvestTime() - plant.getCurrentStage());
            }
            output.append("\nCurrent stage : ").append(plant.getCurrentStage());
            output.append("\nWatered today : ").append(plant.isWatered());
            output.append("\nFertilized : ").append(plant.isFertilized());

            return new Result(1, output.toString());

        } else {
            return new Result(-1, "Does not exist any plant on the tile");
        }

    }

    static Result harvestPlant(Direction direction) {
        Game game = App.getCurrentGame();
        Location location = game.getCurrentPlayer().getCurrentLocation().getLocationAt(direction);
        Farm farm = game.getWorld().getFarmAt(game.getCurrentPlayer().getCurrentLocation());
        Tile tile = farm.getTileAt(location.delta(farm.getLocation()));

        if (tile.getThingOnTile() != null && tile.getThingOnTile() instanceof GreenHouse) {
            tile = tile.getTop();
        }

        Placeable placeable = tile.getThingOnTile();
        if (placeable == null) {
            return new Result(-1, "Does not exist any plant on the tile");
        }

        if (placeable instanceof Fruit fruit) {
            Result addedToBackPack = ToolsController.addToBackPack(game.getCurrentPlayer().
                    getBackpack(), fruit, 1);
            if (addedToBackPack.success()) {
                game.getCurrentPlayer().getSkill(SkillType.FARMING).addXP(5);
                tile.setThingOnTile(null);
            }
            return addedToBackPack;
        }

        if (placeable instanceof Tree tree) {
            if (tree.isFruitIsRipen()) {
                Result addedToBackPack = ToolsController.addToBackPack(game.getCurrentPlayer().getBackpack(),
                        Fruit.getFruit(tree.getFruit()), 1);

                if (addedToBackPack.success()) {
                    game.getCurrentPlayer().getSkill(SkillType.FARMING).addXP(5);
                    tree.setFruitIsRipen(false);
                }

                return addedToBackPack;
            }
            return new Result(-1, "fruit has not ripen");

        }

        if (placeable instanceof Crop crop) {
            if (crop.isFruitIsRipen()) {
                if (crop.getGiantDirection() != null) {
                    Result addedToBackPack = ToolsController.addToBackPack(game.getCurrentPlayer().getBackpack(),
                            Fruit.getFruit(crop.getFruit()), 10);

                    if (addedToBackPack.success()) {
                        game.getCurrentPlayer().getSkill(SkillType.FARMING).addXP(5);
                        crop.setFruitIsRipen(false);
                        if (crop.isOneTime()) {
                            tile.setThingOnTile(null);
                        }

                        for (int i = 0; i < 3; i++) {
                            tile = game.getWorld().getTileAt(location.getLocationAt(crop.getGiantDirection()));
                            crop = (Crop) tile.getThingOnTile();
                            crop.setFruitIsRipen(false);
                            if (crop.isOneTime()) {
                                tile.setThingOnTile(null);
                            }
                        }
                    }

                    return addedToBackPack;
                } else {
                    Result addedToBackPack = ToolsController.addToBackPack(game.getCurrentPlayer().getBackpack(),
                            Fruit.getFruit(crop.getFruit()), 1);
                    if (addedToBackPack.success()) {
                        game.getCurrentPlayer().getSkill(SkillType.FARMING).addXP(5);
                        crop.setFruitIsRipen(false);
                        if (crop.isOneTime()) {
                            tile.setThingOnTile(null);
                        }
                    }

                    return addedToBackPack;
                }
            }
            return new Result(-1, "fruit has not ripen");
        }

        return new Result(-1, "Does not exist any plant on the tile");

    }

    public static Result giveWater(Location location) {
        Game game = App.getCurrentGame();
        Farm farm = game.getWorld().getFarmAt(game.getCurrentPlayer().getCurrentLocation());
        Tile tile = farm.getTileAt(location.delta(farm.getLocation()));

        if (tile.getThingOnTile() != null && tile.getThingOnTile() instanceof GreenHouse) {
            tile = tile.getTop();
        }

        if (!(tile.getThingOnTile() instanceof Plant plant)) {
            return new Result(true, "You wasted some water");
        }

        plant.setWatered(true);

        if (plant instanceof Crop crop && crop.getGiantDirection() != null) {
            crop = (Crop) game.getWorld().getTileAt(location.getLocationAt(crop.getGiantDirection())).getThingOnTile();
            crop.setWatered(true);
            crop = (Crop) game.getWorld().getTileAt(location.getLocationAt(crop.getGiantDirection())).getThingOnTile();
            crop.setWatered(true);
            crop = (Crop) game.getWorld().getTileAt(location.getLocationAt(crop.getGiantDirection())).getThingOnTile();
            crop.setWatered(true);
        }

        return new Result(true, "You watered the plant on this tile");
    }

    public static Result fertilizePlant(String fertilizeName, Direction direction) {
        if (direction == null)
            return new Result(false, "invalid direction");

        Player player = App.getCurrentGame().getCurrentPlayer();
        Farm farm = App.getCurrentGame().getWorld().getFarmAt(player.getCurrentLocation());
        if (farm == null) {
            return new Result(-1, "You must be in a farm to do this action");
        }
        Tile tile = farm.getTileAt(player.getCurrentLocation().delta(farm.getLocation()).getLocationAt(direction));
        if (tile == null) {
            return new Result(-1, "tile is not in this farm");
        }

        if (tile.getThingOnTile() instanceof GreenHouse) {
            tile = tile.getTop();
        }

        if (tile.getThingOnTile() != null) {
            return new Result(-1, "tile is full");
        }

        if (!tile.getFeatures().contains(Feature.PLOWED)) {
            return new Result(-1, "tile has not plowed");
        }

        Fertilize fertilize = Fertilize.getFertilizer(fertilizeName);
        if (fertilize == null) {
            return new Result(-1, "fertilize does not exist");
        }

        return new Result(1, "Tile fertilized successfully");
    }

    public static void foragingSeed(Farm farm, Season season) {
        for (int i = 0; i < Farm.getNumberOfRows(); i++) {
            for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                Tile tile = farm.getTileAt(new Location(i, j));
                if (Math.random() <= 0.01 && tile.getThingOnTile() == null && tile.getFeatures().contains(Feature.PLOWED)) {
                    Crop crop = Crop.getCrop(Seed.getForagingSeed(season).getPlant());
                    if (!cropCanBeGiant(crop, tile.getLocation())) {
                        tile.setThingOnTile(crop);
                    }
                }
            }
        }
    }


    private static Result plantingSeeds(Seed seed, Direction direction) {

        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();

        Farm farm = App.getCurrentGame().getWorld().getFarmAt(currentPlayer.getCurrentLocation());
        if (farm == null) {
            return new Result(-1, "You are not in any farm");
        }

        Location location = currentPlayer.getCurrentLocation().getLocationAt(direction).delta(farm.getLocation());
        Tile tile = farm.getTileAt(location);

        if (tile == null) {
            return new Result(-1, "tile is not in this farm");
        }

        boolean inGreenHouse = false;
        if (tile.getThingOnTile() instanceof GreenHouse) {
            inGreenHouse = true;
            tile = tile.getTop();
        }

        if (!tile.getFeatures().contains(Feature.PLOWED)) {
            return new Result(-1, "tile has not plowed");
        }

        if (tile.getThingOnTile() != null) {
            return new Result(-1, "tile already is full");
        }

        Tree tree = Tree.getTree(seed.getPlant());
        if (tree != null) {
            if(! inGreenHouse){
                if(tree.containSeason(App.getCurrentGame().getDateTime().getSeason())) {
                    return new Result(-1, "You can't plant this plant in this season");
                }
            }
            if (tile.hasFeature(Feature.SPEED_FERTILIZE)) {
                tree.nextDayUpdate();
                tile.removeFeature(Feature.SPEED_FERTILIZE);
            }
            if (tile.hasFeature(Feature.WATER_FERTILIZE)) {
                tree.setFertilized(true);
                tile.removeFeature(Feature.WATER_FERTILIZE);
            }
            tile.setThingOnTile(tree);
        }

        Crop crop = Crop.getCrop(seed.getPlant());
        if (crop != null) {
            if(! inGreenHouse){
                if(crop.containSeason(App.getCurrentGame().getDateTime().getSeason())) {
                    return new Result(-1, "You can't plant this plant in this season");
                }
            }
            if (tile.hasFeature(Feature.SPEED_FERTILIZE)) {
                crop.nextDayUpdate();
                tile.removeFeature(Feature.SPEED_FERTILIZE);
            }
            if (tile.hasFeature(Feature.WATER_FERTILIZE)) {
                crop.setFertilized(true);
                tile.removeFeature(Feature.WATER_FERTILIZE);
            }
            if (!cropCanBeGiant(crop, location)) {
                tile.setThingOnTile(crop);
            }
        }

        tile.removeFeature(Feature.PLOWED);

        return new Result(1, "Planted seed successfully");
    }

    private static boolean cropCanBeGiant(Crop crop, Location location) {
        if (!crop.isGiantPossible())
            return false;

        Farm farm = App.getCurrentGame().getWorld().getFarmAt(location);

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

        if ((upCrop = checkCropOnTile(crop, upTile)) != null) {

            if ((leftCrop = checkCropOnTile(crop, leftTile)) != null) {

                if ((upLeftCrop = checkCropOnTile(crop, up_LeftTile)) != null) {

                    crop = compareCropsGrowth(crop, upCrop, leftCrop, upLeftCrop).clone();
                    upCrop = crop.clone();
                    leftCrop = crop.clone();
                    upLeftCrop = crop.clone();

                    crop.setGiantDirection(Direction.LEFT);
                    upCrop.setGiantDirection(Direction.DOWN);
                    leftCrop.setGiantDirection(Direction.UP);
                    upLeftCrop.setGiantDirection(Direction.RIGHT);

                    tile.setThingOnTile(crop);
                    upTile.setThingOnTile(upCrop);
                    leftTile.setThingOnTile(leftCrop);
                    up_LeftTile.setThingOnTile(upLeftCrop);

                    return true;

                }

            } else if ((rightCrop = checkCropOnTile(crop, rightTile)) != null) {

                if ((upRightCrop = checkCropOnTile(crop, up_RightTile)) != null) {

                    crop = compareCropsGrowth(crop, upCrop, rightCrop, upRightCrop).clone();
                    upCrop = crop.clone();
                    rightCrop = crop.clone();
                    upRightCrop = crop.clone();

                    crop.setGiantDirection(Direction.UP);
                    upCrop.setGiantDirection(Direction.RIGHT);
                    rightCrop.setGiantDirection(Direction.LEFT);
                    upRightCrop.setGiantDirection(Direction.DOWN);

                    tile.setThingOnTile(crop);
                    upTile.setThingOnTile(upCrop);
                    rightTile.setThingOnTile(rightCrop);
                    up_RightTile.setThingOnTile(upRightCrop);

                    return true;
                }

            }

        } else if ((downCrop = checkCropOnTile(crop, downTile)) != null) {

            if ((leftCrop = checkCropOnTile(crop, leftTile)) != null) {

                if ((downLeftCrop = checkCropOnTile(crop, down_LeftTile)) != null) {

                    crop = compareCropsGrowth(crop, downCrop, leftCrop, downLeftCrop).clone();
                    downCrop = crop.clone();
                    leftCrop = crop.clone();
                    downLeftCrop = crop.clone();

                    crop.setGiantDirection(Direction.DOWN);
                    downCrop.setGiantDirection(Direction.LEFT);
                    leftCrop.setGiantDirection(Direction.RIGHT);
                    downLeftCrop.setGiantDirection(Direction.UP);

                    tile.setThingOnTile(crop);
                    downTile.setThingOnTile(downCrop);
                    leftTile.setThingOnTile(leftCrop);
                    down_LeftTile.setThingOnTile(downLeftCrop);

                    return true;
                }

            } else if ((rightCrop = checkCropOnTile(crop, rightTile)) != null) {

                if ((downRightCrop = checkCropOnTile(crop, down_RightTile)) != null) {

                    crop = compareCropsGrowth(crop, downCrop, rightCrop, downRightCrop).clone();
                    downCrop = crop.clone();
                    rightCrop = crop.clone();
                    downRightCrop = crop.clone();

                    crop.setGiantDirection(Direction.RIGHT);
                    downCrop.setGiantDirection(Direction.UP);
                    rightCrop.setGiantDirection(Direction.DOWN);
                    downRightCrop.setGiantDirection(Direction.LEFT);

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

    private static Crop checkCropOnTile(Crop crop, Tile tile) {
        if (tile != null && tile.getThingOnTile() != null &&
                tile.getThingOnTile() instanceof Crop upCrop && upCrop.getName().equals(crop.getName())) {
            return upCrop;
        }
        return null;
    }

    private static Crop compareCropsGrowth(Crop crop1, Crop crop2, Crop crop3, Crop crop4) {

        Crop[] crops = {crop1, crop2, crop3, crop4};
        int[] growth = {0, 0, 0, 0};
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i <= crops[j].getCurrentStage(); i++) {
                growth[j] += crop1.getStages()[i];
            }
            growth[j] += crop1.getDaysInCurrentStage();
        }

        int[] indexes = {0, 0, 0, 0};
        for (int i = 0; i < crops.length; i++) {
            for (int j = 0; j < crops.length; j++) {
                if (growth[i] < growth[j]) {
                    indexes[i]++;
                }
            }
        }

        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] == 0) {
                return crops[i];
            }
        }

        return null;

    }

}
