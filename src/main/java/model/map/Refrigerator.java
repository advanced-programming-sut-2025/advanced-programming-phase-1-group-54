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

    public boolean addItem(Item item, Integer number) {

        Integer numberOfItem = numberOfItemInRefrigerator.get(item);
        if(numberOfItem != null) {
            numberOfItemInRefrigerator.put(item, numberOfItem + number);
            return true;
        }
        else if(item.isEdible()){
            numberOfItemInRefrigerator.put(item,number);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean removeItem(Item item,Integer number) {

        Integer numberOfItem = numberOfItemInRefrigerator.get(item);

        if(numberOfItem == null){
            return false;
        }
        if(numberOfItem.equals(number)){
            numberOfItemInRefrigerator.remove(item);
            return true;
        }
        else if(numberOfItem > number){
            numberOfItemInRefrigerator.put(item,numberOfItem - number);
            return true;
        }
        else {
            return false;
        }

    }


    @Override
    public Symbol getSymbol() {
        return Symbol.REFRIGERATOR;
    }
}
