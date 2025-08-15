package io.github.stardewmini.common.model.relationships;

import io.github.stardewmini.common.model.DailyUpdate;
import io.github.stardewmini.common.model.lives.NPC;

public class NPCFriendship extends Friendship implements DailyUpdate {
    private NPC npc;
    int dailyTalkTime = 0;
    int dailyGift = 0;

    public NPCFriendship(NPC npc) {
        this.npc = npc;
    }

    public void increaseDailyTalkTime() {
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

    public void increaseXP(int xp) {
        this.xp += xp;
        this.setLevel(this.xp / 200);
        if (this.level > 3) {
            this.level = 3;
        }
    }

    @Override
    public void nextDayUpdate() {
        dailyGift = 0;
        dailyTalkTime = 0;
    }
}
