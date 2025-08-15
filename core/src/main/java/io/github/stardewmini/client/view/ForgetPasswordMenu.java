package io.github.stardewmini.client.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.server.controllers.LoginMenuController;
import io.github.stardewmini.server.controllers.RegisterMenuController;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.SoundManager;

public class ForgetPasswordMenu implements Screen {
    private Table root;
    private TextField usernameField;
    private Table firstPage;
    private Label securityQuestionLabel;
    private Table secondPage;
    private Table thirdPage;

    private Stage stage;

    private void createFirstPage(Skin skin) {
        Label enterUsernameLabel = new Label("Enter your username", skin);
        usernameField = new TextField("", skin);
        usernameField.setMessageText("Username");

        Label resultLabel = new Label("", skin);
        TextButton nextButton = new TextButton("Next", skin);
        TextButton backButton = new TextButton("Back", skin);

        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Result result = LoginMenuController.getSecurityQuestion(usernameField.getText());

                if (result.success()) {
                    resultLabel.setText("");
                    root.removeActor(firstPage);
                    securityQuestionLabel.setText(result.message());
                    root.add(secondPage).expand().fill().row();
                } else {
                    resultLabel.setText(result.message());
                }
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new LoginMenu());
            }
        });

        firstPage = new Table();
        firstPage.center();

        firstPage.add(enterUsernameLabel);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(usernameField).width(300);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(resultLabel);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(nextButton).width(300).height(90);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(backButton).width(300).height(90);
    }

    private void createSecondPage(Skin skin) {
        Label titleLabel = new Label("Answer your security question", skin);
        securityQuestionLabel = new Label("", skin);
        TextField answerField = new TextField("", skin);
        answerField.setMessageText("Answer");

        Label resultLabel = new Label("", skin);
        TextButton nextButton = new TextButton("Next", skin);
        TextButton backButton = new TextButton("Back", skin);

        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Result result = LoginMenuController.answer(usernameField.getText(), answerField.getText());

                if (result.success()) {
                    resultLabel.setText("");
                    root.removeActor(secondPage);
                    root.add(thirdPage).expand().fill().row();
                } else {
                    resultLabel.setText(result.message());
                }
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                answerField.setText("");
                root.removeActor(secondPage);
                root.add(firstPage).expand().fill().row();
            }
        });

        secondPage = new Table();
        secondPage.center();

        secondPage.add(titleLabel);
        secondPage.row().pad(10, 0, 10, 0);
        secondPage.add(securityQuestionLabel);
        secondPage.row().pad(10, 0, 10, 0);
        secondPage.add(answerField).width(300);
        secondPage.row().pad(10, 0, 10, 0);
        secondPage.add(nextButton).height(90).width(300);
        secondPage.row().pad(10, 0, 10, 0);
        secondPage.add(backButton).height(90).width(300);
    }

    private void createThirdPage(Skin skin) {
        Label titleLabel = new Label("Choose your new password", skin);
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

        TextButton randomPasswordButton = new TextButton("Random Password", skin);
        randomPasswordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                passwordField.setText(RegisterMenuController.getRandomPassword());
                confirmPasswordField.setText("");
                showPasswordCheckBox.setChecked(true);
                passwordField.setPasswordMode(false);
                confirmPasswordField.setPasswordMode(false);
            }
        });

        Label resultLabel = new Label("", skin);
        TextButton nextButton = new TextButton("Next", skin);
        TextButton backButton = new TextButton("Back", skin);

        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Result result = LoginMenuController.changePassword(
                    usernameField.getText(),
                    passwordField.getText(),
                    confirmPasswordField.getText()
                );

                if (result.success()) {
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(new LoginMenu());
                } else {
                    resultLabel.setText(result.message());
                }
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                passwordField.setText("");
                confirmPasswordField.setText("");
                root.removeActor(thirdPage);
                root.add(secondPage).expand().fill().row();
            }
        });


        thirdPage = new Table();
        thirdPage.center();

        thirdPage.add(titleLabel);
        thirdPage.row().pad(10, 0, 10, 0);
        thirdPage.add(passwordField).width(300);
        thirdPage.row().pad(10, 0, 10, 0);
        thirdPage.add(confirmPasswordField).width(300);
        thirdPage.row().pad(10, 0, 10, 0);
        thirdPage.add(nextButton).height(90).width(300);
        thirdPage.row().pad(10, 0, 10, 0);
        thirdPage.add(backButton).height(90).width(300);
    }


    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = GameAssetManager.getInstance().getSkin();

        createFirstPage(skin);
        createSecondPage(skin);
        createThirdPage(skin);

        root = new Table();
        root.setFillParent(true);
        root.center();

        root.add(new Label("Forgot Your Password?", skin, "Bold"));
        root.row();
        root.add(firstPage).expandX().fillX().row();

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
