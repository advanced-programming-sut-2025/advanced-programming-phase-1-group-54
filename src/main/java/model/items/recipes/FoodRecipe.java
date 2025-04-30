package model.items.recipes;

import model.items.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodRecipe extends Recipe {

    public FoodRecipe(String name, ArrayList<String> ingredients, HashMap<String, Integer> ingredientsNumber) {
        super(name, ingredients, ingredientsNumber);
    }

}
