package model.items;

public class ItemsInShops extends Item {
    protected int count;
    protected int price;
    protected String description;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemsInShops(String name, boolean isEdible, int count, int price, String description) {
        super(name, isEdible);
        this.description = description;
        this.count = count;
        this.price = price;
    }

}
