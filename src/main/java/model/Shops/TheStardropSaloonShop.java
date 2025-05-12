package model.Shops;

import model.alive.Human;
import model.items.ItemsInShops;
import model.map.Location;

import java.util.ArrayList;

public class TheStardropSaloonShop extends Shop {
    ArrayList<ItemsInShops> permanentStock;
    public TheStardropSaloonShop (Human owner, Location upperLeftLocation, Location lowerRightLocation) {
        super(owner,12,24, upperLeftLocation, lowerRightLocation);
        permanentStock = new ArrayList<>();
    }

    public ArrayList<ItemsInShops> getPermanentStock() {
        return permanentStock;
    }

    public void setPermanentStock(ArrayList<ItemsInShops> permanentStock) {
        this.permanentStock = permanentStock;
    }
}
