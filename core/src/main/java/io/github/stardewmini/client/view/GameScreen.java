package io.github.stardewmini.client.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.controller.game.CommonGameController;
import io.github.stardewmini.controller.game.MapController;
import io.github.stardewmini.controller.game.NpcController;
import io.github.stardewmini.common.model.map.Location;
import io.github.stardewmini.common.model.map.Tile;

public class GameScreen implements Screen, InputProcessor {
    private Stage stage;
    private final Label messageLabel;
    private float lastChange = 0;
    private OrthographicCamera camera;
    private final Window[] windows;
    private final Label timeLabel;
    private Sprite thunderSprite = new Sprite();
    private float thunderTime;

    int width = 0;
    int height = 0;

    public GameScreen(Skin skin,String string) {
        this.messageLabel = new Label(string, skin);
        windows = new Window[5];
        for(int i = 0; i < 5; i++) {
            windows[i] = new Window("dialoge", skin);
            windows[i].setSize(Tile.getSize()*8,Tile.getSize()*3);
        }
        this.timeLabel = new Label("", skin);
        this.thunderTime = 10;
        this.thunderSprite = new Sprite();
        this.thunderSprite.setSize(Tile.getSize(),Tile.getSize());
    }

    public GameScreen(Skin skin) {
        this.messageLabel = new Label("salam olagh e azizi halet chetore?", skin);
        windows = new Window[5];
        for(int i = 0; i < 5; i++) {
            windows[i] = new Window("dialoge", skin);
            windows[i].setSize(Tile.getSize()*8,Tile.getSize()*3);
        }
        this.timeLabel = new Label("", skin);
        this.thunderTime = 10;
        this.thunderSprite = new Sprite();
        this.thunderSprite.setSize(Tile.getSize(),Tile.getSize());
    }

    @Override
    public void show() {
        NpcController.fixWindows(this.windows);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new ScreenViewport());

        timeLabel.setPosition(50, Gdx.graphics.getHeight() - 150);
        messageLabel.setPosition(100, 100);

        stage.addActor(timeLabel);
        stage.addActor(messageLabel);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        timeLabel.setText(CommonGameController.updateDateTime(Main.getBatch()));
        CommonGameController.update(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f), camera);

        camera.update();
        Main.getBatch().setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        CommonGameController.draw(Main.getBatch(),stage,camera,windows);
        MapController.updateThunder(thunderSprite,thunderTime,Main.getBatch());
        thunderTime += delta;
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
//        Main.getBatch().begin();
//        Main.getBatch().draw(GameAssetManager.getInstance().getStar(), width, height);
//        Main.getBatch().end();

        lastChange += delta;
        if(lastChange >= 10) {
            messageLabel.setText("");
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
        CommonGameController.mouseClick(screenX, screenY, camera,windows);
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

    public Label getMessageLabel() {
        return messageLabel;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setLastChange(float lastChange) {
        this.lastChange = lastChange;
    }

    public void updateThunder(Location location){
        this.thunderSprite.setPosition(location.column() * Tile.getSize(),location.row() * Tile.getSize());
        this.thunderTime = 0;
    }
}
