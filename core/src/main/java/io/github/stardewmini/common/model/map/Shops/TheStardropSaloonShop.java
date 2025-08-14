package io.github.stardewmini.common.model.map.Shops;

import io.github.stardewmini.client.Renderers.GameAssetManager;
import io.github.stardewmini.common.model.items.ShopItem;
import io.github.stardewmini.common.model.lives.NPC;
import io.github.stardewmini.common.model.map.Area;

import java.util.ArrayList;

public class TheStardropSaloonShop extends Shop {
    ArrayList<ShopItem> permanentStock;
    public TheStardropSaloonShop (NPC owner, Area area) {
        super(owner,12,24, area);
        permanentStock = new ArrayList<>();

        this.getSprite().setRegion(GameAssetManager.getInstance().getBuilding("TheStardropSaloonShop"));
    }

    public ArrayList<ShopItem> getPermanentStock() {
        return permanentStock;
    }

    public void setPermanentStock(ArrayList<ShopItem> permanentStock) {
        this.permanentStock = permanentStock;
    }
}
