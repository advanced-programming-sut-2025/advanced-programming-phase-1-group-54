package io.github.stardewmini.client.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import io.github.stardewmini.controller.GameMenuController;
import io.github.stardewmini.controller.MainMenuController;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.SoundManager;

public class PreGameMenu implements Screen {
    private Stage stage;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Skin skin = GameAssetManager.getInstance().getSkin();

        Label titleLabel = new Label("Game Menu", skin, "Bold");
        TextButton newGameButton = new TextButton("New Game", skin);
        TextButton continueButton = new TextButton("Continue", skin);
        // TODO add game info ...
        Label resultLabel = new Label("", skin);
        TextButton backButton = new TextButton("Back", skin);

        newGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new CreateGameMenu());
            }
        });

        continueButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Result result = GameMenuController.loadGame();
                resultLabel.setText(result.message());

                if (result.success()) {
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
                }
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                MainMenuController.logout();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new MainMenu());
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(titleLabel).pad(100, 0, 100, 0);
        table.row().pad(10, 0 , 10 , 0);
        table.add(newGameButton).height(90).width(300);
        table.row().pad(10, 0 , 10 , 0);
        table.add(continueButton).height(90).width(300);
        table.row().pad(10, 0 , 10 , 0);
        table.add(resultLabel);
        table.row().pad(10, 0 , 10 , 0);
        table.add(backButton).height(90).width(300);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
}
