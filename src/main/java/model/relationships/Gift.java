package model.relationships;

import model.App;
import model.DateTime;
import model.alive.Player;

public class Gift {
    private final Player payer;
    private final String itemName;
    private final DateTime timeStamp;
    private final int amount;
    public Gift(Player payer, String itemName, int amount) {
        this.payer = payer;
        this.itemName = itemName;
        this.amount = amount;
        this.timeStamp = App.getCurrentGame().getDateTime();
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
}
