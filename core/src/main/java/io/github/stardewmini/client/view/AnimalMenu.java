package io.github.stardewmini.client.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.client.Main;
import io.github.stardewmini.server.controllers.game.AnimalController;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.lives.Animal;
import io.github.stardewmini.common.model.map.Location;

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
    private final TextField location;
    private final TextButton moveAnimalButton;
    private final Table table;

    public AnimalMenu(Skin skin,Animal animal) {
        this.animal = animal;
        this.window = new Window("Animal Menu", skin);
        this.menuTitle = new Label(AnimalController.showAnimal(animal).message(), skin);
        this.feedButton = new TextButton("Feed", skin);
        this.petButton = new TextButton("Pet", skin);
        this.moveButton = new TextButton("Move", skin);
        this.getProduceButton = new TextButton("Get Produce", skin);
        this.sellButton = new TextButton("Sell", skin);
        this.backButton = new TextButton("Back", skin);
        this.location = new TextField("X,Y", skin);
        this.moveAnimalButton = new TextButton("Move Animal", skin);
        this.table = new Table(skin);
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

        feedButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = AnimalController.feedAnimal(animal);
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        petButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = AnimalController.pet(animal);
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        moveButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                table.clear();
                table.add(location).expand().pad(10).row();
                table.add(moveAnimalButton);
            }
        });

        moveAnimalButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                int row = 0,column = 0;
                String loc = location.getText();
                try{
                    String[] locParts = loc.split(",");
                   row = Integer.parseInt(locParts[1]);
                   column = Integer.parseInt(locParts[0]);
                } catch (Exception e) {
                    window.remove();
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),
                        "enter location in X,Y format"));
                }
                Location location1 = new Location(row,column);
                Result result = AnimalController.moveAnimal(animal,location1);
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        getProduceButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = AnimalController.getAnimalProduce(animal);
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        sellButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Result result = AnimalController.sellAnimal(animal);
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        window.setSize( Gdx.graphics.getWidth() /2f, Gdx.graphics.getHeight()/2f);
        window.setPosition(Gdx.graphics.getWidth()/4f, Gdx.graphics.getHeight()/4f);

        table.add(feedButton).expand().pad(10);
        table.add(petButton).expand().pad(10);
        table.add(moveButton).expand().pad(10);
        table.row();
        table.add(getProduceButton).expand().pad(10);
        table.add(sellButton).expand().pad(10);
        table.add(menuTitle).expand().pad(10);

        window.add(table).expand().pad(10);
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
