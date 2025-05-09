package model.Shops;

import model.alive.Human;
import model.items.ItemsInShops;

import java.util.ArrayList;

public class PierreGeneralShop extends Shop {
    private ArrayList<ItemsInShops> yearRoundStock;
    private ArrayList<BackPacksItems> backPacks;
    private ArrayList<SeasonalStockItems> springStock;
    private ArrayList<SeasonalStockItems> summerStock;
    private ArrayList<SeasonalStockItems> fallStock;
    public PierreGeneralShop(Human owner) {
        super(owner,9,17);
        this.yearRoundStock = new ArrayList<>();
        this.backPacks = new ArrayList<>();
        this.springStock = new ArrayList<>();
        this.summerStock = new ArrayList<>();
        this.fallStock = new ArrayList<>();
    }
    public class BackPacksItems extends ItemsInShops {
        private boolean isPurchaseAble;
        public BackPacksItems(boolean isPurchaseAble,String name, int price,String description) {
            super(name, false, 1, price, description);
            this.isPurchaseAble = isPurchaseAble;
        }

        public boolean isPurchaseAble() {
            return isPurchaseAble;
        }

        public void setPurchaseAble(boolean purchaseAble) {
            isPurchaseAble = purchaseAble;
        }
    }
    public class SeasonalStockItems extends ItemsInShops {
        private int priceInSeason;
        private int priceOutOfSeason;
        public SeasonalStockItems (String name, int price,String description,int priceInSeason,int priceOutOfSeason) {
            super(name, false, 5, price, description);
            this.priceInSeason = priceInSeason;
            this.priceOutOfSeason = priceOutOfSeason;
        }
    }
}
