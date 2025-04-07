package model.tools;

import model.Item;
import model.Result;
import model.Tool;
import model.enums.toolsLevel.BackPackLevel;

import java.util.ArrayList;

public class BackPack extends Tool {
    private BackPackLevel level;
    private ArrayList<Item> itemsInBackPack = new ArrayList<>();

    public Result addItem(Item item) {
        if(itemsInBackPack.size() < level.getSize()){
            itemsInBackPack.add(item);
            return new Result(true,"Item added to backpack");
        }
        else {
            return new Result(false,"Backpack is Full");
        }
    }

    public Result removeItem(Item item,Integer number) {
        if(itemsInBackPack.contains(item)){
            if(number == null){
                itemsInBackPack.remove(item);
                //TODo
                return new Result(true,"Item removed from backpack");
            }
            else {

            }

        }
        else{
            return new Result(false,"Item not in backpack");
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
    public Result upgrade() {
        if(level != BackPackLevel.THELUX){
            level = BackPackLevel.values()[level.ordinal() + 1];
            return new Result(true,"Backpack upgrade");
        }
        else{
            return new Result(false,"Backpack is last level");
        }
    }
}
