package io.github.stardewmini.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.controller.game.FishingController;

public class FishingMenu implements Screen {

    private static final int screenWidth = Gdx.graphics.getWidth();
    private static final int screenHeight = Gdx.graphics.getHeight();

    private Stage stage;
    private final ShapeRenderer shapeRenderer;
    private final ShapeRenderer bound;
    private final ProgressBar fishingBar;

    public FishingMenu(Skin skin) {
        this.shapeRenderer = new ShapeRenderer();
        this.bound = new ShapeRenderer();
        this.fishingBar = new ProgressBar(0,1_000,1,true, skin);
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        fishingBar.setSize(20,screenHeight/4);
        fishingBar.setPosition(screenWidth/2 + 100, screenHeight /2 - screenHeight/8);
        stage.addActor(fishingBar);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);

        FishingController.handle(shapeRenderer,bound,fishingBar);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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

    }

    public ProgressBar getFishingBar() {
        return fishingBar;
    }
}
