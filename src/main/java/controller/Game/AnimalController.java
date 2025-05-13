package controller.Game;

import model.AnimalHouse;
import model.App;
import model.Result;
import model.alive.Animal;
import model.alive.Player;
import model.items.Item;
import model.map.Farm;
import model.map.Location;
import model.map.Tile;

public class AnimalController {

    public static Result pet(String animalName) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "Animal " + animalName + " not found");
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
            output.append(animal.getName()).append("\n").
                    append(animal.getAnimalName()).append("\n").
                    append("friendship level: ").append(animal.getFriendshipLevel()).append("\n").
                    append("caressed: ").append(animal.isCaressed()).append("\n").
                    append("hungry").append(animal.isHungry()).append("\n").
                    append("--------------------");
        }
        return new Result(1,output.toString());
    }

    public static Result moveAnimal(String animalName,String xString,String yString) {
        int x;
        try{
            x = Integer.parseInt(xString);
        }catch (NumberFormatException e) {
            return new Result(-1, "X format is invalid");
        }

        int y;
        try{
            y = Integer.parseInt(yString);
        }catch (NumberFormatException e) {
            return new Result(-1, "Y format is invalid");
        }

        Location location = new Location(x, y);
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
            if(animal.getLocation() != null &&
                    farm.getTileAt(animal.getLocation()).getThingOnTile() instanceof AnimalHouse pastAnimalHouse) {
                pastAnimalHouse.decreaseNumberOfAnimals(1);
            }
            tile.setThingOnTile(animal);
            animal.setLocation(locationInFarm);
        }
        else if(tile.getThingOnTile() instanceof AnimalHouse animalHouse){
            if(animalHouse.getSize() > animalHouse.getNumberOfAnimals()){
                animalHouse.increaseNumberOfAnimals(1);
                animal.setLocation(locationInFarm);
            }
        }




        return new Result(1,"Animal " + animalName + " has moved successfully");
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

        return new Result(1,"animal " + animalName + " has feeded successfully");
    }

    public static Result showProducedAnimals() {
        Player player = App.getCurrentGame().getCurrentPlayer();
        StringBuilder output = new StringBuilder();
        for(Animal animal : player.getAnimals().values()) {
            if(animal.getProduce() != null){
                output.append(animal.getName()).append("\n").
                        append(animal.getAnimalName()).append("\n").
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

        if(! player.getBackpack().addItem(animal.getProduce(),1)){
            return new Result(-1, "Backpack is full");
        }

        animal.setProduce(null);
        animal.increaseFriendshipLevel(5);
        return new Result(1,"You got produce from " + animalName);
    }

    public static Result sellAnimal(String animalName) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getAnimals().get(animalName);
        if(animal == null) {
            return new Result(-1, "Animal " + animalName + " not found");
        }

        player.getAnimals().remove(animalName);
        player.increaseMoney((int)(animal.getSellPrice() * ((double) animal.getFriendshipLevel() /1000) + 0.3));

        return new Result(1,"You sold " + animalName);
    }
}
