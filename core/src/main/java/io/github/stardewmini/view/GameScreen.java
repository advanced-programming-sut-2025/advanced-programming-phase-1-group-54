package io.github.stardewmini.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.controller.game.CommonGameController;
import io.github.stardewmini.model.App;
import io.github.stardewmini.model.Game;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.SoundManager;
import io.github.stardewmini.model.lives.Animal;
import io.github.stardewmini.model.lives.NPC;
import io.github.stardewmini.model.map.Location;
import io.github.stardewmini.model.map.Shops.Shop;
import io.github.stardewmini.model.map.Tile;
import io.github.stardewmini.model.map.World;

public class GameScreen implements Screen, InputProcessor {
    private Stage stage;

    private OrthographicCamera camera;

    int width = 0;
    int height = 0;

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(this);
//        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.clear();
        CommonGameController.update(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f), camera);
        camera.update();
        Main.getBatch().setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        CommonGameController.draw(Main.getBatch(),stage,camera);
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        Main.getBatch().begin();
        Main.getBatch().draw(GameAssetManager.getInstance().getStar(), width, height);
        Main.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
//        stage.dispose();
    }

    @Override
    public boolean keyDown(int keyCode) {
        return false;
    }

    @Override
    public boolean keyUp(int keyCode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 cameraPosition = camera.position;
        System.out.println("X = " + (int)((cameraPosition.x + screenX - Gdx.graphics.getWidth()/2f)/Tile.getSize()) + " Y = " + (int)((cameraPosition.y - screenY + Gdx.graphics.getHeight()/2f)/Tile.getSize()));
        System.out.println("camX = " + cameraPosition.x + " camY = " + cameraPosition.y);
        Tile tile = App.getCurrentGame().getWorld().getTileAt(
            new Location((int)((cameraPosition.y - screenY + Gdx.graphics.getHeight()/2f)/Tile.getSize()),
                (int)((cameraPosition.x + screenX - Gdx.graphics.getWidth()/2f)/Tile.getSize())));
        width = (int) (cameraPosition.x + screenX - Gdx.graphics.getWidth()/2f);
        height = (int)( cameraPosition.y - screenY + Gdx.graphics.getHeight()/2f);

        if(tile.getThingOnTile() instanceof Shop){
            Main.getInstance().getScreen().dispose();
            Main.getInstance().setScreen(new shopMenu(GameAssetManager.getInstance().getSkin()));
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
