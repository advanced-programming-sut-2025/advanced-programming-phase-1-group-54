package io.github.stardewmini.client.controllers.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.stardewmini.client.Renderers.GameAssetManager;
import io.github.stardewmini.common.model.lives.Animal;
import io.github.stardewmini.common.model.map.Location;
import io.github.stardewmini.common.model.map.Tile;
import io.github.stardewmini.server.app.GameApp;

public class AnimalController {

    public static void update(float delta){
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();

        for(Animal animal : GameApp.getCurrentGame().getCurrentPlayer().getAnimals().values()) {

            if (animal.getLocation() != null  && ( animal.getX() != animal.getLocation().column() * Tile.getSize()||
                animal.getY() != animal.getLocation().row() * Tile.getSize() )) {
                Location location = animal.getLocation();
                if(animal.getX() > location.column() * Tile.getSize()){
                    animal.setX(animal.getX() - Tile.getSize()/60);
                }
                else if(animal.getX() < location.column() * Tile.getSize()){
                    animal.setX(animal.getX() + Tile.getSize()/60);
                }
                else if(animal.getY() > location.row() * Tile.getSize()){
                    animal.setY(animal.getY() - Tile.getSize()/60);
                }
                else if(animal.getY() < location.row() * Tile.getSize()){
                    animal.setY(animal.getY() + Tile.getSize()/60);
                }
                walkAnimation(animal,delta);
            }
            else if (! gameAssetManager.getAnimalPet(animal.getAnimalName()).isAnimationFinished(animal.getPetTime())) {
                petAnimation(animal, delta);
            }
            else if (! gameAssetManager.getAnimalEat(animal.getAnimalName()).isAnimationFinished(animal.getEatTime())) {
                eatAnimation(animal, delta);
            }
            else if (animal.getProduce() != null) {
                animal.getSprite().setRegion(gameAssetManager.getProducedAnimal(animal.getAnimalName()));
            }
            else {
                animal.getSprite().setRegion(gameAssetManager.getAnimal(animal.getAnimalName()));
            }
        }
    }

    public static void draw(SpriteBatch batch){
        for(Animal animal : GameApp.getCurrentGame().getCurrentPlayer().getAnimals().values()) {
            animal.getSprite().setSize(Tile.getSize(), Tile.getSize());
            animal.getSprite().setPosition(animal.getX(),animal.getY());
            animal.getSprite().draw(batch);
        }
    }


    public static void eatAnimation(Animal animal,float delta){
        Animation<TextureRegion> animation = GameAssetManager.getInstance().getAnimalEat(animal.getAnimalName());
        animal.getSprite().setRegion(animation.getKeyFrame(animal.getEatTime()));
        animal.setEatTime(animal.getEatTime() + delta);
        animation.setPlayMode(Animation.PlayMode.REVERSED);
    }

    public static void petAnimation(Animal animal,float delta){
        Animation<TextureRegion> animation = GameAssetManager.getInstance().getAnimalPet(animal.getAnimalName());
        animal.getSprite().setRegion(animation.getKeyFrame(animal.getPetTime()));
        animal.setPetTime(animal.getPetTime() + delta);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    public static void walkAnimation(Animal animal,float delta){
        Animation<TextureRegion> animation = GameAssetManager.getInstance().getAnimalWalk(animal.getAnimalName());
        animal.getSprite().setRegion(animation.getKeyFrame(animal.getWalkTime()));
        if(animation.isAnimationFinished(animal.getWalkTime())){
            animal.setWalkTime(0);
        }
        else{
            animal.setWalkTime(animal.getWalkTime() + delta);
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }
}
