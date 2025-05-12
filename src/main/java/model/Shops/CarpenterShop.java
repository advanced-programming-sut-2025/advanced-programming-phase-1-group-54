package model.Shops;

import model.alive.Human;
import model.items.ItemsInShops;
import model.map.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class CarpenterShop extends Shop {
    private ArrayList<ItemsInShops> permanentStock;
    private ArrayList<ItemsinCarpenterShop> farmBuildings;
    public CarpenterShop(Human owner, Location upperLeftLocation, Location lowerRightLocation) {
        super(owner,8,20, upperLeftLocation, lowerRightLocation);
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

    public static class ItemsinCarpenterShop extends ItemsInShops {
        int xSize;
        int ySize;
        int wood;
        int stone;
        public ItemsinCarpenterShop(int xSize,int ySize,String name, int count, int price,String description, int wood, int stone) {
            super( name,false,  count,  price, description);
            this.xSize = xSize;
            this.ySize = ySize;
            this.wood = wood;
            this.stone = stone;
        }

        public int getySize() {
            return ySize;
        }

        public void setySize(int ySize) {
            this.ySize = ySize;
        }

        public int getWood() {
            return wood;
        }

        public void setWood(int wood) {
            this.wood = wood;
        }

        public int getStone() {
            return stone;
        }

        public void setStone(int stone) {
            this.stone = stone;
        }

        public int getxSize() {
            return xSize;
        }

        public void setxSize(int xSize) {
            this.xSize = xSize;
        }
    }
}
