package model.relationships;

import model.App;
import model.alive.Player;

import java.util.ArrayList;

public class PlayerRelationship extends Friendship {
    private Player player1;
    private Player player2;
    private ArrayList<Talk> talkHistory;
    private ArrayList<Gift> giftHistory;
    private int giftDailyCount;
    private int hugDailyCount;
    private int talkDailyCount;
    private int partnerDailyCount;
    //TODO  private ring

    public PlayerRelationship(Player player1, Player player2) {
        super();
        this.player1 = player1;
        this.player2 = player2;
        this.talkHistory = new ArrayList<>();
        this.giftHistory = new ArrayList<>();
        this.giftDailyCount = 0;
        this.hugDailyCount = 0;
        this.talkDailyCount = 0;
    }

    public int getGiftDailyCount() {
        return giftDailyCount;
    }

    public int getHugDailyCount() {
        return hugDailyCount;
    }

    public int getTalkDailyCount() {
        return talkDailyCount;
    }
    public int getPartnerDailyCount() {
        return partnerDailyCount;
    }
    public void increasePartnerDailyCount() {
        this.partnerDailyCount++;
    }
    public void increaseGiftDailyCount() {
        giftDailyCount++;
    }
    public void increaseHugDailyCount() {
        hugDailyCount++;
    }
    public void increaseTalkDailyCount() {
        talkDailyCount++;
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

    //TODO public void setring ()
    public static ArrayList<PlayerRelationship> getPlayerRelationships(Player player) {
        ArrayList<PlayerRelationship> playerRelationships = new ArrayList<>();
        for (PlayerRelationship playerRelationship : App.getCurrentGame().getPlayerRelationships()){
            if (playerRelationship.getPlayer1().equals(player) || playerRelationship.getPlayer2().equals(player)){
                playerRelationships.add(playerRelationship);
            }
        }
        return playerRelationships;
    }
    public void reset(){
        this.giftDailyCount = 0;
        this.hugDailyCount = 0;
        this.talkDailyCount = 0;
    }
    @Override
    public void increasXp(int xp) {
        this.xp += xp;
        if (this.xp > (this.level + 1) * 100 && this.level < 2){
            this.xp = 0;
            this.level++;
        }
        if (this.xp < 0){
            this.xp += this.level * 100;
            if(this.level > 0) {
                this.level--;
            }
        }
    }
    public void increasLevel(){
        if (this.level < 4) {
            this.level++;
            this.xp = 0;
        }
    }
    public void resetlevel(){
        this.level = 0;
    }
}
