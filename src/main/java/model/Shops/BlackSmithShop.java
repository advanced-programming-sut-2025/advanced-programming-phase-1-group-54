package model.Shops;

import model.alive.Human;
import model.items.ItemsInShops;
import model.map.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class BlackSmithShop extends Shop {
    private ArrayList<upgradesToolsBlacsmithShop> upgradeTools;
    private ArrayList<ItemsInShops> stock;
    public BlackSmithShop(Human owner, Location upperLeftLocation, Location lowerRightLocation) {
        super(owner,9,16, upperLeftLocation, lowerRightLocation);
        upgradeTools = new ArrayList<>();
        stock = new ArrayList<>();
    }
    public ArrayList<upgradesToolsBlacsmithShop> getUpgradeTools() {
        return upgradeTools;
    }

    public void setUpgradeTools(ArrayList<upgradesToolsBlacsmithShop> upgradeTool) {
        this.upgradeTools = upgradeTool;
    }

    public ArrayList<ItemsInShops> getStock() {
        return stock;
    }

    public void setStock(ArrayList<ItemsInShops> stock) {
        this.stock = stock;
    }

    public static class upgradesToolsBlacsmithShop extends ItemsInShops {
        private String ingredientString;
        private int ingredientsInt;
        public upgradesToolsBlacsmithShop(String name, int price, String hashmapString, int hashmapInt) {
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
