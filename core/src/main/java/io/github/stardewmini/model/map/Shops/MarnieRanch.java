package io.github.stardewmini.model.map.Shops;

import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.items.ShopItem;
import io.github.stardewmini.model.lives.NPC;
import io.github.stardewmini.model.map.Area;

import java.util.ArrayList;

public class MarnieRanch extends Shop {
    ArrayList<ShopItem> shopInventory;
    ArrayList<ItemsInMarnieRanch> livesTock;

    public MarnieRanch(NPC owner, Area area) {
        super(owner, 9, 16, area);
        shopInventory = new ArrayList<>();
        livesTock = new ArrayList<>();

        this.getSprite().setRegion(GameAssetManager.getInstance().getBuilding("marine Shop"));
    }

    public ArrayList<ShopItem> getShopInventory() {
        return shopInventory;
    }

    public void setShopInventory(ArrayList<ShopItem> shopInventory) {
        this.shopInventory = shopInventory;
    }

    public ArrayList<ItemsInMarnieRanch> getLiveStock() {
        return livesTock;
    }

    public void setLivesTock(ArrayList<ItemsInMarnieRanch> livesTock) {
        this.livesTock = livesTock;
    }

    public static class ItemsInMarnieRanch extends ShopItem {
        private String buildingRequired;

        public ItemsInMarnieRanch(String buildingRequierd, int count, String name, int price, String descriptionString) {
            super(name, false, count, price, descriptionString);
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
