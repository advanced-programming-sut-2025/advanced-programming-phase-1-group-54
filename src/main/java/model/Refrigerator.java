package model;

import model.items.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class Refrigerator implements Placeable {

    private final HashMap<Item,Integer> itemsNumber = new HashMap<>();

    public HashMap<Item, Integer> getItemsNumber() {
        return itemsNumber;
    }

    public Result addItem(Item item,Integer number) {


        Integer numberOfItem = itemsNumber.get(item);
        if(numberOfItem != null) {
            itemsNumber.put(item, numberOfItem + number);
            return new Result(1, number + " " + item.getName() + " added to Refrigerator");
        }
        else if(item.isEdible()){
            itemsNumber.put(item,number);
            return new Result(1,number + " " + item.getName() + " added to Refrigerator");
        }
        else {
            return new Result(-1,item.getName() + " is not edible");
        }
    }

    public Result removeItem(Item item,Integer number) {

        Integer numberOfItem = itemsNumber.get(item);

        if(numberOfItem == null){
            return new Result(-1,"You do not have " + item.getName() + " in your refrigerator");
        }
        if(numberOfItem.equals(number)){
            itemsNumber.remove(item);
            return new Result(1,number + " " + item.getName() + " removed from Refrigerator completely");
        }
        else if(numberOfItem > number){
            itemsNumber.put(item,numberOfItem - number);
            return new Result(1,number + " " + item.getName() + " removed from Refrigerator");
        }
        else {
            return new Result(-1,"You do not have enough number of " + item.getName() +
                    " in your Refrigerator");
        }

    }




}
