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

    public ArrayList<ItemsInShops> getYearRoundStock() {
        return yearRoundStock;
    }

    public void setYearRoundStock(ArrayList<ItemsInShops> yearRoundStock) {
        this.yearRoundStock = yearRoundStock;
    }

    public ArrayList<BackPacksItems> getBackPacks() {
        return backPacks;
    }

    public void setBackPacks(ArrayList<BackPacksItems> backPacks) {
        this.backPacks = backPacks;
    }

    public ArrayList<SeasonalStockItems> getSpringStock() {
        return springStock;
    }

    public void setSpringStock(ArrayList<SeasonalStockItems> springStock) {
        this.springStock = springStock;
    }

    public ArrayList<SeasonalStockItems> getSummerStock() {
        return summerStock;
    }

    public void setSummerStock(ArrayList<SeasonalStockItems> summerStock) {
        this.summerStock = summerStock;
    }

    public ArrayList<SeasonalStockItems> getFallStock() {
        return fallStock;
    }

    public void setFallStock(ArrayList<SeasonalStockItems> fallStock) {
        this.fallStock = fallStock;
    }

    public static class BackPacksItems extends ItemsInShops {
        private boolean isPurchaseAble;
        private String isAvailable;
        public BackPacksItems(boolean isPurchaseAble,String name, int price,String description, String isAvailable) {
            super(name, false, 1, price, description);
            this.isPurchaseAble = isPurchaseAble;
            this.isAvailable = isAvailable;
        }

        public String getIsAvailable() {
            return isAvailable;
        }

        public void setIsAvailable(String isAvailable) {
            this.isAvailable = isAvailable;
        }

        public boolean isPurchaseAble() {
            return isPurchaseAble;
        }

        public void setPurchaseAble(boolean purchaseAble) {
            isPurchaseAble = purchaseAble;
        }
    }
    public static class SeasonalStockItems extends ItemsInShops {
        private int priceInSeason;
        private int priceOutOfSeason;
        public SeasonalStockItems (String name, int price,String description,int priceInSeason,int priceOutOfSeason) {
            super(name, false, 5, price, description);
            this.priceInSeason = priceInSeason;
            this.priceOutOfSeason = priceOutOfSeason;
        }

        public int getPriceInSeason() {
            return priceInSeason;
        }

        public void setPriceInSeason(int priceInSeason) {
            this.priceInSeason = priceInSeason;
        }

        public int getPriceOutOfSeason() {
            return priceOutOfSeason;
        }

        public void setPriceOutOfSeason(int priceOutOfSeason) {
            this.priceOutOfSeason = priceOutOfSeason;
        }
    }
}
