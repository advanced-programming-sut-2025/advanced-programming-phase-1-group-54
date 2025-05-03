package controller.Game;

import model.App;
import model.items.Fish;
import model.items.Food;
import model.items.Item;
import model.items.Material;
import model.items.crafting.Artisan;
import model.items.crafting.Produce;
import model.items.plants.Fruit;
import model.items.plants.Seed;


public class CommonGameController {

    public Item findItem(String ItemName){
        Seed seed = Seed.seeds.get(ItemName);
        if(seed != null){
            return seed;
        }

        Fruit fruit = Fruit.fruits.get(ItemName);
        if(fruit != null){
            return fruit;
        }

        Artisan artisan = Artisan.artisans.get(ItemName);
        if(artisan != null){
            return artisan;
        }

        Fish fish = Fish.fishes.get(ItemName);
        if(fish != null){
            return fish;
        }

        Food food = Food.foods.get(ItemName);
        if(food != null){
            return food;
        }

        Material material = Material.materials.get(ItemName);
        if(material != null){
            return material;
        }

        Produce produce = Produce.produces.get(ItemName);
        if(produce != null){
            return produce;
        }


        return null;
    }


}
