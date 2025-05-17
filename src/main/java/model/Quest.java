package model;

import model.lives.NPC;
import model.items.Item;

public class Quest {
    private NPC questGiver;



    private String requestedItem;
    private int requestedItemCount;

    private String reward;
    private int rewardCount;

    boolean isActive;
    boolean completed;

    public Quest(NPC questGiver, String requestedItem, int requestedItemCount, String reward, int rewardCount, boolean isActive) {
        this.questGiver = questGiver;
        this.requestedItem = requestedItem;
        this.requestedItemCount = requestedItemCount;
        this.reward = reward;
        this.rewardCount = rewardCount;
        this.isActive = isActive;
        this.completed = false;
    }

    public NPC getQuestGiver() {
        return questGiver;
    }

    public void setQuestGiver(NPC questGiver) {
        this.questGiver = questGiver;
    }

    public String getRequestedItem() {
        return requestedItem;
    }

    public void setRequestedItem(String requestedItem) {
        this.requestedItem = requestedItem;
    }

    public int getRequestedItemCount() {
        return requestedItemCount;
    }

    public void setRequestedItemCount(int requestedItemCount) {
        this.requestedItemCount = requestedItemCount;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public int getRewardCount() {
        return rewardCount;
    }

    public void setRewardCount(int rewardCount) {
        this.rewardCount = rewardCount;
    }

    public boolean isActive() {

        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
