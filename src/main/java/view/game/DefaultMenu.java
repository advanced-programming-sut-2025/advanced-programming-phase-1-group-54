package view.game;

import controller.Game.*;
import model.Result;
import model.enums.Direction;
import model.enums.SubMenu;
import model.enums.commands.Command;
import model.enums.commands.GameCommand;
import model.map.Location;

import java.util.Scanner;

public class DefaultMenu implements GameSubMenu {
    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();

        if (checkGameCommand(input, scanner))
            return;

        if (checkCheatCode(input, scanner))
            return;

        invalidCommand();
    }

    private boolean checkGameCommand(String input, Scanner scanner) {
        if (GameCommand.EXIT_GAME.matches(input))
            handleExitGame();
        else if (GameCommand.NEXT_TURN.matches(input))
            handleNextTurn(scanner);
        else if (GameCommand.TIME.matches(input))
            handleShowTime();
        else if (GameCommand.DATE.matches(input))
            handleShowDate();
        else if (GameCommand.DATETIME.matches(input))
            handleShowDateTime();
        else if (GameCommand.DAY_OF_THE_WEEK.matches(input))
            handleShowDayOfWeek();
        else if (GameCommand.SEASON.matches(input))
            handleShowSeason();
        else if (GameCommand.WEATHER.matches(input))
            handleShowWeather();
        else if (GameCommand.WEATHER_FORECAST.matches(input))
            handleShowWeatherForecast();
        else if (GameCommand.GREENHOUSE_BUILD.matches(input))
            handleGreenHouseBuild();
        else if (GameCommand.WALK.matches(input))
            handleWalk(input, scanner);
        else if (GameCommand.PRINT_MAP.matches(input))
            handlePrintMap(input);
        else if (GameCommand.HELP_READ_MAP.matches(input))
            handleHelpReadMap();
        else if (GameCommand.SHOW_ENERGY.matches(input))
            handleShowEnergy();
        else if (GameCommand.SHOW_INVENTORY.matches(input))
            handleShowInventory();
        else if (GameCommand.THROW_IN_TRASH.matches(input))
            handleThrowInTrash(input);
        else if (GameCommand.EQUIP_TOOL.matches(input))
            handleEquipTool(input);
        else if (GameCommand.SHOW_CURRENT_TOOL.matches(input))
            handleShowCurrentTool();
        else if (GameCommand.SHOW_AVAILABLE_TOOLS.matches(input))
            handleShowAvailableTools();
        else if (GameCommand.USE_TOOL.matches(input))
            handleUseTool(input);
        else if (GameCommand.UPGRADE_TOOL.matches(input))
            handleUpgradeTool(input);
        else if (GameCommand.SHOW_PLANT_INFO.matches(input))
            handleShowPlantInfo(input);
        else if (GameCommand.PLANTING.matches(input))
            handlePlanting(input);
        else if (GameCommand.SHOW_PLANT_AT_LOCATION.matches(input))
            handleShowPlantAtLocation(input);
        else if (GameCommand.FERTILIZE.matches(input))
            handleFertilize(input);
        else if (GameCommand.SHOW_WATER.matches(input))
            handleShowWater();
        else if (GameCommand.SHOW_CRAFTING_RECIPES.matches(input))
            handleShowCraftingRecipes();
        else if (GameCommand.CRAFTING.matches(input))
            handleCrafting(input);
        else if (GameCommand.PLACE_ITEM.matches(input))
            handlePlaceItem(input);
        else if (GameCommand.REFRIGERATOR_ACTION.matches(input))
            handleRefrigeratorAction(input);
        else if (GameCommand.SHOW_COOKING_RECIPES.matches(input))
            handleShowCookingRecipes();
        else if (GameCommand.COOKING.matches(input))
            handleCooking(input);
        else if (GameCommand.EATING.matches(input))
            handleEating(input);
        else if (GameCommand.BUILD_ANIMAL_HOUSE.matches(input))
            handleBuildAnimalHouse(input);
        else if (GameCommand.BUY_ANIMAL.matches(input))
            handleBuyAnimal(input);
        else if (GameCommand.PET_ANIMAL.matches(input))
            handlePetAnimal(input);
        else if (GameCommand.SHOW_ANIMALS.matches(input))
            handleShowAnimals();
        else if (GameCommand.FEED_ANIMAL.matches(input))
            handleFeedAnimal(input);
        else if (GameCommand.MOVE_ANIMAL.matches(input))
            handleMoveAnimal(input);
        else if (GameCommand.SHOW_ANIMAL_PRODUCES.matches(input))
            handleShowAnimalProduces();
        else if (GameCommand.COLLECT_ANIMAL_PRODUCE.matches(input))
            handleCollectAnimalProduce(input);
        else if (GameCommand.SELL_ANIMAL.matches(input))
            handleSellAnimal(input);
        else if (GameCommand.FISHING.matches(input))
            handleFishing(input);
        else if (GameCommand.START_PRODUCER_ARTISAN.matches(input))
            handleStartProducerArtisan(input);
        else if (GameCommand.GET_PRODUCT_ARTISAN.matches(input))
            handleGetProductArtisan(input);
        else if (GameCommand.SHOW_ALL_PRODUCTS.matches(input))
            handleShowAllProducts();
        else if (GameCommand.SHOW_AVAILABLE_PRODUCTS.matches(input))
            handleShowAvailableProducts();
        else if (GameCommand.PURCHASE_ITEM.matches(input))
            handlePurchaseItem(input);
        else if (GameCommand.SELLING.matches(input))
            handleSelling(input);
        else if (GameCommand.SHOW_FRIENDSHIPS.matches(input))
            handleShowFriendships();
        else if (GameCommand.TALKING.matches(input))
            handleTalking(input);
        else if (GameCommand.SHOW_TALK_HISTORY.matches(input))
            handleShowTalkHistory(input);
        else if (GameCommand.GIFT.matches(input))
            handleGift(input);
        else if (GameCommand.SHOW_GIFTS_FROM_PLAYER.matches(input))
            handleShowGiftsFromPlayer(input);
        else if (GameCommand.HUGGING.matches(input))
            handleHugging(input);
        else if (GameCommand.GIVE_FLOWER.matches(input))
            handleGiveFlower(input);
        else if (GameCommand.ASK_MARRIAGE.matches(input))
            handleAskMarriage(input);
        else if (GameCommand.START_TRADING.matches(input))
            goToMenu(SubMenu.TRADING);
        else
            return false;

        return true;
    }

    private boolean checkCheatCode(String input, Scanner scanner) {
        return false;
    }

    private void handleExitGame() {
        showResult(CommonGameController.exitGame());
    }

    private void handleNextTurn(Scanner scanner) {
        showResult(CommonGameController.nextTurn());
        // TODO handle Marriage, handle Gift (VIEW)
    }

    private void handleShowDate() {
        showResult(CommonGameController.showDate());
    }

    private void handleShowDateTime() {
        showResult(CommonGameController.showTime());
    }

    private void handleShowTime() {
        showResult(CommonGameController.showDateTime());
    }

    private void handleShowDayOfWeek() {
        showResult(CommonGameController.showDayOfWeek());
    }

    private void handleShowSeason() {
        showResult(CommonGameController.showSeason());
    }

    private void handleShowWeather() {
        showResult(CommonGameController.showWeather());
    }

    private void handleShowWeatherForecast() {
        showResult(CommonGameController.showWeatherForecast());
    }

    private void handleGreenHouseBuild() {
        showResult(MapController.buildGreenhouse());
    }

    private void handleWalk(String input, Scanner scanner) {
        GameCommand command = GameCommand.WALK;
        Location location = new Location(Integer.parseInt(command.getGroup(input, "x")),
                Integer.parseInt(command.getGroup(input, "y")));

        Result checkForWalkingResult = MapController.checkForWalking(location);
        showResult(checkForWalkingResult);

        if (!checkForWalkingResult.success())
            return;

        boolean confirmation = askForConfirmation(scanner);
        if (confirmation)
            showResult(MapController.walk(location));
    }

    private void handlePrintMap(String input) {
        Command command = GameCommand.PRINT_MAP;
        Location location = new Location(Integer.parseInt(command.getGroup(input, "x")),
                Integer.parseInt(command.getGroup(input, "y")));

        int size = Integer.parseInt(command.getGroup(input, "size"));

        showResult(MapController.printMap(location, size));
    }

    private void handleHelpReadMap() {
        showResult(MapController.helpReadMap());
    }

    private void handleShowEnergy() {
        showResult(CommonGameController.showEnergy());
    }

    private void handleShowInventory() {
        showResult(ToolsController.showInventory());
    }

    private void handleThrowInTrash(String input) {
        Command command = GameCommand.THROW_IN_TRASH;
        String itemName = command.getGroup(input, "itemName");
        int number = Integer.parseInt(command.getGroup(input, "number"));
        showResult(ToolsController.throwInTrash(itemName, number));
    }

    private void handleEquipTool(String input) {
        Command command = GameCommand.EQUIP_TOOL;
        String toolName = command.getGroup(input, "toolName");
        showResult(ToolsController.equipTool(toolName));
    }

    private void handleShowCurrentTool() {
        showResult(CommonGameController.showCurrentTool());
    }

    private void handleShowAvailableTools() {
        showResult(CommonGameController.showAvailableTools());
    }

    private void handleUseTool(String input) {
        Command command = GameCommand.USE_TOOL;
        Direction direction = Direction.fromString(command.getGroup(input, "direction"));
        showResult(ToolsController.useTool(direction));
    }

    private void handleUpgradeTool(String input) {
        Command command = GameCommand.UPGRADE_TOOL;
        String toolName = command.getGroup(input, "toolName");
        showResult(ShopsController.upgradeTool(toolName));
    }


    private void handleShowPlantInfo(String input) {
        Command command = GameCommand.SHOW_PLANT_INFO;
        String plantName = command.getGroup(input, "plantName");
        showResult(PlantsController.showInfo(plantName));
    }

    private void handlePlanting(String input) {
        Command command = GameCommand.PLANTING;
        String seedName = command.getGroup(input, "seedName");
        Direction direction = Direction.fromString(command.getGroup(input, "direction"));
        showResult(PlantsController.planting(seedName, direction));
    }

    private void handleShowPlantAtLocation(String input) {
        Command command = GameCommand.SHOW_PLANT_AT_LOCATION;
        Location location = new Location(
                Integer.parseInt(command.getGroup(input, "x")),
                Integer.parseInt(command.getGroup(input, "y"))
        );
        showResult(PlantsController.showPlant(location));
    }

    private void handleFertilize(String input) {
        Command command = GameCommand.FERTILIZE;
        String fertilizer = command.getGroup(input, "fertilizer");
        Direction direction = Direction.fromString(input);
        showResult(PlantsController.fertilizePlant(fertilizer, direction));
    }

    private void handleShowWater() {
        showResult(ToolsController.howMuchWater());
    }

    private void handleShowCraftingRecipes() {
        showResult(CraftingController.showCraftingRecipe());
    }

    private void handleCrafting(String input) {
        Command command = GameCommand.CRAFTING;
        String itemName = command.getGroup(input, "itemName");
        showResult(CraftingController.crafting(itemName));
    }

    private void handlePlaceItem(String input) {
        Command command = GameCommand.CRAFTING;
        String itemName = command.getGroup(input, "itemName");
        Direction direction = Direction.fromString(command.getGroup(input, "direction"));
        showResult(CraftingController.placeArtisan(itemName, direction));
    }

    private void handleRefrigeratorAction(String input) {
        Command command = GameCommand.REFRIGERATOR_ACTION;
        String action = command.getGroup(input, "action");
        String itemName = command.getGroup(input, "itemName");
        if (action.equals("put"))
            showResult(FoodController.moveToRefrigerator(itemName, 1));
        else
            showResult(FoodController.moveToBackpack(itemName, 1));
    }

    private void handleShowCookingRecipes() {
        showResult(FoodController.showFoodRecipes());
    }

    private void handleCooking(String input) {
        Command command = GameCommand.COOKING;
        String recipeName = command.getGroup(input, "recipeName");
        showResult(FoodController.cooking(recipeName));
    }

    private void handleEating(String input) {
        Command command = GameCommand.EATING;
        String foodName = command.getGroup(input, "foodName");
        showResult(FoodController.eatFood(foodName));
    }

    private void handleBuildAnimalHouse(String input) {
        Command command = GameCommand.BUILD_ANIMAL_HOUSE;
        String buildingName = command.getGroup(input, "buildingName");
        Location location = new Location(
                Integer.parseInt(command.getGroup(input, "x")),
                Integer.parseInt(command.getGroup(input, "y"))
        );
        showResult(ShopsController.buildBuilding(buildingName, location));
    }

    private void handleBuyAnimal(String input) {
        Command command = GameCommand.BUY_ANIMAL;
        String animalName = command.getGroup(input, "animalName");
        String name = command.getGroup(input, "name");
        showResult(ShopsController.buyAnimal(animalName, name));
    }

    private void handlePetAnimal(String input) {
        Command command = GameCommand.PET_ANIMAL;
        String name = command.getGroup(input, "name");
        showResult(AnimalController.pet(name));
    }

    private void handleFeedAnimal(String input) {
        Command command = GameCommand.FEED_ANIMAL;
        String animalName = command.getGroup(input, "animalName");
        showResult(AnimalController.feedAnimal(animalName));
    }

    private void handleShowAnimals() {
        showResult(AnimalController.showAnimals());
    }

    private void handleMoveAnimal(String input) {
        Command command = GameCommand.MOVE_ANIMAL;
        String name = command.getGroup(input, "name");
        Location location = new Location(
                Integer.parseInt(command.getGroup(input, "x")),
                Integer.parseInt(command.getGroup(input, "y"))
        );
        showResult(AnimalController.moveAnimal(name, location));
    }

    private void handleShowAnimalProduces() {
        showResult(AnimalController.showProducedAnimals());
    }

    private void handleCollectAnimalProduce(String input) {
        Command command = GameCommand.COLLECT_ANIMAL_PRODUCE;
        String name = command.getGroup(input, "name");
        showResult(AnimalController.getAnimalProduce(name));
    }

    private void handleSellAnimal(String input) {
        Command command = GameCommand.SELL_ANIMAL;
        String name = command.getGroup(input, "name");
        showResult(AnimalController.sellAnimal(name));
    }

    private void handleFishing(String input) {
        Command command = GameCommand.FISHING;
        String poleName = command.getGroup(input, "poleName");
        showResult(FishingController.fishing(poleName));
    }

    private void handleStartProducerArtisan(String input) {
        Command command = GameCommand.START_PRODUCER_ARTISAN;
        String artisanName = command.getGroup(input, "artisanName");
        String produceName = command.getGroup(input, "produceName");
        showResult(CraftingController.producing(artisanName, produceName));
    }

    private void handleGetProductArtisan(String input) {
        Command command = GameCommand.GET_PRODUCT_ARTISAN;
        String artisanName = command.getGroup(input, "artisanName");
        showResult(CraftingController.getProduceFromArtisan(artisanName));
    }

    private void handleShowAllProducts() {
        showResult(ShopsController.showAllProducts());
    }

    private void handleShowAvailableProducts() {
        showResult(ShopsController.showAvailableProducts());
    }

    private void handlePurchaseItem(String input) {
        Command command = GameCommand.PURCHASE_ITEM;
        String productName = command.getGroup(input, "productName");
        String countString = command.getGroup(input, "count");
        int count = (countString != null ? Integer.parseInt(countString) : 1);
        showResult(ShopsController.purchaseItem(productName, count));
    }

    private void handleSelling(String input) {
        Command command = GameCommand.SELLING;
        String productName = command.getGroup(input, "productName");
        String countString = command.getGroup(input, "count");
        int count = (countString != null ? Integer.parseInt(countString) : 1);
        showResult(CommonGameController.sell(productName, count));
    }

    private void handleShowFriendships() {
        showResult(FriendShipController.showFriendships());
    }

    private void handleTalking(String input) {
        Command command = GameCommand.TALKING;
        String username = command.getGroup(input, "username");
        String message = command.getGroup(input, "message");
        showResult(FriendShipController.talk(username, message));
    }

    private void handleShowTalkHistory(String input) {
        Command command = GameCommand.SHOW_TALK_HISTORY;
        String username = command.getGroup(input, "username");
        showResult(FriendShipController.showTalkHistory(username));
    }

    private void handleGift(String input) {
        Command command = GameCommand.GIFT;
        String username = command.getGroup(input, "username");
        String itemName = command.getGroup(input, "itemName");
        int amount = Integer.parseInt(command.getGroup(input, "amount"));
        showResult(FriendShipController.gift(username, itemName, amount));
    }

    private void handleRateGift(String input) {
        Command command = GameCommand.RATE_GIFT;
        int number = Integer.parseInt(command.getGroup(input, "number"));
        int rate = Integer.parseInt(command.getGroup(input, "rate"));
        showResult(FriendShipController.rateGift(number, rate));
    }

    private void handleShowGiftList() {
        showResult(FriendShipController.showGiftList());
    }

    private void handleShowGiftsFromPlayer(String input) {
        Command command = GameCommand.SHOW_GIFTS_FROM_PLAYER;
        String username = command.getGroup(input, "username");
        showResult(FriendShipController.showGiftsFrom(username));
    }

    private void handleHugging(String input) {
        Command command = GameCommand.HUGGING;
        String username = command.getGroup(input, "username");
        showResult(FriendShipController.hug(username));
    }

    private void handleGiveFlower(String input) {
        Command command = GameCommand.GIVE_FLOWER;
        String username = command.getGroup(input, "username");
        showResult(FriendShipController.flower(username));
    }

    private void handleAskMarriage(String input) {
        Command command = GameCommand.ASK_MARRIAGE;
        String username = command.getGroup(input, "username");
        String ringName = command.getGroup(input, "ringName");
        showResult(FriendShipController.askMarriage(username, ringName));
    }

}
