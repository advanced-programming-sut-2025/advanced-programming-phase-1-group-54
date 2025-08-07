package io.github.stardewmini;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.stardewmini.controller.game.FishingController;
import io.github.stardewmini.model.FishingGame;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.view.*;

import java.util.Scanner;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main instance;
    private static SpriteBatch batch;

    @Override
    public void create() {
        instance = this;
        batch = new SpriteBatch();
//        setScreen(new FirstScreen());
//        setScreen(new AnimalMenu(GameAssetManager.getInstance().getSkin(),Animal.getAnimal("Hen")));
//        setScreen(new NPCMenu(new NPC("sasa","ahh"),GameAssetManager.getInstance().getSkin()));
//        setScreen(new InventoryMenu(GameAssetManager.getInstance().getSkin()));
//        FishingGame game = new FishingGame(null,0);
//        FishingController.setGame(game);
        setScreen(new FishingMenu(GameAssetManager.getInstance().getSkin() , "gsdg","fish"));
//        setScreen(new InventoryMenu(GameAssetManager.getInstance().getSkin()));
//        setScreen(new StartMenu());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static Main getInstance() {
        return instance;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }
}
