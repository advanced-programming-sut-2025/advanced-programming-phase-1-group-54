package io.github.stardewmini.view;

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
import io.github.stardewmini.controller.game.CheatController;

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
    }

    @Override
    public void show() {

        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen());
            }
        });

        addMoneyField.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y,int pointer, Actor actor) {
                addMoneyField.setText("");
            }
        });

        addMoneyButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                CheatController.addMoney(addMoneyField.getText());
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen());
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
                CheatController.addItem(addItemNameField.getText(), addItemCountField.getText());
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen());
            }
        });

        addTimeField.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y,int pointer, Actor actor) {
                addTimeField.setText("");
            }
        });

        addTimeButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                CheatController.advanceDate(addTimeField.getText());
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen());
            }
        });

        addDayField.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y,int pointer, Actor actor) {
                addDayField.setText("");
            }
        });

        addDayButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                CheatController.advanceDate(addDayField.getText());
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen());
            }
        });

        window.setSize( Gdx.graphics.getWidth() /2f, Gdx.graphics.getHeight()/2f);
        window.setPosition(Gdx.graphics.getWidth()/4f, Gdx.graphics.getHeight()/4f);

        window.getTitleTable().add(backButton);
        window.add(addMoneyField).expand().pad(10);
        window.add(addMoneyButton).expand().pad(10).row();
        window.add(addItemNameField).expand().pad(10);
        window.add(addItemCountField).expand().pad(10);
        window.add(addItemButton).expand().pad(10).row();
        window.add(addTimeField).expand().pad(10);
        window.add(addTimeButton).expand().pad(10).row();
        window.add(addDayField).expand().pad(10);
        window.add(addDayButton).expand().pad(10);

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
