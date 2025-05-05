package model.Shops;

import model.alive.Human;
import model.items.ItemsInShops;

import java.util.ArrayList;

public class JojoMartShop extends Shop {
    private ArrayList<ItemsInShops> permanentStock;
    private ArrayList<ItemsInShops> springStock;
    private ArrayList<ItemsInShops> summerStock;
    private ArrayList<ItemsInShops> fallStock;
    private ArrayList<ItemsInShops> winterStock;
    public JojoMartShop(Human human) {
        super(human,9,23);
        permanentStock = new ArrayList<>();
        springStock = new ArrayList<>();
        summerStock = new ArrayList<>();
        fallStock = new ArrayList<>();
        winterStock = new ArrayList<>();
    }

    public ArrayList<ItemsInShops> getPermanentStock() {
        return permanentStock;
    }

    public void setPermanentStock(ArrayList<ItemsInShops> permanentStock) {
        this.permanentStock = permanentStock;
    }

    public ArrayList<ItemsInShops> getSpringStock() {
        return springStock;
    }

    public void setSpringStock(ArrayList<ItemsInShops> springStock) {
        this.springStock = springStock;
    }

    public ArrayList<ItemsInShops> getSummerStock() {
        return summerStock;
    }

    public void setSummerStock(ArrayList<ItemsInShops> summerStock) {
        this.summerStock = summerStock;
    }

    public ArrayList<ItemsInShops> getFallStock() {
        return fallStock;
    }

    public void setFallStock(ArrayList<ItemsInShops> fallStock) {
        this.fallStock = fallStock;
    }

    public ArrayList<ItemsInShops> getWinterStock() {
        return winterStock;
    }

    public void setWinterStock(ArrayList<ItemsInShops> winterStock) {
        this.winterStock = winterStock;
    }
}
