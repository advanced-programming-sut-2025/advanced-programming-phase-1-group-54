package controller.Game;

import model.App;
import model.Result;
import model.items.recipes.Recipe;

import java.util.ArrayList;

public class FoodController {

    public static Result showFoodRecipes(){

        // Todo is in the House?

        ArrayList<Recipe> foodRecipes = App.getCurrentGame().getCurrentPlayer().getLearnedFoodRecipes();
        StringBuilder output = new StringBuilder();
        for(Recipe recipe: Recipe.foodRecipes.values()){
            output.append(recipe.getName()).append(" : ").append(foodRecipes.contains(recipe)).append("\n");
        }
        return new Result(1,output.toString());
    }








}
