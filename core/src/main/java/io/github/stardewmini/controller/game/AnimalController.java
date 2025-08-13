package io.github.stardewmini.controller.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.stardewmini.Main;
import io.github.stardewmini.model.App;
import io.github.stardewmini.model.Game;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.Result;
import io.github.stardewmini.model.enums.SkillType;
import io.github.stardewmini.model.enums.ToolType;
import io.github.stardewmini.model.items.Item;
import io.github.stardewmini.model.lives.Animal;
import io.github.stardewmini.model.lives.Player;
import io.github.stardewmini.model.map.AnimalHouse;
import io.github.stardewmini.model.map.Farm;
import io.github.stardewmini.model.map.Location;
import io.github.stardewmini.model.map.Tile;
import io.github.stardewmini.view.AnimalMenu;

public class AnimalController {

    public static Result pet(Animal animal) {
        Player player = App.getCurrentGame().getCurrentPlayer();
//        Animal animal = player.getAnimals().get(animalName);
//        if(animal == null) {
//            return new Result(-1, "You don't have any animal named " + animalName);
//        }
        if(! MapController.isNear(player.getCurrentLocation(),animal)){
            return new Result(-1, animal + " is not near you");
        }
        animal.increaseFriendshipLevel(15);
        animal.setCaressed(true);
        animal.setPetTime(0);
        return new Result(1,animal + " slightly likes you more!");
    }

    public static Result showAnimals() {
        Player player = App.getCurrentGame().getCurrentPlayer();
        StringBuilder output = new StringBuilder();
        for(Animal animal : player.getAnimals().values()) {
            output.append(animal.getAnimalName()).append(" ").append(animal.getName()).append("\n").
                append("friendship level: ").append(animal.getFriendshipLevel()).append("\n").
                append("caressed: ").append(animal.isCaressed()).append("\n").
                append("fed : ").append(animal.isFed()).append("\n").
                append("--------------------\n");
        }
        output.deleteCharAt(output.length()-1);
        return new Result(1,output.toString());
    }

    public static Result showAnimal(Animal animal) {
        StringBuilder output = new StringBuilder();
        output.append(animal.getAnimalName()).append(" ").append(animal.getName()).append("\n").
            append("friendship level: ").append(animal.getFriendshipLevel()).append("\n").
            append("caressed: ").append(animal.isCaressed()).append("\n").
            append("fed : ").append(animal.isFed()).append("\n").
            append("--------------------");
        return new Result(true,output.toString());
    }

    public static Result moveAnimal(Animal animal, Location location) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Farm farm = App.getCurrentGame().getWorld().getFarm(player);
        Location locationInFarm = location.delta(farm.getLocation());
        Tile tile = farm.getTileAt(locationInFarm);

        if(tile == null) {
            return new Result(-1, "location is not in your farm");
        }

        if(animal.getLocation() != null && animal.getLocation().distance(location) > 5){
            return new Result(false,"location is too far");
        }

        if(tile.getThingOnTile() == null){
            deleteAnimalFromFarm(animal);
            tile.setThingOnTile(animal);
            animal.setLocation(locationInFarm);
            animal.setGoneOut(true);
        }
        else if(tile.getThingOnTile() instanceof AnimalHouse animalHouse){
            if(animalHouse.getSize() > animalHouse.getNumberOfAnimals() && tile.getTop().getThingOnTile() == null){
                deleteAnimalFromFarm(animal);
                animalHouse.increaseNumberOfAnimals(1);
                animal.setLocation(locationInFarm);
                tile.getTop().setThingOnTile(animal);
            }
            else{
                return new Result(false, "Sorry, but there is no room for " + animal);
            }
        }
        else{
            return new Result(false, "Sorry, but there is no space on the tile");
        }

        return new Result(1,animal + " was moved successfully");
    }

    private static void deleteAnimalFromFarm(Animal animal) {
        if(animal.getLocation() != null ) {
            Tile pastTile = App.getCurrentGame().getCurrentPlayer().getFarm().getTileAt(animal.getLocation());
            if(pastTile.getThingOnTile() instanceof AnimalHouse pastAnimalHouse){
                pastAnimalHouse.decreaseNumberOfAnimals(1);
                pastTile = pastTile.getTop();
                if(! animal.isGoneOut()){
                    animal.setEatTime(0);
                }
            }
            pastTile.setThingOnTile(null);
        }
    }

    public static Result feedAnimal(Animal animal) {

        Player player = App.getCurrentGame().getCurrentPlayer();
//        Animal animal = player.getAnimals().get(animalName);
//        if(animal == null) {
//            return new Result(-1, "Animal " + animalName + " not found");
//        }

        Item item = CommonGameController.findItem("Hay");
        if(! player.getBackpack().removeItem(item,1)){
            return new Result(-1, "You don't have any Hay in backpack");
        }

        animal.setFed(true);
        animal.setEatTime(0);
        return new Result(1,animal + " was fed successfully");
    }

    public static Result showProducedAnimals() {
        Player player = App.getCurrentGame().getCurrentPlayer();
        StringBuilder output = new StringBuilder();
        for(Animal animal : player.getAnimals().values()) {
            if(animal.getProduce() != null){
                output.append(animal).append("\n").
                    append(animal.getProduce().getName()).append("\n").
                    append("-------------------");
            }
        }
        return new Result(1,output.toString());
    }

    public static Result getAnimalProduce(Animal animal) {
        Player player = App.getCurrentGame().getCurrentPlayer();
//        Animal animal = player.getAnimals().get(animalName);
//        if(animal == null) {
//            return new Result(-1, "Animal " + animalName + " not found");
//        }

        if(animal.getProduce() == null) {
            return new Result(-1, animal + " doesn't have any produce");
        }

        boolean enoughEnergy = true;
        if(animal.getName().equals("Cow") || animal.getName().equals("Goat")){
            if(! player.getEquippedTool().getToolType().equals(ToolType.MILK_PAIL)){
                return new Result(-1, "You don't have milk pail in your hand");
            }
            enoughEnergy = player.checkEnergy(player.getEquippedTool().getEnergyNeededPerUse(),null);
            player.decreaseEnergy(player.getEquippedTool().getEnergyNeededPerUse(),null);
        }
        else if(animal.getName().equals("Sheep")){
            if(! player.getEquippedTool().getToolType().equals(ToolType.SHEAR)){
                return new Result(-1, "You don't have milk pail in your hand");
            }
            enoughEnergy = player.checkEnergy(player.getEquippedTool().getEnergyNeededPerUse(),null);
            player.decreaseEnergy(player.getEquippedTool().getEnergyNeededPerUse(),null);
        }

        if(! player.getBackpack().addItem(animal.getProduce(),1)){
            return new Result(-1, "Backpack is full");
        }

        animal.setProduce(null);
        animal.increaseFriendshipLevel(5);
        player.getSkill(SkillType.FARMING).addXP(5);
        if(enoughEnergy){
            return new Result(1,"You got produce from " + animal.getName());
        }
        else {
            return new Result(1,"You got produce from " +
                animal.getName()  + ". " + CommonGameController.passOut().message());
        }

    }

    public static Result sellAnimal(Animal animal) {
        Player player = App.getCurrentGame().getCurrentPlayer();
//        Animal animal = player.getAnimals().get(animalName);
//        if(animal == null) {
//            return new Result(-1, "Animal " + animalName + " not found");
//        }

        player.getAnimals().remove(animal.getName());
        deleteAnimalFromFarm(animal);
        int price = (int)(animal.getSellPrice() * ((double) animal.getFriendshipLevel() /1000 + 0.3));
        player.increaseMoney((int)(animal.getSellPrice() * ((double) animal.getFriendshipLevel() /1000) + 0.3));

        return new Result(1,"You sold " + animal.getName() + " for " + price + " money");
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
     private final static Animal animal = Animal.getAnimal("Cow");

    public static void update(float delta){
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();

        for(Animal animal : App.getCurrentGame().getCurrentPlayer().getAnimals().values()) {

            if (false) { // todo  walk animation

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
        for(Animal animal : App.getCurrentGame().getCurrentPlayer().getAnimals().values()) {
            animal.getSprite().setSize(Tile.getSize(), Tile.getSize());
            animal.getSprite().setPosition(animal.getLocation().column() * Tile.getSize(),animal.getLocation().row() * Tile.getSize());
            animal.getSprite().draw(batch);
        }
    }
}
