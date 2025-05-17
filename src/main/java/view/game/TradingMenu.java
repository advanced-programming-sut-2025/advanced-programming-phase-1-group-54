package view.game;

import controller.Game.FriendShipController;
import controller.Game.TradingController;
import model.App;
import model.Result;
import model.enums.SubMenu;
import model.enums.commands.CheatCode;
import model.enums.commands.Command;
import model.enums.commands.GameCommand;
import model.lives.Player;
import model.relationships.Relationship;
import model.relationships.Trade;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Matcher;

public class TradingMenu implements GameSubMenu {
    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();

        if(GameCommand.TRADE.matches(input)) {
            handelTrading(input);
        }
        else if(GameCommand.SHOW_TRADES_LIST.matches(input)) {
            handleShowTradingList();
        }
        else if(GameCommand.TRADING_RESPONSE.matches(input)) {
            handleTradingResponse(input);
        }
        else if(GameCommand.SHOW_TRADES_HISTORY.matches(input)) {
            handleShowTradingHistory();
        }
        else if(GameCommand.STOP_TRADING.matches(input)) {
            handleStopTrading();
        }
        else {
            invalidCommand();
        }

    }

    private void handelTrading(String input) {
        Command command = GameCommand.TRADE;
        String username = command.getGroup(input,"username");
        String type = command.getGroup(input,"type");
        String item = command.getGroup(input,"item");
        String temp1 = command.getGroup(input,"amount");
        int amount = Integer.parseInt(temp1);
        String priceString = command.getGroup(input,"price");

        String targetItem = command.getGroup(input,"targetItem");
        String targetAmountString = command.getGroup(input,"targetAmount");
        if(type.equals("request")) {
            showResult(TradingController.tradeRequest(username, item, amount));
        }
        else if(priceString != null) {
            int price = Integer.parseInt(priceString);
            showResult(TradingController.tradeWithMoney(username, item, amount, price));
        }
        else if(targetAmountString != null) {
            int targetAmount = Integer.parseInt(targetAmountString);
            showResult(TradingController.tradeWithProduct(username, item, amount, targetItem, targetAmount));
        }
        else {
            invalidCommand();
        }
    }

    private void handleShowTradingList(){
        showResult(TradingController.tradeList());
    }

    private void handleTradingResponse(String input){
        Command command = GameCommand.TRADING_RESPONSE;
        String answer = command.getGroup(input,"answer");
        String temp = command.getGroup(input,"id");
        int id = Integer.parseInt(temp);
        showResult(TradingController.tradeResponse(answer.equals("accept"), id));
    }

    private void handleShowTradingHistory(){
        showResult(TradingController.tradeHistory());
    }

    private void handleStopTrading(){
        goToMenu(SubMenu.DEFAULT);
    }










//    private void tradeWithProduct(Matcher matcher) {
//        String username = matcher.group("username");
//        String itemName = matcher.group("item");
//        String temp1 = matcher.group("amount");
//        int amount = Integer.parseInt(temp1);
//        String targetItem = matcher.group("targetItem");
//        String temp2 = matcher.group("targetAmount");
//        int targetAmount = Integer.parseInt(temp2);
//        Result result = FriendShipController.tradeWithProduct(username, itemName, amount, targetItem, targetAmount);
//    }
//
//    private void tradeRequest(Matcher matcher) {
//        String username = matcher.group("username");
//        String item = matcher.group("item");
//        String temp1 = matcher.group("amount");
//        int amount = Integer.parseInt(temp1);
//        Result result = FriendShipController.tradeRequest(username, item, amount);
//        System.out.println(result.message());
//    }
//
//    private void tradeWithMoney(Matcher matcher) {
//        String username = matcher.group("username");
//        String item = matcher.group("item");
//        String temp1 = matcher.group("amount");
//        int amount = Integer.parseInt(temp1);
//        String temp2 = matcher.group("price");
//        int price = Integer.parseInt(temp2);
//        Result result = FriendShipController.tradeWithMoney(username, item, amount, price);
//        System.out.println(result.message());
//    }

}
