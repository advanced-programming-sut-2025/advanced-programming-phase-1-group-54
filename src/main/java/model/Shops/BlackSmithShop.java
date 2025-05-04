package model.Shops;

import model.alive.Human;
import model.items.ItemsInShops;

import java.util.ArrayList;
import java.util.HashMap;

public class BlackSmithShop extends Shop {
    private static ArrayList<upgradesToolsBlacsmithShop> upgradeTools;
    private static ArrayList<ItemsInShops> Stock;
    public BlackSmithShop(Human owner) {
        super(owner,9,16);
        upgradeTools = new ArrayList<>();
        Stock = new ArrayList<>();
    }

    public ArrayList<upgradesToolsBlacsmithShop> getUpgradeTools() {
        return upgradeTools;
    }

    public void setUpgradeTools(ArrayList<upgradesToolsBlacsmithShop> upgradeTool) {
        upgradeTools = upgradeTool;
    }

    public ArrayList<ItemsInShops> getStock() {
        return Stock;
    }

    public void setStock(ArrayList<ItemsInShops> stock) {
        Stock = stock;
    }

    public static class upgradesToolsBlacsmithShop extends ItemsInShops {
        private static HashMap<String,Integer> ingredients;
        public upgradesToolsBlacsmithShop(String name, int price) {
            super(name,false,1, price,null);
            ingredients = new HashMap<>();
        }

        public HashMap<String, Integer> getIngredients() {
            return ingredients;
        }

        public void setIngredients(HashMap<String, Integer> ingredient) {
            ingredients = ingredient;
        }
    }
}
