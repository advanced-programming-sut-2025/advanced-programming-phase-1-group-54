package model;

import model.items.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class Refrigerator implements Placeable {

    private final ArrayList<Item> items = new ArrayList<>();
    private final HashMap<Item,Integer> itemsNumber = new HashMap<>();


    public ArrayList<Item> getItems() {
        return items;
    }

    public HashMap<Item, Integer> getItemsNumber() {
        return itemsNumber;
    }

    public Result addItem(Item item,Integer number) {

        if(items.contains(item)){
            int numberOfItem = itemsNumber.get(item);
            itemsNumber.put(item,numberOfItem + number);
            return new Result(1,number + " " + item.getName() + " added to Refrigerator");
        }
        else if(item.isEdible()){
            items.add(item);
            itemsNumber.put(item,number);
            return new Result(1,number + " " + item.getName() + " added to Refrigerator");
        }
        else {
            return new Result(-1,item.getName() + " is not edible");
        }
    }

    public Result removeItem(Item item,Integer number) {
        if(items.contains(item)){
            int numberOfItem = itemsNumber.get(item);
            if(numberOfItem == number){
                items.remove(item);
                itemsNumber.put(item,0);
                return new Result(1,number + " " + item.getName() + " removed from Refrigerator");
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
        return new Result(-1,"You do not have " + item.getName() + " in your refrigerator");
    }

    public boolean containItem(Item item,Integer number) {
        if(items.contains(item)){
            return itemsNumber.get(item) >= number;
        }
        return false;
    }



}
