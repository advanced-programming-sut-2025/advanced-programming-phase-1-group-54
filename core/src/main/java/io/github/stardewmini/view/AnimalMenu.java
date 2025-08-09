package io.github.stardewmini.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.controller.game.AnimalController;
import io.github.stardewmini.model.Game;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.lives.Animal;

public class AnimalMenu implements Screen {

    private Stage stage;
    private final Animal animal;
    private final Window window;
    private final Label menuTitle;
    private final TextButton feedButton;
    private final TextButton petButton;
    private final TextButton moveButton;
    private final TextButton getProduceButton;
    private final TextButton sellButton;
    private final TextButton backButton;

    public AnimalMenu(Skin skin,Animal animal) {
        this.animal = animal;
        this.window = new Window("Animal Menu", skin);
        this.menuTitle = new Label("carfdfa: sg\nsafdsgs: dgd\nafsghdgfd: sfd", skin);
        this.feedButton = new TextButton("Feed", skin);
        this.petButton = new TextButton("Pet", skin);
        this.moveButton = new TextButton("Move", skin);
        this.getProduceButton = new TextButton("Get Produce", skin);
        this.sellButton = new TextButton("Sell", skin);
        this.backButton = new TextButton("Back", skin);
    }

    @Override
    public void show() {

        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                window.remove();
            }
        });

        feedButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                AnimalController.feedAnimal(animal);
                System.out.println("check");
            }
        });

        petButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                AnimalController.pet(animal);
                System.out.println("check");
            }
        });

        moveButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                AnimalController.moveAnimal(animal,null);
            }
        });

        getProduceButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                AnimalController.getAnimalProduce(animal);
            }
        });

        sellButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                AnimalController.sellAnimal(animal);
            }
        });

        window.setSize( Gdx.graphics.getWidth() /2f, Gdx.graphics.getHeight()/2f);
        window.setPosition(Gdx.graphics.getWidth()/4f, Gdx.graphics.getHeight()/4f);

        window.add(feedButton).expand().pad(10);
        window.add(petButton).expand().pad(10);
        window.add(moveButton).expand().pad(10);
        window.row();
        window.add(getProduceButton).expand().pad(10);
        window.add(sellButton).expand().pad(10);
        window.add(menuTitle).expand().pad(10);

        window.getTitleTable().add(backButton).pad(10);

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
//        AnimalController.render(v);
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
