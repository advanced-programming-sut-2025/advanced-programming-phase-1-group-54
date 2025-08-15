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

import java.util.List;

public class LobbyScreen implements Screen {
    private Stage stage;

    private final LobbyInfo lobbyInfo;
    private List<String> lobbyMembers;
    private Table lobbyMembersTable;

    private void showMembers(Skin skin) {
        lobbyMembersTable.clearChildren();
        for (String member : lobbyMembers) {
            lobbyMembersTable.add(new Label(lobbyInfo.name() + " id:" + lobbyInfo.id(), skin));
            lobbyMembersTable.row().pad(1);
        }
    }

    public LobbyScreen(LobbyInfo lobbyInfo) {
        this.lobbyInfo = lobbyInfo;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = GameAssetManager.getInstance().getSkin();

        Label titleLabel = new Label("Lobby", skin, "Bold");
        Label subtitleLabel = new Label(lobbyInfo.name(), skin);
        lobbyMembersTable = new Table();
        lobbyMembersTable.left();
        ScrollPane scrollPane = new ScrollPane(lobbyMembersTable, skin);
        TextButton startGameButton = new TextButton("Start Game", skin);
        TextButton leaveButton = new TextButton("Leave", skin);


        leaveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Message message = ClientConnectionController.createLeaveLobby(lobbyInfo.id());
                Result result = ClientApp.sendRequest(message);
                if (result.success()) {
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(new PreGameMenu());
                }
            }
        });

        Table root = new Table();
        root.setFillParent(true);
        root.center();

        root.add(titleLabel);
        root.row().pad(10, 0, 10, 0);
        root.add(subtitleLabel);

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
