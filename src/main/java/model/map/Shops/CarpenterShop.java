package model.map.Shops;

import model.lives.NPC;
import model.items.ShopItem;
import model.map.Area;

import java.util.ArrayList;

public class CarpenterShop extends Shop {
    private ArrayList<ShopItem> permanentStock;
    private ArrayList<ItemsinCarpenterShop> farmBuildings;
    public CarpenterShop(NPC owner, Area area) {
        super(owner,8,20, area);
        this.permanentStock = new ArrayList<>();
        this.farmBuildings = new ArrayList<>();
    }

    public ArrayList<ShopItem> getPermanentStock() {
        return permanentStock;
    }

    public void setPermanentStock(ArrayList<ShopItem> permanentStock) {
        this.permanentStock = permanentStock;
    }

    public ArrayList<ItemsinCarpenterShop> getFarmBuildings() {
        return farmBuildings;
    }

    public void setFarmBuildings(ArrayList<ItemsinCarpenterShop> farmBuildings) {
        this.farmBuildings = farmBuildings;
    }

    public static class ItemsinCarpenterShop extends ShopItem {
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
