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
import io.github.stardewmini.client.app.ClientApp;
import io.github.stardewmini.client.controllers.ClientConnectionController;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.server.controllers.ProfileMenuController;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.SoundManager;

public class ProfileMenu implements Screen {
    private Stage stage;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = GameAssetManager.getInstance().getSkin();

        Label titleLabel = new Label("Profile Menu", skin, "Bold");
        Message message = ClientConnectionController.createUserInfo();
        Label userInfoLabel = new Label(ClientApp.sendRequest(message).message(), skin);
        TextField newUsernameField = new TextField("", skin);
        newUsernameField.setText("New Username");
        TextButton changeUsernameButton = new TextButton("Change Username", skin);

        TextField oldPasswordField = new TextField("", skin);
        oldPasswordField.setMessageText("Old Password");
        oldPasswordField.setPasswordMode(true);
        oldPasswordField.setPasswordCharacter('*');
        TextField newPasswordField = new TextField("", skin);
        newPasswordField.setMessageText("New Password");
        newPasswordField.setPasswordMode(true);
        newPasswordField.setPasswordCharacter('*');
        CheckBox showPasswordCheckBox = new CheckBox("Show Password", skin);
        showPasswordCheckBox.setChecked(false);
        showPasswordCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                oldPasswordField.setPasswordMode(!showPasswordCheckBox.isChecked());
                newPasswordField.setPasswordMode(!showPasswordCheckBox.isChecked());
            }
        });
        TextButton changePasswordButton = new TextButton("Change Password", skin);

        TextField newNicknameField = new TextField("", skin);
        newNicknameField.setText("New Nickname");
        TextButton changeNicknameButton = new TextButton("Change Nickname", skin);

        TextField newEmailField = new TextField("", skin);
        newEmailField.setText("New Email");
        TextButton changeEmailButton = new TextButton("Change Email", skin);

        Label resultLabel = new Label("", skin);
        TextButton backButton = new TextButton("Back", skin);

        changeUsernameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Message message = ClientConnectionController.createChangeUsername(newUsernameField.getText());
                Result result = ClientApp.sendRequest(message);
                resultLabel.setText(result.message());
                userInfoLabel.setText(ClientApp.sendRequest(message).message());
            }
        });

        changePasswordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Message message = ClientConnectionController.createChangePassword(
                    newPasswordField.getText(),
                    oldPasswordField.getText()
                );
                Result result = ClientApp.sendRequest(message);
                resultLabel.setText(result.message());
                userInfoLabel.setText(ClientApp.sendRequest(message).message());
            }
        });

        changeNicknameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Message message = ClientConnectionController.createChangeNickname(newNicknameField.getText());
                Result result = ClientApp.sendRequest(message);
                resultLabel.setText(result.message());
                userInfoLabel.setText(ClientApp.sendRequest(message).message());
            }
        });

        changeEmailButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Message message = ClientConnectionController.createChangeEmail(newEmailField.getText());
                Result result = ClientApp.sendRequest(message);
                resultLabel.setText(result.message());
                userInfoLabel.setText(ClientApp.sendRequest(message).message());
            }
        });


        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new MainMenu());
            }
        });


        Table root = new Table();
        root.setFillParent(true);
        root.center();

        root.add(titleLabel).colspan(4);
        root.row().pad(10, 0, 10, 0);
        root.add(userInfoLabel).colspan(4);
        root.row().pad(10, 0, 10, 0);
        root.add(newUsernameField).width(300).colspan(3).pad(10);
        root.add(changeUsernameButton).width(300).height(90);
        root.row().pad(10, 0, 10, 0);
        root.add(oldPasswordField).width(300).pad(10);
        root.add(newPasswordField).width(300).pad(10);
        root.add(showPasswordCheckBox).pad(10);
        root.add(changePasswordButton).width(300).height(90);
        root.row().pad(10, 0, 10, 0);
        root.add(newNicknameField).width(300).colspan(3).pad(10);
        root.add(changeNicknameButton).width(300).height(90);
        root.row().pad(10, 0, 10, 0);
        root.add(newEmailField).width(300).colspan(3).pad(10);
        root.add(changeEmailButton).width(300).height(90);
        root.row().pad(10, 0, 10, 0);
        root.add(backButton).width(300).height(90).colspan(4);

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
