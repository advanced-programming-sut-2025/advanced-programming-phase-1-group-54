package io.github.stardewmini.server.controllers.game;

import io.github.stardewmini.client.app.App;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.Location;
import io.github.stardewmini.common.model.map.Tile;
import io.github.stardewmini.common.model.map.World;

public class PlayerController {

    public static Result walk(String requester, String dyString, String dxString){

        int dx,dy;
        try {
            dx = Integer.parseInt(dxString);
            dy = Integer.parseInt(dyString);
        }
        catch (NumberFormatException e) {
            return new Result(false, "Invalid dx/dx");
        }
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        World world = App.getCurrentGame().getWorld();
        Tile currentTile = world.getTileAt(player.getCurrentLocation());
        Tile targetTile = world.getTileAt(player.getCurrentLocation().add(new Location(dy, dx)));
        if (targetTile != null && targetTile.isWalkable()) {
            if (player.tryMove(dx, dy)) {
                currentTile.getTop().setThingOnTile(null);
                targetTile.getTop().setThingOnTile(player);
            }
        }
        return new Result(true, "moved successfully");
    }
}
