package model.relationships;

import model.App;
import model.DateTime;
import model.lives.Player;

public class Gift {
    private final Player payer;
    private final String itemName;
    private final DateTime timeStamp;
    private final int amount;
    private int rate;
    public Gift(Player payer, String itemName, int amount, DateTime timeStamp) {
        this.payer = payer;
        this.itemName = itemName;
        this.amount = amount;
        this.rate = 0;
        this.timeStamp = timeStamp;
    }

    public Player getPayer() {
        return payer;
    }

    public String getItemName() {
        return itemName;
    }

    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public int getAmount() {
        return amount;
    }
    public int getRate() {
        return rate;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
}
