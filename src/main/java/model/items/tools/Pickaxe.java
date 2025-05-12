package model.items.tools;


import model.enums.Feature;
import model.enums.ToolLevel;
import model.items.Item;
import model.items.Material;
import model.map.Tile;

public class Pickaxe extends Tool {

    @Override
    public boolean use(BackPack backPack, Tile tile) {
        if (tile.getThingOnTile() == null) {
            if (tile.hasFeature(Feature.PLOWED)) {
                tile.removeFeature(Feature.PLOWED);
                return true;
            }
            return false;
        }

        if (tile.getThingOnTile() instanceof Item) {
            if (tile.getThingOnTile() instanceof Material rock) {
                switch (rock.getName()) {
                    case "Wood":
                        return false;
                    case "Stone", "Coal", "Copper Ore":
                        backPack.addItem(rock, 1);
                        break;
                    case "Iron Ore":
                        if (getToolLevel() == ToolLevel.NORMAL)
                            return false;
                        backPack.addItem(rock, 1);
                        break;
                    case "Iridium Ore":
                        if (getToolLevel() == ToolLevel.NORMAL ||
                                getToolLevel() == ToolLevel.COPPER ||
                                getToolLevel() == ToolLevel.IRON)
                            return false;
                        backPack.addItem(rock, 1);
                        break;
                    default:
                        if (getToolLevel() == ToolLevel.NORMAL ||
                                getToolLevel() == ToolLevel.COPPER)
                            return false;
                        backPack.addItem(rock, 1);
                        break;
                }
            }

            tile.setThingOnTile(null);
            return true;
        }

        return false;
    }
}
