package io.github.stardewmini.view;

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
import io.github.stardewmini.Main;
import io.github.stardewmini.controller.game.CheatController;
import io.github.stardewmini.controller.game.ShopController;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.Result;

public class shopMenu implements Screen {

    private Stage stage;
    private final Window window;
    private final TextButton  backButton;
    private final Table scrollTable;
    private final ScrollPane scrollPane;
    private final Table table;
    private final TextButton buyButton;
    private final TextField number;
    private final TextField nameField;
    private final Label itemName;
    private final Label itemPrice;
    private final TextField location;



    public shopMenu(Skin skin) {
        this.window = new Window("Shop Menu", skin);
        this.backButton = new TextButton("Back", skin);
        this.scrollTable = new Table(skin);
        this.scrollPane = new ScrollPane(scrollTable);
        this.table = new Table(skin);
        this.buyButton = new TextButton("Buy", skin);
        this.number = new TextField("number", skin);
        this.nameField = new TextField("name", skin);
        this.location = new TextField("location : X,Y", skin);
        this.itemName = new Label("", skin);
        this.itemPrice = new Label("", skin);
    }

    @Override
    public void show() {

        scrollPane.setScrollingDisabled(true, false);
        ShopController.showItems(scrollTable,table,buyButton,number,itemName,itemPrice,nameField,location);

        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin()));
            }
        });

        number.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y,int pointer, Actor actor) {
                number.setText("");
            }
        });

        nameField.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y,int pointer, Actor actor) {
                nameField.setText("");
            }
        });

        location.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y,int pointer, Actor actor) {
                location.setText("");
            }
        });

        buyButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                String priceString = itemPrice.getText().toString();
                int price = Integer.parseInt(priceString.substring(0,priceString.length() - 5));
                Result result = ShopController.buy(itemName.getText().toString(),nameField.getText(),
                    price,location.getText());
                window.remove();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result.message()));
            }
        });

        table.add(scrollPane);

        window.setSize( Gdx.graphics.getWidth() /2f, Gdx.graphics.getHeight()/2f);
        window.setPosition(Gdx.graphics.getWidth()/4f, Gdx.graphics.getHeight()/4f);

        window.getTitleTable().add(backButton);
        window.add(table);

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
