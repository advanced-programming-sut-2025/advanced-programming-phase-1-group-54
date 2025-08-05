package io.github.stardewmini.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.controller.RegisterMenuController;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.Result;
import io.github.stardewmini.model.SoundManager;
import io.github.stardewmini.model.enums.Gender;

import java.util.ArrayList;

public class RegisterMenu implements Screen {
    private Table root;
    private Table firstPage;
    private Table secondPage;
    private Stage stage;

    private void createFirstPage(Skin skin) {
        Label titleLabel = new Label("Register", skin, "Bold");

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

        TextButton randomPasswordButton = new TextButton("Random Password", skin);
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
                    root.removeActor(firstPage);
                    root.add(secondPage).expand().fill().row();
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

        firstPage = new Table(skin);
        firstPage.setFillParent(true);
        firstPage.center();

        firstPage.add(titleLabel).colspan(2);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(usernameField).width(300).colspan(2);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(passwordField).width(300).pad(10);
        firstPage.add(confirmPasswordField).width(300);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(randomPasswordButton).height(90).width(300).pad(10);
        firstPage.add(showPasswordCheckBox);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(nicknameField).width(300).colspan(2);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(emailField).width(300).colspan(2);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(genderSelectBox).colspan(2);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(resultLabel).colspan(2);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(submitButton).height(90).width(300).colspan(2);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(backButton).height(90).width(300).colspan(2);
    }

    private void createSecondPage(Skin skin) {
        Label titleLabel = new Label("Security Question", skin, "Bold");

        ArrayList<Label> securityQuestionLabels = new ArrayList<>();
        int n = 1;
        for (String securityQuestion : RegisterMenuController.getSecurityQuestions()) {
            securityQuestionLabels.add(new Label(n + ". " + securityQuestion, skin));
            n++;
        }

        Integer[] itemNumbers = new Integer[n];
        for (int i = 0; i < n; i++) {
            itemNumbers[i] = i + 1;
        }

        SelectBox<Integer> securityQuestionSelectBox = new SelectBox<>(skin);
        securityQuestionSelectBox.setItems(itemNumbers);

        TextField answerField = new TextField("", skin);
        answerField.setMessageText("Answer");

        TextField confirmAnswerField = new TextField("", skin);
        confirmAnswerField.setMessageText("Confirm Answer");

        Label resultField = new Label("", skin);

        TextButton submitButton = new TextButton("Submit", skin);
        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Result result = RegisterMenuController.pickQuestion(
                    securityQuestionSelectBox.getSelected(),
                    answerField.getText(),
                    confirmAnswerField.getText()
                );

                resultField.setText(result.message());
                if (result.success()) {
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(new MainMenu());
                }
            }
        });

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                RegisterMenuController.resetUserBuilder();
                root.removeActor(secondPage);
                root.add(firstPage).expand().fill().row();
            }
        });

        secondPage = new Table(skin);
        secondPage.setFillParent(true);
        secondPage.center();

        secondPage.add(titleLabel).colspan(3);
        secondPage.row().pad(10, 0, 10, 0);
        for (Label securityQuestionLabel : securityQuestionLabels) {
            secondPage.add(securityQuestionLabel).colspan(3);
            secondPage.row().pad(10, 0, 10, 0);
        }
        secondPage.add(securityQuestionSelectBox);
        secondPage.add(answerField).width(300);
        secondPage.add(confirmAnswerField).width(300);
        secondPage.row().pad(10, 0, 10, 0);
        secondPage.add(submitButton).height(90).width(300).colspan(3);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = GameAssetManager.getInstance().getSkin();

        root = new Table();
        root.setFillParent(true);
        root.center();

        createFirstPage(skin);
        createSecondPage(skin);
        root.add(firstPage).expand().fill().row();

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
