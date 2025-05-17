package view.game;

import controller.Game.FriendShipController;
import model.App;
import model.Result;
import model.lives.Player;
import model.relationships.Relationship;
import model.relationships.Trade;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradingMenu implements GameSubMenu {
    @Override
    public void run(Scanner scanner) {

    }

    private void tradeHistory() {
        System.out.println("sender      receiver         type        item        amount      price       target item      target amount       is Accepted");
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (!player.equals(App.getCurrentGame().getCurrentPlayer())) {
                Relationship relationship = App.getCurrentGame().getRelationship(player, App.getCurrentGame().getCurrentPlayer());
                if (relationship.getTradeHistory() == null) {
                    continue;
                }
                for (Trade trade : relationship.getTradeHistory()) {
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
        showResult(FriendShipController.tradeResponse(answer.equals("accept"), id));
    }

    private void tradeList() {
        for (int i = 0; i < App.getCurrentGame().getCurrentPlayer().getReceivedTrades().size(); i++) {
            Trade trade = App.getCurrentGame().getCurrentPlayer().getReceivedTrades().get(i);
            if (trade.getType().equals("offer")) {
                String string = trade.getSender().getName() + "whants " + trade.getTargetItem() + " " + trade.getTargetAmount();
            } else if (trade.getType().equals("request")) {
                if (trade.getPrice() == 0) {
                    String st = trade.getSender().getName() + "whants " + trade.getTargetItem() + " " + trade.getTargetAmount() + " and gives " + trade.getAmount() + " " + trade.getItem();
                    System.out.println(i + ": " + st);
                } else {
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
        Result result = FriendShipController.tradeWithProduct(username, itemName, amount, targetItem, targetAmount);
    }

    private void tradeRequest(Matcher matcher) {
        String username = matcher.group("username");
        String item = matcher.group("item");
        String temp1 = matcher.group("amount");
        int amount = Integer.parseInt(temp1);
        Result result = FriendShipController.tradeRequest(username, item, amount);
        System.out.println(result.message());
    }

    private void tradeWithMoney(Matcher matcher) {
        String username = matcher.group("username");
        String item = matcher.group("item");
        String temp1 = matcher.group("amount");
        int amount = Integer.parseInt(temp1);
        String temp2 = matcher.group("price");
        int price = Integer.parseInt(temp2);
        Result result = FriendShipController.tradeWithMoney(username, item, amount, price);
        System.out.println(result.message());
    }

}
