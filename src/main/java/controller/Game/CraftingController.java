package controller.Game;

import model.App;
import model.Result;
import model.alive.Player;
import model.enums.Direction;
import model.items.crafting.Artisan;
import model.items.crafting.ProducerArtisan;
import model.items.crafting.UnProducerArtisan;
import model.items.plants.Seed;
import model.items.recipes.Recipe;
import model.map.Tile;

import java.util.ArrayList;

public class CraftingController {


    public static Result showCraftingRecipe(){

        // Todo is in the House?

        ArrayList<Recipe> craftingRecipes = App.getCurrentGame().getCurrentPlayer().getLearnedCraftingRecipes();
        StringBuilder output = new StringBuilder();
        for (Recipe recipe : Recipe.craftRecipes.values()) {
            output.append(recipe.getName()).append(" : ").append(craftingRecipes.contains(recipe)).append("\n");
        }
        output.deleteCharAt(output.length() - 1);

        return new Result(1, output.toString());

    }

    // Todo
    public Result Crafting(String artisanName){

        // Todo is in the House?


        Player player = App.getCurrentGame().getCurrentPlayer();

        Artisan artisan = ProducerArtisan.getProducerArtisan(artisanName);
        if(artisan == null){
            artisan = UnProducerArtisan.getUnProducerArtisan(artisanName);
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
            if(! player.getBackpack().addItem(Seed.getSeed("Mystic Tree Seed"),1)){
                return new Result(-1,"Backpack is full");
            };
        }
        else{
            if(! player.getBackpack().addItem(artisan,1)){
                return new Result(-1,"Backpack is full");
            }
        }

       return new Result(1,artisanName + " crafted successfully");

    }


    public Result placeArtisan(String artisanName,String directionString){

        Direction direction;
        try{
            direction = Direction.valueOf(directionString);
        }catch(IllegalArgumentException e){
            return new Result(-1,"Invalid direction");
        }

        Player player = App.getCurrentGame().getCurrentPlayer();
        Tile tile = App.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation().getLocationAt(direction));

        if(tile.getThingOnTile() != null){
            return new Result(-1,"Tile is already placed");
        }
        // TODO oooooooooooooooooooo
//        ProducerArtisan producerArtisan = ProducerArtisan.getProducerArtisan(artisanName);
//        if(producerArtisan == null){
//
//        }
        return null;

    }





}
