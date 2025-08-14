package io.github.stardewmini.client.controllers.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.stardewmini.client.Main;
import io.github.stardewmini.client.view.CheatMenu;
import io.github.stardewmini.client.view.CookingMenu;
import io.github.stardewmini.client.view.CraftingMenu;
import io.github.stardewmini.client.view.InventoryMenu;
import io.github.stardewmini.common.model.GameAssetManager;

public class GameController {
    public static void draw(SpriteBatch batch, Stage stage, OrthographicCamera camera) {
        MapController.draw(batch, stage, camera);
        PlayerController.draw(batch);
        AnimalController.draw(batch);
    }

    public static void update(float delta, OrthographicCamera camera) {
        PlayerController.update(delta, camera);
        AnimalController.update(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Main.getInstance().setScreen(new InventoryMenu(GameAssetManager.getInstance().getSkin()));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            Main.getInstance().setScreen(new CookingMenu(GameAssetManager.getInstance().getSkin()));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            Main.getInstance().setScreen(new CraftingMenu(GameAssetManager.getInstance().getSkin()));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.V)) {
            Main.getInstance().setScreen(new CheatMenu(GameAssetManager.getInstance().getSkin()));
        }
        // TODO update game each frame.
    }

    public static void mouseClick(int screenX, int screenY, OrthographicCamera camera) {
        MapController.mouseClick(screenX, screenY, camera);
        PlayerController.mouseClick(screenX, screenY, camera);
    }
}
