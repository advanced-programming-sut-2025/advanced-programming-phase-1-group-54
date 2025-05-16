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
        }
        else if (Objects.equals(number, numberOfItem)) {
            numberOfItemInBackPack.remove(item);
            return true;
        }
        else if(numberOfItem > number){
            numberOfItemInBackPack.put(item,numberOfItem - number);
            return true;
        }
        else {
            return false;
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
            return numberOfItemInBackPack.size() < this.getCapacity();
        }
    }

    public int getCapacity() {
        return level.getSize();
    }

    public void upgrade() {
        if (level != BackPackLevel.DELUX) {
            level = BackPackLevel.values()[level.ordinal() + 1];
        }
    }
}
