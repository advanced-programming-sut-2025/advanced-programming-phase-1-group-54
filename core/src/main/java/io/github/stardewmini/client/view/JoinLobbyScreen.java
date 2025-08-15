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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JoinLobbyScreen implements Screen {
    private Stage stage;

    private Table lobbyListTable;
    private List<LobbyInfo> lobbyList = new ArrayList<>();
    private Label resultLabel;

    private void showLobbyList(Skin skin) {
        lobbyListTable.clearChildren();
        for (LobbyInfo lobbyInfo : lobbyList) {
            Label rowLabel = new Label(lobbyInfo.name() + " id:" + lobbyInfo.id(), skin);
            TextField passwordField = new TextField("", skin);
            passwordField.setMessageText("Password");
            TextButton joinButton = new TextButton("Join", skin);
            joinButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    SoundManager.getInstance().playClick();
                    Message message = ClientConnectionController.createJoinLobby(lobbyInfo.id(),
                        passwordField.getText());
                    Result result = ClientApp.sendRequest(message);

                    resultLabel.setText(result.message());
                    if (result.success()) {
                        Main.getInstance().getScreen().dispose();
                        Main.getInstance().setScreen(new LobbyScreen(lobbyInfo));
                    }
                }
            });

            lobbyListTable.add(rowLabel).pad(10);
            if (lobbyInfo.isPrivate()) lobbyListTable.add(passwordField).width(300).pad(10);
            lobbyListTable.add(joinButton).pad(10);
            lobbyListTable.row().pad(1);
        }
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = GameAssetManager.getInstance().getSkin();

        Label titleLabel = new Label("Join Lobby", skin, "Bold");
        lobbyListTable = new Table();
        lobbyListTable.center();

        TextField findLobbyField = new TextField("", skin);
        findLobbyField.setMessageText("Lobby ID");
        TextButton findLobbyButton = new TextButton("Find Lobby", skin);
        findLobbyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Message message = ClientConnectionController.createFindLobby(findLobbyField.getText());
                Message response = ClientApp.sendMessageAndGetResponse(message);
                lobbyList.clear();
                for (Map<String, Object> lobbyEntry : (List<Map<String, Object>>) response.getFromBody("lobbies")) {
                    lobbyList.add(new LobbyInfo(
                        (String) lobbyEntry.get("name"),
                        (int) ((double) ((Double) lobbyEntry.get("id"))),
                        (Boolean) lobbyEntry.get("isPrivate")
                    ));
                }
                showLobbyList(skin);
            }
        });

        ScrollPane scrollPane = new ScrollPane(lobbyListTable, skin);
        TextButton refreshButton = new TextButton("Refresh", skin);
        resultLabel = new Label("", skin);

        refreshButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Message message = ClientConnectionController.createRefreshLobbyList();
                Message response = ClientApp.sendMessageAndGetResponse(message);
                lobbyList.clear();
                for (Map<String, Object> lobbyEntry : (List<Map<String, Object>>) response.getFromBody("lobbies")) {
                    lobbyList.add(new LobbyInfo(
                        (String) lobbyEntry.get("name"),
                        (int) ((double) ((Double) lobbyEntry.get("id"))),
                        (Boolean) lobbyEntry.get("isPrivate")
                    ));
                }
                showLobbyList(skin);
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
        root.add(scrollPane).width(600).height(500).colspan(2);
        root.row().pad(10, 0, 10, 0);
        root.add(refreshButton).width(300).height(90);
        root.row().pad(10, 0, 10, 0);
        root.add(findLobbyField).pad(10);
        root.add(findLobbyButton).width(300).height(90);
        root.row().pad(10, 0, 10, 0);
        root.add(resultLabel).colspan(2);
        root.row().pad(10, 0, 10, 0);
        root.add(backButton).width(300).height(90);

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
