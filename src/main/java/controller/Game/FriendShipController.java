package controller.Game;

import model.App;
import model.DateTime;
import model.Game;
import model.Result;
import model.lives.Player;
import model.items.Item;
import model.items.UniqueItem;
import model.relationships.Gift;
import model.relationships.Relationship;
import model.relationships.Talk;
import model.relationships.Trade;

import java.util.ArrayList;

public class FriendShipController {
    public static Result showFriendships() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        ArrayList<Relationship> relationships = game.getRelationshipsOf(player);

        StringBuilder messageBuilder = new StringBuilder();
        for (Relationship relationship : relationships) {
            messageBuilder.append(relationship.getOtherPlayer(player).getName())
                    .append("level: ").append(relationship.getLevel()).append("\nXP: ").append(relationship.getXP());
            // TODO add more details about the relationship (if needed)
        }

        return new Result(true, messageBuilder.toString());
    }

    public static Result talk(String username, String message) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player otherPlayer = game.getPlayerByUsername(username);
        if (otherPlayer == null) {
            return new Result(false, "no such user was found");
        }
        Relationship relationship = game.getRelationship(player, otherPlayer);

        if (App.getCurrentGame().getPlayerByUsername(username) == null) {
            return new Result(false, "user not found");
        }

        if (!MapController.isNear(player.getCurrentLocation(), otherPlayer))
            return new Result(false, "you must be next to each other to talk to each other!");

        Talk talk = new Talk(App.getCurrentGame().getCurrentPlayer(), message, new DateTime(App.getCurrentGame().getDateTime()));
        relationship.getTalkHistory().add(talk);
        if (relationship.getTalkDailyCount() == 0) {
            relationship.increaseXP(20);
        }
        relationship.increaseTalkDailyCount();
        return new Result(true, "message sent");
    }

    public static Result showTalkHistory(String username) {
        Game game = App.getCurrentGame();

        Player currentPlayer = game.getCurrentPlayer();
        Player player = game.getPlayerByUsername(username);

        if (player == null)
            return CommonGameController.playerNotFound();

        Relationship relationship = game.getRelationship(currentPlayer, player);

        StringBuilder messageBuilder = new StringBuilder();
        for (Talk talk : relationship.getTalkHistory()) {
            messageBuilder
                    .append(talk.sayer().getName())
                    .append(": ")
                    .append(talk.message())
                    .append(" (").append(talk.timestamp().toString()).append(")")
                    .append("\n");
        }

        return new Result(true, messageBuilder.toString().trim());
    }

    public static Result gift(String username, String itemName, int count) {
        Game game = App.getCurrentGame();

        Player player = App.getCurrentGame().getPlayerByUsername(username);
        if (player == null) {
            return CommonGameController.playerNotFound();
        }
        Relationship relationship = game.getRelationship(App.getCurrentGame().getCurrentPlayer(), player);

        if (relationship.getLevel() < 1) {
            return new Result(false, "you should reach level 1 first");
        }
        if (count < 0) {
            return new Result(false, "amount must be a positive integer");
        }
        if (App.getCurrentGame().getPlayerByUsername(username) == null) {
            return new Result(false, "user not found");
        }
        if (CommonGameController.findItem(itemName) == null) {
            return new Result(false, "there is no item with this name that you could gift");
        }
        Item item = CommonGameController.findItem(itemName);
        if (CommonGameController.numberOfItemInBackPack(itemName) < count) {
            return new Result(false, "not enough " + itemName + " in back pack");
        }
        if (!App.getCurrentGame().getCurrentPlayer().getBackpack().removeItem(item, count)) {
            return new Result(false, "not enough " + itemName + " in back pack");
        }
        if (!player.getBackpack().addItem(item, count)) {
            return new Result(false, "not enough in " + player.getName() + " back pack");
        }
        Gift gift = new Gift(App.getCurrentGame().getCurrentPlayer(), itemName, count, new DateTime(App.getCurrentGame().getDateTime()));
        player.getReceivedGifts().add(gift);
        relationship.getGiftHistory().add(gift);
        return new Result(true, "gift sent");
    }

    public static Result showGiftsFrom(String username) {
        Player player = App.getCurrentGame().getPlayerByUsername(username);
        if (player == null)
            return CommonGameController.playerNotFound();

        StringBuilder messageBuilder = new StringBuilder();
        Relationship relationship = App.getCurrentGame().getRelationship(App.getCurrentGame().getCurrentPlayer(), player);

        for (int i = 0; i < relationship.getGiftHistory().size(); i++) {
            Gift gift = relationship.getGiftHistory().get(i);
            if (gift.getPayer().equals(player)) {
                // all gifts are always rated at start of new turn
                messageBuilder.append(gift.getPayer().getName())
                        .append(" gave you ")
                        .append(gift.getAmount())
                        .append(" ")
                        .append(gift.getItemName())
                        .append(" as gift and you gave rating")
                        .append(gift.getRate())
                        .append("/5 to it. ").append("(").append(gift.getTimeStamp()).append(")")
                        .append("\n");
            } else {
                if (gift.getRate() == 0) {

                    messageBuilder.append("You gave ")
                            .append(player.getName())
                            .append(" ")
                            .append(gift.getAmount())
                            .append(" ")
                            .append(gift.getItemName())
                            .append(" and ")
                            .append(player.getName())
                            .append(" has not seen it yet. ").append("(").append(gift.getTimeStamp()).append(")")
                            .append("\n");

                } else {
                    messageBuilder
                            .append("You gave ")
                            .append(player.getName())
                            .append(" ")
                            .append(gift.getAmount())
                            .append(" ")
                            .append(gift.getItemName())
                            .append(" and ")
                            .append(player.getName())
                            .append(" gave ")
                            .append(gift.getRate())
                            .append("/5 to it. ").append("(").append(gift.getTimeStamp()).append(")")
                            .append("\n");
                }
            }
        }
        return new Result(true, messageBuilder.toString().trim());
    }

    public static Result showGiftList() {
        StringBuilder messageBuilder = new StringBuilder();
        int i = 1;
        for (Gift gift : App.getCurrentGame().getCurrentPlayer().getReceivedGifts()) {
            messageBuilder
                    .append("gift No.").append(i).append(": ")
                    .append(gift.getPayer().getName())
                    .append(" gave you ")
                    .append(gift.getAmount())
                    .append(" ")
                    .append(gift.getItemName())
                    .append(" as gift.")
                    .append("\n");
        }
        return new Result(true, messageBuilder.toString().trim());
    }

    public static Result rateGift(int number, int rate) {
        if (rate > 5 || rate < 1) {
            return new Result(false, "rate between 1 to 5");
        }
        if (number < 1 || number > (App.getCurrentGame().getCurrentPlayer().getReceivedGifts().size())) {
            return new Result(false, "number not in list");
        }
        Gift gift = App.getCurrentGame().getCurrentPlayer().getReceivedGifts().get(number - 1);
        Player player = gift.getPayer();
        Relationship relationship = App.getCurrentGame().getRelationship(App.getCurrentGame().getCurrentPlayer(), player);
        if (relationship.getGiftDailyCount() == 0) {
            relationship.increaseXP(((rate - 3) * 30) + 15);
        }

        if (App.getCurrentGame().getCurrentPlayer().getPartner() != null) {
            if (App.getCurrentGame().getCurrentPlayer().getPartner().equals(player) && relationship.getPartnerDailyCount() == 0) {
                player.increaseEnergy(50 +
                        player.getEnergy());
                App.getCurrentGame().getCurrentPlayer().setEnergy(50 +
                        App.getCurrentGame().getCurrentPlayer().getEnergy());
            }
        }
        App.getCurrentGame().getCurrentPlayer().getReceivedGifts().remove(number - 1);
        gift.setRate(rate);
        return new Result(true, "done successfully");
    }

    public static Result hug(String username) {
        Game game = App.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player player = game.getPlayerByUsername(username);
        if (player == null) {
            return CommonGameController.playerNotFound();
        }

        Relationship relationship = game.getRelationship(currentPlayer, player);

        if (!MapController.isNear(currentPlayer.getCurrentLocation(), player)) {
            return new Result(false, "You must be next to each other to hug :)");
        }

        if (relationship.getLevel() < 2) {
            return new Result(false, "Wait, it's too soon! you should reach relationship level 2 first");
        }

        if (relationship.getHugDailyCount() == 0) {
            relationship.increaseXP(60);
        }
        if (currentPlayer.getPartner() != null) {
            if (App.getCurrentGame().getCurrentPlayer().getPartner().equals(player) && relationship.getPartnerDailyCount() == 0) {
                player.increaseEnergy(50 +
                        player.getEnergy());
                App.getCurrentGame().getCurrentPlayer().setEnergy(50 +
                        App.getCurrentGame().getCurrentPlayer().getEnergy());
            }
        }
        return new Result(true, "you hugged " + player.getName());
    }

    public static Result flower(String username) {
        Game game = App.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player player = game.getPlayerByUsername(username);
        if (player == null)
            return CommonGameController.playerNotFound();

        Relationship relationship = game.getRelationship(App.getCurrentGame().getCurrentPlayer(), player);
        UniqueItem uniqueItem = UniqueItem.getUniqueItem("Bouquet");
        if (App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(uniqueItem) == 0) {
            return new Result(false, "you don't have any flower bouquets");
        }
        if (relationship.getXP() < 300 && relationship.getLevel() < 3) {
            return new Result(false, "Wait, it's too soon! your relationship should be level 2 with at least 300XP for giving flower");
        }
        if (!player.getBackpack().addItem(uniqueItem, 1)) {
            return new Result(false, player.getName() + " has no space in backpack");
        }
        if (currentPlayer.getPartner() != null) {
            if (currentPlayer.getPartner().equals(player) && relationship.getPartnerDailyCount() == 0) {
                player.increaseEnergy(50 +
                        player.getEnergy());
                App.getCurrentGame().getCurrentPlayer().setEnergy(50 +
                        App.getCurrentGame().getCurrentPlayer().getEnergy());
            }
        }
        relationship.increaseLevel();
        App.getCurrentGame().getCurrentPlayer().getBackpack().removeItem(uniqueItem, 1);
        return new Result(true, "flower sent");
    }

    public static Result askMarriage(String username, String ringName) {
        Player player = App.getCurrentGame().getPlayerByUsername(username);
        if (player == null)
            return CommonGameController.playerNotFound();
        Relationship relationship = App.getCurrentGame().getRelationship(App.getCurrentGame().getCurrentPlayer(), player);
        if (App.getCurrentGame().getCurrentPlayer().getPartner() != null) {
            return new Result(false, "You are already married.");
        }
        if (player.getPartner() != null) {
            return new Result(false, "this player is already married.");
        }
        if (App.getCurrentGame().getCurrentPlayer().getGender().equals(player.getGender())) {
            return new Result(true, "Sorry, we don't support gays.");
        }
        if (relationship.getLevel() < 3 && relationship.getXP() < 400) {
            return new Result(false, "Wait, it's too soon!  your relationship should be level 3 with at least 400XP for marriage proposal");
        }

        if (relationship.getRing() != null) {
            return new Result(false, "You have already asked this player! please wait for their response");
        }

        // TODO why is ringName unused ?

        UniqueItem ring = UniqueItem.getUniqueItem("Wedding Ring");
        if (ring == null) {
            return new Result(false, "ERROR: ring doesn't exist in items");
        }

        if (App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(ring) == 0) {
            return new Result(false, "you don't have " + ring.getName());
        }
        relationship.setRing(ring);
        player.getAskedForMarriage().add(App.getCurrentGame().getCurrentPlayer());
        return new Result(true, "You have asked this player for marriage, wait for their response");
    }

    public static Result respondToMarriage(String otherPlayerName, String answer) {
        Game game = App.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player player = game.getPlayerByUsername(otherPlayerName);
        if (player == null)
            return CommonGameController.playerNotFound();

        Relationship relationship = game.getRelationship(currentPlayer, player);
        boolean marriage = answer.equals("accept");
        if (!App.getCurrentGame().getCurrentPlayer().getAskedForMarriage().contains(player)) {
            return new Result(false, player.getName() + "isn't asking for your hand in marriage");
        }

        if (marriage) {
            relationship.increaseLevel();
            int totalMoney = currentPlayer.getMoney() + player.getMoney();
            player.decreaseMoney(player.getMoney());
            currentPlayer.decreaseMoney(currentPlayer.getMoney());
            currentPlayer.setMarriage(relationship);
            relationship.setSharedMoney(totalMoney);
            currentPlayer.getBackpack().addItem(relationship.getRing(), 1);
            return new Result(true, "successfully accepted");
        }

        relationship.resetLevel();
        player.setHeartBreakDaysRemaining(7);
        player.getBackpack().addItem(relationship.getRing(), 1);
        return new Result(true, "successfully rejected");

    }

    public static Result tradeRequest(String username, String itemName, int amount) {
        if (App.getCurrentGame().getPlayerByUsername(username) == null) {
            return CommonGameController.playerNotFound();
        }
        Item item = CommonGameController.findItem(itemName);
        if (item == null) {
            return new Result(false, "item not found");
        }
        if (amount < 1) {
            return new Result(false, "amount shpuld be positive");
        }
        if (!App.getCurrentGame().getCurrentPlayer().getBackpack().removeItem(item, amount)) {
            return new Result(false, "not enough product");
        }
        Trade trade = new Trade(App.getCurrentGame().getCurrentPlayer(), App.getCurrentGame().getPlayerByUsername(username), "offer", itemName, amount, 0, "", 0);
        App.getCurrentGame().getPlayerByUsername(username).getReceivedTrades().add(trade);
        return new Result(true, "offer sent successfully");
    }

    public static Result tradeWithMoney(String username, String itemName, int amount, int price) {
        if (App.getCurrentGame().getPlayerByUsername(username) == null) {
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
        if (App.getCurrentGame().getCurrentPlayer().getMoney() < price) {
            return new Result(false, "not enough money");
        }
        Trade trade = new Trade(App.getCurrentGame().getCurrentPlayer(), App.getCurrentGame().getPlayerByUsername(username), "request", itemName, amount, price, "", 0);
        App.getCurrentGame().getPlayerByUsername(username).getReceivedTrades().add(trade);
        return new Result(true, "offer sent successfully");
    }

    public static Result tradeWithProduct(String username, String itemName, int amount, String targetItem, int targetAmount) {
        if (App.getCurrentGame().getPlayerByUsername(username) == null) {
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
        if (!App.getCurrentGame().getCurrentPlayer().getBackpack().removeItem(item, amount)) {
            return new Result(false, "not enough product");
        }
        Trade trade = new Trade(App.getCurrentGame().getCurrentPlayer(), App.getCurrentGame().getPlayerByUsername(username), "request", itemName, amount, 0, "", targetAmount);
        App.getCurrentGame().getPlayerByUsername(username).getReceivedTrades().add(trade);
        return new Result(true, "offer sent successfully");
    }

    public static Result tradeResponse(boolean accept, int id) {
        if (id > App.getCurrentGame().getCurrentPlayer().getReceivedTrades().size()) {
            return new Result(false, "id out of range");
        }
        Trade trade = App.getCurrentGame().getCurrentPlayer().getReceivedTrades().get(id - 1);
        Relationship relationship = App.getCurrentGame().getRelationship(trade.getSender(), trade.getReceiver());
        Item item = CommonGameController.findItem(trade.getItem());

        if (accept) {
            if (trade.getType().equals("request")) {
                if (trade.getPrice() == 0) {
                    Item item1 = CommonGameController.findItem(trade.getTargetItem());
                    if (!App.getCurrentGame().getCurrentPlayer().getBackpack().removeItem(item1, trade.getTargetAmount())) {
                        return new Result(false, "not enough product");
                    }
                    App.getCurrentGame().getCurrentPlayer().getBackpack().addItem(item, trade.getAmount());
                    trade.getSender().getBackpack().addItem(item1, trade.getTargetAmount());
                    Trade temp = new Trade(trade.getSender(), trade.getReceiver(), trade.getType(), trade.getItem(), trade.getAmount(), trade.getPrice(), trade.getTargetItem(), trade.getTargetAmount());
                    temp.setAccepted(true);
                    relationship.getTradeHistory().add(temp);
                    App.getCurrentGame().getCurrentPlayer().getReceivedTrades().remove(id - 1);
                    return new Result(true, "offer accepted successfully");
                } else {
                    if (App.getCurrentGame().getCurrentPlayer().getMoney() < trade.getPrice()) {
                        return new Result(false, "not enough money");
                    } else if (!App.getCurrentGame().getCurrentPlayer().getBackpack().addItem(item, trade.getAmount())) {
                        return new Result(false, "not enough space in backpack");
                    } else {
                        App.getCurrentGame().getCurrentPlayer().decreaseMoney(trade.getPrice());
                        Trade temp = new Trade(trade.getSender(), trade.getReceiver(), trade.getType(), trade.getItem(), trade.getAmount(), trade.getPrice(), trade.getTargetItem(), trade.getTargetAmount());
                        temp.setAccepted(true);
                        relationship.getTradeHistory().add(temp);
                        App.getCurrentGame().getCurrentPlayer().getReceivedTrades().remove(id - 1);
                        return new Result(true, "offer accepted");
                    }
                }
            } else {
                if (!App.getCurrentGame().getCurrentPlayer().getBackpack().addItem(item, trade.getAmount())) {
                    return new Result(false, "not enough space");
                }
                Trade temp = new Trade(trade.getSender(), trade.getReceiver(), trade.getType(), trade.getItem(), trade.getAmount(), trade.getPrice(), trade.getTargetItem(), trade.getTargetAmount());
                temp.setAccepted(true);
                relationship.getTradeHistory().add(temp);
                App.getCurrentGame().getCurrentPlayer().getReceivedTrades().remove(id - 1);
                return new Result(true, "offer accepted successfully");
            }
        } else {
            trade.setAccepted(false);
            trade.getSender().getBackpack().addItem(item, trade.getAmount());
            Trade temp = new Trade(trade.getSender(), trade.getReceiver(), trade.getType(), trade.getItem(), trade.getAmount(), trade.getPrice(), trade.getTargetItem(), trade.getTargetAmount());
            temp.setAccepted(false);
            relationship.getTradeHistory().add(temp);
            App.getCurrentGame().getCurrentPlayer().getReceivedTrades().remove(id - 1);
            return new Result(true, "offer rejected successfully");
        }
    }

}
