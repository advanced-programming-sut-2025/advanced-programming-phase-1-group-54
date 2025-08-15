package io.github.stardewmini.client.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.server.controllers.game.FishingController;
import io.github.stardewmini.common.model.GameAssetManager;

public class FishingMenu implements Screen {

    private static final int screenWidth = Gdx.graphics.getWidth();
    private static final int screenHeight = Gdx.graphics.getHeight();

    private Stage stage;
    private final ShapeRenderer shapeRenderer;
    private final ShapeRenderer bound;
    private final ProgressBar fishingBar;
    private final Label fishingType;
    private final Label fishName;
    private final Image fishImage;
    private final Image starImage;

    public FishingMenu(Skin skin,String fishingType ,String fishName) {
        this.shapeRenderer = new ShapeRenderer();
        this.bound = new ShapeRenderer();
        this.fishingBar = new ProgressBar(0,1_000,1,true, skin);
        this.fishingType = new Label( fishingType, skin);
        this.fishName = new Label(fishName, skin);
        this.fishImage = new Image(GameAssetManager.getInstance().getFishes("Salmon"));
        this.starImage = new Image(GameAssetManager.getInstance().getStar());
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.addActor(fishImage);
        stage.addActor(starImage);

        fishingType.setPosition(screenWidth/2f, screenHeight/2f + screenHeight/4f);
        fishName.setPosition(screenWidth/2f, screenHeight/2f + screenHeight/4f + screenHeight/30f);
        stage.addActor(fishingType);
        stage.addActor(fishName);

        fishingBar.setValue(fishingBar.getMaxValue()/2);
        fishingBar.setSize(20,screenHeight/4f);
        fishingBar.setPosition(screenWidth/2f + 100, screenHeight /2f - screenHeight/8f);
        stage.addActor(fishingBar);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);

//        FishingController.handle(shapeRenderer,bound,fishingBar, fishImage,starImage);

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
