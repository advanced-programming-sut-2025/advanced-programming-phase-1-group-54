package model.map;

import model.DailyUpdate;
import model.enums.Symbol;
import model.items.Material;

public class Quarry extends Building implements DailyUpdate {
    private static final int NUMBER_OF_FORAGING_MATERIALS = 3;

    public Quarry(Area area) {
        super(area.upperLeftLocation(), new Map(area.numberOfRows(), area.numberOfColumns()));
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.QUARRY;
    }

    public void foragingMaterial() {
        for (int i = 0; i < NUMBER_OF_FORAGING_MATERIALS; i++) {
            Tile tile = getTileAt(getRandomLocation());
            if (tile.getThingOnTile() == null) {
                tile.setThingOnTile(Material.getForagingMaterial());
            }
        }
    }

    @Override
    public void nextDayUpdate() {
        foragingMaterial();
    }
}
