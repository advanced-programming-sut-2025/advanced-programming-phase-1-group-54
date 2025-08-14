package io.github.stardewmini.server.controllers.game;

import io.github.stardewmini.client.controllers.game.CommonGameController;
import io.github.stardewmini.common.model.*;
import io.github.stardewmini.common.model.map.*;
import io.github.stardewmini.common.model.enums.Direction;
import io.github.stardewmini.common.model.enums.Symbol;
import io.github.stardewmini.common.model.items.Material;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.server.app.GameApp;

import java.util.ArrayList;

public class MapController {
    public static Result buildGreenhouse() {
        Game game = GameApp.getCurrentGame();
        Player player = game.getCurrentPlayer();

        Farm farm = game.getWorld().getFarm(player);

        GreenHouse greenhouse = farm.getGreenhouse();

        if (greenhouse.isBuilt())
            return new Result(true, "greenhouse is already built");

        boolean hasCoins = player.getMoney() > GreenHouse.getNeededMoney();
        if (!hasCoins) {
            return new Result(true, "you can not have enough money");
        }

        Material wood = Material.getMaterial("Wood");

        boolean hasWood = player.getBackpack().getNumberOfItemInBackPack().get(wood) >=
            GreenHouse.getNeededWood();

        if (!hasWood) {
            return new Result(true, "you don't have enough wood");
        }

        player.decreaseMoney(GreenHouse.getNeededMoney());
        player.getBackpack().removeItem(wood, GreenHouse.getNeededWood());
        greenhouse.setBuilt(true);

        return new Result(true, "greenhouse built successfully!");
    }

    public static Result checkForWalking(Location location) {
        Game game = GameApp.getCurrentGame();
        Player player = game.getCurrentPlayer();

        if (location.row() < 0 || location.column() < 0 || location.row() >= World.getNumberOfRows() || location.column() >= World.getNumberOfColumns())
            return new Result(false, "invalid location");

        int distance = game.getWorld().getDistance(player.getCurrentLocation(), location);

        boolean inOthersFarm = false;
        for (Player otherPlayer : game.getPlayers()) {
            if (otherPlayer.equals(player))
                continue;

            if (otherPlayer.equals(player.getPartner()))
                continue;

            Location locationInOtherFarm = location.delta(otherPlayer.getFarm().getLocation());
            if (otherPlayer.getFarm().getTileAt(locationInOtherFarm) != null) {
                inOthersFarm = true;
                break;
            }
        }

        if (distance == Integer.MAX_VALUE || inOthersFarm)
            return new Result(false, "Location unreachable from here!");

        return new Result(true, "Location reachable, energy needed is: " + distance / 20);
    }

    public static Result walk(Location location) { // NOT USING THIS FOR GRAPHICS.
        Game game = GameApp.getCurrentGame();
        World world = game.getWorld();
        Player player = game.getCurrentPlayer();

        Result result = checkForWalking(location);
        if (!result.success())
            return result;

        ArrayList<Direction> shortestPath = game.getWorld().getShortestPath(player.getCurrentLocation(), location);
        int energyNeeded = 0;

        Direction lastDirection = null;
        for (Direction direction : shortestPath) {
            energyNeeded += 1 + (lastDirection != null && direction == lastDirection ? 10 : 0);
            if (energyNeeded >= player.getEnergy() * 20) {
                Result passOut = CommonGameController.passOut();
                return new Result(true, String.format("Player has fallen at location (%d, %d)!\n",
                    player.getCurrentLocation().row(), player.getCurrentLocation().column()) + passOut.message());
            }

            Tile A = world.getTileAt(player.getCurrentLocation()).getTop();
            player.setCurrentLocation(player.getCurrentLocation().getLocationAt(direction));
            Tile B = world.getTileAt(player.getCurrentLocation()).getTop();
            A.setThingOnTile(null);
            B.setThingOnTile(player);

            lastDirection = direction;
        }

        player.decreaseEnergy(energyNeeded / 20);
        // Energy is integer, it is possible for user to move nearby without using any energy

        return new Result(true, "You walked successfully!");
    }

    public static Result printMap(Location location, int size) {
        Game game = GameApp.getCurrentGame();
        Player player = game.getCurrentPlayer();
        World world = game.getWorld();

        Building currentBuilding = null;
        if (world.getTileAt(player.getCurrentLocation()).getThingOnTile() instanceof Building building)
            currentBuilding = building;

        if (location.row() < 0 || location.column() < 0 ||
            location.row() + size - 1 >= World.getNumberOfRows() || location.column() + size - 1 >= World.getNumberOfColumns())
            return new Result(false, "invalid location and size");

        StringBuilder message = new StringBuilder();
        for (int dRow = 0; dRow < size; dRow++) {
            for (int dColumn = 0; dColumn < size; dColumn++) {
                Location tileLocation = new Location(location.row() + dRow, location.column() + dColumn);
                Tile tile = world.getTileAt(tileLocation);

                if (tile == null)
                    return new Result(false, "invalid location and size");

                if (tile.getThingOnTile() != null && tile.getThingOnTile().equals(currentBuilding))
                    tile = tile.getTop();

                message.append(tile.toString());
            }
            message.append("\n");
        }
        return new Result(true, message.toString());
    }

    static boolean isNear(Location location, Placeable placeable) {
        World world = GameApp.getCurrentGame().getWorld();

        if (world.getTileAt(location).getThingOnTile() instanceof Building building) {
            for (Direction direction : Direction.values()) {
                Location nearLocation = location.getLocationAt(direction);
                if (world.getTileAt(nearLocation).getThingOnTile() == null ||
                    !world.getTileAt(nearLocation).getThingOnTile().equals(building))
                    continue;

                if (world.getTileAt(nearLocation).getTop().getThingOnTile() != null &&
                    world.getTileAt(nearLocation).getTop().getThingOnTile().equals(placeable)) {
                    return true;
                }
            }

            return false;
        }

        for (Direction direction : Direction.values()) {
            Location nearLocation = location.getLocationAt(direction);
            if (world.getTileAt(nearLocation).getThingOnTile() != null &&
                world.getTileAt(nearLocation).getThingOnTile().equals(placeable)) {
                return true;
            }
        }
        return true;
    }

    public static Result helpReadMap() {
        StringBuilder message = new StringBuilder();
        for (Symbol symbol : Symbol.values()) {
            message.append(symbol.name().toLowerCase()).append(": ").append(symbol).append("\n");
        }
        return new Result(true, message.toString());
    }


}
