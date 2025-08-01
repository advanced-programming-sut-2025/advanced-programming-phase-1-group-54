package io.github.stardewmini.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.stardewmini.model.enums.Season;
import io.github.stardewmini.model.items.plants.Crop;
import io.github.stardewmini.model.items.plants.Fruit;
import io.github.stardewmini.model.items.plants.Seed;
import io.github.stardewmini.model.items.plants.Tree;
import org.w3c.dom.Text;

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

    private String fileName(String input){
        return input.replace(" ", "_");
    }

    private final Skin skin = new Skin(Gdx.files.internal("LibGdx-Skin-main/NzSkin.json"));

    public Skin getSkin() {
        return skin;
    }

    private final HashMap<String,Texture> fruits = new HashMap<>();
    {
//        FileHandle file;
//        for(String key : Fruit.getFruitsList()) {
//            System.out.println(key);
//            file = Gdx.files.internal("Stardew_Valley_Images-main/Crops/" + fileName(key) + ".png");
//            if(! file.exists()){
//                file = Gdx.files.internal("Stardew_Valley_Images-main/Trees/" + fileName(key) + ".png");
//            }
//            fruits.put(key,new Texture(file));
//        }
    }

    public Texture getFruits(String name) {
        return fruits.get(name);
    }

    private final HashMap<String,Texture> seeds = new HashMap<>();
    {
//        FileHandle file;
//        for(String key : Seed.getSeedsList()) {
//            System.out.println(key);
//            file = Gdx.files.internal("Stardew_Valley_Images-main/Crops/" + fileName(key) + ".png");
//            if(! file.exists()){
//                file = Gdx.files.internal("Stardew_Valley_Images-main/Trees/" + fileName(key) + ".png");
//            }
//            seeds.put(key,new Texture(file));
//        }
    }

    public Texture getSeeds(String name) {
        return seeds.get(name);
    }

    private final HashMap<String,HashMap<String,Texture>> crops = new HashMap<>();
    {
        for(String key : Crop.getCropsList()) {
            HashMap<String,Texture> textures = new HashMap<>();
            Crop crop = Crop.getCrop(key);
            String withOutCrop = key.substring(0,key.length() - 5);
            System.out.println(withOutCrop);
            for(int i = 1 ;i <= crop.getMaxStages();i++){
                textures.put(i - 1 + "",new Texture("Stardew_Valley_Images-main/Crops/" + fileName(withOutCrop)
                    + "_Stage_" + i + ".png"));
            }
            crops.put(key,textures);
        }
    }

    private final HashMap<String,HashMap<String,Texture>> trees = new HashMap<>();
    {
//        for(String key : Tree.getTreesList()) {
//            HashMap<String,Texture> textures = new HashMap<>();
//            Tree tree = Tree.getTree(key);
//            String withOutTree = key.substring(0,key.length() - 5);
//            System.out.println(withOutTree);
//            for(int i = 1 ;i < tree.getMaxStages();i++){
//                textures.put(i - 1 + "",new Texture("Stardew_Valley_Images-main/Trees/" +
//                    withOutTree + "_Stage_"+ i + ".png"));
//            }
//            Texture texture = new Texture("Stardew_Valley_Images-main/Trees/" + withOutTree  +"_Stage_5.png");
//            int totalWidth = texture.getWidth();
//            int totalHeight = texture.getHeight();
//            TextureRegion[][] season = TextureRegion.split(new
//                Texture("Stardew_Valley_Images-main/Trees/" + withOutTree + "_Stage_5.png"),totalWidth/4,totalHeight);
//            for(int i = 0 ; i < 4 ; i++) {
//                textures.put(Season.values()[i].toString(), season[0][i].getTexture());
//            }
//            try{
//                textures.put("fruit",new Texture("Stardew_Valley_Images-main/Trees/"+
//                    withOutTree + "_Stage_5_Fruit.png"));
//                textures.put("lightning",new Texture("Stardew_Valley_Images-main/Trees/" +
//                    key.replace(" ","") + "Lightning.png"));
//            }
//            catch(Exception ignored){}
//            trees.put(key,textures);
//        }
    }

    public Texture getTrees(String treeName, String stateName) {
        return trees.get(treeName).get(stateName);
    }

    private final HashMap<String,Texture> minerals = new HashMap<>();
    {

    }

    private final HashMap<String,Texture> mineralStones = new HashMap<>();
    {

    }

}
