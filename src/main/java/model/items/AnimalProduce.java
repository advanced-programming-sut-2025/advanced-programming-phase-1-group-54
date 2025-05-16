package model.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.enums.ProduceQuality;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class AnimalProduce extends Item implements Cloneable {

    private final static HashMap<String, AnimalProduce> animalProduces;

    static {
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("animalProduces.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, AnimalProduce>>() {
        }.getType();
        animalProduces = gson.fromJson(file, type);
        System.out.println(animalProduces.size());
    }

    public static AnimalProduce getAnimalProduce(String name) {
        AnimalProduce animalProduce = animalProduces.get(name);
        if (animalProduce == null) {
            return null;
        } else {
            return animalProduce.clone();
        }
    }

    public AnimalProduce(String name, boolean isEdible, int sellPrice) {
        super(name, isEdible, sellPrice);
    }

    private ProduceQuality quality;

    public ProduceQuality getQuality() {
        return quality;
    }

    public void setQuality(ProduceQuality quality) {
        this.quality = quality;
    }


    @Override
    public AnimalProduce clone() {
        try {
            return (AnimalProduce) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnimalProduce animalProduce) {
            return this.getName().equals(animalProduce.getName()) && this.getQuality().equals(animalProduce.getQuality());
        }
        return false;
    }

    public static void writeToJson() {

        HashMap<String, AnimalProduce> animalProducesType = new HashMap<>();

        AnimalProduce animalProduce;

        animalProduce = new AnimalProduce("Egg", true, 50);
        animalProducesType.put("Egg", animalProduce);

        animalProduce = new AnimalProduce("Large Egg", true, 95);
        animalProducesType.put("Large Egg", animalProduce);

        animalProduce = new AnimalProduce("Duck Egg", true, 95);
        animalProducesType.put("Duck Egg", animalProduce);

        animalProduce = new AnimalProduce("Duck feather", false, 250);
        animalProducesType.put("Duck feather", animalProduce);

        animalProduce = new AnimalProduce("Wool", false, 340);
        animalProducesType.put("Wool", animalProduce);

        animalProduce = new AnimalProduce("Rabbit Leg", false, 565);
        animalProducesType.put("Rabbit Leg", animalProduce);

        animalProduce = new AnimalProduce("Dinosaur Egg", true, 350);
        animalProducesType.put("Dinosaur Egg", animalProduce);

        animalProduce = new AnimalProduce("Milk", true, 125);
        animalProducesType.put("Milk", animalProduce);

        animalProduce = new AnimalProduce("Large Milk", true, 190);
        animalProducesType.put("Large Milk", animalProduce);

        animalProduce = new AnimalProduce("Goat Milk", true, 225);
        animalProducesType.put("Goat Milk", animalProduce);

        animalProduce = new AnimalProduce("Large Goat Milk", true, 345);
        animalProducesType.put("Large Goat Milk", animalProduce);

        animalProduce = new AnimalProduce("Truffle", false, 625);
        animalProducesType.put("Truffle", animalProduce);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("animalProduces.json")) {
            gson.toJson(animalProducesType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
