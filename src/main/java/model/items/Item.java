package model.items;

public abstract class Item {
    protected final String name;
    protected final boolean isEdible;
    protected final int baseSellPrice;
//    protected final boolean sellable;

//    public Item(String name, boolean isEdible, boolean sellable) {
//        this.name = name;
//        this.isEdible = isEdible;
//        this.sellable = sellable;
//    }


    public Item(String name, boolean isEdible, int sellPrice) {
        this.name = name;
        this.isEdible = isEdible;
        this.baseSellPrice = sellPrice;
    }

    public String getName() {
        return name;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }
}
