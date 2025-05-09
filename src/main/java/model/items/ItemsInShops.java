package model.items;

public class ItemsInShops extends Item {
    protected int count;
    protected int price;
    protected String description;
    public ItemsInShops(String name,boolean isEdible, int count, int price,String description) {
        super(name, isEdible);
        this.description = description;
        this.count = count;
        this.price = price;
    }

}
