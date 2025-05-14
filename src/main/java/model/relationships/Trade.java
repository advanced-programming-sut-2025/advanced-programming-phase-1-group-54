package model.relationships;

import model.alive.Player;

public class Trade {
    private Player sender;
    private Player receiver;
    private String type;
    private String item;
    private int amount;
    private int price;
    private String targetItem;
    private int targetAmount;
    private boolean accepted;
    public Trade(Player sender, Player receiver, String type, String item, int amount, int price, String targetItem, int targetAmount) {
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.item = item;
        this.amount = amount;
        this.price = price;
        this.targetItem = targetItem;
        this.targetAmount = targetAmount;
    }

    public Player getSender() {
        return sender;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

    public Player getReceiver() {
        return receiver;
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTargetItem() {
        return targetItem;
    }

    public void setTargetItem(String targetItem) {
        this.targetItem = targetItem;
    }

    public int getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(int targetAmount) {
        this.targetAmount = targetAmount;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
