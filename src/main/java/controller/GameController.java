package controller;

import model.map.Location;
import model.Result;
import model.enums.Direction;

public class GameController {
    public static Result exitGame() {
        return null;
    }

    public static Result deleteGame() {
        return null;
    }

    // TODO add functions for "force terminate"

    public static void nextTurn() {
    }

    public static Result showTime() {
        return null;
    }

    public static Result showDate() {
        return null;
    }

    public static Result showDateTime() {
        return null;
    }

    public static Result showDayOfWeek() {
        return null;
    }

    public static Result showSeason() {
        return null;
    }

    public static Result showWeather() {
        return null;
    }

    public static Result showWeatherForecast() {
        return null;
    }

    public static Result buildGreenhouse() {
        return null;
    }

    public static Result walk(Location location) {
        return null;
    }

    public static Result printMap(Location location, int size) {
        return null;
    }

    public static Result showEnergy() {
        return null;
    }

    public static Result showInventory() {
        return null;
    }

    public static Result throwInTrash(String itemName, int number) {
        return null;
    }

    public static Result showTrash() {
        return null;
    }

    public static Result equipTool(String toolName) {
        return null;
    }

    public static Result upgradeTool(String toolName) {
        return null;
    }

    public static Result useTool(Direction direction) {
        return null;
    }

    public static Result plant(String seedName, Direction direction) {
        return null;
    }

    public static Result showPlant(Location location) {
        return null;
    }

    public static Result fertilize(String fertilizerName, Direction direction) {
        return null;
    }

    public static Result howMuchWater() {
        return null;
    }

    public static Result showRecipesForCrafting() {
        return null;
    }

    public static Result craft(String itemName) {
        return null;
    }

    public static Result placeItem(String itemName, Direction direction) {
        return null;
    }

    public static Result putInRefrigerator(String itemName) {
        return null;
    }

    public static Result pickInRefrigerator(String itemName) {
        return null;
    }

    public static Result showRecipesForCooking() {
        return null;
    }

    public static Result cookingPrepare(String recipeName) {
        return null;
    }

    public static Result eatFood(String foodName) {
        return null;
    }

    public static Result eatFood(String foodName, int amount) {
        return null;
    }

    public static Result buildBuilding(String buildingName, Location location) {
        return null;
    }

    public static Result buyAnimal(String animalType, String animalName) {
        return null;
    }

    public static Result petAnimal(String animalName) {
        return null;
    }

    public static Result showAnimals() {
        return null;
    }

    public static Result shepherdAnimals(String animalName, Location location) {
        return null;
    }

    public static Result feedHay(String animalName) {
        return null;
    }

    public static Result showProduces(String animalName) {
        return null;
    }

    public static Result collectProduce() {
        return null;
    }

    public static Result sellAnimal(String animalName) {
        return null;
    }

    public static Result startFishing(String finishingPoleName) {
        return null;
    }

    public static Result useArtisan(String artisanName, String itemName) {
        return null;
    }

    public static Result getArtisan(String artisanName) {
        return null;
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
