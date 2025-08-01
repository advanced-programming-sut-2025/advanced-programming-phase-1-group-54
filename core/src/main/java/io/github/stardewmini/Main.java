package io.github.stardewmini;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.items.plants.Crop;
import io.github.stardewmini.model.lives.Animal;
import io.github.stardewmini.view.AnimalMenu;
import io.github.stardewmini.view.FirstScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        setScreen(new FirstScreen());
//        setScreen(new AnimalMenu(GameAssetManager.getGameAssetManager().getSkin(),Animal.getAnimal("Hen")));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }
}
