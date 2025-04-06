package model;

public class Gift {
    private final Player payer;
    private final String itemName;
    private final DateTime timeStamp;
    private int rate;

    public Gift(Player payer, String itemName, DateTime timeStamp) {
        this.payer = payer;
        this.itemName = itemName;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
