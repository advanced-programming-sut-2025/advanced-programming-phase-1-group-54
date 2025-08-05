package io.github.stardewmini.view;

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
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.SoundManager;

public class StartMenu implements Screen {
    private Stage stage;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = GameAssetManager.getInstance().getSkin();

        Label titleLabel = new Label("Stardew Valley", skin);
        titleLabel.setFontScale(3f);
        TextButton signUpMenuButton = new TextButton("Sign Up", skin);
        TextButton loginMenuButton = new TextButton("Login", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        signUpMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new RegisterMenu());
            }
        });

        loginMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new LoginMenu());
            }
        });

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Gdx.app.exit();
            }
        });


        Table table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(titleLabel).pad(100, 0, 100, 0);
        table.row().pad(10, 0 , 10 , 0);
        table.add(signUpMenuButton).width(300);
        table.row().pad(10, 0 , 10 , 0);
        table.add(loginMenuButton).width(300);
        table.row().pad(10, 0 , 10 , 0);
        table.add(exitButton).width(300);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
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
