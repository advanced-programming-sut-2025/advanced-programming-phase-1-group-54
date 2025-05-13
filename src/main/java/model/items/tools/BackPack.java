package model.items.tools;

import model.enums.BackPackLevel;
import model.items.Item;
import model.Result;

import java.util.HashMap;
import java.util.Objects;

public class BackPack {
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

    public boolean removeItem(Item item, Integer number) {

        Integer numberOfItem = numberOfItemInBackPack.get(item);
        if(numberOfItem == null){
            return false;
//            return new Result(-1,"You do not have " + item.getName() + " in your backpack");
        }
        else if (Objects.equals(number, numberOfItem)) {
            numberOfItemInBackPack.remove(item);
            return true;
//            return new Result(1,number + item.getName() + "Removed completely from backpack");
        }
        else if(numberOfItem > number){
            numberOfItemInBackPack.put(item,numberOfItem - number);
            return true;
//            return new Result(1,number + item.getName() + " Removed from backpack");
        }
        else {
            return false;
//            return new Result(-1,"You do not have enough " + item.getName() + " to remove from backpack");
        }
    }

    public BackPackLevel getLevel() {
        return level;
    }

    public HashMap<Item, Integer> getNumberOfItemInBackPack() {
        return numberOfItemInBackPack;
    }

    public boolean haveSpace(Item item){
        if(numberOfItemInBackPack.containsKey(item)){
            return true;
        }
        else{
            return numberOfItemInBackPack.size() < level.getSize();
        }
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


    public void upgrade() {
        if (level != BackPackLevel.DELUX) {
            level = BackPackLevel.values()[level.ordinal() + 1];
        }
    }
}
