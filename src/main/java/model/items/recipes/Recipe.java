package model.items.recipes;

import model.items.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe extends Item {

    static final HashMap<String,Recipe> recipes ;

    static {
        recipes = new HashMap<String,Recipe>();
    }

    private final ArrayList<String> ingredients;
    private final HashMap<String,Integer> ingredientsNumber;

    public Recipe(String name, ArrayList<String> ingredients, HashMap<String, Integer> ingredientsNumber) {
        super(name);
        this.ingredients = ingredients;
        this.ingredientsNumber = ingredientsNumber;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public HashMap<String, Integer> getIngredientsNumber() {
        return ingredientsNumber;
    }

}
