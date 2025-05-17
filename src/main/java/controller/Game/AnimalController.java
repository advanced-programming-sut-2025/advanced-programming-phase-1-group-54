package controller.Game;

import model.enums.SkillType;
import model.enums.ToolType;
import model.map.*;
import model.App;
import model.Result;
import model.lives.Animal;
import model.lives.Player;
import model.items.Item;

public class AnimalController {

    public static Result pet(String animalName) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "Animal " + animalName + " not found");
        }
        if(! MapController.isNear(player.getCurrentLocation(),animal)){
            return new Result(-1, "Animal " + animalName + " is not near you");
        }
        animal.increaseFriendshipLevel(15);
        animal.setCaressed(true);
        return new Result(1,"Animal " + animalName + " has petted");
    }

    public static Result cheatCodeSetAnimalFriendship(String animalName,String friendshipScoreString) {
        int friendshipScore;
        try{
            friendshipScore = Integer.parseInt(friendshipScoreString);
        } catch (NumberFormatException e) {
            return new Result(-1, "Invalid friendship score format");
        }

        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "Animal " + animalName + " not found");
        }

        animal.setFriendshipLevel(friendshipScore);
        return new Result(1,"Friendship set successfully");
    }

    public static Result showAnimals() {
        Player player = App.getCurrentGame().getCurrentPlayer();
        StringBuilder output = new StringBuilder();
        for(Animal animal : player.getAnimals().values()) {
            output.append(animal.getAnimalName()).append(" ").
                    append(animal.getName()).append("\n").
                    append("friendship level: ").append(animal.getFriendshipLevel()).append("\n").
                    append("caressed: ").append(animal.isCaressed()).append("\n").
                    append("hungry").append(animal.isHungry()).append("\n").
                    append("--------------------");
        }
        return new Result(1,output.toString());
    }

    public static Result moveAnimal(String animalName, Location location) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Farm farm = App.getCurrentGame().getWorld().getFarm(player);
        Location locationInFarm = location.delta(farm.getLocation());
        Tile tile = farm.getTileAt(locationInFarm);

        if(tile == null) {
            return new Result(-1, "location is not in your farm");
        }

        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "You don't have any animal with name : " + animalName);
        }

        if(tile.getThingOnTile() == null){
            deleteAnimalFromFarm(animal);
            tile.setThingOnTile(animal);
            animal.setLocation(locationInFarm);
            animal.setGoneOut(true);
        }
        else if(tile.getThingOnTile() instanceof AnimalHouse animalHouse){
            if(animalHouse.getSize() > animalHouse.getNumberOfAnimals() && tile.getTop().getThingOnTile() == null){
                deleteAnimalFromFarm(animal);
                animalHouse.increaseNumberOfAnimals(1);
                animal.setLocation(locationInFarm);
                tile.getTop().setThingOnTile(animal);
            }
        }


        return new Result(1,"Animal " + animalName + " has moved successfully");
    }

    private static void deleteAnimalFromFarm(Animal animal) {
        if(animal.getLocation() != null ) {
            Tile pastTile = App.getCurrentGame().getWorld().getFarm(App.getCurrentGame().getCurrentPlayer()).
                    getTileAt(animal.getLocation());
            if(pastTile.getThingOnTile() instanceof AnimalHouse pastAnimalHouse){
                pastAnimalHouse.decreaseNumberOfAnimals(1);
                pastTile.setThingOnTile(null);
            }
            else{
                pastTile.setThingOnTile(null);
            }
        }
    }

    public static Result feedAnimal(String animalName) {

        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "Animal " + animalName + " not found");
        }

        Item item = CommonGameController.findItem("Hay");
        if(! player.getBackpack().removeItem(item,1)){
            return new Result(-1, "You don't have any Hay in backpack");
        }

        animal.setHungry(false);

        return new Result(1,"animal " + animalName + " was fed successfully");
    }

    public static Result showProducedAnimals() {
        Player player = App.getCurrentGame().getCurrentPlayer();
        StringBuilder output = new StringBuilder();
        for(Animal animal : player.getAnimals().values()) {
            if(animal.getProduce() != null){
                output.append(animal.getAnimalName()).append("\n").
                        append(animal.getName()).append("\n").
                        append(animal.getProduce().getName()).append("\n").
                        append("-------------------");
            }
        }
        return new Result(1,output.toString());
    }

    public static Result getAnimalProduce(String animalName) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "Animal " + animalName + " not found");
        }

        if(animal.getProduce() == null) {
            return new Result(-1, "Animal " + animalName + " hasn't any produce");
        }

        boolean enoughEnergy = true;
        if(animal.getName().equals("Cow") || animal.getName().equals("Goat")){
            if(! player.getEquippedTool().getToolType().equals(ToolType.MILK_PAIL)){
                return new Result(-1, "You don't have milk pail in your hand");
            }
            enoughEnergy = player.checkEnergy(player.getEquippedTool().getEnergyNeededPerUse(),null);
            player.decreaseEnergy(player.getEquippedTool().getEnergyNeededPerUse(),null);
        }
        else if(animal.getName().equals("Sheep")){
            if(! player.getEquippedTool().getToolType().equals(ToolType.SHEAR)){
                return new Result(-1, "You don't have milk pail in your hand");
            }
            enoughEnergy = player.checkEnergy(player.getEquippedTool().getEnergyNeededPerUse(),null);
            player.decreaseEnergy(player.getEquippedTool().getEnergyNeededPerUse(),null);
        }

        if(! player.getBackpack().addItem(animal.getProduce(),1)){
            return new Result(-1, "Backpack is full");
        }

        animal.setProduce(null);
        animal.increaseFriendshipLevel(5);
        player.getSkill(SkillType.FARMING).addXP(5);
        if(enoughEnergy){
            return new Result(1,"You got produce from " + animalName);
        }
        else {
            return new Result(1,"You got produce from " +
                    animalName  + ". " + CommonGameController.passOut().message());
        }

    }

    public static Result sellAnimal(String animalName) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "Animal " + animalName + " not found");
        }

        player.getAnimals().remove(animalName);
        deleteAnimalFromFarm(animal);
        player.increaseMoney((int)(animal.getSellPrice() * ((double) animal.getFriendshipLevel() /1000) + 0.3));

        return new Result(1,"You sold " + animalName);
    }
}
