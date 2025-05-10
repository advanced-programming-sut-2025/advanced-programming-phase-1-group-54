package model.items.tools;

import model.items.Item;
import model.Result;
import model.enums.toolsLevel.BackPackLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class BackPack extends Tool {
    private BackPackLevel level;
    private final HashMap<Item, Integer> numberOfItemInBackPack = new HashMap<>();

    public boolean addItem(Item item,Integer number) {
        Integer numberOfItem = numberOfItemInBackPack.get(item);
        if(numberOfItem == null) {
            if(numberOfItemInBackPack.size() < level.getSize()) {
                numberOfItemInBackPack.put(item, number);
                return true;
            }
            return false;
        }
        numberOfItemInBackPack.put(item, numberOfItem + number);
        return true;
    }

    public Result removeItem(Item item, Integer number) {

        Integer numberOfItem = numberOfItemInBackPack.get(item);
        if(numberOfItem == null){
            return new Result(-1,"You do not have " + item.getName() + " in your backpack");
        }
        else if (Objects.equals(number, numberOfItem)) {
            numberOfItemInBackPack.remove(item);
            return new Result(1,number + item.getName() + "Removed completely from backpack");
        }
        else if(numberOfItem > number){
            numberOfItemInBackPack.put(item,numberOfItem - number);
            return new Result(1,number + item.getName() + " Removed from backpack");
        }
        else {
            return new Result(-1,"You do not have enough " + item.getName() + " to remove from backpack");
        }
    }

    public BackPackLevel getLevel() {
        return level;
    }

    public HashMap<Item, Integer> getNumberOfItemInBackPack() {
        return numberOfItemInBackPack;
    }

    //    @Override
//    public Result use(Item item) {
//        if(itemsInBackPack.size() < level.getSize()){
//            itemsInBackPack.add(item);
//            return new Result(true,"Item added to backpack");
//        }
//        else {
//            return new Result(false,"Backpack is Full");
//        }
//    }

    @Override
    public void upgrade() {
        if (level != BackPackLevel.DELUX) {
            level = BackPackLevel.values()[level.ordinal() + 1];
        }
    }
}
