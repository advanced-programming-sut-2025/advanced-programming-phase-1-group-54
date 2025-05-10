package model.items;

import model.enums.ProduceQuality;

import java.util.HashMap;

public class AnimalProduce extends Item implements Cloneable{

    private final static HashMap<String, AnimalProduce> animalProduces;

    static {
        animalProduces = new HashMap<String, AnimalProduce>();
    }

    public static AnimalProduce getAnimalProduce(String name){
        AnimalProduce animalProduce = animalProduces.get(name);
        if(animalProduce == null){
            return null;
        }
        else {
            return animalProduce.clone();
        }
    }



    private ProduceQuality quality;

    public ProduceQuality getQuality() {
        return quality;
    }

    public void setQuality(ProduceQuality quality) {
        this.quality = quality;
    }

    public AnimalProduce(String name, boolean isEdible) {
        super(name, isEdible);
    }

    @Override
    public AnimalProduce clone()  {
        try {
            return (AnimalProduce) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AnimalProduce animalProduce){
            return this.getName().equals(animalProduce.getName()) && this.getQuality().equals(animalProduce.getQuality());
        }
        return false;
    }

    public static void writeToJson(){
        // Todo
    }


}
