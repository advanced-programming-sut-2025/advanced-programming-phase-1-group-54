package io.github.stardewmini.client.controllers.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.stardewmini.common.model.App;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.items.tools.Tool;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.Location;
import io.github.stardewmini.common.model.map.Tile;
import io.github.stardewmini.common.model.map.World;

public class PlayerController {

    public static void upAnimation(Player player,float delta){

        Animation<TextureRegion> animation = GameAssetManager.getInstance().getPlayerWalkUp();
        player.getSprite().setRegion(animation.getKeyFrame(player.getAnimationTime()));
        if(animation.isAnimationFinished(player.getAnimationTime())){
            player.setAnimationTime(0);
        }
        else{
            player.setAnimationTime(player.getAnimationTime() + delta);
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public static void downAnimation(Player player,float delta){

        Animation<TextureRegion> animation = GameAssetManager.getInstance().getPlayerWalkDown();
        player.getSprite().setRegion(animation.getKeyFrame(player.getAnimationTime()));
        if(animation.isAnimationFinished(player.getAnimationTime())){
            player.setAnimationTime(0);
        }
        else{
            player.setAnimationTime(player.getAnimationTime() + delta);
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public static void rightAnimation(Player player,float delta){

        Animation<TextureRegion> animation = GameAssetManager.getInstance().getPlayerWalkRight();
        player.getSprite().setRegion(animation.getKeyFrame(player.getAnimationTime()));
        if(animation.isAnimationFinished(player.getAnimationTime())){
            player.setAnimationTime(0);
        }
        else{
            player.setAnimationTime(player.getAnimationTime() + delta);
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public static void leftAnimation(Player player,float delta){

        Animation<TextureRegion> animation = GameAssetManager.getInstance().getPlayerWalkLeft();
        player.getSprite().setRegion(animation.getKeyFrame(player.getAnimationTime()));
        if(animation.isAnimationFinished(player.getAnimationTime())){
            player.setAnimationTime(0);
        }
        else{
            player.setAnimationTime(player.getAnimationTime() + delta);
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public static void dieAnimation(Player player,float delta){
        Animation<TextureRegion> animation = GameAssetManager.getInstance().getPlayerDie();
        player.getSprite().setRegion(animation.getKeyFrame(player.getAnimationTime()));
        if(animation.isAnimationFinished(player.getAnimationTime())){
            player.setAnimationTime(0);
        }
        else{
            player.setAnimationTime(player.getAnimationTime() + delta);
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public static void update(float delta, OrthographicCamera camera) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        World world = App.getCurrentGame().getWorld();

        int dx = 0, dy = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dy++;
            upAnimation(player,delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dx--;
            leftAnimation(player,delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dy--;
            downAnimation(player,delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx++;
            rightAnimation(player,delta);
        }

        if(player.getEnergy() <= 0){
            dieAnimation(player,delta);
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
            Tool tool = player.getEquippedTool();
            batch.draw(GameAssetManager.getInstance().getTool(tool.getToolType().toString(),tool.getToolLevel().toString())
                ,player.getX() + 2 * Tile.getSize()/3f, player.getY() + Tile.getSize()/2f);
        }
    }
}
