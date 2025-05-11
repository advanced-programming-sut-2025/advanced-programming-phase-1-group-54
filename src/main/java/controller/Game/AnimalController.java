package controller.Game;

import model.App;
import model.Result;
import model.alive.Animal;
import model.alive.Player;

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

    // TODO
    public static Result moveAnimal(String animalName,String x,String y) {
        // todo


        return null;
    }

    //TODO
    public static Result  feedAnimal(String animalName) {
        //todo

        return null;
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
