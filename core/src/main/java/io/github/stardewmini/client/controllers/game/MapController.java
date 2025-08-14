package io.github.stardewmini.client.controllers.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.stardewmini.client.Main;
import io.github.stardewmini.client.view.AnimalMenu;
import io.github.stardewmini.client.view.NPCMenu;
import io.github.stardewmini.client.view.ShopMenu;
import io.github.stardewmini.common.model.Game;
import io.github.stardewmini.client.Renderers.GameAssetManager;
import io.github.stardewmini.common.model.lives.Animal;
import io.github.stardewmini.common.model.lives.NPC;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.Building;
import io.github.stardewmini.common.model.map.Location;
import io.github.stardewmini.common.model.map.Shops.Shop;
import io.github.stardewmini.common.model.map.Tile;
import io.github.stardewmini.common.model.map.World;
import io.github.stardewmini.server.app.GameApp;

public class MapController {
    public static void draw(SpriteBatch batch, Stage stage, OrthographicCamera camera) {
        Game game = GameApp.getCurrentGame();
        Player player = game.getCurrentPlayer();
        World world = game.getWorld();

        Building currentBuilding = null;
        if (world.getTileAt(player.getCurrentLocation()).getThingOnTile() instanceof Building building)
            currentBuilding = building;

        batch.draw(GameAssetManager.getInstance().getBackground(), 0, 0,
            Tile.getSize() * World.getNumberOfColumns(),
            Tile.getSize() * World.getNumberOfRows());

        for (int row = World.getNumberOfRows() - 1; row >= 0; row--) {
            for (int column = World.getNumberOfColumns() - 1; column >= 0; column--) {
                Location tileLocation = new Location(row, column);
                Tile tile = world.getTileAt(tileLocation);

                if (tile.getThingOnTile() != null && tile.getThingOnTile().equals(currentBuilding))
                    tile = tile.getTop();

                if (tile.getSprite().getTexture() != null)
                    tile.getSprite().draw(batch);

                if (tile.getThingOnTile() != null && !(tile.getThingOnTile() instanceof Player)) {
                    if (tile.getThingOnTile().getSprite() == null) {
                        System.out.println("BAD SPRITE " + tile.getThingOnTile());
                    } else if (tile.getThingOnTile().getSprite().getTexture() == null) {
                        System.out.println("BAD TEXTURE " + tile.getThingOnTile());
                    } else if (!(tile.getThingOnTile() instanceof Building building) || tileLocation.equals(building.getLocation())) {
                        tile.getThingOnTile().getSprite().setPosition(column * Tile.getSize(), row * Tile.getSize());
                        tile.getThingOnTile().getSprite().draw(batch);
                    }
                }
            }
        }
    }

    public static void mouseClick(int screenX, int screenY, OrthographicCamera camera) {
        Vector3 cameraPosition = camera.position;
        System.out.println("X = " + (int)((cameraPosition.x + screenX - Gdx.graphics.getWidth()/2f)/Tile.getSize()) + " Y = " + (int)((cameraPosition.y - screenY + Gdx.graphics.getHeight()/2f)/Tile.getSize()));
        System.out.println("camX = " + cameraPosition.x + " camY = " + cameraPosition.y);
        Tile tile = GameApp.getCurrentGame().getWorld().getTileAt(
            new Location((int)((cameraPosition.y - screenY + Gdx.graphics.getHeight()/2f)/Tile.getSize()),
                (int)((cameraPosition.x + screenX - Gdx.graphics.getWidth()/2f)/Tile.getSize())));

        if(tile != null){
            if(tile.getThingOnTile() instanceof Shop){
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new ShopMenu(GameAssetManager.getInstance().getSkin()));
            }
            else if(tile.getThingOnTile() instanceof NPC npc){
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new NPCMenu(npc,GameAssetManager.getInstance().getSkin()));
            }
            else if(tile.getTop().getThingOnTile() instanceof NPC npc){
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new NPCMenu(npc,GameAssetManager.getInstance().getSkin()));
            }
            else if(tile.getThingOnTile() instanceof Animal animal){
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new AnimalMenu(GameAssetManager.getInstance().getSkin(),animal));
            }
            else if(tile.getTop().getThingOnTile() instanceof Animal animal){
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new AnimalMenu(GameAssetManager.getInstance().getSkin(),animal));
            }
        }
    }
}
