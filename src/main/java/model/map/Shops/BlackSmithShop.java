package model.map.Shops;

import model.lives.NPC;
import model.items.ShopItem;
import model.map.Area;

import java.util.ArrayList;

public class BlackSmithShop extends Shop {
    private ArrayList<UpgradeToolBlackSmith> upgradeTools;
    private ArrayList<ShopItem> stock;
    public BlackSmithShop(NPC owner, Area area) {
        super(owner,9,16, area);
        upgradeTools = new ArrayList<>();
        stock = new ArrayList<>();
    }
    public ArrayList<UpgradeToolBlackSmith> getUpgradeTools() {
        return upgradeTools;
    }

    public void setUpgradeTools(ArrayList<UpgradeToolBlackSmith> upgradeTool) {
        this.upgradeTools = upgradeTool;
    }

    public ArrayList<ShopItem> getStock() {
        return stock;
    }

    public void setStock(ArrayList<ShopItem> stock) {
        this.stock = stock;
    }

    public static class UpgradeToolBlackSmith extends ShopItem {
        private String ingredientString;
        private int ingredientsInt;
        public UpgradeToolBlackSmith(String name, int price, String hashmapString, int hashmapInt) {
            super(name,false,1, price,"no description");
            this.ingredientsInt = hashmapInt;
            this.ingredientString = hashmapString;
        }

        public String getIngredientString() {
            return ingredientString;
        }

        public void setIngredientString(String ingredientString) {
            this.ingredientString = ingredientString;
        }

        public int getIngredientsInt() {
            return ingredientsInt;
        }

        public void setIngredientsInt(int ingredientsInt) {
            this.ingredientsInt = ingredientsInt;
        }
    }
}
