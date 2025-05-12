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
        private HashMap<String,Integer> ingredients;
        public upgradesToolsBlacsmithShop(String name, int price, String hashmapString, int hashmapInt) {
            super(name,false,1, price,"no description");
            ingredients = new HashMap<>();
            ingredients.put(hashmapString, hashmapInt);
        }
        public HashMap<String, Integer> getIngredients() {
            return ingredients;
        }
        public void setIngredients(HashMap<String, Integer> ingredient) {
            ingredients = ingredient;
        }
    }
}
