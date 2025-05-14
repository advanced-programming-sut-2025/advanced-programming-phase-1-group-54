package view.game;

import controller.Game.CommonGameController;
import controller.Game.FriendShipController;
import controller.Game.MapController;
import controller.Game.NPCShopsController;
import model.Game;
import model.Result;
import model.Shops.MarnieRanch;
import model.enums.commands.Command;
import model.App;
import model.Result;
import model.alive.Player;
import model.enums.commands.GameCommand;
import model.map.Location;
import model.relationships.Gift;
import model.relationships.PlayerRelationship;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class DefaultMenu implements GameSubMenu {
    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();

        if (GameCommand.EXIT_GAME.matches(input))
            handleExitGame();
        else if (GameCommand.NEXT_TURN.matches(input))
            handleNextTurn();
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
//
//        else if(GameCommand.PURCHASE_ITEM.getMatcher(input) != null)
//            purchaseItem(GameCommand.PURCHASE_ITEM.getMatcher(input));
//        else if(GameCommand.PURCHASE_ANIMAL.getMatcher(input) != null)
//            purchaseAnimal(GameCommand.PURCHASE_ANIMAL.getMatcher(input));
//        else if(GameCommand.SHOW_ALL_PRODUCTS.matches(input))
//            showAllItems();
//        else if (GameCommand.SHOW_ALL_AVAILABLE_PRODUCTS.matches(input))
//            showAllAvailableItems();
//        else if (GameCommand.FRIENDSHIP.matches(input))
//            friendShips();
//        else if(GameCommand.TALK.getMatcher(input) != null)
//            talk(GameCommand.TALK.getMatcher(input));
//        else if(GameCommand.TALK_HISTORY.getMatcher(input) != null)
//            talkHistory(GameCommand.TALK_HISTORY.getMatcher(input));
//        else if(GameCommand.GIFT.getMatcher(input) != null)
//            gift(GameCommand.GIFT.getMatcher(input));
//        else if(GameCommand.GIFT_LIST.getMatcher(input) != null){
//            giftList();
//            App.getCurrentGame().getCurrentPlayer().setInGiftList(true);
//        }
//        else if(GameCommand.GIFT_RATE.getMatcher(input) != null ) {
//            if(App.getCurrentGame().getCurrentPlayer().isInGiftList()){
//                giftRate(GameCommand.GIFT_RATE.getMatcher(input));
//            }
//            else{
//                System.out.println("go to gift list first");
//            }
//        }
//        else if(GameCommand.GIFT_HISTORY.getMatcher(input) != null){
//            giftHistory(GameCommand.GIFT_HISTORY.getMatcher(input));
//        }
//        else if(GameCommand.HUG.getMatcher(input) != null)
//            hug(GameCommand.HUG.getMatcher(input));
//        else if(GameCommand.FLOWER.getMatcher(input) != null)
//            flower(GameCommand.FLOWER.getMatcher(input));
//        else if(GameCommand.ASK_MARRIAGE.getMatcher(input) != null)
//            askMarriage(GameCommand.ASK_MARRIAGE.getMatcher(input));
//        else if (GameCommand.RESPOND_MARRIAGE.getMatcher(input) != null)
//            respondForMarriage(GameCommand.RESPOND_MARRIAGE.getMatcher(input));
        else if (GameCommand.WALK.matches(input))
            handleWalk(input, scanner);
        else if (GameCommand.PRINT_MAP.matches(input))
            handlePrintMap(input);



        /*else if (GameCommand.SHOW_ALL_PRODUCTS.matches(input))
            NPCShopsController.showProducts(shop , false);
        else if (GameCommand.SHOW_ALL_AVAILABLE_PRODUCTS.matches(input))
            NPCShopsController.showProducts(shop , true);
        */
//      todo  else if (GameComman


//        else if (GameCommand.SHOW_ENERGY.matches(input))
//            handleShowEnergy();
//
//        else if (GameCommand.SHOW_INVENTORY.matches(input))
//            handleShowInventory();
//        else if (GameCommand.TRASH.matches(input))
//            handleTrash(input);
    }

//    private void purchaseAnimal(Matcher matcher) {
//        String animalName = matcher.group("name");
//        String product = matcher.group("product_name");
//        String temp = matcher.group("count");
//        int count = Integer.parseInt(temp);
//        for (MarnieRanch.ItemsInMarineRanch items : ((MarnieRanch)(App.getCurrentGame().getNpcShops().get(5))).getLivesTock()){
//            if (items.getName().equals(product)){
//                Result result = NPCShopsController.buyLivesStockInMarine(items,count,animalName);
//                System.out.println(result.message());
//                return;
//            }
//        }
//        System.out.println("there is no animal with name " + product);
//    }
//
//    private void showAllAvailableItems() {
//
//        /*if(){
//        //  ArrayList<String> temp = NPCShopsController.showPrductsinBlackSmith(true);
//        // }
//        // if(){
//        //
//        //
//        ArrayList<String> temp = NPCShopsController.showproductsMarinesRench(true);
//        ArrayList<String> temp = NPCShopsController.showCarpenterShop(true);
//        ArrayList<String> temp = NPCShopsController.showJojaMart(true);
//        ArrayList<String> temp = NPCShopsController.showPierreGeneralShop(true);
//        ArrayList<String> temp = NPCShopsController.showFishShop(true);
//        */
//        ArrayList<String> temp = NPCShopsController.showProductsStardropSaloon(true);
//    }
//
//    private void showAllItems() {
//        /*
//        ArrayList<String> temp = NPCShopsController.showPrductsinBlackSmith(false);
//        ArrayList<String> temp = NPCShopsController.showproductsMarinesRench(false);
//        ArrayList<String> temp = NPCShopsController.showCarpenterShop(false);
//        ArrayList<String> temp = NPCShopsController.showJojaMart(false);
//        ArrayList<String> temp = NPCShopsController.showPierreGeneralShop(false);
//        ArrayList<String> temp = NPCShopsController.showFishShop(false);
//        ArrayList<String> temp = NPCShopsController.showProductsStardropSaloon(false);
//        */
//    }
//
//    private void respondForMarriage(Matcher matcher) {
//        String username = matcher.group("username");
//        Player player = App.getCurrentGame().getPlayerByUsername(username);
//        String temp = matcher.group("bool");
//        boolean marriage = false;
//        if(temp.equals("–accept")){
//            marriage = true;
//        }
//        if(player == null){
//            System.out.println("player not found");
//            return;
//        }
//        Result result = FriendShipController.respondForMarriage(player,marriage);
//    }
//
//    private void askMarriage(Matcher matcher) {
//        String username = matcher.group("username");
//        String ring = matcher.group("ring");
//        Player player = App.getCurrentGame().getPlayerByUsername(username);
//        if (player == null) {
//            System.out.println("Player not found");
//        }
//        //TODO check ring name
//        Result result = FriendShipController.askMarriage(player,ring);
//    }
//
//    private void flower(Matcher matcher) {
//        String username = matcher.group("username");
//        Player player = App.getCurrentGame().getPlayerByUsername(username);
//        if(player == null){
//            System.out.println("player not found");
//            return;
//        }
//        FriendShipController.flower(player);
//    }
//
//    private void hug(Matcher matcher) {
//        String username = matcher.group("username");
//        Player player = App.getCurrentGame().getPlayerByUsername(username);
//        if(player == null){
//            System.out.println("Player not found");
//            return;
//        }
//        Result result = FriendShipController.hug(player);
//        System.out.println(result.message());
//    }
//
//    private void giftHistory(Matcher matcher) {
//        String username = matcher.group("username");
//        Player player = App.getCurrentGame().getPlayerByUsername(username);
//        if (player == null){
//            System.out.println("user not found");
//            return;
//        }
//        else{
//            ArrayList<String> gifts = FriendShipController.giftHistory(player);
//            for (String st : gifts){
//                System.out.println(st);
//            }
//        }
//    }
//    private void giftList() {
//        ArrayList<String> gifts = FriendShipController.giftList();
//        for(int i = 0; i < gifts.size(); i++){
//            System.out.println(i + ". " + gifts.get(i));
//        }
//        if (gifts.size() > 0){
//            App.getCurrentGame().getCurrentPlayer().setInGiftList(true);
//        }
//        else{
//            System.out.println("gift list empty");
//        }
//    }

    private void handleExitGame() {
        showResult(CommonGameController.exitGame());
    }

    private void handleNextTurn() {
        showResult(CommonGameController.nextTurn());
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
    private void friendShips(){
        ArrayList<PlayerRelationship> relationships = FriendShipController.showFriendShip(App.getCurrentGame().getCurrentPlayer());
        for (PlayerRelationship playerRelationship : relationships){
            Player temp = null;
            if(playerRelationship.getPlayer1().equals(App.getCurrentGame().getCurrentPlayer())){
                temp = playerRelationship.getPlayer2();
            }
            else{
                temp = playerRelationship.getPlayer1();
            }
            System.out.println(temp.getName() + "  level: " + playerRelationship.getLevel() + "  Xp:  " + playerRelationship.getXp());
        }
    }
    private void talk(Matcher mathcer){
        String username = mathcer.group("username");
        String message = mathcer.group("message");
        Result result = FriendShipController.talk(username, message);
        System.out.println(result.message());
    }
    private void talkHistory(Matcher mathcer){
        String username = mathcer.group("username");
        if (App.getCurrentGame().getPlayerByUsername(username) == null){
            System.out.println("user not found");
        }
        else{
            ArrayList<String> talkHistory = FriendShipController.talkHistory(username);
            for (String string : talkHistory){
                System.out.println(string);
            }
        }
    }
    private void gift(Matcher mathcer){
        // TODO if (next to each other)
        String username = mathcer.group("username");
        String item = mathcer.group("item");
        String amount = mathcer.group("amount");
        Result result = FriendShipController.gift(username,item,amount);
        System.out.println(result.message());
    }
    private void giftRate(Matcher mathcer){
        String temp1 = mathcer.group("number");
        String temp2 = mathcer.group("rate");
        int number = Integer.parseInt(temp1);
        int rate = Integer.parseInt(temp2);
        Result result = FriendShipController.giftRate(number,rate);
        System.out.println(result.message());
        App.getCurrentGame().getCurrentPlayer().setInGiftList(false);
    }
    private void purchaseItem(Matcher matcher){

    }
}
