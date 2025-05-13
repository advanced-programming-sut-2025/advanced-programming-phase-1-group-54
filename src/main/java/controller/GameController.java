package controller;

import controller.Game.*;
import model.App;
import model.Building.Building;
import model.Building.GreenHouse;
import model.Game;
import model.alive.Player;
import model.enums.ToolType;
import model.items.Food;
import model.items.Item;
import model.items.Material;
import model.items.tools.*;
import model.map.Farm;
import model.map.Location;
import model.Result;
import model.enums.Direction;
import model.map.Tile;
import model.map.World;

import java.util.ArrayList;

public class GameController {
    public static Result exitGame() {
        App.setCurrentGame(null);
        return new Result(true, "exited game");
    }

    public static Result deleteGame() {
        return null;
    }

    // TODO add functions for "force terminate"

    public static Result nextTurn() {
        Game game = App.getCurrentGame();
        game.nextTurn();
        return new Result (true, String.format("it is %s's turn", game.getCurrentPlayer()));
    }

    public static Result showTime() {
        Game game = App.getCurrentGame();
        return new Result(true, String.format("%d o'clock", game.getDateTime().getHour()));
    }

    public static Result showDate() {
        Game game = App.getCurrentGame();
        return new Result(true, String.format("%d/%s/%d",
                game.getDateTime().getYear(), game.getDateTime().getSeason().toString().toLowerCase(), game.getDateTime().getDay()));
    }

    public static Result showDateTime() {
        return new Result(true, showDate().message() + ' ' + showTime().message());

    }

    public static Result showDayOfWeek() {
        Game game = App.getCurrentGame();
        return new Result(true, game.getDateTime().getWeekDay().toString().toLowerCase());
    }

    public static Result showSeason() {
        Game game = App.getCurrentGame();
        return new Result(true, game.getDateTime().getSeason().toString().toLowerCase());
    }

    public static Result showWeather() {
        Game game = App.getCurrentGame();
        return new Result(true, game.getCurrentWeather().toString().toLowerCase());
    }

    public static Result showWeatherForecast() {
        Game game = App.getCurrentGame();
        return new Result(true, game.getTomorrowWeather().toString().toLowerCase());
    }

    public static Result buildGreenhouse() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        Farm farm = game.getWorld().getFarm(player);

        GreenHouse greenhouse = farm.getGreenhouse();

        if (greenhouse.isBuilt())
            return new Result(true, "greenhouse is already built");

        boolean hasCoins = player.getMoney() > GreenHouse.getNeededMoney();
        if (!hasCoins) {
            return new Result(true, "you can not have enough money");
        }

        boolean hasWood = player.getBackpack().removeItem(Material.getMaterial("Wood"),
                GreenHouse.getNeededWood());

        if (!hasWood) {
            return new Result(true, "you don't have enough wood");
        }

        player.decreaseMoney(GreenHouse.getNeededMoney());
        greenhouse.setBuilt(true);

        return new Result(true, "greenhouse built successfully!");
    }

    public static Result checkForWalking(Location location) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        if (location.row() < 0 || location.column() < 0 || location.row() >= World.getNumberOfRows() || location.column() >= World.getNumberOfColumns())
            return new Result(false, "invalid location");

        int distance = game.getWorld().getDistance(player.getCurrentLocation(), location);
        if (distance == Integer.MAX_VALUE)
            return new Result(false, "Location unreachable from here!");

        return new Result(true, "Location reachable, energy needed is: " + distance / 20);
    }

    public static Result walk(Location location) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        Result result = checkForWalking(location);
        if (!result.success())
            return result;

        ArrayList<Direction> shortestPath = game.getWorld().getShortestPath(player.getCurrentLocation(), location);
        int energyNeeded = 0;

        Direction lastDirection = null;
        for (Direction direction : shortestPath) {
            energyNeeded += 1 + (lastDirection != null && direction == lastDirection ? 10 : 0);
            if (energyNeeded >= player.getEnergy() * 20) {
                player.setEnergy(0);
                return new Result(true, String.format("Player has fallen at location (%d, %d)!",
                        player.getCurrentLocation().row(), player.getCurrentLocation().column()));
            }

            player.setCurrentLocation(player.getCurrentLocation().getLocationAt(direction));
            lastDirection = direction;
        }

        player.decreaseEnergy(energyNeeded / 20);
        // Energy is integer, it is possible for user to move nearby without using any energy

        return new Result(true, "You walked successfully!");
    }

    public static Result printMap(Location location, int size) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        World world = game.getWorld();

        Building currentBuilding = null;
        if (world.getTileAt(player.getCurrentLocation()).getThingOnTile() instanceof Building building)
            currentBuilding = building;

        if (location.row() < 0 || location.column() < 0 ||
                location.row() + size - 1 >= World.getNumberOfRows() || location.column() + size - 1 >= World.getNumberOfColumns())
            return new Result(false, "invalid location and size");

        StringBuilder message = new StringBuilder();
        for (int dRow = 0; dRow < size; dRow++) {
            for (int dColumn = 0; dColumn < size; dColumn++) {
                Location tileLocation = new Location(location.row() + dRow, location.column() + dColumn);
                Tile tile = world.getTileAt(tileLocation);

                if (tile.getThingOnTile() != null && tile.getThingOnTile().equals(currentBuilding))
                    tile = currentBuilding.getTileAt(new Location(tileLocation.row() - currentBuilding.getLocation().row(),
                            tileLocation.column() - currentBuilding.getLocation().column()));

                message.append(tile.toString());
            }
            message.append("\n");
        }
        return new Result(true, message.toString());
    }

    public static Result showEnergy() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        return new Result(true, String.format("you have %d energy left.", player.getEnergy()));
    }

    public static Result showInventory() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        BackPack backPack = player.getBackpack();

        StringBuilder message = new StringBuilder();
        for (Item item : backPack.getNumberOfItemInBackPack().keySet()) {
            message.append(item.getName())
                    .append(": ")
                    .append(backPack.getNumberOfItemInBackPack().get(item))
                    .append('\n');
        }

        return new Result(true, message.toString());
    }

    public static Result throwInTrash(String itemName, int number) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        BackPack backPack = player.getBackpack();
        TrashCan trashCan = player.getTrashCan();

        return null;
    }

    public static Result equipTool(String toolName) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        ToolType toolType = ToolType.fromString(toolName);
        if (toolType == null)
            return new Result(false, "no tool with this name can be equipped");

        player.setEquippedTool(player.getTool(toolType));

        if (player.getEquippedTool() == null) {
            return new Result(false, "you don't have this equipment");
        }

        return new Result(false, "tool equipped!");
    }

    public static Result upgradeTool(String toolName) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        World world = game.getWorld();

        Location playerLocation = player.getCurrentLocation();
        // TODO if (world.getTileAt(playerLocation).getThingOnTile() instanceof )

        return null;
    }

    public static Result useTool(Direction direction) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Farm farm = game.getWorld().getFarm(player);

        // TODO check if other person farm ...

        Location location = player.getCurrentLocation().getLocationAt(direction).delta(farm.getLocation());
        Tile tile = farm.getTileAt(location);

        if (tile == null) {
            return new Result(false, "this tile doesn't exist in your farm");
        }

        Tool equippedTool = player.getEquippedTool();
        if (equippedTool == null) {
            return new Result(false, "you don't have any tool equipped");
        }

        boolean successfulUse = equippedTool.checkSuccess(tile);
        int energyNeeded = equippedTool.getEnergyNeededPerUse();

        if (!successfulUse && equippedTool instanceof Pickaxe) {}

        return null;
    }

    public static Result plant(String seedName, String directionString) {
        return PlantsController.planting(seedName, directionString);
    }

    public static Result showPlant(Location location) {
        return PlantsController.showPlant(location);
    }

    public static Result fertilize(String fertilizerName, String direction) {
        return PlantsController.fertilizePlant(fertilizerName, direction);
    }

    public static Result howMuchWater() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        WateringCan wateringCan = (WateringCan) player.getTool(ToolType.WATERING_CAN);

        return new Result(true, "water: " + wateringCan.getCurrentWater());
    }

    public static Result showRecipesForCrafting() {
        return CraftingController.showCraftingRecipe();
    }

    public static Result craft(String itemName) {
        return CraftingController.crafting(itemName);
    }

    public static Result placeItem(String itemName, String direction) {
        return CraftingController.placeArtisan(itemName, direction);
    }

    public static Result putInRefrigerator(String itemName, int number) {
        return FoodController.moveToRefrigerator(itemName, number);
    }

    public static Result pickInRefrigerator(String itemName, int number) {
        return FoodController.moveToBackpack(itemName, number);
    }

    public static Result showRecipesForCooking() {
        return FoodController.showFoodRecipes();
    }

    public static Result cookingPrepare(String recipeName) {
        return FoodController.cooking(recipeName);
    }

    public static Result eatFood(String foodName) {
        return FoodController.eatFood(foodName);
    }

    public static Result buildBuilding(String buildingName, Location location) {
        return null;
    }

    public static Result buyAnimal(String animalType, String animalName) {
        return null;
    }

    public static Result petAnimal(String animalName) {
        return AnimalController.pet(animalName);
    }

    public static Result showAnimals() {
        return AnimalController.showAnimals();
    }

    public static Result shepherdAnimals(String animalName, Location location) {
        return AnimalController.moveAnimal(animalName, location);
    }

    public static Result feedHay(String animalName) {
        return AnimalController.feedAnimal(animalName);
    }

    public static Result collectProduces(String animalName) {
        return AnimalController.getAnimalProduce(animalName);
    }

    public static Result getAnimalProduce() {
        return AnimalController.showProducedAnimals();
    }

    public static Result sellAnimal(String animalName) {
        return AnimalController.sellAnimal(animalName);
    }

    public static Result startFishing(String fishingPoleName) {
        return FishingController.fishing(fishingPoleName);
    }

    public static Result useArtisan(String artisanName, String itemName) {
        return CraftingController.producing(artisanName, itemName);
    }

    public static Result getArtisan(String artisanName) {
        return CraftingController.getProduceFromArtisan(artisanName);
    }

    public static Result showAllShopProducts() {
        return null;
    }

    public static Result showAllAvailableShopProducts() {
        return null;
    }

    public static Result purchaseProduct(String productName, int count) {
        return null;
    }

    public static Result sellProduct(String productName, int count) {
        return null;
    }

    public static Result showFriendshipsWithPlayers() {
        return null;
    }

    public static Result talkToPlayer(String username) {
        return null;
    }

    public static Result showTalkHistory(String username) {
        return null;
    }

    public static Result giveGiftToPlayer(String username, String itemName, int amount) {
        return null;
    }

    public static Result showGiftList() {
        return null;
    }

    public static Result rateGift(int giftNumber, int rate) {
        return null;
    }

    public static Result showGiftHistory(String username) {
        return null;
    }

    public static Result hugPlayer(String username) {
        return null;
    }

    public static Result buyFlower(String username) {
        return null;
    }

    public static Result askForMarriage(String username, String ringName) {
        return null;
    }

    public static Result acceptMarriage(String username) {
        return null;
    }

    public static Result rejectMarriage(String username) {
        return null;
    }

    public static Result addTrade(String username, String tradeType, String itemName, int amount,
                                  int price, String targetItemName, int targetItemAmount) {
        return null;
    }

    public static Result showTradeList() {
        return null;
    }

    public static Result acceptTrade(int tradeId) {
        return null;
    }

    public static Result rejectTrade(int tradeId) {
        return null;
    }

    public static Result showTradeHistory() {
        return null;
    }

    public static Result meetNPC(String npcName) {
        return null;
    }

    public static Result giveGiftToNPC(String npcName) {
        return null;
    }

    public static Result showFriendshipsWithNPCs() {
        return null;
    }

    public static Result showQuestsList() {
        return null;
    }

    public static Result finishQuest(int questIndex) {
        return null;
    }
}
