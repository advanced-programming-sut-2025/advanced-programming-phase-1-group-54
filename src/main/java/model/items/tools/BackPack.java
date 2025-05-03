package model.items.tools;

import model.items.Item;
import model.Result;
import model.enums.toolsLevel.BackPackLevel;

import java.util.ArrayList;
import java.util.HashMap;

public class BackPack extends Tool {
    private BackPackLevel level;
    private final ArrayList<Item> itemsInBackPack = new ArrayList<>();
    private final HashMap<Item, Integer> numberOfItemInBackPack = new HashMap<>();

    public Result addItem(Item item,Integer number) {
        if(itemsInBackPack.contains(item)){
            int numberOfItem = numberOfItemInBackPack.get(item);
            numberOfItemInBackPack.put(item, numberOfItem + number);
        }
        else if (itemsInBackPack.size() < level.getSize()) {
            itemsInBackPack.add(item);
            numberOfItemInBackPack.put(item, number);
        }
        else{
            return new Result(-1,"Backpack is Full");
        }
        return new Result(1,"Item Added to backpack");
    }

    public Result removeItem(Item item, Integer number) {
        if (itemsInBackPack.contains(item)) {
            int numberOfItem = numberOfItemInBackPack.get(item);
            if (number == numberOfItem) {
                itemsInBackPack.remove(item);
                numberOfItemInBackPack.put(item,0);
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
        else {
            return new Result(-1,"You do not have " + item.getName() + " in your backpack");
        }
    }

    public boolean ContainItem(Item item,Integer number) {
        if(itemsInBackPack.contains(item)){
            return number <= numberOfItemInBackPack.get(item);
        }
        return false;
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
        if (level != BackPackLevel.THELUX) {
            level = BackPackLevel.values()[level.ordinal() + 1];
        }
    }
}
