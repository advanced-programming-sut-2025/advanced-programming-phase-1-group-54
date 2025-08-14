package io.github.stardewmini.client.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.client.Main;
import io.github.stardewmini.server.controllers.game.FriendShipController;
import io.github.stardewmini.client.controllers.game.InventoryController;
import io.github.stardewmini.server.controllers.game.NpcController;
import io.github.stardewmini.server.controllers.game.ToolsController;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.enums.SkillType;

public class InventoryMenu implements Screen {

    private Stage stage;
    private  Window window;
    private final TextButton backButton;

    private final Table table1;
    private final Table table2;

    private final ScrollPane scrollPane;
    private final Table inventoryTable;
    private final TextButton inventoryButton;
//    private final Label trashcanLabel;
    private final TextField trashItem;
    private final Label trashItemLabel;
    private final TextField trashNumber;
    private final Label trashNumberLabel;
    private final Table trashTable;
    private final Window trashWindow;
    private final TextButton trashButton;

    private final Label [] skillsLabel;
    private final TextButton skillButton;

    private final Table socialTable;
    private final Label npcsFriendshipLabel;
    private final Label playersFriendshipLabel;
    private final TextButton socialButton;

    private final TextButton mapButton;

    private final TextButton settingButton;
    private final TextButton exitButton;
    private final TextButton kickButton;
    private final Window kickWindow;
    private final TextField kickField;
    private final Label kickLabel;
    private final Table kickTable;




    public InventoryMenu(Skin skin) {
        this.window = new Window("inventory Menu",skin);
        this.backButton = new TextButton("Back", skin);
        this.inventoryTable = InventoryController.showInventory();
//        this.inventoryTable = new Table(skin);
        this.scrollPane = new ScrollPane(this.inventoryTable,skin);
        this.table1 = new Table(skin);
        this.table2 = new Table(skin);
        this.inventoryButton = new TextButton("Inventory", skin);

//        this.trashcanLabel = new Label("Trashcan normal", skin);
        this.trashItem = new TextField("", skin);
        this.trashItemLabel = new Label("Enter Item name : ", skin);
        this.trashNumber = new TextField("", skin);
        this.trashNumberLabel = new Label("Enter Number : ", skin);
        this.trashTable = new Table(skin);
        this.trashWindow = new Window("Trashcan", skin);
        this.trashButton = new TextButton("Trash", skin);

        this.skillsLabel = new Label[4];
        for(int i = 0; i < skillsLabel.length; i++){
            skillsLabel[i] = new Label(InventoryController.showSkill(i), skin);
        }
        this.skillButton = new TextButton("Skills", skin);

        this.socialTable = new Table(skin);
        this.npcsFriendshipLabel = new Label(NpcController.getNPCsFriendship().message(), skin);
        this.playersFriendshipLabel = new Label(FriendShipController.showFriendships().message(), skin);
        this.socialButton = new TextButton("Social", skin);

        this.mapButton = new TextButton("Map", skin);

        this.settingButton = new TextButton("Setting", skin);
        this.exitButton = new TextButton("Exit", skin);
        this.kickButton = new TextButton("Kick", skin);
        this.kickWindow = new Window("Kick Menu", skin);
        this.kickField = new TextField("", skin);
        this.kickLabel = new Label("Enter Username", skin);
        this.kickTable = new Table(skin);
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

        inventoryButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                table2.clear();
                table2.add(scrollPane).expand().pad(10).row();
                table2.add(trashWindow).expand().pad(10);

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
                for (Label label : skillsLabel) {
                    table2.add(label).pad(10).row();
                }
            }
        });

        mapButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        settingButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                table2.clear();
                table2.add(exitButton).expand().pad(10).row();
                table2.add(kickWindow).expand().pad(10);
            }
        });

        trashButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = ToolsController.throwInTrash(trashItem.getText(), trashNumber.getText());
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // todo
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin()));
            }
        });

        kickButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // todo
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin()));
            }
        });

        for(int i = 0 ; i < skillsLabel.length ; i++){
            Label label = skillsLabel[i];
            SkillType skillType = SkillType.values()[i];
            int finalI = i;
            skillsLabel[i].addListener(new InputListener() {

                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                    label.setText("increase " + skillType.name() + "skill to consume less energy for " +
                        skillType.name());
                }

                public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                    label.setText(InventoryController.showSkill(finalI));
                }
            });
        }


        window.setSize( 3 * Gdx.graphics.getWidth()/4f, 3 * Gdx.graphics.getHeight()/4f);
        window.setPosition(Gdx.graphics.getWidth()/8f, Gdx.graphics.getHeight()/8f);
        window.getTitleTable().add(backButton);

        socialTable.add(playersFriendshipLabel).pad(10);
        socialTable.add(npcsFriendshipLabel).pad(10);

        trashTable.add(trashItemLabel).pad(10);
        trashTable.add(trashItem).pad(10).row();
        trashTable.add(trashNumberLabel).pad(10);
        trashTable.add(trashNumber).pad(10);

        trashWindow.add(trashTable);
        trashWindow.add(trashButton).pad(10);

        table1.add(inventoryButton).pad(10);
        table1.add(skillButton).pad(10);
        table1.add(socialButton).pad(10);
        table1.add(mapButton).pad(10);
        table1.add(settingButton).pad(10);

        kickWindow.add(kickLabel).pad(10);
        kickTable.add(kickLabel).pad(10);
        kickTable.add(kickField).pad(10);
        kickWindow.add(kickTable).row();
        kickWindow.add(kickButton).pad(10);

        table2.add(scrollPane).expand().pad(10).row();
        table2.add(trashWindow).expand().pad(10);

        window.add(table1);
        window.row();
        window.add(table2).expand().pad(10);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.addActor(window);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 0);
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
