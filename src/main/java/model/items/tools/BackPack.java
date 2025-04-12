package model.items.tools;

import model.items.Item;
import model.Result;
import model.enums.toolsLevel.BackPackLevel;

import java.util.ArrayList;
import java.util.HashMap;

public class BackPack extends Tool {
    private BackPackLevel level;
    private ArrayList<Item> itemsInBackPack = new ArrayList<>();
    private HashMap<Item, Integer> numberOfItemsInBackPack = new HashMap<>();

    public void addItem(Item item) {
        if (itemsInBackPack.size() < level.getSize()) {
            itemsInBackPack.add(item);
        }
    }

    public void removeItem(Item item, Integer number) {
        if (itemsInBackPack.contains(item)) {
            if (number == null) {
                itemsInBackPack.remove(item);
                //TODO
            } else {

            }

        } else {

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

    @Override
    public void upgrade() {
        if (level != BackPackLevel.THELUX) {
            level = BackPackLevel.values()[level.ordinal() + 1];
        }
    }
}
