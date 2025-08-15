package io.github.stardewmini.client.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.server.controllers.game.CheatController;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.map.Location;

public class CheatMenu implements Screen {

    private Stage stage;
    private final Window window;
    private final TextButton backButton;
    private final TextField addMoneyField;
    private final TextButton addMoneyButton;
    private final TextField addItemNameField;
    private final TextField addItemCountField;
    private final TextButton addItemButton;
    private final TextField addTimeField;
    private final TextButton addTimeButton;
    private final TextField addDayField;
    private final TextButton addDayButton;
    private final TextField addThunderField;
    private final TextButton addThunderButton;
    private final TextField setEnergyField;
    private final TextButton setEnergyButton;


    public CheatMenu(Skin skin) {
        this.window = new Window("Cheat Menu", skin);
        this.backButton = new TextButton("Back", skin);
        this.addMoneyField = new TextField("money", skin);
        this.addMoneyButton = new TextButton("Add money", skin);
        this.addItemNameField = new TextField("Item name", skin);
        this.addItemCountField = new TextField("count", skin);
        this.addItemButton = new TextButton("Add item", skin);
        this.addTimeField = new TextField("time", skin);
        this.addTimeButton = new TextButton("Add time", skin);
        this.addDayField = new TextField("day", skin);
        this.addDayButton = new TextButton("Add day", skin);
        this.addThunderField = new TextField("X,Y", skin);
        this.addThunderButton = new TextButton("Add tunder", skin);
        this.setEnergyField = new TextField("energy", skin);
        this.setEnergyButton = new TextButton("Set energy", skin);
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

/*          TODO add each command to (ClientGameController)
        addMoneyField.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y,int pointer, Actor actor) {
                addMoneyField.setText("");
            }
        });

        addMoneyButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = CheatController.addMoney(addMoneyField.getText());
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });


        addItemNameField.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y,int pointer, Actor actor) {
                addItemNameField.setText("");
            }
        });

        addItemCountField.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y,int pointer, Actor actor) {
                addItemCountField.setText("");
            }
        });

        addItemButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = CheatController.addItem(addItemNameField.getText(), addItemCountField.getText());
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        addTimeField.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y,int pointer, Actor actor) {
                addTimeField.setText("");
            }
        });

        addTimeButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = CheatController.advanceTime(addTimeField.getText());
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        addDayField.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y,int pointer, Actor actor) {
                addDayField.setText("");
            }
        });

        addDayButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = CheatController.advanceDate(addDayField.getText());
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        addThunderButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                int a,b;
                Location location;
                Result result;
                try{
                    String[] locParts = addThunderField.getText().split(",");
                    a = Integer.parseInt(locParts[0]);
                    b = Integer.parseInt(locParts[1]);
                    location = new Location(b,a);
                    result = CheatController.thunderStrike(addThunderField.getText());
                    window.remove();
                    GameScreen gameScreen = new GameScreen(GameAssetManager.getInstance().getSkin(),result.message());
                    gameScreen.updateThunder(location);
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(gameScreen);
                }catch (Exception e){
                    result = new Result(false,"enter location in X,Y format");
                    window.remove();
                    GameScreen gameScreen = new GameScreen(GameAssetManager.getInstance().getSkin(),result.message());
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(gameScreen);
                }

            }
        });

        setEnergyButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = CheatController.setEnergy(setEnergyField.getText());
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });
*/

        window.setSize( 3 * Gdx.graphics.getWidth()/4f, 3 * Gdx.graphics.getHeight()/4f);
        window.setPosition(Gdx.graphics.getWidth()/8f, Gdx.graphics.getHeight()/8f);

        window.getTitleTable().add(backButton);
        window.add(addMoneyField).expand().pad(10);
        window.add(addMoneyButton).expand().pad(10).row();
        window.add(addItemNameField).expand().pad(10);
        window.add(addItemCountField).expand().pad(10);
        window.add(addItemButton).expand().pad(10).row();
        window.add(addTimeField).expand().pad(10);
        window.add(addTimeButton).expand().pad(10).row();
        window.add(addDayField).expand().pad(10);
        window.add(addDayButton).expand().pad(10).row();
        window.add(addThunderField).expand().pad(10);
        window.add(addThunderButton).expand().pad(10).row();
        window.add(setEnergyField).expand().pad(10);
        window.add(setEnergyButton).expand().pad(10);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.addActor(window);
    }

    @Override
    public void render(float v) {
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
