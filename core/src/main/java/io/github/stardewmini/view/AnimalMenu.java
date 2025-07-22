package io.github.stardewmini.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.controller.game.AnimalController;

public class AnimalMenu implements Screen {

    private Stage stage;
    private final Label menuTitle;
    private final TextButton feedButton;
    private final TextButton petButton;
    private final TextButton moveButton;
    private final Table table;
    private final AnimalController controller;

    public AnimalMenu(AnimalController controller,Skin skin) {
        this.menuTitle = new Label("Animal menu", skin,"title");
        this.feedButton = new TextButton("Feed", skin);
        this.petButton = new TextButton("Pet", skin);
        this.moveButton = new TextButton("Move", skin);
        this.table = new Table();
        this.controller = controller;
    }

    @Override
    public void show() {

        feedButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                AnimalController.feedAnimal("name");
                System.out.println("check");
            }
        });

        petButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                AnimalController.pet("name");
                System.out.println("check");
            }
        });

        moveButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                AnimalController.moveAnimal("name",null);
            }
        });

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);

        table.center();
        table.add(feedButton);
        table.add(petButton);
        table.add(moveButton);


    }

    @Override
    public void render(float v) {

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
