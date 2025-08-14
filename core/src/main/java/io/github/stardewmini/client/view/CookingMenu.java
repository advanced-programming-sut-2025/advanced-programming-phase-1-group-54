package io.github.stardewmini.client.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.server.controllers.game.FoodController;
import io.github.stardewmini.common.model.GameAssetManager;

public class CookingMenu implements Screen {

    private Stage stage;
    private final Window window;
    private final TextButton backButton;

    public CookingMenu(Skin skin) {
        this.window = new Window("Cooking Menu", skin);
        this.backButton = new TextButton("Back", skin);
    }

    @Override
    public void show() {

        FoodController.showFoodRecipes(window);

        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin()));
            }
        });

        window.setSize( Gdx.graphics.getWidth() /2f, Gdx.graphics.getHeight()/2f);
        window.setPosition(Gdx.graphics.getWidth()/4f, Gdx.graphics.getHeight()/4f);

        window.getTitleTable().add(backButton);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.addActor(window);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);


        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        Main.getBatch().begin();
        Main.getBatch().end();
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
}
