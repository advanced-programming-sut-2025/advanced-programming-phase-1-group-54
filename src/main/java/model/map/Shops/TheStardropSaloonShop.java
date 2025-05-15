package model.map.Shops;

import model.lives.NPC;
import model.items.ShopItem;
import model.map.Area;

import java.util.ArrayList;

public class TheStardropSaloonShop extends Shop {
    ArrayList<ShopItem> permanentStock;
    public TheStardropSaloonShop (NPC owner, Area area) {
        super(owner,12,24, area);
        permanentStock = new ArrayList<>();
    }

    public ArrayList<ShopItem> getPermanentStock() {
        return permanentStock;
    }

    public void setPermanentStock(ArrayList<ShopItem> permanentStock) {
        this.permanentStock = permanentStock;
    }
}
