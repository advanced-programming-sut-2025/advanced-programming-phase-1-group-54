package controller.Game;

import model.App;
import model.map.Cabin;
import model.map.Refrigerator;
import model.Result;
import model.lives.Player;
import model.items.Food;
import model.items.Item;
import model.items.recipes.Recipe;
import model.items.tools.BackPack;
import model.map.Tile;

import java.util.ArrayList;

public class FoodController {

    public static Result showFoodRecipes(){

        Player player = App.getCurrentGame().getCurrentPlayer();
        Tile tile = App.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());

        if(! (tile.getThingOnTile() instanceof Cabin)){
            return new Result(-1,"You are not in Cabin");
        }

        ArrayList<Recipe> foodRecipes = App.getCurrentGame().getCurrentPlayer().getLearnedFoodRecipes();
        StringBuilder output = new StringBuilder();
        for(Recipe recipe: Recipe.foodRecipes.values()){
            output.append(recipe.getName()).append(" : ").append(foodRecipes.contains(recipe)).append("\n");
        }
        return new Result(1,output.toString());
    }

    public static Result cooking(String foodName){

        Player player = App.getCurrentGame().getCurrentPlayer();
        Food food = Food.getFood(foodName);
        Tile tile = App.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());
        if(! (tile.getThingOnTile() instanceof Cabin)){
            return new Result(-1,"You are not in Cabin");
        }

        if(! player.checkEnergy(3, null)){
            return new Result(-1,"You don't have enough energy to cook");
        }

        if(food == null){
            return new Result(-1,foodName + " doesn't exist");
        }

        Recipe recipe = Recipe.foodRecipes.get(food.getName() + " Recipe");
        if(player.getLearnedFoodRecipes().contains(recipe)){
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
        player.getBackpack().addItem(food, 1);

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
