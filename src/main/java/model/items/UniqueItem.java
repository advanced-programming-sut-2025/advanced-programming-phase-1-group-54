package model.items;

import java.util.HashMap;

public class UniqueItem extends Item {
    private final static HashMap<String, UniqueItem> items;

    public UniqueItem(String name, boolean isEdible, int sellPrice) {
        super(name, isEdible, sellPrice);
    }

    static {
        items = new HashMap<>();
        items.put("Rice", new UniqueItem("Rice", false, 0));
        items.put("Wheat Flour", new UniqueItem("Wheat Flour", false, 0));
        items.put("Bouquet", new UniqueItem("Bouquet", false, 0));
        items.put("Wedding Ring", new UniqueItem("Wedding Ring", false, 0));
        items.put("Sugar", new UniqueItem("Sugar", false, 0));
        items.put("Joja Cola", new UniqueItem("Joja Cola", true, 0));
        items.put("Hay", new UniqueItem("Hay", false, 0));
        items.put("Trout Soup", new UniqueItem("Trout Soup", true, 0));
    }

    public static UniqueItem getUniqueItem(String name) {
        UniqueItem uniqueItem = items.get(name);
        if (uniqueItem == null) {
            return null;
        } else {
            return uniqueItem.clone();
        }
    }

    @Override
    protected UniqueItem clone() {
        try {
            return (UniqueItem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof UniqueItem uniqueItem) {
            return uniqueItem.getName().equals(this.getName());
        }
        return false;
    }
}
