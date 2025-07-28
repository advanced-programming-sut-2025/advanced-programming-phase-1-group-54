package io.github.stardewmini.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.stardewmini.model.items.plants.Fruit;
import io.github.stardewmini.model.items.plants.Seed;

import java.util.HashMap;
import java.util.logging.FileHandler;

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

    private final HashMap<String,Texture> fruits = new HashMap<>();
    {
        FileHandle file;
        for(String key : Fruit.getFruitsList()) {
            file = Gdx.files.internal("Stardew_Valley_Images-main/Crops/" + key + ".png");
            if(! file.exists()){
                file = Gdx.files.internal("Stardew_Valley_Images-main/Trees/" + key + ".png");
            }
            fruits.put(key,new Texture(file));
        }
    }

    public Texture getFruits(String name) {
        return fruits.get(name);
    }

    private final HashMap<String,Texture> seeds = new HashMap<>();
    {
        FileHandle file;
        for(String key : Seed.getSeedsList()) {
            file = Gdx.files.internal("Stardew_Valley_Images-main/Crops/" + key + ".png");
            if(! file.exists()){
                file = Gdx.files.internal("Stardew_Valley_Images-main/Trees/" + key + ".png");
            }
            seeds.put(key,new Texture(file));
        }
    }

    public Texture getSeeds(String name) {
        return seeds.get(name);
    }

    private final HashMap<String,Texture[]> crops = new HashMap();

    private final HashMap<String,Texture[]> trees = new HashMap();

    private final HashMap<String,Texture> minerals = new HashMap();
    {

    }

    private final HashMap<String,Texture> mineralStones = new HashMap();
    {

    }

}
