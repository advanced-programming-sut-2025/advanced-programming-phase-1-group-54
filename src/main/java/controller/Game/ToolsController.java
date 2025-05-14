package controller.Game;

import model.App;
import model.Game;
import model.Result;
import model.alive.Player;
import model.enums.Direction;
import model.enums.ToolType;
import model.items.Item;
import model.items.tools.*;
import model.map.Farm;
import model.map.Location;
import model.map.Tile;
import model.map.World;

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
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Farm farm = game.getWorld().getFarm(player);

        // partner farm ??

        Location location = player.getCurrentLocation().getLocationAt(direction).delta(farm.getLocation());
        Tile tile = farm.getTileAt(location);

        if (tile == null) {
            return new Result(false, "this tile doesn't exist in your farm");
        }

        Tool equippedTool = player.getEquippedTool();
        if (equippedTool == null) {
            return new Result(false, "you don't have any tool equipped");
        }

        boolean successfulUse = equippedTool.checkSuccess(tile);
        int energyNeeded = equippedTool.getEnergyNeededPerUse();

        if (!successfulUse && (equippedTool instanceof Pickaxe || equippedTool instanceof Axe)) {
            energyNeeded--;
        }

        boolean enoughEnergy = player.checkEnergy(energyNeeded, equippedTool.getSkillType());

        if (!enoughEnergy)
            player.setEnergy(0);
        else {
            player.decreaseEnergy(energyNeeded, equippedTool.getSkillType());
            equippedTool.use(player.getBackpack(), tile);
        }

        if (enoughEnergy) {
            if (!successfulUse)
                return new Result(true, "tool was used, and you failed.");

            return new Result(true, "tool was used, and you succeeded.");
        }

        return new Result(true, "you did not have enough energy, you passed out!");
    }

    public static Result howMuchWater() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        WateringCan wateringCan = (WateringCan) player.getTool(ToolType.WATERING_CAN);

        return new Result(true, "water: " + wateringCan.getCurrentWater());
    }
}
