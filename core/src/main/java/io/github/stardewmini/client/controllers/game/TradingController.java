package io.github.stardewmini.client.controllers.game;

import io.github.stardewmini.server.app.GameApp;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.items.Item;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.relationships.Relationship;
import io.github.stardewmini.common.model.relationships.Trade;

public class TradingController {

    public static Result tradeRequest(String username, String itemName, int amount) {
        if (GameApp.getCurrentGame().getPlayerByUsername(username) == null) {
            return CommonGameController.playerNotFound();
        }
        Item item = CommonGameController.findItem(itemName);
        if (item == null) {
            return new Result(false, "item not found");
        }
        if (amount < 1) {
            return new Result(false, "amount shpuld be positive");
        }
        if (!GameApp.getCurrentGame().getCurrentPlayer().getBackpack().removeItem(item, amount)) {
            return new Result(false, "not enough product");
        }
        Trade trade = new Trade(GameApp.getCurrentGame().getCurrentPlayer(), GameApp.getCurrentGame().
                getPlayerByUsername(username), "offer", itemName, amount, 0, "", 0);
        GameApp.getCurrentGame().getPlayerByUsername(username).getReceivedTrades().add(trade);
        return new Result(true, "offer sent successfully");
    }

    public static Result tradeWithMoney(String username, String itemName, int amount, int price) {
        if (GameApp.getCurrentGame().getPlayerByUsername(username) == null) {
            return CommonGameController.playerNotFound();
        }
        Item item = CommonGameController.findItem(itemName);
        if (item == null) {
            return new Result(false, "item not found");
        }
        if (amount < 1) {
            return new Result(false, "amount shpuld be positive");
        }
        if (price < 1) {
            return new Result(false, "price shpuld be positive");
        }
        if (GameApp.getCurrentGame().getCurrentPlayer().getMoney() < price) {
            return new Result(false, "not enough money");
        }
        Trade trade = new Trade(GameApp.getCurrentGame().getCurrentPlayer(), GameApp.getCurrentGame().
                getPlayerByUsername(username), "request", itemName, amount, price, "", 0);
        GameApp.getCurrentGame().getPlayerByUsername(username).getReceivedTrades().add(trade);
        return new Result(true, "offer sent successfully");
    }

    public static Result tradeWithProduct(String username, String itemName, int amount, String targetItem, int targetAmount) {
        if (GameApp.getCurrentGame().getPlayerByUsername(username) == null) {
            return CommonGameController.playerNotFound();
        }
        Item item = CommonGameController.findItem(itemName);
        if (item == null) {
            return new Result(false, itemName + " not found");
        }
        Item item2 = CommonGameController.findItem(targetItem);
        if (item2 == null) {
            return new Result(false, targetItem + " not found");
        }
        if (amount < 1) {
            return new Result(false, "amount shpuld be positive");
        }
        if (targetAmount < 1) {
            return new Result(false, "targetAmount shpuld be positive");
        }
        if (!GameApp.getCurrentGame().getCurrentPlayer().getBackpack().removeItem(item, amount)) {
            return new Result(false, "not enough product");
        }
        Trade trade = new Trade(GameApp.getCurrentGame().getCurrentPlayer(), GameApp.getCurrentGame().
                getPlayerByUsername(username), "request", itemName, amount, 0, "", targetAmount);
        GameApp.getCurrentGame().getPlayerByUsername(username).getReceivedTrades().add(trade);
        return new Result(true, "offer sent successfully");
    }

    public static Result tradeResponse(boolean accept, int id) {
        if (id > GameApp.getCurrentGame().getCurrentPlayer().getReceivedTrades().size()) {
            return new Result(false, "id out of range");
        }
        Trade trade = GameApp.getCurrentGame().getCurrentPlayer().getReceivedTrades().get(id - 1);
        Relationship relationship = GameApp.getCurrentGame().getRelationship(trade.getSender(), trade.getReceiver());
        Item item = CommonGameController.findItem(trade.getItem());

        if (accept) {
            if (trade.getType().equals("request")) {
                if (trade.getPrice() == 0) {
                    Item item1 = CommonGameController.findItem(trade.getTargetItem());
                    if (!GameApp.getCurrentGame().getCurrentPlayer().getBackpack().removeItem(item1, trade.getTargetAmount())) {
                        return new Result(false, "not enough product");
                    }
                    GameApp.getCurrentGame().getCurrentPlayer().getBackpack().addItem(item, trade.getAmount());
                    trade.getSender().getBackpack().addItem(item1, trade.getTargetAmount());
                    Trade temp = new Trade(trade.getSender(), trade.getReceiver(), trade.getType(), trade.getItem(), trade.getAmount(), trade.getPrice(), trade.getTargetItem(), trade.getTargetAmount());
                    temp.setAccepted(true);
                    relationship.getTradeHistory().add(temp);
                    GameApp.getCurrentGame().getCurrentPlayer().getReceivedTrades().remove(id - 1);
                    return new Result(true, "offer accepted successfully");
                } else {
                    if (GameApp.getCurrentGame().getCurrentPlayer().getMoney() < trade.getPrice()) {
                        return new Result(false, "not enough money");
                    } else if (!GameApp.getCurrentGame().getCurrentPlayer().getBackpack().addItem(item, trade.getAmount())) {
                        return new Result(false, "not enough space in backpack");
                    } else {
                        GameApp.getCurrentGame().getCurrentPlayer().decreaseMoney(trade.getPrice());
                        Trade temp = new Trade(trade.getSender(), trade.getReceiver(), trade.getType(), trade.getItem(), trade.getAmount(), trade.getPrice(), trade.getTargetItem(), trade.getTargetAmount());
                        temp.setAccepted(true);
                        relationship.getTradeHistory().add(temp);
                        GameApp.getCurrentGame().getCurrentPlayer().getReceivedTrades().remove(id - 1);
                        return new Result(true, "offer accepted");
                    }
                }
            } else {
                if (!GameApp.getCurrentGame().getCurrentPlayer().getBackpack().addItem(item, trade.getAmount())) {
                    return new Result(false, "not enough space");
                }
                Trade temp = new Trade(trade.getSender(), trade.getReceiver(), trade.getType(), trade.getItem(), trade.getAmount(), trade.getPrice(), trade.getTargetItem(), trade.getTargetAmount());
                temp.setAccepted(true);
                relationship.getTradeHistory().add(temp);
                GameApp.getCurrentGame().getCurrentPlayer().getReceivedTrades().remove(id - 1);
                return new Result(true, "offer accepted successfully");
            }
        } else {
            trade.setAccepted(false);
            trade.getSender().getBackpack().addItem(item, trade.getAmount());
            Trade temp = new Trade(trade.getSender(), trade.getReceiver(), trade.getType(), trade.getItem(), trade.getAmount(), trade.getPrice(), trade.getTargetItem(), trade.getTargetAmount());
            temp.setAccepted(false);
            relationship.getTradeHistory().add(temp);
            GameApp.getCurrentGame().getCurrentPlayer().getReceivedTrades().remove(id - 1);
            return new Result(true, "offer rejected successfully");
        }
    }

    public static Result tradeHistory() {
        StringBuilder output = new StringBuilder();
        output.append("sender      receiver         type        item        amount      price       target item      target amount       is Accepted\n");
        for (Player player : GameApp.getCurrentGame().getPlayers()) {
            if (!player.equals(GameApp.getCurrentGame().getCurrentPlayer())) {
                Relationship relationship = GameApp.getCurrentGame().getRelationship(player, GameApp.getCurrentGame().getCurrentPlayer());
                if (relationship.getTradeHistory() == null) {
                    continue;
                }
                for (Trade trade : relationship.getTradeHistory()) {
                    output.append(trade.getSender().getName()).append("      ").append(trade.getReceiver().getName()).
                            append("      ").append(trade.getType()).append("      ").append(trade.getItem()).
                            append("      ").append(trade.getAmount()).append("     ").append(trade.getPrice()).
                            append("      ").append(trade.getTargetItem()).append("      ").
                            append(trade.getTargetAmount()).append("      ").append(trade.isAccepted());
                }
            }
        }

        return new Result(true, output.toString());
    }

    public static Result tradeList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < GameApp.getCurrentGame().getCurrentPlayer().getReceivedTrades().size(); i++) {
            Trade trade = GameApp.getCurrentGame().getCurrentPlayer().getReceivedTrades().get(i);
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
        return new Result(true, output.toString());
    }


}
