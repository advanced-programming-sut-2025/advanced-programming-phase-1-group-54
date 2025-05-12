package model.relationships;

import model.App;
import model.alive.Player;

import java.util.ArrayList;

public class PlayerRelationship extends Friendship {
    Player player1;
    Player player2;
    ArrayList<Talk> talkHistory;
    ArrayList<Gift> giftHistory;
    public PlayerRelationship(Player player1, Player player2) {
        super();
        this.player1 = player1;
        this.player2 = player2;
        this.talkHistory = new ArrayList<>();
        this.giftHistory = new ArrayList<>();
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public ArrayList<Talk> getTalkHistory() {
        return talkHistory;
    }

    public void setTalkHistory(ArrayList<Talk> talkHistory) {
        this.talkHistory = talkHistory;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public ArrayList<Gift> getGiftHistory() {
        return giftHistory;
    }

    public void setGiftHistory(ArrayList<Gift> giftHistory) {
        this.giftHistory = giftHistory;
    }

    public static ArrayList<PlayerRelationship> getPlayerRelationships(Player player) {
        ArrayList<PlayerRelationship> playerRelationships = new ArrayList<>();
        for (PlayerRelationship playerRelationship : App.getCurrentGame().getPlayerRelationships()){
            if (playerRelationship.getPlayer1().equals(player) || playerRelationship.getPlayer2().equals(player)){
                playerRelationships.add(playerRelationship);
            }
        }
        return playerRelationships;
    }
}
