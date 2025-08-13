package io.github.stardewmini.controller.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.stardewmini.Main;
import io.github.stardewmini.model.App;
import io.github.stardewmini.model.DateTime;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.Result;
import io.github.stardewmini.model.enums.Direction;
import io.github.stardewmini.model.enums.Feature;
import io.github.stardewmini.model.items.Item;
import io.github.stardewmini.model.items.crafting.Artisan;
import io.github.stardewmini.model.items.crafting.FeatureArtisan;
import io.github.stardewmini.model.items.crafting.Produce;
import io.github.stardewmini.model.items.crafting.ProducerArtisan;
import io.github.stardewmini.model.items.plants.Seed;
import io.github.stardewmini.model.items.recipes.Recipe;
import io.github.stardewmini.model.lives.Player;
import io.github.stardewmini.model.map.Cabin;
import io.github.stardewmini.model.map.Farm;
import io.github.stardewmini.model.map.Location;
import io.github.stardewmini.model.map.Tile;
import io.github.stardewmini.view.GameScreen;

import java.util.ArrayList;

public class CraftingController {

    public static Result showCraftingRecipe(Window window){

        Player player = App.getCurrentGame().getCurrentPlayer();
        Tile tile = App.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());
        if(! (tile.getThingOnTile() instanceof Cabin)){
            return new Result(-1,"You are not in the Cabin");
        }

        ArrayList<Recipe> craftingRecipes = player.getLearnedCraftingRecipes();
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        int inRow = 0;
        for (Recipe recipe : Recipe.craftRecipes.values()) {
            Image image = new Image(gameAssetManager.getRecipe(recipe.getName()));
            if(craftingRecipes.contains(recipe)){
                image.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        String artisanName = recipe.getName();
                        Result result = crafting(artisanName.substring(0,artisanName.length() - 7));
                        window.remove();
                        Main.getInstance().getScreen().dispose();
                        Main.getInstance().setScreen(new GameScreen(gameAssetManager.getSkin(),result.message()));
                    }
                });
            }
            else {
                image.setColor(Color.GRAY);
            }
            window.add(image).expand().pad(10);
            inRow++;
            if(inRow == 10){
                window.row();
                inRow = 0;
            }
        }
        return new Result(1, "");

    }

    public static Result crafting(String artisanName){

        Player player = App.getCurrentGame().getCurrentPlayer();
        Tile tile = App.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());
        if(! (tile.getThingOnTile() instanceof Cabin)){
            return new Result(-1,"You are not in the Cabin");
        }



        if(! player.checkEnergy(2,null)){
            return new Result(-1,"you don't have enough energy");
        }

        Artisan artisan = ProducerArtisan.getProducerArtisan(artisanName);
        if(artisan == null){
            artisan = FeatureArtisan.getFeatureArtisan(artisanName);
            if(artisan == null){
                return new Result(-1,"Artisan dose not exist");
            }
        }


        Recipe recipe = Recipe.craftRecipes.get(artisan.getRecipeName());
        if(!player.getLearnedCraftingRecipes().contains(recipe)){
            return new Result(-1,"You doesn't have the crafting recipe");
        }


        for(String ingredient : recipe.getIngredientsNames()){
            if(CommonGameController.numberOfItemInBackPack(ingredient) < recipe.getIngredientsNumber().get(ingredient)){
                return new Result(-1,"You do not have the enough ingredients");
            }
        }

        for(String ingredient : recipe.getIngredientsNames()){
            CommonGameController.removeItemFromBackPack(ingredient, recipe.getIngredientsNumber().get(ingredient));
        }

        if(artisanName.equals("Mystic Tree Seeds")){
            if(! player.getBackpack().addItem(Seed.getSeed("Mystic Tree Seeds"),1)){
                return new Result(-1,"Backpack is full");
            }
        }
        else{
            if(! player.getBackpack().addItem(artisan,1)){
                return new Result(-1,"Backpack is full");
            }
        }

        player.decreaseEnergy(2,null);

       return new Result(1,artisanName + " crafted successfully");

    }

    // TODO
    public static Result placeArtisan(String artisanName, Direction direction){
        if (direction == null)
            return new Result(false, "invalid direction");

        Player player = App.getCurrentGame().getCurrentPlayer();
        Farm farm = App.getCurrentGame().getWorld().getFarmAt(player.getCurrentLocation());

        if(farm == null){
            return new Result(-1,"You aren't in any farm");
        }

        Tile tile = farm.getTileAt(player.getCurrentLocation().getLocationAt(direction).delta(farm.getLocation()));

        if(tile.getThingOnTile() != null){
            return new Result(-1,"Tile is already placed");
        }

        ProducerArtisan producerArtisan = ProducerArtisan.getProducerArtisan(artisanName);
        if(producerArtisan != null){
            if(! player.getBackpack().removeItem(producerArtisan,1)){
                return new Result(-1,"You don't have the artisan");
            }

            tile.setThingOnTile(producerArtisan);
            player.getPlacedArtisans().add(producerArtisan);
            App.getCurrentGame().getDateTime().addHourUpdateListener(producerArtisan);

            return new Result(1,"Artisan placed successfully");
        }
        FeatureArtisan featureArtisan = FeatureArtisan.getFeatureArtisan(artisanName);
        if(featureArtisan == null){
            return new Result(-1,"Artisan dose not exist");
        }

        Location location = player.getCurrentLocation().delta(farm.getLocation());

        if(featureArtisan.getFeature().equals(Feature.DESTROYED)){

            for(int i = -featureArtisan.getRadius(); i <= featureArtisan.getRadius(); i++){
                for(int j = -featureArtisan.getRadius(); j <= featureArtisan.getRadius(); j++){
                    Location location1 = location.delta(new Location(location.row() + i,location.column() + j));
                    Tile tile1 =  farm.getTileAt(location1);
                    if(tile1 != null){
                        CommonGameController.deleteThingOnTile(tile1,farm);
                    }
                }
            }

        }
        else{
            for(int i = -featureArtisan.getRadius(); i <= featureArtisan.getRadius(); i++){
                for(int j = -featureArtisan.getRadius(); j <= featureArtisan.getRadius(); j++){
                    Location location1 = location.delta(new Location(location.row() + i,location.column() + j));
                    Tile tile1 =  farm.getTileAt(location1);
                    if(tile1 != null){
                        tile.getFeatures().add(featureArtisan.getFeature());
                    }
                }
            }
        }

        return new Result(-1,"Artisan placed successfully");
    }

    public static Result producing(String artisanName,String produceName){

        Player player = App.getCurrentGame().getCurrentPlayer();

        ProducerArtisan producerArtisan = null;
        boolean haveArtisan = false;
        for(ProducerArtisan artisan : player.getPlacedArtisans()){
            if(artisan.getName().equals(artisanName)){
                haveArtisan = true;
                if(artisan.getProcessingProduce() == null){
                    producerArtisan = artisan;
                }
            }
        }

        if(! haveArtisan){
            return new Result(-1,"You don't have the producing artisan");
        }

        if(producerArtisan == null){
            return new Result(-1,"Artisan is producing");
        }

        Produce produce = Produce.getProduce(produceName);
        if(produce == null){
            return new Result(-1,"Produce doesn't exist");
        }

        if(! producerArtisan.getProducesNames().contains(produce.getName())){
            return new Result(-1,"Artisan can't make this produce");
        }

        for(String ingredient : produce.getIngredientsNames()){
            if(CommonGameController.numberOfItemInBackPack(ingredient) < produce.getIngredientsNumber().get(ingredient)){
                return new Result(-1,"You don't have enough ingredient to make this produce");
            }
        }


        producerArtisan.setProcessingProduce(produce);
        if(produce.getProcessingMornings() > 0){
            producerArtisan.setRemainingHours(DateTime.getHoursInDay() - App.getCurrentGame().getDateTime().getHour() +
                    (produce.getProcessingMornings() - 1) * DateTime.getHoursInDay());
        }
        else{
            producerArtisan.setRemainingHours(produce.getProcessingHours());
        }

        for(String ingredient : produce.getIngredientsNames()){
            Item item = CommonGameController.findItem(ingredient);
            player.getBackpack().removeItem(item,produce.getIngredientsNumber().get(ingredient));
        }

        return new Result(1,"Start Producing");

    }

    public static Result getProduceFromArtisan(String artisanName){

        Player player = App.getCurrentGame().getCurrentPlayer();

        ProducerArtisan producerArtisan = null;
        for(ProducerArtisan artisan : player.getPlacedArtisans()){
            if(artisan.getName().equals(artisanName)){
                producerArtisan = artisan;
            }
        }

        if(producerArtisan == null){
            return new Result(-1,"you don't have the artisan");
        }

        if(! producerArtisan.isProduceReady()){
            return new Result(-1,"Produce isn't ready");
        }

//        if(! MapController.isNear(player.getCurrentLocation(),producerArtisan)){
//            return new Result(-1,"You aren't near artisan " + producerArtisan.getName());
//        }

        if(! player.getBackpack().addItem(producerArtisan.getProcessingProduce(),1)){
            return new Result(-1,"Backpack is full");
        }

        producerArtisan.setProduceIsReady(false);
        producerArtisan.setProcessingProduce(null);


        return new Result(1,"You got the produce");
    }


}
