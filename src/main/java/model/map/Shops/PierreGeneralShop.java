package model.map.Shops;

import model.lives.NPC;
import model.items.ShopItem;
import model.map.Area;

import java.util.ArrayList;

public class PierreGeneralShop extends Shop {
    private ArrayList<ShopItem> yearRoundStock;
    private ArrayList<BackPacksItems> backPacks;
    private ArrayList<SeasonalStockItems> springStock;
    private ArrayList<SeasonalStockItems> summerStock;
    private ArrayList<SeasonalStockItems> fallStock;
    public PierreGeneralShop(NPC owner, Area area) {
        super(owner,9,17, area);
        this.yearRoundStock = new ArrayList<>();
        this.backPacks = new ArrayList<>();
        this.springStock = new ArrayList<>();
        this.summerStock = new ArrayList<>();
        this.fallStock = new ArrayList<>();
    }

    public ArrayList<ShopItem> getYearRoundStock() {
        return yearRoundStock;
    }

    public void setYearRoundStock(ArrayList<ShopItem> yearRoundStock) {
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

    public static class BackPacksItems extends ShopItem {
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
    public static class SeasonalStockItems extends ShopItem {
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
