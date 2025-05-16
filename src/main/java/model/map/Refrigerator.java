package model.map;

import model.Placeable;
import model.Result;
import model.enums.Symbol;
import model.items.Item;

import java.util.HashMap;

public class Refrigerator implements Placeable {

    private final HashMap<Item,Integer> numberOfItemInRefrigerator = new HashMap<>();

    public HashMap<Item, Integer> getNumberOfItemInRefrigerator() {
        return numberOfItemInRefrigerator;
    }

    public Result addItem(Item item, Integer number) {

        Integer numberOfItem = numberOfItemInRefrigerator.get(item);
        if(numberOfItem != null) {
            numberOfItemInRefrigerator.put(item, numberOfItem + number);
            return new Result(1, number + " " + item.getName() + " added to Refrigerator");
        }
        else if(item.isEdible()){
            numberOfItemInRefrigerator.put(item,number);
            return new Result(1,number + " " + item.getName() + " added to Refrigerator");
        }
        else {
            return new Result(-1,item.getName() + " is not edible");
        }
    }

    public Result removeItem(Item item,Integer number) {

        Integer numberOfItem = numberOfItemInRefrigerator.get(item);

        if(numberOfItem == null){
            return new Result(-1,"You do not have " + item.getName() + " in your refrigerator");
        }
        if(numberOfItem.equals(number)){
            numberOfItemInRefrigerator.remove(item);
            return new Result(1,number + " " + item.getName() + " removed from Refrigerator completely");
        }
        else if(numberOfItem > number){
            numberOfItemInRefrigerator.put(item,numberOfItem - number);
            return new Result(1,number + " " + item.getName() + " removed from Refrigerator");
        }
        else {
            return new Result(-1,"You do not have enough number of " + item.getName() +
                    " in your Refrigerator");
        }

    }


    @Override
    public Symbol getSymbol() {
        return Symbol.REFRIGERATOR;
    }
}
