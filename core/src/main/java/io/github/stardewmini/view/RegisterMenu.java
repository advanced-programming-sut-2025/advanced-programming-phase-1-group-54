package io.github.stardewmini.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.controller.RegisterMenuController;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.Result;
import io.github.stardewmini.model.SoundManager;
import io.github.stardewmini.model.enums.Gender;

public class RegisterMenu implements Screen {
    private Table firstPage;
    private Table secondPage;
    private Stage stage;

    private void createFirstPage(Skin skin) {
        TextField usernameField = new TextField("", skin);
        usernameField.setMessageText("Username");

        TextField passwordField = new TextField("", skin);
        passwordField.setMessageText("Password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        TextField confirmPasswordField = new TextField("", skin);
        confirmPasswordField.setMessageText("Confirm Password");
        confirmPasswordField.setPasswordMode(true);
        confirmPasswordField.setPasswordCharacter('*');

        CheckBox showPasswordCheckBox = new CheckBox("Show Password", skin);
        showPasswordCheckBox.setChecked(false);
        showPasswordCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                passwordField.setPasswordMode(!showPasswordCheckBox.isChecked());
                confirmPasswordField.setPasswordMode(!showPasswordCheckBox.isChecked());
            }
        });

        TextButton randomPasswordButton = new TextButton("Generate Random Password", skin);
        randomPasswordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                passwordField.setText(RegisterMenuController.getRandomPassword());
                showPasswordCheckBox.setChecked(true);
                passwordField.setPasswordMode(false);
                confirmPasswordField.setPasswordMode(false);
            }
        });


        TextField nicknameField = new TextField("", skin);
        nicknameField.setMessageText("Nickname");

        TextField emailField = new TextField("", skin);
        emailField.setMessageText("Email");

        SelectBox<String> genderSelectBox = new SelectBox<>(skin);
        genderSelectBox.setItems("Male", "Female");

        Label resultLabel = new Label("", skin);

        TextButton submitButton = new TextButton("Submit", skin);
        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Result result = RegisterMenuController.register(
                    usernameField.getText(),
                    passwordField.getText(),
                    confirmPasswordField.getText(),
                    nicknameField.getText(),
                    emailField.getText(),
                    Gender.getGender(genderSelectBox.getSelected())
                );

                resultLabel.setText(result.message());
                if (result.success()) {
                    // TODO goto next page;
                }
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
    }

    private void createSecondPage(Skin skin) {

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = GameAssetManager.getInstance().getSkin();

        Table root = new Table();
        root.setFillParent(true);
        root.center();

        stage.addActor(root);
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
