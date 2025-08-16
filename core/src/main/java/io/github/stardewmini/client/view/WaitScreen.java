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
import io.github.stardewmini.client.app.App;
import io.github.stardewmini.client.app.ClientApp;
import io.github.stardewmini.client.controllers.ClientConnectionController;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.SoundManager;

public class WaitScreen implements Screen {
    private Stage stage;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = GameAssetManager.getInstance().getSkin();

        Label titleLabel = new Label("Stardew Valley", skin, "Bold");
        Label subtitleLabel = new Label("Waiting for Others ...", skin);
        TextButton refreshButton = new TextButton("Refresh list", skin);
        refreshButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();

                if (App.isNextScreenReady()) {
                    App.setNextScreenReady(false);
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(), ""));
                }
            }
        });
        Table root = new Table();
        root.setFillParent(true);
        root.center();

        root.add(titleLabel);
        root.row().pad(10, 0, 10, 0);
        root.add(subtitleLabel);
        root.row().pad(10, 0, 10, 0);
        root.add(refreshButton);

        stage.addActor(root);
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
