package controller;

import model.Location;
import model.Result;
import model.enums.Direction;

public class GameController {
    public Result exitGame() {
        return null;
    }

    // TODO add functions for "force terminate"

    public void nextTurn() {
    }

    public Result showTime() {
        return null;
    }

    public Result showDate() {
        return null;
    }

    public Result showDateTime() {
        return null;
    }

    public Result showDayOfWeek() {
        return null;
    }

    public Result showSeason() {
        return null;
    }

    public Result showWeather() {
        return null;
    }

    public Result showWeatherForecast() {
        return null;
    }

    public Result buildGreenhouse() {
        return null;
    }

    public Result walk(Location location) {
        return null;
    }

    public Result printMap(Location location, int size) {
        return null;
    }

    public Result showEnergy() {
        return null;
    }

    public Result showInventory() {
        return null;
    }

    public Result throwInTrash(String itemName, int number) {
        return null;
    }

    public Result showTrash() {
        return null;
    }

    public Result equipTool(String toolName) {
        return null;
    }

    public Result upgradeTool(String toolName) {
        return null;
    }

    public Result useTool(Direction direction) {
        return null;
    }

    public Result plant(String seedName, Direction direction) {
        return null;
    }

    public Result showPlant(Location location) {
        return null;
    }

    public Result fertilize(String fertilizerName, Direction direction) {
        return null;
    }

    public Result howMuchWater() {
        return null;
    }

    public Result showRecipesForCrafting() {
        return null;
    }

    public Result craft(String itemName) {
        return null;
    }

    public Result placeItem(String itemName, Direction direction) {
        return null;
    }

    public Result putInRefrigerator(String itemName) {
        return null;
    }

    public Result pickInRefrigerator(String itemName) {
        return null;
    }

    public Result showRecipesForCooking() {
        return null;
    }

    public Result cookingPrepare(String recipeName) {
        return null;
    }

    public Result eatFood(String foodName) {
        return null;
    }

    public Result eatFood(String foodName, int amount) {
        return null;
    }

    public Result buildBuilding(String buildingName, Location location) {
        return null;
    }

    public Result buyAnimal(String animalType, String animalName) {
        return null;
    }

    public Result petAnimal(String animalName) {
        return null;
    }

    public Result showAnimals() {
        return null;
    }

    public Result shepherdAnimals(String animalName, Location location) {
        return null;
    }

    public Result feedHay(String animalName) {
        return null;
    }

    public Result showProduces(String animalName) {
        return null;
    }

    public Result collectProduce() {
        return null;
    }

    public Result sellAnimal(String animalName) {
        return null;
    }

    public Result startFishing(String finishingPoleName) {
        return null;
    }

    public Result useArtisan(String artisanName, String itemName) {
        return null;
    }

    public Result getArtisan(String artisanName) {
        return null;
    }

    public Result showAllShopProducts() {
        return null;
    }

    public Result showAllAvailableShopProducts() {
        return null;
    }

    public Result purchaseProduct(String productName, int count) {
        return null;
    }

    public Result sellProduct(String productName, int count) {
        return null;
    }

    public Result showFriendshipsWithPlayers() {
        return null;
    }

    public Result talkToPlayer(String username) {
        return null;
    }

    public Result showTalkHistory(String username) {
        return null;
    }

    public Result giveGiftToPlayer(String username, String itemName, int amount) {
        return null;
    }

    public Result showGiftList() {
        return null;
    }

    public Result rateGift(int giftNumber, int rate) {
        return null;
    }

    public Result showGiftHistory(String username) {
        return null;
    }

    public Result hugPlayer(String username) {
        return null;
    }

    public Result buyFlower(String username) {
        return null;
    }

    public Result askForMarriage(String username, String ringName) {
        return null;
    }

    public Result acceptMarriage(String username) {
        return null;
    }

    public Result rejectMarriage(String username) {
        return null;
    }

    public Result addTrade(String username, String tradeType, String itemName, int amount,
                           int price, String targetItemName, int targetItemAmount) {
        return null;
    }

    public Result showTradeList() {
        return null;
    }

    public Result acceptTrade(int tradeId) {
        return null;
    }

    public Result rejectTrade(int tradeId) {
        return null;
    }

    public Result showTradeHistory() {
        return null;
    }

    public Result meetNPC(String npcName) {
        return null;
    }

    public Result giveGiftToNPC(String npcName) {
        return null;
    }

    public Result showFriendshipsWithNPCs() {
        return null;
    }

    public Result showQuestsList() {
        return null;
    }

    public Result finishQuest(int questIndex) {
        return null;
    }
}
