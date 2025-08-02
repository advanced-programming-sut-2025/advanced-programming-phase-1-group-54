package io.github.stardewmini;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.stardewmini.view.FirstScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main instance;
    private static SpriteBatch batch;

    @Override
    public void create() {
        instance = this;
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

    public static Main getInstance() {
        return instance;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }
}
