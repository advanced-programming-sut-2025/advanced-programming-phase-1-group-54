package model.items;

public abstract class Item{
    protected final String name;
    protected final boolean isEdible;

    public Item(String name, boolean isEdible) {
        this.name = name;
        this.isEdible = isEdible;
    }

    public String getName() {
        return name;
    }

    public boolean isEdible() {
        return isEdible;
    }
}
