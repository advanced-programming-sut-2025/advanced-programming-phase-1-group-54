package io.github.stardewmini.client.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.client.controllers.game.TalkController;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;

public class TalkMenu implements Screen {

    private Stage stage;
    private final Window window;
    private final TextButton backButton;
    private final Table table;
    private final Label usernamesLabel;
    private final TextButton talkButton;
    private final TextField usernameField;
    private final TextField messageField;
    private final TextButton sendButton;
    private Label talkingUsername;
    private Label talkingHistory;

    public TalkMenu(Skin skin) {
        this.window = new Window("Talk Menu", skin);
        this.backButton = new TextButton("Back", skin);
        this.table = new Table(skin);
        this.usernamesLabel = new Label(TalkController.gameUsernames().message(), skin);
        this.talkButton = new TextButton("Talk", skin);
        this.usernameField = new TextField("", skin);
        this.messageField = new com.badlogic.gdx.scenes.scene2d.ui.TextField("", skin);
        this.sendButton = new TextButton("Send", skin);
        this.talkingUsername = new Label("", skin);
        this.talkingHistory = new Label("", skin);
    }

    @Override
    public void show() {

        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin()));
            }
        });

        talkButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                talkingUsername.setText(usernameField.getText());
                Result result = TalkController.talkingHistory(talkingUsername.getText().toString());
                if(result.success()){
                    table.clear();
                    table.add(talkingUsername).expand().pad(10).row();
                    table.add(talkingHistory).expand().pad(10).row();
                    table.add(sendButton).expand().pad(10);
                }
                else{
                    window.remove();
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
                }
            }
        });

        sendButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                TalkController.talk(talkingUsername.getText().toString(),messageField.getText());
            }
        });

        window.getTitleTable().add(backButton);
        window.add(table);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.addActor(window);
    }

    @Override
    public void render(float v) {
        talkingHistory.setText(TalkController.talkingHistory(talkingUsername.getText().toString()).message());
        ScreenUtils.clear(0, 0, 0, 1);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        Main.getBatch().begin();
        Main.getBatch().end();
    }

    @Override
    public void resize(int i, int i1) {

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

    }
}
