package model.items.tools;


import model.enums.Feature;
import model.enums.SkillType;
import model.enums.ToolLevel;
import model.items.Item;
import model.items.Material;
import model.map.Tile;

public class Pickaxe extends Tool {

    @Override
    public boolean checkSuccess(Tile tile) {
        if (tile.getThingOnTile() instanceof Item) {
            if (tile.getThingOnTile() instanceof Material rock) {
                switch (rock.getName()) {
                    case "Wood", "Stone", "Coal", "Copper Ore":
                        break;
                    case "Iron Ore":
                        if (getToolLevel() == ToolLevel.NORMAL)
                            return false;
                        break;
                    case "Iridium Ore":
                        if (getToolLevel() == ToolLevel.NORMAL ||
                                getToolLevel() == ToolLevel.COPPER ||
                                getToolLevel() == ToolLevel.IRON)
                            return false;
                        break;
                    default:
                        if (getToolLevel() == ToolLevel.NORMAL ||
                                getToolLevel() == ToolLevel.COPPER)
                            return false;
                        break;
                }
            }

            return true;
        }

        return true;
    }

    @Override
    public void use(BackPack backPack, Tile tile) {
        if (!checkSuccess(tile)) {
            return;
        }

        if (tile.getThingOnTile() instanceof Item) {
            if (tile.getThingOnTile() instanceof Material rock && !rock.getName().equals("Wood")) {
                backPack.addItem(rock, 1);
            }
            tile.setThingOnTile(null);
        }

        tile.removeFeature(Feature.PLOWED);
    }

    @Override
    public SkillType getSkillType() {
        return SkillType.MINING;
    }
}
