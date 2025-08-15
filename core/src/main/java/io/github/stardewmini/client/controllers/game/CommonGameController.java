package io.github.stardewmini.client.controllers.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import io.github.stardewmini.Main;
import io.github.stardewmini.client.app.App;
import io.github.stardewmini.client.view.*;
import io.github.stardewmini.common.model.DateTime;
import io.github.stardewmini.common.model.GameAssetManager;

public class CommonGameController {
    public static void draw(SpriteBatch batch, Stage stage, OrthographicCamera camera, Window[] windows) {
        MapController.draw(batch,stage,camera);
        PlayerController.draw(batch);
        AnimalController.draw(batch);
        NpcController.draw(batch,windows);
    }

    public static void update(float delta, OrthographicCamera camera) {
        PlayerController.update(delta, camera);
        AnimalController.update(delta);
        NpcController.update(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Main.getInstance().setScreen(new InventoryMenu(GameAssetManager.getInstance().getSkin()));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.B)){
            Main.getInstance().setScreen(new CookingMenu(GameAssetManager.getInstance().getSkin()));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.C)){
            Main.getInstance().setScreen(new CraftingMenu(GameAssetManager.getInstance().getSkin()));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.V)){
            Main.getInstance().setScreen(new CheatMenu(GameAssetManager.getInstance().getSkin()));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.X)){
            Main.getInstance().setScreen(new PlantingMenu(GameAssetManager.getInstance().getSkin()));
        }
        // TODO update game each frame.
    }

    public static void mouseClick(int screenX, int screenY, OrthographicCamera camera,Window[] windows) {
        MapController.mouseClick(screenX, screenY, camera, windows);
        ToolsController.mouseClick(screenX, screenY, camera);
    }

    public static String updateDateTime(SpriteBatch batch){
        StringBuilder output = new StringBuilder();
        DateTime dateTime = App.getCurrentGame().getDateTime();
        output.append("Hour : ").append(dateTime.getHour()).append("\nday : ").append(dateTime.getDay()).
            append("\nyear : ").append(dateTime.getYear()).append("\nweekDay : ").append(dateTime.getWeekDay()).
            append("\nseason : ").append(dateTime.getSeason()).append("\nweather : ").
            append(App.getCurrentGame().getCurrentWeather()).append("\nEnergy : ").
            append(App.getCurrentPlayer().getEnergy());

        if(dateTime.getHour() >= 18){
            batch.setColor(0.7f, 0.7f, 0.7f, 1);
        }
        else{
            batch.setColor(1, 1, 1, 1);
        }

        return output.toString();
    }
}
