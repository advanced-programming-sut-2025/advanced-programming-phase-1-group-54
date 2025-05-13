package model.items;

import java.util.HashMap;

public class UniqeItems extends Item {
    private static  HashMap<String,UniqeItems> items;
    public UniqeItems(String name,boolean isEdible,int sellPrice) {
        super(name,isEdible,sellPrice);
    }
    static {
        items = new HashMap<>();
        items.put("Rice",new UniqeItems("Rice",false,0));
        items.put("Wheat Flour",new UniqeItems("Wheat Flour",false,0));
        items.put("Bouquet",new UniqeItems("Bouquet",false,0));
        items.put("Wedding Ring",new UniqeItems("Wedding Ring",false,0));
        items.put("Sugar",new UniqeItems("Sugar",false,0));
        items.put("Joja Cola",new UniqeItems("Joja Cola",false,0));
        items.put("Vinegar",new UniqeItems("Vinegar",false,0));
        items.put("Deluxe Retaining Soil",new UniqeItems("Deluxe Retaining Soil",false,0));
        items.put("Speed-Gro",new UniqeItems("Speed-Gro",false,0));
    }
    public static UniqeItems getUniqeItems(String name) {
        UniqeItems uniqeItems = items.get(name);
        if(uniqeItems == null){
            return null;
        }
        else{
            return uniqeItems.clone();
        }
    }
    @Override
    protected UniqeItems clone() {
        try {
            return (UniqeItems) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
