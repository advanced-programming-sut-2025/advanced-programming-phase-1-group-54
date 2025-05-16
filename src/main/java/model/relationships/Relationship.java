package model.relationships;

import model.lives.Player;
import model.items.UniqueItem;

import java.util.ArrayList;

public class Relationship extends Friendship {
    private final Player player1;
    private final Player player2;
    private final ArrayList<Talk> talkHistory = new ArrayList<>();
    private final ArrayList<Gift> giftHistory = new ArrayList<>();
    private final ArrayList<Trade> tradeHistory = new ArrayList<>();
    private int giftDailyCount;
    private int hugDailyCount;
    private int talkDailyCount;
    private int partnerDailyCount;

    private UniqueItem ring;

    public Relationship(Player player1, Player player2) {
        super();
        this.player1 = player1;
        this.player2 = player2;
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

    public void setGiftDailyCount(int giftDailyCount) {
        this.giftDailyCount = giftDailyCount;
    }

    public void setHugDailyCount(int hugDailyCount) {
        this.hugDailyCount = hugDailyCount;
    }

    public void setTalkDailyCount(int talkDailyCount) {
        this.talkDailyCount = talkDailyCount;
    }

    public void setPartnerDailyCount(int partnerDailyCount) {
        this.partnerDailyCount = partnerDailyCount;
    }

    public UniqueItem getRing() {
        return ring;
    }

    public void setRing(UniqueItem ring) {
        this.ring = ring;
    }

    public ArrayList<Trade> getTradeHistory() {
        return tradeHistory;
    }

    public Player getPlayer1() {
        return player1;
    }

    public ArrayList<Talk> getTalkHistory() {
        return talkHistory;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getOtherPlayer(Player player) {
        if (player1.equals(player))
            return player2;

        return player1;
    }

    public ArrayList<Gift> getGiftHistory() {
        return giftHistory;
    }

    //TODO public void setring ()

    public void reset(){
        this.giftDailyCount = 0;
        this.hugDailyCount = 0;
        this.talkDailyCount = 0;
    }

    @Override
    public void increaseXP(int xp) {
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
