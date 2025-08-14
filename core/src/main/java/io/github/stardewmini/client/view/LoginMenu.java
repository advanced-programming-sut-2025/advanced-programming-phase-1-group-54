package io.github.stardewmini.client.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.client.Main;
import io.github.stardewmini.server.controllers.LoginMenuController;
import io.github.stardewmini.client.Renderers.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.SoundManager;

public class LoginMenu implements Screen {
    private Table root;
    private Stage stage;

    private void createMainPage(Skin skin) {
        Label titleLabel = new Label("Login", skin, "Bold");

        TextField usernameField = new TextField("", skin);
        usernameField.setMessageText("Username");

        TextField passwordField = new TextField("", skin);
        passwordField.setMessageText("Password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        CheckBox showPasswordCheckBox = new CheckBox("Show Password", skin);
        showPasswordCheckBox.setChecked(false);
        showPasswordCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                passwordField.setPasswordMode(!showPasswordCheckBox.isChecked());
            }
        });

        CheckBox stayLoggedInCheckBox = new CheckBox("Stay Logged in", skin);
        stayLoggedInCheckBox.setChecked(false);

        Label resultLabel = new Label("", skin);

        TextButton submitButton = new TextButton("Submit", skin);
        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Result result = LoginMenuController.login(
                    usernameField.getText(),
                    passwordField.getText(),
                    stayLoggedInCheckBox.isChecked()
                );

                resultLabel.setText(result.message());
                if (result.success()) {
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(new MainMenu());
                }
            }
        });

        TextButton forgetPasswordButton = new TextButton("Forget Password", skin);
        forgetPasswordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new ForgetPasswordMenu());
            }
        });

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new StartMenu());
            }
        });

        root = new Table(skin);
        root.setFillParent(true);
        root.center();

        root.add(titleLabel);
        root.row().pad(10, 0, 10, 0);
        root.add(usernameField).width(300);
        root.row().pad(10, 0, 10, 0);
        root.add(passwordField).width(300).pad(10);
        root.row().pad(10, 0, 10, 0);
        root.add(showPasswordCheckBox);
        root.row().pad(10, 0, 10, 0);
        root.add(stayLoggedInCheckBox);
        root.row().pad(10, 0, 10, 0);
        root.add(resultLabel);
        root.row().pad(10, 0, 10, 0);
        root.add(submitButton).height(90).width(300);
        root.row().pad(10, 0, 10, 0);
        root.add(forgetPasswordButton).height(90).width(300);
        root.row().pad(10, 0, 10, 0);
        root.add(backButton).height(90).width(300);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = GameAssetManager.getInstance().getSkin();

        root = new Table();
        root.setFillParent(true);
        root.center();

        createMainPage(skin);

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
