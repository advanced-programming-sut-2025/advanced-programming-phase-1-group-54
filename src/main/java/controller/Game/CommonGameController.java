package controller.Game;

import model.App;
import model.alive.Player;
import model.enums.ProduceQuality;
import model.items.*;
import model.items.crafting.Artisan;
import model.items.crafting.Produce;
import model.items.plants.Fruit;
import model.items.plants.Seed;
import model.items.tools.BackPack;


public class CommonGameController {

    public static Item findItem(String ItemName){

        Seed seed = Seed.getSeed(ItemName);
        if(seed != null){
            return seed;
        }

        Fruit fruit = Fruit.getFruit(ItemName);
        if(fruit != null){
            return fruit;
        }

        Artisan artisan = Artisan.getArtisan(ItemName);
        if(artisan != null){
            return artisan;
        }

        Fish fish = Fish.getFish(ItemName);
        if(fish != null){
            return fish;
        }

        Food food = Food.getFood(ItemName);
        if(food != null){
            return food;
        }

        Material material = Material.getMaterial(ItemName);
        if(material != null){
            return material;
        }

        Produce produce = Produce.getProduce(ItemName);
        if(produce != null){
            return produce;
        }

        AnimalProduce animalProduce = AnimalProduce.getAnimalProduce(ItemName);
        if(animalProduce != null){
            return animalProduce;
        }

        return null;
    }

    public static int numberOfItemInBackPack(String ItemName){
        Player player = App.getCurrentGame().getCurrentPlayer();
        int number = 0;
        if(ItemName.equals("fish")){
            for(Fish fish : Fish.getFishesValues()){
                for(ProduceQuality quality : ProduceQuality.values()){
                    fish.setQuality(quality);
                    number += player.getBackpack().getNumberOfItemInBackPack().get(fish);
                }
            }
        }
        else {
            Item item = findItem(ItemName);
            if(item instanceof Fish fish){
                for(ProduceQuality quality : ProduceQuality.values()){
                    fish.setQuality(quality);
                    number += player.getBackpack().getNumberOfItemInBackPack().get(fish);
                }
            }
            else if(item instanceof Fruit fruit){
                for(ProduceQuality quality : ProduceQuality.values()){
                    fruit.setQuality(quality);
                    number += player.getBackpack().getNumberOfItemInBackPack().get(fruit);
                }
            }
            else if(item instanceof AnimalProduce animalProduce){
                for(ProduceQuality quality : ProduceQuality.values()){
                    animalProduce.setQuality(quality);
                    number += player.getBackpack().getNumberOfItemInBackPack().get(animalProduce);
                }
            }
            else {
                number = player.getBackpack().getNumberOfItemInBackPack().get(item);
            }

        }

        return number;

    }

    public static void removeItemFromBackPack(String ItemName, int number){
        Player player = App.getCurrentGame().getCurrentPlayer();
        BackPack backPack = player.getBackpack();

        int amount ;
        if(ItemName.equals("fish")){
            boolean isDone = false;
            for(Fish fish : Fish.getFishesValues()){
                for(ProduceQuality quality : ProduceQuality.values()){
                    fish.setQuality(quality);
                    amount = backPack.getNumberOfItemInBackPack().get(fish);
                    if(amount <= number){
                        backPack.removeItem(fish,amount);
                        number -= amount;
                    }
                    else {
                        backPack.removeItem(fish,number);
                        number = 0;
                    }
                    if(number == 0){
                        isDone = true;
                        break;
                    }
                }
                if(isDone){
                    break;
                }
            }
        }
        else {
            Item item = findItem(ItemName);
            if(item instanceof Fish fish){
                for(ProduceQuality quality : ProduceQuality.values()){
                    fish.setQuality(quality);
                    amount = backPack.getNumberOfItemInBackPack().get(fish);
                    if(amount <= number){
                        backPack.removeItem(fish,amount);
                        number -= amount;
                    }
                    else {
                        backPack.removeItem(fish,number);
                        number = 0;
                    }

                    if(number == 0){
                        break;
                    }
                }
            }
            else if(item instanceof Fruit fruit){
                for(ProduceQuality quality : ProduceQuality.values()){
                    fruit.setQuality(quality);
                    amount = backPack.getNumberOfItemInBackPack().get(fruit);
                    if(amount <= number){
                        backPack.removeItem(fruit,amount);
                        number -= amount;
                    }
                    else{
                        backPack.removeItem(fruit,number);
                        number = 0;
                    }

                    if(number == 0){
                        break;
                    }
                }
            }
            else if(item instanceof AnimalProduce animalProduce){
                for(ProduceQuality quality : ProduceQuality.values()){
                    animalProduce.setQuality(quality);
                    amount = backPack.getNumberOfItemInBackPack().get(animalProduce);
                    if(amount <= number){
                        backPack.removeItem(animalProduce,amount);
                        number -= amount;
                    }
                    else {
                        backPack.removeItem(animalProduce,number);
                        number = 0;
                    }

                    if(number == 0){
                        break;
                    }
                }
            }
            else{
                backPack.removeItem(item,number);
            }
        }
    }




}
