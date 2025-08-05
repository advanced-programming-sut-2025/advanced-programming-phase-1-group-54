package io.github.stardewmini.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.stardewmini.model.enums.Season;
import io.github.stardewmini.model.items.AnimalProduce;
import io.github.stardewmini.model.items.Fish;
import io.github.stardewmini.model.items.Material;
import io.github.stardewmini.model.items.plants.Crop;
import io.github.stardewmini.model.items.plants.Fruit;
import io.github.stardewmini.model.items.plants.Seed;
import io.github.stardewmini.model.items.plants.Tree;
import io.github.stardewmini.model.lives.NPC;

import java.util.HashMap;

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
        FileHandle file;
        for(String key : Fruit.getFruitsList()) {
//            System.out.println(key);
            file = Gdx.files.internal("Stardew_Valley_Images-main/Crops/" + fileName(key) + ".png");
            if(! file.exists()){
                file = Gdx.files.internal("Stardew_Valley_Images-main/Trees/" + fileName(key) + ".png");
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
//            System.out.println(key);
            file = Gdx.files.internal("Stardew_Valley_Images-main/Crops/" + fileName(key) + ".png");
            if(! file.exists()){
                file = Gdx.files.internal("Stardew_Valley_Images-main/Trees/" + fileName(key) + ".png");
            }
            seeds.put(key,new Texture(file));
        }
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
//            System.out.println(withOutCrop);
            for(int i = 1 ;i <= crop.getMaxStages() + 1;i++){
                textures.put((i - 1) + "",new Texture("Stardew_Valley_Images-main/Crops/" + fileName(withOutCrop)
                    + "_Stage_" + i + ".png"));
            }
            if(! crop.isOneTime()){
                textures.put((crop.getMaxStages() + 1) + "", new Texture("Stardew_Valley_Images-main/Crops/" +
                    fileName(withOutCrop) + "_Stage_" + (crop.getMaxStages() + 2) + ".png"));
            }
            crops.put(key,textures);
        }
    }

    public Texture getCrops(String cropName,String stateName) {
        return crops.get(cropName).get(stateName);
    }

    private final HashMap<String,HashMap<String,Texture>> trees = new HashMap<>();
    {
        for(String key : Tree.getTreesList()) {
            HashMap<String,Texture> textures = new HashMap<>();
            Tree tree = Tree.getTree(key);
            String withOutTree = key.substring(0,key.length() - 5);
//            System.out.println(withOutTree);
            for(int i = 1 ;i < tree.getMaxStages();i++){
                textures.put(i - 1 + "",new Texture("Stardew_Valley_Images-main/Trees/" +
                    withOutTree + "_Stage_"+ i + ".png"));
            }
            Texture texture = new Texture("Stardew_Valley_Images-main/Trees/" + withOutTree  +"_Stage_5.png");
            int totalWidth = texture.getWidth();
            int totalHeight = texture.getHeight();
            TextureRegion[][] season = TextureRegion.split(new
                Texture("Stardew_Valley_Images-main/Trees/" + withOutTree + "_Stage_5.png"),totalWidth/4,totalHeight);
            for(int i = 0 ; i < 4 ; i++) {
                textures.put(Season.values()[i].toString(), season[0][i].getTexture());
            }
            try{
                textures.put("fruit",new Texture("Stardew_Valley_Images-main/Trees/"+
                    withOutTree + "_Stage_5_Fruit.png"));
                textures.put("lightning",new Texture("Stardew_Valley_Images-main/Trees/" +
                    key.replace(" ","") + "Lightning.png"));
            }
            catch(Exception ignored){}
            trees.put(key,textures);
        }
    }

    public Texture getTrees(String treeName, String stateName) {
        return trees.get(treeName).get(stateName);
    }

    private final HashMap<String,Texture> materials = new HashMap<>();

    private final HashMap<String,Texture> materialStones = new HashMap<>();
    {
        for(String key : Material.getMineralList()){
//            System.out.println(key);
            materials.put(key,new Texture("Stardew_Valley_Images-main/Mineral/"+ fileName(key) +".png"));
            materialStones.put(key,new Texture("Stardew_Valley_Images-main/Node/"+ fileName(key) + "_Node.png"));
        }
    }

    public Texture getMaterials(String name) {
        return materials.get(name);
    }

    public Texture getMaterialStones(String name) {
        return materialStones.get(name);
    }

    private final HashMap<String,TextureRegion[][]> NPCsFrames = new HashMap<>();
    {
        TextureRegion[][] frames;
        Texture texture;

        texture = new Texture("Stardew_Valley_Images-main/NPCCharacters/Abigail0.png");
        frames = TextureRegion.split(texture,texture.getWidth()/4,
            texture.getHeight()/14);
        NPCsFrames.put("Abigail",frames);

        texture = new Texture("Stardew_Valley_Images-main/NPCCharacters/Harvey0.png");
        frames = TextureRegion.split(texture,texture.getWidth()/4,
            texture.getHeight()/14);
        NPCsFrames.put("Harvey",frames);

        texture = new Texture("Stardew_Valley_Images-main/NPCCharacters/Leah0.png");
        frames = TextureRegion.split(texture,texture.getWidth()/4,
            texture.getHeight()/14);
        NPCsFrames.put("Leah",frames);

        texture = new Texture("Stardew_Valley_Images-main/NPCCharacters/Robin0.png");
        frames = TextureRegion.split(texture,texture.getWidth()/4,
            texture.getHeight()/14);
        NPCsFrames.put("Robin",frames);

        texture = new Texture("Stardew_Valley_Images-main/NPCCharacters/Sebastian0.png");
        frames = TextureRegion.split(texture,texture.getWidth()/4,
            texture.getHeight()/14);
        NPCsFrames.put("Sebastian",frames);

    }

    public TextureRegion[][] getNPCsFrames(String name) {
        return NPCsFrames.get(name);
    }

    private final HashMap<String,Texture> NPCsFace  = new HashMap<>();
    {
        TextureRegion[][] frames;
        Texture texture;

        texture = new Texture("Stardew_Valley_Images-main/NPCCharacters/Abigail2.png");
        frames = TextureRegion.split(texture,texture.getWidth()/2,
            texture.getHeight()/5);
        NPCsFace.put("Abigail",frames[0][0].getTexture());

        texture = new Texture("Stardew_Valley_Images-main/NPCCharacters/Harvey2.png");
        frames = TextureRegion.split(texture,texture.getWidth()/2,
            texture.getHeight()/5);
        NPCsFace.put("Harvey",frames[0][0].getTexture());

        texture = new Texture("Stardew_Valley_Images-main/NPCCharacters/Leah2.png");
        frames = TextureRegion.split(texture,texture.getWidth()/2,
            texture.getHeight()/5);
        NPCsFace.put("Leah",frames[0][0].getTexture());

        texture = new Texture("Stardew_Valley_Images-main/NPCCharacters/Robin2.png");
        frames = TextureRegion.split(texture,texture.getWidth()/2,
            texture.getHeight()/5);
        NPCsFace.put("Robin",frames[0][0].getTexture());

        texture = new Texture("Stardew_Valley_Images-main/NPCCharacters/Sebastian2.png");
        frames = TextureRegion.split(texture,texture.getWidth()/2,
            texture.getHeight()/5);
        NPCsFace.put("Sebastian",frames[0][0].getTexture());
    }

    public Texture getNPCsFace(String name) {
        return NPCsFace.get(name);
    }

    private final HashMap<String,Texture> fishes  = new HashMap<>();
    {
        for(String key : Fish.getFishesList()){
            fishes.put(key,new Texture("Stardew_Valley_Images-main/Fish/" + fileName(key) + ".png"));
        }
    }

    public Texture getFishes(String name) {
        return fishes.get(name);
    }

    private final HashMap<String,Texture> animalProduces  = new HashMap<>();
    {
        for(String key : AnimalProduce.getAnimalProducesList()){
//            System.out.println(key);
            animalProduces.put(key,new Texture("Stardew_Valley_Images-main/Animal_product/" +
                fileName(key) + ".png"));
        }
    }

    public Texture getAnimalProduce(String name) {
        return animalProduces.get(name);
    }




    public Texture getItem(String name){
        Texture texture;

        texture = fruits.get(name);
        if(texture != null){
            return texture;
        }

        texture = seeds.get(name);
        if(texture != null){
            return texture;
        }

        texture = materials.get(name);
        if(texture != null){
            return texture;
        }

        texture = fishes.get(name);
        if(texture != null){
            return texture;
        }

        texture = animalProduces.get(name);
        if(texture != null){
            return texture;
        }

        return null;
    }
}
