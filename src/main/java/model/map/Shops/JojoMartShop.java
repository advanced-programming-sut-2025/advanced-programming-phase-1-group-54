package model.map.Shops;

import model.lives.NPC;
import model.items.ShopItem;
import model.map.Area;

import java.util.ArrayList;

public class JojoMartShop extends Shop {
    private ArrayList<ShopItem> permanentStock;
    private ArrayList<ShopItem> springStock;
    private ArrayList<ShopItem> summerStock;
    private ArrayList<ShopItem> fallStock;
    private ArrayList<ShopItem> winterStock;
    public JojoMartShop(NPC owner, Area area) {
        super(owner,9,23, area);
        permanentStock = new ArrayList<>();
        springStock = new ArrayList<>();
        summerStock = new ArrayList<>();
        fallStock = new ArrayList<>();
        winterStock = new ArrayList<>();
    }

    public ArrayList<ShopItem> getPermanentStock() {
        return permanentStock;
    }

    public void setPermanentStock(ArrayList<ShopItem> permanentStock) {
        this.permanentStock = permanentStock;
    }

    public ArrayList<ShopItem> getSpringStock() {
        return springStock;
    }

    public void setSpringStock(ArrayList<ShopItem> springStock) {
        this.springStock = springStock;
    }

    public ArrayList<ShopItem> getSummerStock() {
        return summerStock;
    }

    public void setSummerStock(ArrayList<ShopItem> summerStock) {
        this.summerStock = summerStock;
    }

    public ArrayList<ShopItem> getFallStock() {
        return fallStock;
    }

    public void setFallStock(ArrayList<ShopItem> fallStock) {
        this.fallStock = fallStock;
    }

    public ArrayList<ShopItem> getWinterStock() {
        return winterStock;
    }

    public void setWinterStock(ArrayList<ShopItem> winterStock) {
        this.winterStock = winterStock;
    }
}
