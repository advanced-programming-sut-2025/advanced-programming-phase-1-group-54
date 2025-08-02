package io.github.stardewmini.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.model.lives.NPC;

public class NPCMenu implements Screen {

    private Stage stage;
    private final NPC npc;
    private final Window window;
    private final TextButton backButton;
    private final TextButton giftButton;
    private final TextButton missionsButton;
    private final TextButton friendshipButton;

    public NPCMenu(NPC npc, Skin skin) {
        this.npc = npc;
        this.window = new Window("NPC Menu", skin);
        this.backButton = new TextButton("Back", skin);
        this.giftButton = new TextButton("Gift", skin);
        this.missionsButton = new TextButton("Missions", skin);
        this.friendshipButton = new TextButton("Friendship", skin);
    }

    @Override
    public void show() {

        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                window.remove();
            }
        });

        giftButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        missionsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        friendshipButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

            }
        });


        window.setSize( Gdx.graphics.getWidth() /2f, Gdx.graphics.getHeight()/2f);
        window.setPosition(Gdx.graphics.getWidth()/4f, Gdx.graphics.getHeight()/4f);

        window.add(giftButton).expand().pad(10);
        window.add(missionsButton).expand().pad(10);
        window.add(friendshipButton).expand().pad(10);


        window.getTitleTable().add(backButton).pad(10);


        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.addActor(window);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
