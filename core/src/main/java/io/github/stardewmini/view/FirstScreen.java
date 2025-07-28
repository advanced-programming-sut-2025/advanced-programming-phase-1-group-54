package io.github.stardewmini.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.model.GameAssetManager;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    @Override
    public void show() {
        GameAssetManager gameAssetManager = GameAssetManager.getGameAssetManager();
        TextureRegion[][] cow = TextureRegion.split(new
            Texture("Stardew_Valley_Images-main/sprites/Cow Brown.png"),32,32);
        Main.getBatch().begin();
        for(int i = 0; i < cow.length; i++){
            for(int j = 0; j < cow[i].length; j++){
                Main.getBatch().draw(cow[i][j],i*100 + 100,j*100 + 100,50,50);
            }
        }
        Main.getBatch().end();

        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
