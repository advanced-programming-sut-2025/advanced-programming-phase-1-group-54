package model.Building;

import model.items.Item;

import java.util.HashMap;

public class Shop extends Building {
    private Character owner;
    private int openingHours;
    private int closingHours;

    public Character getOwner() {
        return owner;
    }

    public void setOwner(Character owner) {
        this.owner = owner;
    }

    public int getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(int openingHours) {
        this.openingHours = openingHours;
    }

    public int getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(int closingHours) {
        this.closingHours = closingHours;
    }

    public HashMap<Item, Integer> getItemPriceMap() {
        return itemPriceMap;
    }

    public void setItemPriceMap(HashMap<Item, Integer> itemPriceMap) {
        this.itemPriceMap = itemPriceMap;
    }

    HashMap<Item, Integer> itemPriceMap;
}
