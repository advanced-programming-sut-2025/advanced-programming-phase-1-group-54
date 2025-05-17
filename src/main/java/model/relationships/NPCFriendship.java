package model.relationships;

import model.lives.NPC;

public class NPCFriendship extends Friendship {
    private NPC npc;
    int dailyTalkTime = 0;
    int dailyGift = 0;
    public NPCFriendship(NPC npc) {
        this.npc = npc;
    }
    public void incresmentDailyTalkTime() {
        dailyTalkTime++;
    }
    public int getDailyTalkTime() {
        return dailyTalkTime;
    }

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public int getDailyGift() {
        return dailyGift;
    }
    public void increaseDailyGift() {
        dailyGift++;
    }
    public void setDailyGift(int dailyGift) {
        this.dailyGift = dailyGift;
    }
    public void setDailyTalkTime(int dailyTalkTime) {
        this.dailyTalkTime = dailyTalkTime;
    }
    @Override
    public void increaseXP(int xp){
        this.xp += xp;
        this.setLevel(this.xp/200);
        if (this.level > 3){
            this.level = 3;
        }
    }
}
