package io.github.stardewmini.controller.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.stardewmini.Main;
import io.github.stardewmini.common.model.App;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.items.Food;
import io.github.stardewmini.common.model.items.Item;
import io.github.stardewmini.common.model.items.recipes.Recipe;
import io.github.stardewmini.common.model.items.tools.BackPack;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.Cabin;
import io.github.stardewmini.common.model.map.Refrigerator;
import io.github.stardewmini.common.model.map.Tile;
import io.github.stardewmini.client.view.GameScreen;

import java.util.ArrayList;

public class FoodController {

    public static Result showFoodRecipes(Window window){

        Player player = App.getCurrentGame().getCurrentPlayer();
        Tile tile = App.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());

        if(! (tile.getThingOnTile() instanceof Cabin)){
            return new Result(-1,"You are not in Cabin");
        }

        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        ArrayList<Recipe> foodRecipes = App.getCurrentGame().getCurrentPlayer().getLearnedFoodRecipes();
        int inRow = 0;
        for(Recipe recipe: Recipe.foodRecipes.values()){
            Image image = new Image(gameAssetManager.getRecipe(recipe.getName()));
            if(foodRecipes.contains(recipe)){
                image.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        String artisanName = recipe.getName();
                        Result result = cooking(artisanName.substring(0,artisanName.length() - 7));
                        window.remove();
                        Main.getInstance().getScreen().dispose();
                        Main.getInstance().setScreen(new GameScreen(gameAssetManager.getSkin(),result.message()));
                    }
                });
            }
            else{
                image.setColor(Color.GRAY);
            }

            window.add(image).expand().pad(10);
            inRow++;
            if(inRow == 10){
                window.row();
                inRow = 0;
            }
        }
        return new Result(1,"");
    }

    public static Result cooking(String foodName){

        Player player = App.getCurrentGame().getCurrentPlayer();
        Food food = Food.getFood(foodName);
        Tile tile = App.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());
        if(! (tile.getThingOnTile() instanceof Cabin)){
            return new Result(-1,"You are not in Cabin");
        }

        boolean passOut = false;
        if(! player.checkEnergy(3, null)){
            passOut = true;
        }

        if(food == null){
            return new Result(-1,foodName + " doesn't exist");
        }

        Recipe recipe = Recipe.foodRecipes.get(food.getName() + " Recipe");
        if(! player.getLearnedFoodRecipes().contains(recipe)){
            return new Result(-1,"You don't have enough recipe");
        }

        for(String ingredient : recipe.getIngredientsNames()){


            if(CommonGameController.numberOfItemInBackPack(ingredient) < recipe.getIngredientsNumber().get(ingredient)){
                return new Result(-1,"You do not have enough ingredients");
            }
        }

        if(! player.getBackpack().addItem(food, 1)){
            return new Result(-1,"Backpack is full");
        }

        for(String ingredient : recipe.getIngredientsNames()){
            CommonGameController.removeItemFromBackPack(ingredient,recipe.getIngredientsNumber().get(ingredient));
        }

        player.decreaseEnergy(3,null);

        if(passOut){
            return new Result(1,foodName + " cooked successfully. " +
                    CommonGameController.passOut().message());
        }

        return new Result(1,foodName + " cooked successfully");

    }

    public static Result eatFood(String foodName){

        Player player = App.getCurrentGame().getCurrentPlayer();
        Food food = Food.getFood(foodName);
        if(food == null){
            return new Result(-1, "Food doesn't exist");
        }

        if(! CommonGameController.removeItemFromInventory(food,1)){
            return new Result(-1, "You don't have food");
        }

        player.increaseEnergy(food.getEnergy());

        if(food.getEnergyBuff() > 0){
            player.setEnergy(Player.getMaximumEnergy() + food.getEnergyBuff());
        }
        else if(food.getSkillType() != null){
            player.setBuffSkill(food.getSkillType());
            player.setBuffHours(food.getBuffHours());
        }

        return new Result(1,foodName + " eaten");
    }

    public static Result moveToRefrigerator(String itemName ,int number){

        Player player = App.getCurrentGame().getCurrentPlayer();
        Tile tile = App.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());

        if(! (tile.getThingOnTile() instanceof Cabin cabin)){
            return new Result(-1,"You are not in Cabin");
        }

        Item item = CommonGameController.findItem(itemName);
        if(item == null){
            return new Result(-1, "Item doesn't exist");
        }

        Refrigerator refrigerator = cabin.getRefrigerator();
        BackPack backPack = App.getCurrentGame().getCurrentPlayer().getBackpack();

        if(! refrigerator.addItem(item,number)){
            return new Result(-1,"Item isn't edible");
        }

        if(! backPack.removeItem(item,number)){
            refrigerator.removeItem(item,number);
            return new Result(-1,"You don't have enough " + itemName + " in your backpack");
        }



        return new Result(1,number + " of " + itemName + " moved successfully");
    }

    public static Result moveToBackpack(String itemName,int number){

        Item item = CommonGameController.findItem(itemName);
        if(item == null){
            return new Result(-1, "Item doesn't exist");
        }

        Refrigerator refrigerator = App.getCurrentGame().getCurrentPlayer().getRefrigerator();
        BackPack backPack = App.getCurrentGame().getCurrentPlayer().getBackpack();

        if(! backPack.addItem(item,number)){
            return new Result(-1,"Backpack is full");
        }

        if(! refrigerator.removeItem(item,number)){
            backPack.removeItem(item,number);
            return new Result(-1 ,"you don't have enough " + itemName + " in your refrigerator");
        }


        return new Result(1,number + " of " + itemName + " moved successfully");
    }





}
