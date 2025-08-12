package io.github.stardewmini.model.map.Shops;

import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.items.ShopItem;
import io.github.stardewmini.model.lives.NPC;
import io.github.stardewmini.model.map.Area;

import java.util.ArrayList;

public class FishShop extends Shop {
    private ArrayList<StockInShop> stockInShop;

    public FishShop(NPC owner, Area area) {
        super(owner, 9, 17, area);
        stockInShop = new ArrayList<>();

        this.getSprite().setRegion(GameAssetManager.getInstance().getBuilding("Fish Shop"));
    }

    public ArrayList<StockInShop> getStockInShop() {
        return stockInShop;
    }

    public void setStockInShop(ArrayList<StockInShop> stockInShop) {
        this.stockInShop = stockInShop;
    }

    public static class StockInShop extends ShopItem {
        private int fishingSkillRequired;

        public StockInShop(int fishingSkillRequired, String name, boolean isEdible, int price, String description) {
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
