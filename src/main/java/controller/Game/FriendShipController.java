package controller.Game;

import model.App;
import model.Result;
import model.alive.Player;
import model.items.Item;
import model.relationships.Gift;
import model.relationships.PlayerRelationship;
import model.relationships.Talk;

import java.util.ArrayList;

public class FriendShipController {
    public static ArrayList<PlayerRelationship> showFriendShip(Player player1){
        return PlayerRelationship.getPlayerRelationships(player1);
    }
    public static Result talk(String username, String message){
        if(App.getCurrentGame().getPlayerByUsername(username) == null){
            return new Result(false, "user not found");
        }
        Player player = App.getCurrentGame().getPlayerByUsername(username);
        PlayerRelationship relationship = getPlayerRelationship(App.getCurrentGame().getCurrentPlayer(), player);
        //TODO
        //if (player next to current player)
        Talk talk = new Talk(App.getCurrentGame().getCurrentPlayer(),message,App.getCurrentGame().getDateTime());
        relationship.getTalkHistory().add(talk);
        if(relationship.getTalkDailyCount() == 0) {
            relationship.increasXp(20);
        }
        relationship.increaseTalkDailyCount();
        return new Result(true, "message sent");
    }
    public static PlayerRelationship getPlayerRelationship(Player player1, Player player2){
        for (PlayerRelationship relationship : PlayerRelationship.getPlayerRelationships(player1)) {
            if(relationship.getPlayer2().equals(player2)){
                return relationship;
            }
        }
        return null;
    }
    public static ArrayList<String> talkHistory(String username){
        ArrayList<String> talkHistory = new ArrayList<>();
        Player player = App.getCurrentGame().getPlayerByUsername(username);
        PlayerRelationship relationship = getPlayerRelationship(App.getCurrentGame().getCurrentPlayer(), player);
        for (Talk talk : relationship.getTalkHistory()) {
            talkHistory.add(talk.sayer().getName() + ": " + talk.message());
        }
        return talkHistory;
    }

    public static Result gift(String username, String item, String amount) {
        int count = Integer.parseInt(amount);
        Player player = App.getCurrentGame().getPlayerByUsername(username);
        PlayerRelationship relationship = FriendShipController.getPlayerRelationship(App.getCurrentGame().getCurrentPlayer(), player);
        if(relationship.getLevel() < 1){
            return new Result(false,"you should reach level 1 first");
        }
        if(count < 0){
            return new Result(false, "amount must be a positive integer");
        }
        if(App.getCurrentGame().getPlayerByUsername(username) == null){
            return new Result(false, "user not found");
        }
        if (CommonGameController.findItem(item) == null){
            return new Result(false, "there is no item with this name that you could gift");
        }
        Item item1 = CommonGameController.findItem(item);
        if(CommonGameController.numberOfItemInBackPack(item) < count){
            return new Result(false,"not enough " + item + " in back pack");
        }
        if (App.getCurrentGame().getCurrentPlayer().getBackpack().removeItem(item1,count) == false){
            return new Result(false,"not enough " + item + " in back pack");
        }
        if (player.getBackpack().addItem(item1,count) == false){
            return new Result(false,"not enough in " + player.getName() + " back pack");
        }
        Gift gift = new Gift(App.getCurrentGame().getCurrentPlayer(),item,count);
        player.getReceivedGifts().add(gift);
        relationship.getGiftHistory().add(gift);
        return new Result(true,"gift sent");
    }
    public static ArrayList<String> giftHistory(Player player){
        ArrayList<String> giftHistory = new ArrayList<>();
        PlayerRelationship relationship = getPlayerRelationship(App.getCurrentGame().getCurrentPlayer(), player);
        for (int i = 0; i < relationship.getGiftHistory().size(); i++) {
            Gift gift = relationship.getGiftHistory().get(i);
            if(gift.getPayer().equals(player)) {
                if (gift.getRate() == 0) {
                    String st = gift.getPayer().getName() + " gave you " + gift.getAmount() + gift.getItemName() + " as gift and you gave " + gift.getRate() + "/5 to it.";
                    giftHistory.add(st);

                } else {
                    String st = gift.getPayer().getName() + " gave you " + gift.getAmount() + gift.getItemName() + " as gift and you still haven't given it a rate.";
                    giftHistory.add(st);
                }
            }
            else{
                if (gift.getRate() == 0) {

                    String st = "You gave " + player.getName() + " " + gift.getAmount() + " " + gift.getItemName() + " and " + player.getName() + " still hasn't given them a point.";
                    giftHistory.add(st);

                } else {
                    String st = "You gave " + player.getName() + " " + gift.getAmount() + " " + gift.getItemName() + " and " + player.getName() + " gave " + gift.getRate() + "/5 to it.";
                    giftHistory.add(st);
                }
            }
        }
        return giftHistory;
    }
    public static  ArrayList<String> giftList(){
        ArrayList<String> giftHistory = new ArrayList<>();
        for (Gift gift : App.getCurrentGame().getCurrentPlayer().getReceivedGifts()){
            String st = gift.getPayer().getName() + " gave you " + gift.getAmount() + gift.getItemName() + " as gift.";
            giftHistory.add(st);
        }
        return giftHistory;
    }

    public static Result giftRate(int number, int rate) {
        if (rate > 5 || rate < 1){
            return new Result(false,"rate between 1 to 5");
        }
        if (number < 1 || number > (App.getCurrentGame().getCurrentPlayer().getReceivedGifts().size())){
            return new Result(false,"number not in list");
        }
        Gift gift = App.getCurrentGame().getCurrentPlayer().getReceivedGifts().get(number-1);
        Player player = gift.getPayer();
        PlayerRelationship relationship = FriendShipController.getPlayerRelationship(App.getCurrentGame().getCurrentPlayer(), player);
        if (relationship.getGiftDailyCount() == 0) {
            relationship.increasXp(((rate - 3) * 30) + 15);
        }

        if (App.getCurrentGame().getCurrentPlayer().getPartner() != null){
            if(App.getCurrentGame().getCurrentPlayer().getPartner().equals(player) && relationship.getPartnerDailyCount() == 0){
                player.increaseEnergy(50 +
                        player.getEnergy());
                App.getCurrentGame().getCurrentPlayer().setEnergy(50 +
                        App.getCurrentGame().getCurrentPlayer().getEnergy());
            }
        }
        App.getCurrentGame().getCurrentPlayer().getReceivedGifts().remove(number-1);
        gift.setRate(rate);
        return new Result(true,"done successfully");
    }

    public static Result hug(Player player) {
        PlayerRelationship relationship = FriendShipController.getPlayerRelationship(App.getCurrentGame().getCurrentPlayer(), player);
        //TODO if (next to each other)
        if(relationship.getLevel() < 2){
            return new Result(false,"you should reach level 2 first");
        }
        if(relationship.getHugDailyCount() == 0) {
            relationship.increasXp(60);
        }
        if (App.getCurrentGame().getCurrentPlayer().getPartner() != null){
            if(App.getCurrentGame().getCurrentPlayer().getPartner().equals(player) && relationship.getPartnerDailyCount() == 0){
                player.increaseEnergy(50 +
                        player.getEnergy());
                App.getCurrentGame().getCurrentPlayer().setEnergy(50 +
                        App.getCurrentGame().getCurrentPlayer().getEnergy());
            }
        }
        return new Result(true,"you hugged " + player.getName());
    }
    public static void relaitionshipUpdate(){
        for (PlayerRelationship relationship : App.getCurrentGame().getPlayerRelationships()){
            relationship.reset();
        }
    }

    public static Result flower(Player player) {
        PlayerRelationship relationship = FriendShipController.getPlayerRelationship(App.getCurrentGame().getCurrentPlayer(), player);
        //TODO check flower exist and send it
        if (relationship.getXp() < 300 && relationship.getLevel() < 3){
            return new Result(false,"your friendship xp should reach to 300 at level 2 for sending flower");
        }
        if (App.getCurrentGame().getCurrentPlayer().getPartner() != null){
            if(App.getCurrentGame().getCurrentPlayer().getPartner().equals(player) && relationship.getPartnerDailyCount() == 0){
                player.increaseEnergy(50 +
                        player.getEnergy());
                App.getCurrentGame().getCurrentPlayer().setEnergy(50 +
                        App.getCurrentGame().getCurrentPlayer().getEnergy());
            }
        }
        relationship.increasLevel();
        return new Result(true,"flower sent");
    }

    public static Result askMarriage(Player player, String ring) {
        PlayerRelationship relationship = FriendShipController.getPlayerRelationship(App.getCurrentGame().getCurrentPlayer(), player);
        if (App.getCurrentGame().getCurrentPlayer().getPartner() != null){
            return new Result(false,"player is married");
        }
        if (relationship.getLevel() < 3 || relationship.getLevel() > 4){
            return new Result(false,"you can't because of your friendship level");
        }
        if (relationship.getXp() < 400){
            return new Result(false,"your friendship xp should reach to 400 at level 3 for asking marriage");
        }
        //TODO check ring exist and can be transported
        //TODO relaitionship. ring = ring
        player.getAskedForMarriage().add(App.getCurrentGame().getCurrentPlayer());
        return null;
    }

    public static Result respondForMarriage(Player player, boolean marriage) {
        if(!App.getCurrentGame().getCurrentPlayer().getAskedForMarriage().contains(player)){
            return new Result(false,player.getName() + " not asking marriage");
        }
        PlayerRelationship relationship = FriendShipController.getPlayerRelationship(App.getCurrentGame().getCurrentPlayer(), player);
        if(marriage){
            //TODO relaition.ring add to inv
            relationship.increasLevel();
            App.getCurrentGame().getCurrentPlayer().setPartner(player);
            player.setPartner(App.getCurrentGame().getCurrentPlayer());
            CommonGameController.acceptMarriage(player);
            return new Result(true,"successfully accepted");
        }
        else{
            relationship.resetlevel();
            CommonGameController.getregectedInMarriage(App.getCurrentGame().getCurrentPlayer());
            return new Result(true,"successfully rejected");
            //TODO relaitionship.back to player
        }
    }

    public static void decreaseHeartBropken() {
        for (Player player : App.getCurrentGame().getPlayers()){
            if(player.getHeartBroken() > 0){
                player.decreaseHeartBroken();
            }
        }
    }
}
