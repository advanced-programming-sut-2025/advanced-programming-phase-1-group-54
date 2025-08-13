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
    private Label label;
    private float lastChange = 0;
    private OrthographicCamera camera;

    int width = 0;
    int height = 0;

    public GameScreen(Skin skin,String string) {
        this.label = new Label(string, skin);
    }

    public GameScreen(Skin skin) {
        this.label = new Label("salam olagh azizi halet chetore?", skin);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new ScreenViewport());

        label.setPosition(100, 100);
        stage.addActor(label);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        CommonGameController.update(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f), camera);
        camera.update();
        Main.getBatch().setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        CommonGameController.draw(Main.getBatch(),stage,camera);
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
//        Main.getBatch().begin();
//        Main.getBatch().draw(GameAssetManager.getInstance().getStar(), width, height);
//        Main.getBatch().end();

        lastChange += delta;
        if(lastChange >= 10) {
            label.setText("");
        }
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
        stage.dispose();
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
        CommonGameController.mouseClick(screenX, screenY, camera);
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

    public Label getLabel() {
        return label;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setLastChange(float lastChange) {
        this.lastChange = lastChange;
    }
}
