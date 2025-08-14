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
import io.github.stardewmini.server.controllers.game.NpcController;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.lives.NPC;

public class NPCMenu implements Screen {

    private Stage stage;
    private final NPC npc;
    private Window currentWindow;
    private final Window[] windows = new Window[4];
    private final TextButton backButton;
    private final TextButton giftButton;
    private final TextButton missionsButton;
    private final TextButton friendshipButton;

    private final Label giftLabel;
    private final TextField giftItem;
    private final TextButton giveGiftButton;

    private final TextField questNumber;
    private final TextButton completeQuestButton;
    private final Label quests;

    private final Label friendshipLabel;



    public NPCMenu(NPC npc, Skin skin) {
        this.npc = npc;
        for(int i = 0;i < 4;i++){
            windows[i] = new Window("NPC Menu", skin);
        }
        this.backButton = new TextButton("Back", skin);
        this.giftButton = new TextButton("Gift", skin);
        this.missionsButton = new TextButton("Missions", skin);
        this.friendshipButton = new TextButton("Friendship", skin);
        this.giftLabel = new Label("Enter gift item name", skin);
        this.giftItem = new TextField("", skin);
        this.giveGiftButton = new TextButton("Give Gift", skin);
        this.questNumber = new TextField("",skin);
        this.completeQuestButton = new TextButton("complete",skin);
        this.quests = new Label("Quest 1  Gold Ore : 2  Wood : 10",skin);
        this.friendshipLabel = new Label("friendship Level : 1  friendship XP : 146",skin);
    }

    @Override
    public void show() {

        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                currentWindow.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin()));
            }
        });

        giftButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //TODO bad inventory
                currentWindow.remove();
                currentWindow = windows[1];
                currentWindow.getTitleTable().add(backButton);
                stage.addActor(currentWindow);
            }
        });

        missionsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
//                NpcController.questList(npc);
                currentWindow.remove();
                currentWindow = windows[2];
                currentWindow.getTitleTable().add(backButton);
                stage.addActor(currentWindow);
            }
        });

        friendshipButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
//                NpcController.friendShipNpc(npc);
                currentWindow.remove();
                currentWindow = windows[3];
                currentWindow.getTitleTable().add(backButton);
                stage.addActor(currentWindow);
            }
        });

        giveGiftButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = NpcController.giftNpc(npc,giftItem.getMessageText());
                currentWindow.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        completeQuestButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = NpcController.questFinish(questNumber.getMessageText(),npc);
                currentWindow.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        for(int i = 0;i < 4;i++){
            windows[i].setSize( Gdx.graphics.getWidth() /2f, Gdx.graphics.getHeight()/2f);
            windows[i].setPosition(Gdx.graphics.getWidth()/4f, Gdx.graphics.getHeight()/4f);
        }


        windows[0].add(giftButton).expand().pad(10);
        windows[0].add(missionsButton).expand().pad(10);
        windows[0].add(friendshipButton).expand().pad(10);

        windows[1].add(giftLabel);
        windows[1].row();
        windows[1].add(giftItem);
        windows[1].row();
        windows[1].add(giveGiftButton);

        windows[2].add(quests).expand().pad(10);
        windows[2].row();
        windows[2].add(questNumber).expand().pad(10);
        windows[2].row();
        windows[2].add(completeQuestButton).expand().pad(10);

        windows[3].add(friendshipLabel).expand().pad(10);

        currentWindow = windows[0];
        currentWindow.getTitleTable().add(backButton);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.addActor(currentWindow);
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
