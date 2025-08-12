package io.github.stardewmini;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.stardewmini.controller.game.FishingController;
import io.github.stardewmini.model.FishingGame;
import io.github.stardewmini.model.App;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.items.recipes.Recipe;
import io.github.stardewmini.model.lives.Animal;
import io.github.stardewmini.view.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main instance;
    private static SpriteBatch batch;

    @Override
    public void create() {
        instance = this;
        batch = new SpriteBatch();
//        setScreen(new FirstScreen());
//        setScreen(new AnimalMenu(GameAssetManager.getInstance().getSkin(), Animal.getAnimal("Hen")));
//        setScreen(new NPCMenu(new NPC("sasa","ahh"),GameAssetManager.getInstance().getSkin()));
//        setScreen(new InventoryMenu(GameAssetManager.getInstance().getSkin()));
//        FishingGame game = new FishingGame(null,0);
//        FishingController.setGame(game);
//        setScreen(new FishingMenu(GameAssetManager.getInstance().getSkin() , "gsdg","fish"));
//        setScreen(new CraftingMenu(GameAssetManager.getInstance().getSkin()));
//        setScreen(new CookingMenu(GameAssetManager.getInstance(Skin()));
//        setScreen(new shopMenu(GameAssetManager.getInstance().getSkin()));
//        setScreen(new CheatMenu(GameAssetManager.getInstance().getSkin()));


        if (App.getLoggedInUser() != null) {
            setScreen(new MainMenu());
        }
        else {
            setScreen(new StartMenu());
        }
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
