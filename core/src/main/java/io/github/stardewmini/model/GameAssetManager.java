package io.github.stardewmini.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;

    private GameAssetManager() {
    }

    public static GameAssetManager getGameAssetManager() {
        if(gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    private final Skin skin = new Skin(Gdx.files.internal("LibGdx-Skin-main/NzSkin.json"));

    public Skin getSkin() {
        return skin;
    }


}
