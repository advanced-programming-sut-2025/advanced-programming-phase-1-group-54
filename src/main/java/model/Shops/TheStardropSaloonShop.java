package model.Shops;

import model.alive.Human;
import model.items.ItemsInShops;

import java.util.ArrayList;

public class TheStardropSaloonShop extends Shop {
    ArrayList<ItemsInShops> permanentStock;
    public TheStardropSaloonShop (Human owner) {
        super(owner,12,24);
        permanentStock = new ArrayList<>();
    }

    public ArrayList<ItemsInShops> getPermanentStock() {
        return permanentStock;
    }

    public void setPermanentStock(ArrayList<ItemsInShops> permanentStock) {
        this.permanentStock = permanentStock;
    }
}
