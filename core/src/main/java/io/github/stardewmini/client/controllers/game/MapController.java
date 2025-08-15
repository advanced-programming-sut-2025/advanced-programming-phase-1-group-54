package io.github.stardewmini.client.controllers.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import io.github.stardewmini.Main;
import io.github.stardewmini.client.app.App;
import io.github.stardewmini.client.view.AnimalMenu;
import io.github.stardewmini.client.view.NPCMenu;
import io.github.stardewmini.client.view.ShopMenu;
import io.github.stardewmini.common.model.Game;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.enums.Feature;
import io.github.stardewmini.common.model.lives.Animal;
import io.github.stardewmini.common.model.lives.NPC;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.Building;
import io.github.stardewmini.common.model.map.Location;
import io.github.stardewmini.common.model.map.Shops.Shop;
import io.github.stardewmini.common.model.map.Tile;
import io.github.stardewmini.common.model.map.World;
import io.github.stardewmini.server.controllers.game.FishingController;
import io.github.stardewmini.server.controllers.game.NpcController;

public class MapController {
    public static void draw(SpriteBatch batch, Stage stage, OrthographicCamera camera) {
        Game game = App.getCurrentGame();
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

    public static void mouseClick(int screenX, int screenY, OrthographicCamera camera, Window[] windows) {
        Vector3 cameraPosition = camera.position;
        Tile tile = App.getCurrentGame().getWorld().getTileAt(
            new Location((int)((cameraPosition.y - screenY + Gdx.graphics.getHeight()/2f)/Tile.getSize()),
                (int)((cameraPosition.x + screenX - Gdx.graphics.getWidth()/2f)/Tile.getSize())));

        Tile downTile = App.getCurrentGame().getWorld().getTileAt(tile.getLocation().add(new Location(-1,0)));
        if(downTile  == null){
            System.out.println("ridi");
        }

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
            else if(downTile != null && downTile.getThingOnTile() instanceof NPC npc){
                if(npc.getDialogTime() >= NPC.dialogTiming){
                    int i = App.getCurrentGame().getWorld().getNPCIndex(npc);
                    windows[i].getTitleLabel().setText(NpcController.meetsNpc(npc.getName()).message());
                    npc.setDialogTime(0);
                }
            }
            else if(downTile != null && downTile.getTop().getThingOnTile() instanceof NPC npc){
                if(npc.getDialogTime() >= NPC.dialogTiming) {
                    int i = App.getCurrentGame().getWorld().getNPCIndex(npc);
                    windows[i].getTitleLabel().setFontScale(0.5f);
                    windows[i].getTitleLabel().setText(NpcController.meetsNpc(npc.getName()).message());
                    npc.setDialogTime(0);
                }
            }
            else if(tile.hasFeature(Feature.WATER)){
                camera.position.set(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f, 0);
                FishingController.startFishing("Training");
            }
        }
    }

    public static void updateThunder(Sprite sprite, float time, SpriteBatch batch){
        Animation<TextureRegion> animation = GameAssetManager.getInstance().getThunderAnimation();
        sprite.setRegion(animation.getKeyFrame(time));
        animation.setPlayMode(Animation.PlayMode.NORMAL);
        if(! animation.isAnimationFinished(time)){
            sprite.draw(batch);
        }
    }

}
