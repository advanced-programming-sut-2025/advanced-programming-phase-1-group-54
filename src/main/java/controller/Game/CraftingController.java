package controller.Game;

import model.App;
import model.map.Cabin;
import model.Result;
import model.alive.Player;
import model.enums.Direction;
import model.enums.Feature;
import model.items.Item;
import model.items.crafting.Artisan;
import model.items.crafting.Produce;
import model.items.crafting.ProducerArtisan;
import model.items.crafting.UnProducerArtisan;
import model.items.plants.Seed;
import model.items.recipes.Recipe;
import model.map.Farm;
import model.map.Location;
import model.map.Tile;

import java.util.ArrayList;

public class CraftingController {

    public static Result showCraftingRecipe(){

        Player player = App.getCurrentGame().getCurrentPlayer();
        Tile tile = App.getCurrentGame().getWorld().getTileAt(player.getCurrentLocation());
        if(! (tile.getThingOnTile() instanceof Cabin)){
            return new Result(-1,"You are not in the Cabin");
        }

        ArrayList<Recipe> craftingRecipes = App.getCurrentGame().getCurrentPlayer().getLearnedCraftingRecipes();
        StringBuilder output = new StringBuilder();
        for (Recipe recipe : Recipe.craftRecipes.values()) {
            output.append(recipe.getName()).append(" : ").append(craftingRecipes.contains(recipe)).append("\n");
        }
        output.deleteCharAt(output.length() - 1);

        return new Result(1, output.toString());

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
    public static Result placeArtisan(String artisanName,String directionString){

        Direction direction;
        try{
            direction = Direction.valueOf(directionString);
        }catch(IllegalArgumentException e){
            return new Result(-1,"Invalid direction");
        }

        Player player = App.getCurrentGame().getCurrentPlayer();
        Farm farm = App.getCurrentGame().getWorld().getFarm(player);
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

            return new Result(1,"Artisan placed successfully");
        }
        UnProducerArtisan unProducerArtisan = UnProducerArtisan.getUnProducerArtisan(artisanName);
        if(unProducerArtisan == null){
            return new Result(-1,"Artisan dose not exist");
        }

        Location location = player.getCurrentLocation().delta(farm.getLocation());

        if(unProducerArtisan.getFeature().equals(Feature.DESTROYED)){

            // TODO Destroy

        }
        else{
            for(int i = -unProducerArtisan.getRadius();i < unProducerArtisan.getRadius();i++){
                for(int j = -unProducerArtisan.getRadius(); j < unProducerArtisan.getRadius();j++){
                    Location location1 = location.delta(new Location(location.row() + i,location.column() + j));
                    Tile tile1 =  farm.getTileAt(location1);
                    if(tile1 != null){
                        tile.getFeatures().add(unProducerArtisan.getFeature());
                    }
                }
            }
        }

        return new Result(-1,"Artisan placed successfully");
    }

    public static Result cheatCodeAddItem(String itemName,String numberString){

        Item item = CommonGameController.findItem(itemName);
        if(item == null){
            return new Result(-1,"Item doesn't exist");
        }

        int number ;
        try{
            number = Integer.parseInt(numberString);
        }catch (NumberFormatException e){
            return new Result(-1,"Invalid number");
        }

        if(! App.getCurrentGame().getCurrentPlayer().getBackpack().addItem(item,number)){
            return new Result(-1,"Backpack is full");
        }

        return new Result(1,number + " of "+ itemName + " added successfully");

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
            return new Result(-1,"You 'don't have the producing artisan");
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
            producerArtisan.setRemainingHours(24 - App.getCurrentGame().getDateTime().getHour() +
                    (produce.getProcessingMornings() - 1) * 24);
        }
        else{
            producerArtisan.setRemainingHours(produce.getProcessingHours());
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

        if(! producerArtisan.isProduceIsReady()){
            return new Result(-1,"Produce isn't ready");
        }

        if(! player.getBackpack().addItem(producerArtisan.getProcessingProduce(),1)){
            return new Result(-1,"Backpack is full");
        }

        producerArtisan.setProduceIsReady(false);
        producerArtisan.setProcessingProduce(null);


        return new Result(1,"You got the produce");
    }

}
