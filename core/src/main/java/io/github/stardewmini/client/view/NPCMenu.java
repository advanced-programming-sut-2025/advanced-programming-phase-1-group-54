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
    private final Window currentWindow;
    private final Table table;
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
        this.currentWindow = new Window("NPC Menu", skin);
        this.table = new Table(skin);
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
                table.clear();
                table.add(giftLabel);
                table.row();
                table.add(giftItem);
                table.row();
                table.add(giveGiftButton);
            }
        });

        missionsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                quests.setText(NpcController.questList(npc).message());
                table.clear();
                table.add(quests).expand().pad(10);
                table.row();
                table.add(questNumber).expand().pad(10);
                table.row();
                table.add(completeQuestButton).expand().pad(10);
            }
        });

        friendshipButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                friendshipLabel.setText(NpcController.friendShipNpc(npc));
                table.clear();
                table.add(friendshipLabel).expand().pad(10);
            }
        });

        giveGiftButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = NpcController.giftNpc(npc,giftItem.getText());
                currentWindow.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        completeQuestButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = NpcController.questFinish(questNumber.getText(),npc);
                currentWindow.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        currentWindow.setSize( Gdx.graphics.getWidth() /2f, Gdx.graphics.getHeight()/2f);
        currentWindow.setPosition(Gdx.graphics.getWidth()/4f, Gdx.graphics.getHeight()/4f);

        table.add(giftButton).expand().pad(10);
        table.add(missionsButton).expand().pad(10);
        table.add(friendshipButton).expand().pad(10);


        currentWindow.add(table);
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
