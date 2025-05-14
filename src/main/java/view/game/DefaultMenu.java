package view.game;

import controller.Game.CommonGameController;
import controller.Game.FriendShipController;
import controller.Game.NPCShopsController;
import controller.GameController;
import model.Game;
import model.Result;
import model.Shops.*;
import model.enums.commands.CheatCode;
import model.enums.commands.Command;
import model.App;
import model.Result;
import model.alive.Player;
import model.enums.commands.GameCommand;
import model.map.Location;
import model.relationships.Gift;
import model.relationships.PlayerRelationship;
import model.relationships.Trade;

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
        else if(GameCommand.PURCHASE_ITEM.getMatcher(input) != null)
            purchaseItem(GameCommand.PURCHASE_ITEM.getMatcher(input));
        else if(GameCommand.PURCHASE_ANIMAL.getMatcher(input) != null)
            purchaseAnimal(GameCommand.PURCHASE_ANIMAL.getMatcher(input));
        else if(GameCommand.SHOW_ALL_PRODUCTS.matches(input))
            showAllItems();
        else if (GameCommand.SHOW_ALL_AVAILABLE_PRODUCTS.matches(input))
            showAllAvailableItems();
        else if(GameCommand.SELL.getMatcher(input) != null)
            sell(GameCommand.SELL.getMatcher(input));
        else if (GameCommand.SELL_ALL.getMatcher(input) != null)
            sellAll(GameCommand.SELL_ALL.getMatcher(input));
        else if (GameCommand.FRIENDSHIP.matches(input))
            friendShips();
        else if(GameCommand.TALK.getMatcher(input) != null)
            talk(GameCommand.TALK.getMatcher(input));
        else if(GameCommand.TALK_HISTORY.getMatcher(input) != null)
            talkHistory(GameCommand.TALK_HISTORY.getMatcher(input));
        else if(GameCommand.GIFT.getMatcher(input) != null)
            gift(GameCommand.GIFT.getMatcher(input));
        else if(GameCommand.GIFT_LIST.getMatcher(input) != null){
            giftList();
            App.getCurrentGame().getCurrentPlayer().setInGiftList(true);
        }
        else if(GameCommand.GIFT_RATE.getMatcher(input) != null ) {
            if(App.getCurrentGame().getCurrentPlayer().isInGiftList()){
                giftRate(GameCommand.GIFT_RATE.getMatcher(input));
            }
            else{
                System.out.println("go to gift list first");
            }
        }
        else if(GameCommand.GIFT_HISTORY.getMatcher(input) != null){
            giftHistory(GameCommand.GIFT_HISTORY.getMatcher(input));
        }
        else if(GameCommand.HUG.getMatcher(input) != null)
            hug(GameCommand.HUG.getMatcher(input));
        else if(GameCommand.FLOWER.getMatcher(input) != null)
            flower(GameCommand.FLOWER.getMatcher(input));
        else if(GameCommand.ASK_MARRIAGE.getMatcher(input) != null)
            askMarriage(GameCommand.ASK_MARRIAGE.getMatcher(input));
        else if (GameCommand.RESPOND_MARRIAGE.getMatcher(input) != null)
            respondForMarriage(GameCommand.RESPOND_MARRIAGE.getMatcher(input));
        else if(GameCommand.START_TRADE.matches(input))
            startTrade();
        else if(GameCommand.TRADE_REQUSET.getMatcher(input) != null)
            tradeRequset(GameCommand.TRADE_REQUSET.getMatcher(input));
        else if (GameCommand.TRADE_WITH_MONEY.getMatcher(input) != null)
            tradeWithMoney(GameCommand.TRADE_WITH_MONEY.getMatcher(input));
        else if(GameCommand.TRADE_WITH_PRODUCT.getMatcher(input) != null)
            tradeWithProduct(GameCommand.TRADE_WITH_PRODUCT.getMatcher(input));
        else if (GameCommand.TRADE_LIST.matches(input))
            tradeList();
        else if(GameCommand.TRADE_RESPONSE.getMatcher(input) != null)
            tradeResponse(GameCommand.TRADE_RESPONSE.getMatcher(input));
        else if(GameCommand.TRADE_HISTORY.matches(input))
            tradeHistory();
        else if (GameCommand.WALK.matches(input))
            handleWalk(input, scanner);
        else if (GameCommand.PRINT_MAP.matches(input))
            handlePrintMap(input);
        else if(CheatCode.ADD_MONEY.getMatcher(input) != null){
            cheatAddMoney(CheatCode.ADD_MONEY.getMatcher(input));
        }

        System.out.println("invalid command");
//      todo  else if (GameComman


//        else if (GameCommand.SHOW_ENERGY.matches(input))
//            handleShowEnergy();
//
//        else if (GameCommand.SHOW_INVENTORY.matches(input))
//            handleShowInventory();
//        else if (GameCommand.TRASH.matches(input))
//            handleTrash(input);
    }

    private void tradeHistory() {
        System.out.println("sender      reciver         type        item        amount      price       targetitem      targaetamount       is Accepted");
        for (Player player : App.getCurrentGame().getPlayers()) {
            if(!player.equals(App.getCurrentGame().getCurrentPlayer())){
                PlayerRelationship relationship = FriendShipController.getPlayerRelationship(player,App.getCurrentGame().getCurrentPlayer());
                if(relationship.getTradeHistory() == null){
                    continue;
                }
                for (Trade trade : relationship.getTradeHistory()){
                    System.out.println(trade.getSender().getName() + "      " + trade.getReceiver().getName() + "      " + trade.getType() + "      "
                    + trade.getItem() + "      " + trade.getAmount() + "     " + trade.getPrice() + "      " + trade.getTargetItem() + "      "
                    + trade.getTargetAmount() + "      " + trade.isAccepted());
                }
            }
        }
    }

    private void tradeResponse(Matcher matcher) {
        String answer = matcher.group("answer");
        String temp = matcher.group("id");
        int id = Integer.parseInt(temp);
        if (answer.equals("-accept")) {
            Result result = FriendShipController.tradeResponse(true,id);
            System.out.println(result.message());
            return;
        }
        if(answer.equals("-reject")){
            Result result = FriendShipController.tradeResponse(false,id);
            System.out.println(result.message());
        }
    }

    private void tradeList() {
        for (int i = 0; i < App.getCurrentGame().getCurrentPlayer().getRecivedTrades().size(); i++) {
            Trade trade = App.getCurrentGame().getCurrentPlayer().getRecivedTrades().get(i);
            if(trade.getType().equals("offer")){
                String string = trade.getSender().getName() + "whants " + trade.getTargetItem() + " " + trade.getTargetAmount();
            }
            else if(trade.getType().equals("request")){
                if (trade.getPrice() == 0){
                    String st = trade.getSender().getName() + "whants " + trade.getTargetItem() + " " + trade.getTargetAmount() + " and gives " + trade.getAmount() + " "+ trade.getItem();
                    System.out.println(i + ": " + st);
                }
                else{
                    String st = trade.getSender().getName() + "whants " + trade.getTargetItem() + " " + trade.getTargetAmount() + " and gives " + trade.getPrice();
                    System.out.println(i + ": " + st);
                }
            }
        }
    }

    private void tradeWithProduct(Matcher matcher) {
        String username = matcher.group("username");
        String itemName = matcher.group("item");
        String temp1 = matcher.group("amount");
        int amount = Integer.parseInt(temp1);
        String targetItem = matcher.group("targetItem");
        String temp2 = matcher.group("targetAmount");
        int targetAmount = Integer.parseInt(temp2);
        Result result = FriendShipController.tradeWithProduct(username,itemName,amount,targetItem,targetAmount);
    }

    private void tradeRequset(Matcher matcher) {
        String username = matcher.group("username");
        String item = matcher.group("item");
        String temp1 = matcher.group("amount");
        int amount = Integer.parseInt(temp1);
        Result result = FriendShipController.tradeRequest(username,item,amount);
        System.out.println(result.message());
    }

    private void tradeWithMoney(Matcher matcher) {
        String username = matcher.group("username");
        String item = matcher.group("item");
        String temp1 = matcher.group("amount");
        int amount = Integer.parseInt(temp1);
        String temp2 = matcher.group("price");
        int price = Integer.parseInt(temp2);
        Result result = FriendShipController.tradeWithMoney(username,item,amount,price);
        System.out.println(result.message());
    }

    private void startTrade() {
        for (Player player : App.getCurrentGame().getPlayers()){
            System.out.println(player.getName());
        }
    }

    private void sellAll(Matcher matcher) {
        String product = matcher.group("product_name");
        Result result = CommonGameController.sell(product,-1);
        System.out.println(result.message());
    }

    private void cheatAddMoney(Matcher matcher) {
        String temp = matcher.group("count");
        int count = Integer.parseInt(temp);
        App.getCurrentGame().getCurrentPlayer().increaseMoney(count);
    }

    private void sell(Matcher matcher) {
        String product = matcher.group("product_name");
        String Temp = matcher.group("count");
        int count = Integer.parseInt(Temp);
        if(count < 1){
            System.out.println("number should be positive");
            return;
        }
        Result result = CommonGameController.sell(product,count);
        System.out.println(result.message());
    }

    private void purchaseAnimal(Matcher matcher) {
        if(!(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof MarnieRanch)){
            System.out.println("you are not in shop");
            return;
        }
        String animalName = matcher.group("name");
        String product = matcher.group("product_name");
        String temp = matcher.group("count");
        int count = Integer.parseInt(temp);
        for (MarnieRanch.ItemsInMarineRanch items : ((MarnieRanch)(App.getCurrentGame().getNpcShops().get(5))).getLivesTock()){
            if (items.getName().equals(product)){
                Result result = NPCShopsController.buyLivesStockInMarine(items,count,animalName);
                System.out.println(result.message());
                return;
            }
        }
        System.out.println("there is no animal with name " + product);
    }

    private void showAllAvailableItems() {
        ArrayList<String> temp = null;
        if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof BlackSmithShop){
            temp = NPCShopsController.showPrductsinBlackSmith(true);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof MarnieRanch){
            temp = NPCShopsController.showproductsMarinesRench(true);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof CarpenterShop){
            temp = NPCShopsController.showCarpenterShop(true);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof JojoMartShop){
            temp = NPCShopsController.showJojaMart(true);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof PierreGeneralShop){
            temp = NPCShopsController.showPierreGeneralShop(true);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof FishShop){
            temp = NPCShopsController.showFishShop(true);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof TheStardropSaloonShop){
            temp = NPCShopsController.showProductsStardropSaloon(true);
        }
        for(String st : temp){
            System.out.println(st);
        }
    }
    private void showAllItems() {
        ArrayList<String> temp = null;
        if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof BlackSmithShop){
            temp = NPCShopsController.showPrductsinBlackSmith(false);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof MarnieRanch){
            temp = NPCShopsController.showproductsMarinesRench(false);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof CarpenterShop){
            temp = NPCShopsController.showCarpenterShop(false);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof JojoMartShop){
            temp = NPCShopsController.showJojaMart(false);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof PierreGeneralShop){
            temp = NPCShopsController.showPierreGeneralShop(false);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof FishShop){
            temp = NPCShopsController.showFishShop(false);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof TheStardropSaloonShop){
            temp = NPCShopsController.showProductsStardropSaloon(false);
        }
        for(String st : temp){
            System.out.println(st);
        }
    }

    private void respondForMarriage(Matcher matcher) {
        String username = matcher.group("username");
        Player player = App.getCurrentGame().getPlayerByUsername(username);
        String temp = matcher.group("bool");
        boolean marriage = false;
        if(temp.equals("–accept")){
            marriage = true;
        }
        if(player == null){
            System.out.println("player not found");
            return;
        }
        Result result = FriendShipController.respondForMarriage(player,marriage);
    }

    private void askMarriage(Matcher matcher) {
        String username = matcher.group("username");
        String ring = matcher.group("ring");
        Player player = App.getCurrentGame().getPlayerByUsername(username);
        if (player == null) {
            System.out.println("Player not found");
        }
        //TODO check ring name
        Result result = FriendShipController.askMarriage(player,ring);
    }

    private void flower(Matcher matcher) {
        String username = matcher.group("username");
        Player player = App.getCurrentGame().getPlayerByUsername(username);
        if(player == null){
            System.out.println("player not found");
            return;
        }
        FriendShipController.flower(player);
    }

    private void hug(Matcher matcher) {
        String username = matcher.group("username");
        Player player = App.getCurrentGame().getPlayerByUsername(username);
        if(player == null){
            System.out.println("Player not found");
            return;
        }
        Result result = FriendShipController.hug(player);
        System.out.println(result.message());
    }

    private void giftHistory(Matcher matcher) {
        String username = matcher.group("username");
        Player player = App.getCurrentGame().getPlayerByUsername(username);
        if (player == null){
            System.out.println("user not found");
            return;
        }
        else{
            ArrayList<String> gifts = FriendShipController.giftHistory(player);
            for (String st : gifts){
                System.out.println(st);
            }
        }
    }
    private void giftList() {
        ArrayList<String> gifts = FriendShipController.giftList();
        for(int i = 0; i < gifts.size(); i++){
            System.out.println(i + ". " + gifts.get(i));
        }
        if (gifts.size() > 0){
            App.getCurrentGame().getCurrentPlayer().setInGiftList(true);
        }
        else{
            System.out.println("gift list empty");
        }
    }

    private void handleExitGame() {
        showResult(GameController.exitGame());
    }

    private void handleNextTurn() {
        showResult(GameController.nextTurn());
    }

    private void handleShowDate() {
        showResult(GameController.showDate());
    }

    private void handleShowDateTime() {
        showResult(GameController.showTime());
    }

    private void handleShowTime() {
        showResult(GameController.showDateTime());
    }

    private void handleShowDayOfWeek() {
        showResult(GameController.showDayOfWeek());
    }

    private void handleShowSeason() {
        showResult(GameController.showSeason());
    }

    private void handleShowWeather() {
        showResult(GameController.showWeather());
    }

    private void handleShowWeatherForecast() {
        showResult(GameController.showWeatherForecast());
    }

    private void handleGreenHouseBuild() {

    }

    private void handleWalk(String input, Scanner scanner) {
        GameCommand command = GameCommand.WALK;
        Location location = new Location(Integer.parseInt(command.getGroup(input, "x")),
                Integer.parseInt(command.getGroup(input, "y")));

        Result checkForWalkingResult = GameController.checkForWalking(location);
        showResult(checkForWalkingResult);

        if (!checkForWalkingResult.success())
            return;

        boolean confirmation = askForConfirmation(scanner);
        if (confirmation)
            showResult(GameController.walk(location));
    }

    private void handlePrintMap(String input) {
        Command command = GameCommand.PRINT_MAP;
        Location location = new Location(Integer.parseInt(command.getGroup(input, "x")),
                Integer.parseInt(command.getGroup(input, "y")));

        int size = Integer.parseInt(command.getGroup(input, "size"));

        showResult(GameController.printMap(location, size));
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
        String product = matcher.group("product_name");
        String temp = matcher.group("count");
        int number = Integer.parseInt(temp);
        Result result = null;
        if (App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof BlackSmithShop){
             result = NPCShopsController.buySomthingFromBlacksmith(product, number);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof MarnieRanch){
             result = NPCShopsController.buySomthingFromMarnie(product, number);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof CarpenterShop){
             result = NPCShopsController.buySomthingFromCarpenter(product, number);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof JojoMartShop){
             result = NPCShopsController.buySomthingFromJojaMart(product, number);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof PierreGeneralShop){
             result = NPCShopsController.buySomthingFromPierre(product, number);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof FishShop){
             result = NPCShopsController.buySomthingFromFishShop(product,number);
        }
        if(App.getCurrentGame().getWorld().getTileAt(App.getCurrentGame().getCurrentPlayer().getCurrentLocation())
                .getThingOnTile() instanceof TheStardropSaloonShop){
             result = NPCShopsController.buySomthingFromStardrop(product,number);
        }
        System.out.println(result.message());
    }
}
