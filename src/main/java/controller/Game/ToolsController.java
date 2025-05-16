package controller.Game;

import model.App;
import model.Game;
import model.Result;
import model.lives.Player;
import model.enums.*;
import model.items.Item;
import model.items.Material;
import model.items.plants.Seed;
import model.items.plants.Tree;
import model.items.tools.*;
import model.map.*;

public class ToolsController {
    public static Result showInventory() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        BackPack backPack = player.getBackpack();

        StringBuilder message = new StringBuilder();
        for (Item item : backPack.getNumberOfItemInBackPack().keySet()) {
            message.append(item.getName())
                    .append(": ")
                    .append(backPack.getNumberOfItemInBackPack().get(item))
                    .append('\n');
        }

        return new Result(true, message.toString());
    }

    public static Result throwInTrash(String itemName, int number) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        BackPack backPack = player.getBackpack();
        TrashCan trashCan = player.getTrashCan();

        Item item = CommonGameController.findItem(itemName);
        if (item == null) {
            return new Result(false, "Item not found");
        }
        if (backPack.getNumberOfItemInBackPack().get(item) < number) {
            return new Result(false, String.format("You don't have %d of %s in backpack", number, itemName));
        }

        backPack.removeItem(item, number);
        int money = trashCan.getMoneyFromTrashCan(item, number);
        player.increaseMoney(money);

        if (money == 0)
            return new Result(true, String.format("%d of %s were thrown in trash", number, itemName));

        return new Result(true, String.format("%d of %s were thrown in trash, and you gained %d coins!", number, itemName, money));
    }

    public static Result equipTool(String toolName) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        ToolType toolType = ToolType.fromString(toolName);
        if (toolType == null)
            return new Result(false, "no tool with this name can be equipped");

        player.setEquippedTool(player.getTool(toolType));

        if (player.getEquippedTool() == null) {
            return new Result(false, "you don't have this equipment");
        }

        return new Result(false, "tool equipped!");
    }

    public static Result upgradeTool(String toolName) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        World world = game.getWorld();

        Location playerLocation = player.getCurrentLocation();
        // TODO if (world.getTileAt(playerLocation).getThingOnTile() instanceof )

        return null;
    }

    public static Result useTool(Direction direction) {
        if (direction == null)
            return new Result(false, "invalid direction");

        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Farm farm = game.getWorld().getFarmAt(player.getCurrentLocation());

        if (farm == null) {
            return new Result(false, "You must be in a farm to use your tools");
        }

        Location currentLocation = player.getCurrentLocation().delta(farm.getLocation());
        Location location = currentLocation.getLocationAt(direction).delta(farm.getLocation());
        Tile playerTile = farm.getTileAt(currentLocation);
        Tile tile = farm.getTileAt(location);

        if (playerTile == null) {
            return new Result(false, "you are not in your farm");
        }

        if (tile == null) {
            return new Result(false, "this tile doesn't exist in your farm");
        }

        if (playerTile.getThingOnTile() != null && playerTile.getThingOnTile() instanceof Building playerBuilding) {
            if (tile.getThingOnTile() == null || !tile.getThingOnTile().equals(playerBuilding)) {
                return new Result(false, "you should go out of building to use this tool on this tile");
            }
        } else if (tile.getThingOnTile() != null && tile.getThingOnTile() instanceof Building) {
            return new Result(false, "you should go in building to use this tool on this tile");
        }

        Tool equippedTool = player.getEquippedTool();
        if (equippedTool == null) {
            return new Result(false, "you don't have any tool equipped");
        }

        Result useToolDetail = useToolDetail(direction);
        int energyNeeded = equippedTool.getEnergyNeededPerUse();

        if (!useToolDetail.success() && (equippedTool.getToolType() == ToolType.AXE
                || equippedTool.getToolType() == ToolType.PICKAXE)) {
            energyNeeded--;
        }

        boolean enoughEnergy = player.checkEnergy(energyNeeded, equippedTool.getSkillType());

        String message = String.format("tool was used, and you %s\n%s",
                (useToolDetail.success() ? "succeeded" : "failed"), useToolDetail.message());

        if (!enoughEnergy) {
            Result passOut = CommonGameController.passOut();
            message += "\n" + passOut.message();
        }

        return new Result(useToolDetail.success(), message);
    }

    private static Result useToolDetail(Direction direction) {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        World world = game.getWorld();

        Tile tile = world.getTileAt(player.getCurrentLocation().getLocationAt(direction));

        BackPack backpack = player.getBackpack();
        Tool tool = player.getEquippedTool();

        switch (tool.getToolType()) {
            case HOE:
                return useHoe(tile);
            case WATERING_CAN:
                return useWateringCan(tool, tile, direction);
            case SCYTHE:
                return PlantsController.harvestPlant(direction);
            case AXE:
                return useAxe(player, backpack, tile);
            case PICKAXE:
                return usePickaxe(player, tool, backpack, tile);
            default:
                return new Result(true, "nothing happened");
        }
    }

    private static Result useHoe(Tile tile) {
        if (tile.getThingOnTile() instanceof GreenHouse) {
            tile = tile.getTop();
        }

        if (tile.getThingOnTile() != null) {
            if (tile.getThingOnTile() instanceof Building) {
                return new Result(false, "you can't plow a building");
            }

            return new Result(false, "there is something on this tile, you can't plow it");
        }

        if (tile.hasFeature(Feature.PLOWED))
            return new Result(true, "This tile has already been plowed");

        tile.addFeature(Feature.PLOWED);
        return new Result(true, "This tile has been plowed");
    }

    private static Result useWateringCan(Tool tool, Tile tile, Direction direction) {
        tile = tile.getTop();
        WateringCan wateringCan = (WateringCan) tool;

        if (tile.hasFeature(Feature.WATER))
            return new Result(true, "water increased by 1");

        if (wateringCan.getCurrentWater() > 0) {
            return PlantsController.giveWater(direction);
        }

        return new Result(false, "watering can is empty");
    }

    private static Result useAxe(Player player, BackPack backpack, Tile tile) {
        if (tile.getThingOnTile() instanceof Tree tree) {
            player.getSkill(SkillType.FORAGING).addXP(10);
            Result woodAdded = addToBackPack(backpack, Material.getMaterial("Wood"), 1);
            Result seedsAdded = addToBackPack(backpack, Seed.getSeed(tree.getSource()), 2);

            String message = "you cut " + tree.getName();
            if (woodAdded.success())
                message += "\n" + woodAdded.message();

            if (seedsAdded.success())
                message += "\n" + seedsAdded.message();

            return new Result(true, message);
        }
        if (tile.getThingOnTile() instanceof Material material) {
            if (material.getName().equals("Wood")) {
                player.getSkill(SkillType.FORAGING).addXP(10);
                Result woodAdded = addToBackPack(backpack, Material.getMaterial("Wood"), 1);


                String message = "you cut some " + material.getName();
                if (woodAdded.success())
                    message += "\n" + woodAdded.message();

                return new Result(true, message);
            }

            return new Result(false, "you can't cut this with an axe");
        }

        return new Result(true, "nothing happened");
    }

    private static Result usePickaxe(Player player, Tool tool, BackPack backpack, Tile tile) {
        if (tile.getThingOnTile() instanceof Item) {
            if (tile.getThingOnTile() instanceof Material rock) {
                switch (rock.getName()) {
                    case "Wood":
                        player.getSkill(SkillType.MINING).addXP(10);
                        return new Result(true, "item on tile destroyed");
                    case "Stone", "Coal", "Copper Ore":
                        break;
                    case "Iron Ore":
                        if (tool.getToolLevel() == ToolLevel.NORMAL) {
                            player.getSkill(SkillType.MINING).addXP(10);
                            return new Result(false, "Iron Ore can be mined with Copper or higher pickaxe");
                        }
                        break;
                    case "Iridium Ore":
                        if (tool.getToolLevel() == ToolLevel.NORMAL ||
                                tool.getToolLevel() == ToolLevel.COPPER ||
                                tool.getToolLevel() == ToolLevel.IRON) {
                            player.getSkill(SkillType.MINING).addXP(10);
                            return new Result(false, "Iridium Ore can be mined with Gold or higher pickaxe");
                        }
                        break;
                    default:
                        if (tool.getToolLevel() == ToolLevel.NORMAL ||
                                tool.getToolLevel() == ToolLevel.COPPER) {
                            player.getSkill(SkillType.MINING).addXP(10);
                            return new Result(false, rock.getName() + " can be mined with Iron or higher pickaxe");
                        }
                        break;
                }

               Result result = addToBackPack(player.getBackpack(), rock, 1);

                if (player.getSkill(SkillType.MINING).getLevel() >= 2) {
                    Material material = Material.getForagingMaterial();
                    Result materialAddedResult = addToBackPack(backpack, material, 1);
                    result = new Result(true, result.message() + "\n" + materialAddedResult.message());
                }
            }

            return new Result(true, "item on tile destroyed");
        }


        return new Result(true, "nothing happened");
    }

    public static Result howMuchWater() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        WateringCan wateringCan = (WateringCan) player.getTool(ToolType.WATERING_CAN);

        return new Result(true, "water: " + wateringCan.getCurrentWater());
    }

    static Result addToBackPack(BackPack backPack, Item item, int number) {
        if (backPack.addItem(item, number)) {
            return new Result(true,  number + " of " + item.getName() + " added to backpack");
        }

        return new Result(true, "you gained " + number + " of " + item.getName() + ", but your backpack is full");
    }
}
