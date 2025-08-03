package io.github.stardewmini.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.model.GameAssetManager;

public class InventoryMenu implements Screen {

    private Stage stage;
    private  Window window;
    private final TextButton backButton;

    private final Table table1;
    private final Table table2;
    private final ScrollPane scrollPane;
    private final Table inventoryTable;
    private final TextButton inventoryButton;
    private final Label skillLabel;
    private final TextButton skillButton;
    private final Table socialTable;
    private final Label npcsFriendshipLabel;
    private final Label playersFriendshipLabel;
    private final TextButton socialButton;
    private final TextButton mapButton;


    public InventoryMenu(Skin skin) {
        this.window = new Window("inventory Menu",skin);
        this.backButton = new TextButton("Back", skin);
        this.inventoryTable = new Table(skin);
        this.scrollPane = new ScrollPane(this.inventoryTable,skin);
        this.table1 = new Table(skin);
        this.table2 = new Table(skin);
        this.inventoryButton = new TextButton("Inventory", skin);
        this.skillLabel = new Label("skill", skin);
        this.skillButton = new TextButton("Skills", skin);
        this.socialTable = new Table(skin);
        this.npcsFriendshipLabel = new Label("npc", skin);
        this.playersFriendshipLabel = new Label("player", skin);
        this.socialButton = new TextButton("Social", skin);
        this.mapButton = new TextButton("Map", skin);
    }

    @Override
    public void show() {

        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                window.remove();
            }
        });

        inventoryButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                table2.clear();
                table2.add(scrollPane).expand().pad(10);
            }
        });

        socialButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                table2.clear();
                table2.add(socialTable).expand().pad(10);
            }
        });

        skillButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                table2.clear();
                table2.add(skillLabel).expand().pad(10);
            }
        });

        mapButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        window.setSize( Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f);
        window.setPosition(Gdx.graphics.getWidth()/4f, Gdx.graphics.getHeight()/4f);
        window.getTitleTable().add(backButton);

        inventoryTable.add(new TextButton("salam",GameAssetManager.getGameAssetManager().getSkin())).pad(10);
        inventoryTable.add(new TextButton("salam",GameAssetManager.getGameAssetManager().getSkin())).pad(10).row();
        inventoryTable.add(new TextButton("salam",GameAssetManager.getGameAssetManager().getSkin())).pad(10);
        inventoryTable.add(new TextButton("salam",GameAssetManager.getGameAssetManager().getSkin())).pad(10).row();
        inventoryTable.add(new TextButton("salam",GameAssetManager.getGameAssetManager().getSkin())).pad(10);
        inventoryTable.add(new TextButton("salam",GameAssetManager.getGameAssetManager().getSkin())).pad(10);



        socialTable.add(playersFriendshipLabel).pad(10);
        socialTable.add(npcsFriendshipLabel).pad(10);


        table1.add(inventoryButton).pad(10);
        table1.add(skillButton).pad(10);
        table1.add(socialButton).pad(10);
        table1.add(mapButton).pad(10);

        table2.add(scrollPane).expand().pad(10);

        window.add(table1);
        window.row();
        window.add(table2).expand().pad(10);

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
