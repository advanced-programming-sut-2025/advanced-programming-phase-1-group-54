package model.Shops;

import model.alive.Human;
import model.items.ItemsInShops;

import java.util.ArrayList;
import java.util.HashMap;

public class CarpenterShop extends Shop {
    private ArrayList<ItemsInShops> permanentStock;
    private ArrayList<ItemsinCarpenterShop> farmBuildings;
    public CarpenterShop(Human owner) {
        super(owner,8,20);
        this.permanentStock = new ArrayList<>();
        this.farmBuildings = new ArrayList<>();
    }

    public ArrayList<ItemsInShops> getPermanentStock() {
        return permanentStock;
    }

    public void setPermanentStock(ArrayList<ItemsInShops> permanentStock) {
        this.permanentStock = permanentStock;
    }

    public ArrayList<ItemsinCarpenterShop> getFarmBuildings() {
        return farmBuildings;
    }

    public void setFarmBuildings(ArrayList<ItemsinCarpenterShop> farmBuildings) {
        this.farmBuildings = farmBuildings;
    }

    public class ItemsinCarpenterShop extends ItemsInShops {
        int xSize;
        int ySize;
        HashMap<String,Integer> Cost;
        public ItemsinCarpenterShop(int xSize,int ySize,String name, int count, int price,String description) {
            super( name,false,  count,  price, description);
            this.xSize = xSize;
            this.ySize = ySize;
            this.Cost = new HashMap<>();
        }

        public int getySize() {
            return ySize;
        }

        public void setySize(int ySize) {
            this.ySize = ySize;
        }

        public HashMap<String, Integer> getCost() {
            return Cost;
        }

        public void setCost(HashMap<String, Integer> cost) {
            Cost = cost;
        }

        public int getxSize() {
            return xSize;
        }

        public void setxSize(int xSize) {
            this.xSize = xSize;
        }
    }
}
