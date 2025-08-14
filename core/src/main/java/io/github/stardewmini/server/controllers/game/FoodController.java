package io.github.stardewmini.server.controllers.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.stardewmini.client.Main;
import io.github.stardewmini.server.app.GameApp;
import io.github.stardewmini.client.Renderers.GameAssetManager;
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

    public static Result cooking(String foodName){

        Player player = GameApp.getCurrentGame().getCurrentPlayer();
        Food food = Food.getFood(foodName);
        Tile tile = GameApp.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());
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

        Player player = GameApp.getCurrentGame().getCurrentPlayer();
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

        Player player = GameApp.getCurrentGame().getCurrentPlayer();
        Tile tile = GameApp.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());

        if(! (tile.getThingOnTile() instanceof Cabin cabin)){
            return new Result(-1,"You are not in Cabin");
        }

        Item item = CommonGameController.findItem(itemName);
        if(item == null){
            return new Result(-1, "Item doesn't exist");
        }

        Refrigerator refrigerator = cabin.getRefrigerator();
        BackPack backPack = GameApp.getCurrentGame().getCurrentPlayer().getBackpack();

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

        Refrigerator refrigerator = GameApp.getCurrentGame().getCurrentPlayer().getRefrigerator();
        BackPack backPack = GameApp.getCurrentGame().getCurrentPlayer().getBackpack();

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
