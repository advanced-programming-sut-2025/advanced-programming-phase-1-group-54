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
import io.github.stardewmini.client.app.ClientApp;
import io.github.stardewmini.client.controllers.ClientGameController;
import io.github.stardewmini.client.controllers.game.PlantsController;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;

public class PlantingMenu implements Screen {
    private Stage stage;
    private final Window window;
    private final TextButton backButton;
    private final TextField plantingField;
    private final TextField plantingDirectionField;
    private final TextButton plantingButton;
    private final TextField craftInfoField;
    private final TextButton craftInfoButton;
    private final Label craftInfoLabel;

    public PlantingMenu(Skin skin) {
        this.window = new Window("Planting Menu", skin);
        this.backButton = new TextButton("Back", skin);
        this.plantingField = new TextField("",skin);
        this.plantingDirectionField = new TextField("direction", skin);
        this.plantingButton = new TextButton("Planting", skin);
        this.craftInfoField = new TextField("",skin);
        this.craftInfoButton = new TextButton("Craft Info", skin);
        this.craftInfoLabel = new Label("Craft Info", skin);
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

        plantingButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Message message = ClientGameController.createPlanting(plantingField.getText(), plantingDirectionField.getText());
                Result result = ClientApp.sendRequest(message);
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        craftInfoButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                craftInfoLabel.setText(PlantsController.showInfo(craftInfoField.getText()).message());
            }
        });

        window.setSize( Gdx.graphics.getWidth() /2f, Gdx.graphics.getHeight()/2f);
        window.setPosition(Gdx.graphics.getWidth()/4f, Gdx.graphics.getHeight()/4f);

        window.add(plantingDirectionField).expandX().pad(10);
        window.add(plantingField).expand().pad(10);
        window.row();
        window.add(plantingButton).expand().pad(10);
        window.row();
        window.add(craftInfoField).expand().pad(10);
        window.add(craftInfoButton).expand().pad(10);
        window.row();
        window.add(craftInfoLabel).expand().pad(10);

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
