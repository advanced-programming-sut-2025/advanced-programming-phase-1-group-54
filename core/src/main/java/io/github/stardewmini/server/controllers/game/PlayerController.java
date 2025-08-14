package io.github.stardewmini.server.controllers.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.stardewmini.common.model.App;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.Location;
import io.github.stardewmini.common.model.map.Tile;
import io.github.stardewmini.common.model.map.World;

public class PlayerController {
    public static void update(float delta, OrthographicCamera camera) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        World world = App.getCurrentGame().getWorld();

        int dx = 0, dy = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dy++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dx--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dy--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx++;
        }

        Tile currentTile = world.getTileAt(player.getCurrentLocation());
        Tile targetTile = world.getTileAt(player.getCurrentLocation().add(new Location(dy, dx)));
        if (targetTile != null && targetTile.isWalkable()) {
            if (player.tryMove(dx, dy)) {
                currentTile.getTop().setThingOnTile(null);
                targetTile.getTop().setThingOnTile(player);
            }
        }

        player.update(delta);

        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();
    }

    public static void draw(SpriteBatch batch) {
        for (Player player : App.getCurrentGame().getPlayers()) {
            player.getSprite().setPosition(player.getX(), player.getY());
            player.getSprite().draw(batch);
        }
    }
}
