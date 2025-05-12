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
        relationship.increasXp(20);
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
        if(count < 0){
            return new Result(false, "amount must be a positive integer");
        }
        if(App.getCurrentGame().getPlayerByUsername(username) == null){
            return new Result(false, "user not found");
        }
        Player player = App.getCurrentGame().getPlayerByUsername(username);
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
        player.getRecivedGifts().add(new Gift(App.getCurrentGame().getCurrentPlayer(),item,count));
        return new Result(true,"gift sent");
    }
}
