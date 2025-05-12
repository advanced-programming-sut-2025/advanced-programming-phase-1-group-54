package model.Shops;

import model.alive.Human;
import model.items.ItemsInShops;
import model.map.Location;

import java.util.ArrayList;

public class FishShop extends Shop {
    private ArrayList<StockInShop> stockInShop;
    public FishShop(Human human, Location upperLeftLocation, Location lowerRightLocation) {
        super(human,9,17, upperLeftLocation, lowerRightLocation);
        stockInShop = new ArrayList<>();
    }

    public ArrayList<StockInShop> getStockInShop() {
        return stockInShop;
    }

    public void setStockInShop(ArrayList<StockInShop> stockInShop) {
        this.stockInShop = stockInShop;
    }

    public static class StockInShop extends ItemsInShops {
        private int fishingSkillRequired;
        public StockInShop(int fishingSkillRequired,String name,boolean isEdible, int price,String description) {
            super(name, isEdible, 1, price, description);
            this.fishingSkillRequired = fishingSkillRequired;
        }

        public int getPurchaseable() {
            return fishingSkillRequired;
        }

        public void setPurchaseable(int fishingSkillRequire) {
            fishingSkillRequired = fishingSkillRequire;
        }
    }
}
