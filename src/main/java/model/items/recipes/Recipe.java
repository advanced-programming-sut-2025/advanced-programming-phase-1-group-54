package model.items.recipes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.items.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Recipe {

    public static final LinkedHashMap<String,Recipe> craftRecipes;
    public static final LinkedHashMap<String,Recipe> foodRecipes;

    static {
        craftRecipes = new LinkedHashMap<>();
        foodRecipes = new LinkedHashMap<>();
    }

    private final String name;
    private final ArrayList<String> ingredientsNames;
    private final HashMap<String,Integer> ingredientsNumber;

    public Recipe(String name, ArrayList<String> ingredientsNames, HashMap<String, Integer> ingredientsNumber) {
        this.name = name;
        this.ingredientsNames = ingredientsNames;
        this.ingredientsNumber = ingredientsNumber;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getIngredientsNames() {
        return ingredientsNames;
    }

    public HashMap<String, Integer> getIngredientsNumber() {
        return ingredientsNumber;
    }

    public static void craftRecipeWriteToJson(){

        LinkedHashMap<String,Recipe> craftRecipesType = new LinkedHashMap<String,Recipe>();

        Recipe recipe;
        ArrayList<String> ingredients;
        HashMap<String, Integer> numberOfIngredients;

        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Copper Ore");
        numberOfIngredients.put("Copper Ore", 4);
        ingredients.add("Coal");
        numberOfIngredients.put("Coal", 1);

        recipe = new Recipe("Cherry Bomb Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Cherry Bomb Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Iron Ore");
        numberOfIngredients.put("Iron Ore", 4);
        ingredients.add("Coal");
        numberOfIngredients.put("Coal", 1);

        recipe = new Recipe("Bomb Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Bomb Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Gold Ore");
        numberOfIngredients.put("Gold Ore", 4);
        ingredients.add("Coal");
        numberOfIngredients.put("Coal", 1);

        recipe = new Recipe("Mega Bomb Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Mega Bomb Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Copper Bar");
        numberOfIngredients.put("Copper Bar", 1);
        ingredients.add("Iron Bar");
        numberOfIngredients.put("Iron Bar", 1);

        recipe = new Recipe("Sprinkler Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Sprinkler Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Iron Bar");
        numberOfIngredients.put("Iron Bar", 1);
        ingredients.add("Gold Bar");
        numberOfIngredients.put("Gold Bar", 1);

        recipe = new Recipe("Quality Sprinkler Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Quality Sprinkler Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Gold Bar");
        numberOfIngredients.put("Gold Bar", 1);
        ingredients.add("Iridium Bar");
        numberOfIngredients.put("Iridium Bar", 1);

        recipe = new Recipe("Iridium Sprinkler Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Iridium Sprinkler Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 20);
        ingredients.add("Copper Bar");
        numberOfIngredients.put("Copper Bar", 2);

        recipe = new Recipe("Charcoal Kiln Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Charcoal Kiln Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Copper Ore");
        numberOfIngredients.put("Copper Ore", 20);
        ingredients.add("Stone");
        numberOfIngredients.put("Stone", 25);

        recipe = new Recipe("Furnace Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Furnace Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 50);
        ingredients.add("Coal");
        numberOfIngredients.put("Coal", 1);
        ingredients.add("Fiber");
        numberOfIngredients.put("Fiber", 20);

        recipe = new Recipe("Scarecrow Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Scarecrow Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 50);
        ingredients.add("Coal");
        numberOfIngredients.put("Coal", 1);
        ingredients.add("Fiber");
        numberOfIngredients.put("Fiber", 20);
        ingredients.add("Iridium Ore");
        numberOfIngredients.put("Iridium Ore", 1);

        recipe = new Recipe("Deluxe Scarecrow Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Deluxe Scarecrow Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 40);
        ingredients.add("Coal");
        numberOfIngredients.put("Coal", 8);
        ingredients.add("Iron Bar");
        numberOfIngredients.put("Iron Bar", 1);

        recipe = new Recipe("Bee House Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Bee House Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 45);
        ingredients.add("Stone");
        numberOfIngredients.put("Stone",45);
        ingredients.add("Copper Bar");
        numberOfIngredients.put("Copper Bar", 1);

        recipe = new Recipe("Cheese Press Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Cheese Press Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 30);
        ingredients.add("Copper Bar");
        numberOfIngredients.put("Copper Bar", 1);
        ingredients.add("Iron Bar");
        numberOfIngredients.put("Iron Bar", 1);

        recipe = new Recipe("Keg Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Keg Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 60);
        ingredients.add("Fiber");
        numberOfIngredients.put("Fiber", 30);

        recipe = new Recipe("Loom Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Loom Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 15);
        ingredients.add("Stone");
        numberOfIngredients.put("Stone", 15);
        ingredients.add("Copper Bar");
        numberOfIngredients.put("Copper Bar", 1);

        recipe = new Recipe("Mayonnaise Machine Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Mayonnaise Machine Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 100);
        ingredients.add("Gold Bar");
        numberOfIngredients.put("Gold Bar", 1);
        ingredients.add("Iron Bar");
        numberOfIngredients.put("Iron Bar", 1);

        recipe = new Recipe("Oil Maker Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Oil Maker Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 50);
        ingredients.add("Stone");
        numberOfIngredients.put("Stone", 40);
        ingredients.add("Coal");
        numberOfIngredients.put("Coal", 8);

        recipe = new Recipe("Preserves Jar Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Preserves Jar Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 30);
        ingredients.add("Stone");
        numberOfIngredients.put("Stone", 20);
        ingredients.add("Fiber");
        numberOfIngredients.put("Fiber", 30);

        recipe = new Recipe("Dehydrator Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Dehydrator Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 1);
        ingredients.add("Fiber");
        numberOfIngredients.put("Fiber", 1);

        recipe = new Recipe("Grass Starter Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Grass Starter Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 50);
        ingredients.add("Iron Bar");
        numberOfIngredients.put("Iron Bar", 3);
        ingredients.add("Coal");
        numberOfIngredients.put("Coal", 10);

        recipe = new Recipe("Fish Smoker Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Fish Smoker Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Acorn");
        numberOfIngredients.put("acorn", 5);
        ingredients.add("Maple Seed");
        numberOfIngredients.put("Maple Seed", 5);
        ingredients.add("Pine Cone");
        numberOfIngredients.put("Pine Cone", 5);
        ingredients.add("Mahogany Seed");
        numberOfIngredients.put("Mahogany Seed", 5);

        recipe = new Recipe("Mystic Tree Seeds Recipe",ingredients,numberOfIngredients);
        craftRecipesType.put("Mystic Tree Seeds Recipe",recipe);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("craftRecipes.json")){
            gson.toJson(craftRecipesType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void foodRecipeWriteFromJson(){

        LinkedHashMap<String,Recipe> foodRecipesType = new LinkedHashMap<String,Recipe>();

        Recipe recipe;
        ArrayList<String> ingredients;
        HashMap<String, Integer> numberOfIngredients;

        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Egg");
        numberOfIngredients.put("Egg", 1);

        recipe = new Recipe("Fried Egg Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Fried Egg Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Sardine");
        numberOfIngredients.put("Sardine", 1);
        ingredients.add("Salmon");
        numberOfIngredients.put("Salmon", 1);
        ingredients.add("Wheat");
        numberOfIngredients.put("Wheat", 1);

        recipe = new Recipe("Baked Fish Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Baked Fish Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Leek");
        numberOfIngredients.put("Leek", 1);
        ingredients.add("Dandelion");
        numberOfIngredients.put("Dandelion", 1);

        recipe = new Recipe("Salad Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Salad Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Egg");
        numberOfIngredients.put("Egg", 1);
        ingredients.add("Milk");
        numberOfIngredients.put("Milk", 1);

        recipe = new Recipe("Omelet Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Omelet Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Pumpkin");
        numberOfIngredients.put("Pumpkin", 1);
        ingredients.add("Wheat Flour");
        numberOfIngredients.put("Wheat Flour", 1);
        ingredients.add("Milk");
        numberOfIngredients.put("Milk", 1);
        ingredients.add("Sugar");
        numberOfIngredients.put("Sugar", 1);

        recipe = new Recipe("Pumpkin Pie Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Pumpkin Pie Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wheat Flour");
        numberOfIngredients.put("Wheat Flour", 1);
        ingredients.add("Tomato");
        numberOfIngredients.put("Tomato", 1);

        recipe = new Recipe("Spaghetti Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Spaghetti Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wheat Flour");
        numberOfIngredients.put("Wheat Flour", 1);
        ingredients.add("Tomato");
        numberOfIngredients.put("Tomato", 1);
        ingredients.add("Cheese");
        numberOfIngredients.put("Cheese", 1);

        recipe = new Recipe("Pizza Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Pizza Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Corn");
        numberOfIngredients.put("Corn", 1);

        recipe = new Recipe("Tortilla Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Tortilla Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Fish");
        numberOfIngredients.put("Fish", 1);
        ingredients.add("Rice");
        numberOfIngredients.put("Rice", 1);
        ingredients.add("Fiber");
        numberOfIngredients.put("Fiber", 1);

        recipe = new Recipe("Maki Roll Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Maki Roll Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Coffee");
        numberOfIngredients.put("Coffee", 3);

        recipe = new Recipe("Triple Shot Espresso Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Triple Shot Espresso Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wheat Flour");
        numberOfIngredients.put("Wheat Flour", 1);
        ingredients.add("Sugar");
        numberOfIngredients.put("Sugar", 1);
        ingredients.add("Egg");
        numberOfIngredients.put("Egg", 1);

        recipe = new Recipe("Cookie Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Cookie Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Potato");
        numberOfIngredients.put("Potato", 1);
        ingredients.add("Oil");
        numberOfIngredients.put("Oil", 1);

        recipe = new Recipe("Hash Browns Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Hash Browns Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wheat Flour");
        numberOfIngredients.put("Wheat Flour", 1);
        ingredients.add("Egg");
        numberOfIngredients.put("Egg", 1);

        recipe = new Recipe("Pancakes Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Pancakes Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Blueberry");
        numberOfIngredients.put("Blueberry", 1);
        ingredients.add("Melon");
        numberOfIngredients.put("Melon", 1);
        ingredients.add("Apricot");
        numberOfIngredients.put("Apricot", 1);

        recipe = new Recipe("Fruit Salad Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Fruit Salad Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Red Cabbage");
        numberOfIngredients.put("Red Cabbage", 1);
        ingredients.add("Radish");
        numberOfIngredients.put("Radish", 1);

        recipe = new Recipe("Red Plate Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Red Plate Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wheat Flour");
        numberOfIngredients.put("Wheat Flour", 1);

        recipe = new Recipe("Bread Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Bread Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Salmon");
        numberOfIngredients.put("Salmon", 1);
        ingredients.add("Amaranth");
        numberOfIngredients.put("Amaranth", 1);
        ingredients.add("Kale");
        numberOfIngredients.put("Kale", 1);

        recipe = new Recipe("Salmon Dinner Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Salmon Dinner Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Tomato");
        numberOfIngredients.put("Tomato", 1);
        ingredients.add("Beet");
        numberOfIngredients.put("Beet", 1);

        recipe = new Recipe("Vegetable Medley Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Vegetable Medley Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Omelet");
        numberOfIngredients.put("Omelet", 1);
        ingredients.add("Parsnip");
        numberOfIngredients.put("Parsnip", 1);

        recipe = new Recipe("Farmer's Lunch Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Farmer's Lunch Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Bread");
        numberOfIngredients.put("Bread", 1);
        ingredients.add("Carrot");
        numberOfIngredients.put("Carrot", 1);
        ingredients.add("Eggplant");
        numberOfIngredients.put("Eggplant", 1);

        recipe = new Recipe("Survival Burger Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Survival Burger Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Sardine");
        numberOfIngredients.put("Sardine", 2);
        ingredients.add("Hash Browns");
        numberOfIngredients.put("Hash Browns", 1);

        recipe = new Recipe("Dish O' The Sea Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Dish O' The Sea Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Flounder");
        numberOfIngredients.put("Flounder", 1);
        ingredients.add("Midnight Carp");
        numberOfIngredients.put("Midnight Carp", 1);

        recipe = new Recipe("Seaform Pudding Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Seaform Pudding Recipe",recipe);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Carrot");
        numberOfIngredients.put("Carrot", 2);
        ingredients.add("Sugar");
        numberOfIngredients.put("Sugar", 1);
        ingredients.add("Milk");
        numberOfIngredients.put("Milk", 1);

        recipe = new Recipe("Miner's Treat Recipe",ingredients,numberOfIngredients);
        foodRecipesType.put("Miner's Treat Recipe",recipe);



        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("foodRecipes.json")){
            gson.toJson(foodRecipesType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
