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
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.LobbyInfo;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.SoundManager;


public class HostLobbyScreen implements Screen {
    private Stage stage;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = GameAssetManager.getInstance().getSkin();

        Label titleLabel = new Label("Host Lobby", skin, "Bold");
        TextField nameField = new TextField("", skin);
        nameField.setMessageText("Lobby Name");
        TextField passwordField = new TextField("", skin);
        passwordField.setMessageText("Lobby password");
        passwordField.setDisabled(true);
        CheckBox privateCheckBox = new CheckBox("Private", skin);
        privateCheckBox.setChecked(false);
        privateCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                passwordField.setDisabled(!privateCheckBox.isChecked());
                if (!privateCheckBox.isChecked())
                    passwordField.setText("");
            }
        });

        CheckBox invisibleCheckBox = new CheckBox("Invisible", skin);
        privateCheckBox.setChecked(false);

        TextButton hostButton = new TextButton("Host", skin);
        hostButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Message message = ClientConnectionController.createHostLobby(
                    nameField.getText(), passwordField.getText(), invisibleCheckBox.isChecked()
                );

                Message response = ClientApp.sendMessageAndGetResponse(message);
                if (response.getBooleanFromBody("success")) {
                    LobbyInfo lobbyInfo = new LobbyInfo(
                        response.getFromBody("name"),
                        response.getIntFromBody("id"),
                        response.getBooleanFromBody("isPrivate"));
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(new LobbyScreen(lobbyInfo));
                }
            }
        });

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new PreGameMenu());
            }
        });

        Table root = new Table();
        root.setFillParent(true);
        root.center();

        root.add(titleLabel).colspan(2);
        root.row().pad(10, 0, 10, 0);
        root.add(nameField).width(500).colspan(2);
        root.row().pad(10, 0, 10, 0);
        root.add(passwordField).width(500).pad(20);
        root.add(privateCheckBox);
        root.row().pad(10, 0, 10, 0);
        root.add(invisibleCheckBox).colspan(2);
        root.row().pad(10, 0, 10, 0);
        root.add(hostButton).width(300).height(90).colspan(2);
        root.row().pad(10, 0, 10, 0);
        root.add(backButton).width(300).height(90).colspan(2);

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

