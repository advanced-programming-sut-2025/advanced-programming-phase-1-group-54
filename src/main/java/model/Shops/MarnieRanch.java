package model.Shops;

import model.alive.Human;
import model.items.ItemsInShops;
import model.map.Location;

import java.util.ArrayList;

public class MarnieRanch extends Shop {
    ArrayList<ItemsInShops> shopInventory;
    ArrayList<ItemsInMarineRanch> livesTock;
    public MarnieRanch(Human owner, Location upperLeftLocation, Location lowerRightLocation) {
        super(owner,9,16, upperLeftLocation, lowerRightLocation);
        shopInventory = new ArrayList<>();
        livesTock = new ArrayList<>();
    }
    public ArrayList<ItemsInShops> getShopInventory() {
        return shopInventory;
    }

    public void setShopInventory(ArrayList<ItemsInShops> shopInventory) {
        this.shopInventory = shopInventory;
    }

    public ArrayList<ItemsInMarineRanch> getLivesTock() {
        return livesTock;
    }

    public void setLivesTock(ArrayList <ItemsInMarineRanch> livesTock) {
        this.livesTock = livesTock;
    }
    public static class ItemsInMarineRanch extends ItemsInShops {
        private String buildingRequired;
        public ItemsInMarineRanch(String buildingRequierd,int count,String name, int price,String descriptionString) {
            super(name,false,count,price,descriptionString);
            this.buildingRequired = buildingRequierd;
        }
        public String getBuildingRequired() {
            return buildingRequired;
        }

        public void setBuildingRequired(String buildingRequired) {
            this.buildingRequired = buildingRequired;
        }
    }
}
