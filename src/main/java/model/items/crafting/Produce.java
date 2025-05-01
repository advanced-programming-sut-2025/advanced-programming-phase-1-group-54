package model.items.crafting;

import model.DateTime;
import model.items.Item;
import model.items.plants.Fruit;

import java.util.ArrayList;
import java.util.HashMap;

public class Produce extends Item {

    static final HashMap<String, Produce> produces;

    static final int dayHours = 24;

    static {
        produces = new HashMap<String, Produce>();
    }

    private final int sellPrice;
    private final boolean edible;
    private final int energy;
    private final int processingHours;
    private final int processingMornings;
    private final ArrayList<String> ingredientsNames;
    private final HashMap<String,Integer> ingredientsNumber;

    public Produce(String name, int sellPrice, boolean edible, int energy, int processingHours, int processingMornings,
                   ArrayList<String> ingredientsNames, HashMap<String, Integer> ingredientsNumber) {
        super(name);
        this.sellPrice = sellPrice;
        this.edible = edible;
        this.energy = energy;
        this.processingHours = processingHours;
        this.processingMornings = processingMornings;
        this.ingredientsNames = ingredientsNames;
        this.ingredientsNumber = ingredientsNumber;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public boolean isEdible() {
        return edible;
    }

    public int getEnergy() {
        return energy;
    }

    public int getProcessingHours() {
        return processingHours;
    }

    public int getProcessingMornings() {
        return processingMornings;
    }

    public HashMap<String, Integer> getIngredientsNumber() {
        return ingredientsNumber;
    }

    public ArrayList<String> getIngredientsNames() {
        return ingredientsNames;
    }

    public static void writeToJson(){

        HashMap<String, Produce> produceTypes = new HashMap<String, Produce>();

        Produce produce;
        ArrayList<String> ingredients;
        HashMap<String, Integer> numberOfIngredients;
        Fruit fruit;
        //Honey

        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        produce = new Produce(
                "Honey",
                350,
                true,
                75,
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);

        /// /////////////////////////////////////
        // Cheese

        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("milk");
        numberOfIngredients.put("milk", 1);

        produce = new Produce(
                "Cheese",
                230,
                true,
                100,
                3,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);

        /// /////////////////////////////

        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Wheat");
        numberOfIngredients.put("Wheat", 1);

        produce = new Produce(
                "Beer",
                200,
                true,
                50,
                1 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);

        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Rice");
        numberOfIngredients.put("Rice", 1);

        produce = new Produce(
                "Vinegar",
                100,
                true,
                13,
                10,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Coffee Bean");
        numberOfIngredients.put("Coffee Bean", 5);

        produce = new Produce(
                "Coffee",
                150,
                true,
                75,
                2,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();


        ingredients.add("Honey");
        numberOfIngredients.put("Honey", 1);

        produce = new Produce(
                "Mead",
                300,
                true,
                100,
                10,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients = new ArrayList<>();
        numberOfIngredients = new HashMap<>();

        ingredients.add("Hops");
        numberOfIngredients.put("Hops", 1);

        produce = new Produce(
                "Pale Ale",
                300,
                true,
                50,
                3 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);

        // Juice


        ingredients.add("Amaranth");
        numberOfIngredients.put("Amaranth", 1);
        fruit = Fruit.fruits.get("Amaranth");

        produce = new Produce(
                "Amaranth Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Artichoke");
        numberOfIngredients.put("Artichoke", 1);
        fruit = Fruit.fruits.get("Artichoke");

        produce = new Produce(
                "Artichoke Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Beet");
        numberOfIngredients.put("Beet", 1);
        fruit = Fruit.fruits.get("Beet");

        produce = new Produce(
                "Beet Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Bok Choy");
        numberOfIngredients.put("Bok Choy", 1);
        fruit = Fruit.fruits.get("Bok Choy");

        produce = new Produce(
                "Bok Choy Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Broccoli");
        numberOfIngredients.put("Broccoli", 1);
        fruit = Fruit.fruits.get("Broccoli");

        produce = new Produce(
                "Broccoli Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Carrot");
        numberOfIngredients.put("Carrot", 1);
        fruit = Fruit.fruits.get("Carrot");

        produce = new Produce(
                "Carrot Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Cauliflower");
        numberOfIngredients.put("Cauliflower", 1);
        fruit = Fruit.fruits.get("Cauliflower");

        produce = new Produce(
                "Cauliflower Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Corn");
        numberOfIngredients.put("Corn", 1);
        fruit = Fruit.fruits.get("Corn");

        produce = new Produce(
                "Corn Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Eggplant");
        numberOfIngredients.put("Eggplant", 1);
        fruit = Fruit.fruits.get("Eggplant");

        produce = new Produce(
                "Eggplant Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Fiddlehead Fern");
        numberOfIngredients.put("Fiddlehead Fern", 1);
        fruit = Fruit.fruits.get("Fiddlehead Fern");

        produce = new Produce(
                "Fiddlehead Fern Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Garlic");
        numberOfIngredients.put("Garlic", 1);
        fruit = Fruit.fruits.get("Garlic");

        produce = new Produce(
                "Garlic Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Green Bean");
        numberOfIngredients.put("Green Bean", 1);
        fruit = Fruit.fruits.get("Green Bean");

        produce = new Produce(
                "Green Bean Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Hops");
        numberOfIngredients.put("Hops", 1);
        fruit = Fruit.fruits.get("Hops");

        produce = new Produce(
                "Hops Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Kale");
        numberOfIngredients.put("Kale", 1);
        fruit = Fruit.fruits.get("Kale");

        produce = new Produce(
                "Kale Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Parsnip");
        numberOfIngredients.put("Parsnip", 1);
        fruit = Fruit.fruits.get("Parsnip");

        produce = new Produce(
                "Parsnip Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Potato");
        numberOfIngredients.put("Potato", 1);
        fruit = Fruit.fruits.get("Potato");

        produce = new Produce(
                "Potato Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Pumpkin");
        numberOfIngredients.put("Pumpkin", 1);
        fruit = Fruit.fruits.get("Pumpkin");

        produce = new Produce(
                "Pumpkin Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Radish");
        numberOfIngredients.put("Radish", 1);
        fruit = Fruit.fruits.get("Radish");

        produce = new Produce(
                "Radish Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Red Cabbage");
        numberOfIngredients.put("Red Cabbage", 1);
        fruit = Fruit.fruits.get("Red Cabbage");

        produce = new Produce(
                "Red Cabbage Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Summer Squash");
        numberOfIngredients.put("Summer Squash", 1);
        fruit = Fruit.fruits.get("Summer Squash");

        produce = new Produce(
                "Summer Squash Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Taro Root");
        numberOfIngredients.put("Taro Root", 1);
        fruit = Fruit.fruits.get("Taro Root");

        produce = new Produce(
                "Taro Root Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Tea Leaves");
        numberOfIngredients.put("Tea Leaves", 1);
        fruit = Fruit.fruits.get("Tea Leaves");

        produce = new Produce(
                "Tea Leaves Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Tomato");
        numberOfIngredients.put("Tomato", 1);
        fruit = Fruit.fruits.get("Tomato");

        produce = new Produce(
                "Tomato Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Unmilled Rice");
        numberOfIngredients.put("Unmilled Rice", 1);
        fruit = Fruit.fruits.get("Unmilled Rice");

        produce = new Produce(
                "Unmilled Rice Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Wheat");
        numberOfIngredients.put("Wheat", 1);
        fruit = Fruit.fruits.get("Wheat");

        produce = new Produce(
                "Wheat Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Yam");
        numberOfIngredients.put("Yam", 1);
        fruit = Fruit.fruits.get("Yam");

        produce = new Produce(
                "Yam Juice",
                (int)(2.25 * fruit.getBaseSellPrice()),
                true,
                2 * fruit.getEnergy(),
                4 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);

        // Wine

        ingredients.add("Ancient Fruit");
        numberOfIngredients.put("Ancient Fruit", 1);
        fruit = Fruit.fruits.get("Ancient Fruit");

        produce = new Produce(
                "Ancient Fruit Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Apple");
        numberOfIngredients.put("Apple", 1);
        fruit = Fruit.fruits.get("Apple");

        produce = new Produce(
                "Apple Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Apricot");
        numberOfIngredients.put("Apricot", 1);
        fruit = Fruit.fruits.get("Apricot");

        produce = new Produce(
                "Apricot Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Banana");
        numberOfIngredients.put("Banana", 1);
        fruit = Fruit.fruits.get("Banana");

        produce = new Produce(
                "Banana Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Blackberry");
        numberOfIngredients.put("Blackberry", 1);
        fruit = Fruit.fruits.get("Blackberry");

        produce = new Produce(
                "Blackberry Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Blueberry");
        numberOfIngredients.put("Blueberry", 1);
        fruit = Fruit.fruits.get("Blueberry");

        produce = new Produce(
                "Blueberry Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Cactus Fruit");
        numberOfIngredients.put("Cactus Fruit", 1);
        fruit = Fruit.fruits.get("Cactus Fruit");

        produce = new Produce(
                "Cactus Fruit JWine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Cherry");
        numberOfIngredients.put("Cherry", 1);
        fruit = Fruit.fruits.get("Cherry");

        produce = new Produce(
                "Cherry Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Coconut");
        numberOfIngredients.put("Coconut", 1);
        fruit = Fruit.fruits.get("Coconut");

        produce = new Produce(
                "Coconut Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Cranberries");
        numberOfIngredients.put("Cranberries", 1);
        fruit = Fruit.fruits.get("Cranberries");

        produce = new Produce(
                "Cranberries Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Crystal Fruit");
        numberOfIngredients.put("Crystal Fruit", 1);
        fruit = Fruit.fruits.get("Crystal Fruit");

        produce = new Produce(
                "Crystal Fruit Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Grape");
        numberOfIngredients.put("Grape", 1);
        fruit = Fruit.fruits.get("Grape");

        produce = new Produce(
                "Grape Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Hot Pepper");
        numberOfIngredients.put("Hot Pepper", 1);
        fruit = Fruit.fruits.get("Hot Pepper");

        produce = new Produce(
                "Hot Pepper Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Mango");
        numberOfIngredients.put("Mango", 1);
        fruit = Fruit.fruits.get("Mango");

        produce = new Produce(
                "Mango Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Melon");
        numberOfIngredients.put("Melon", 1);
        fruit = Fruit.fruits.get("Melon");

        produce = new Produce(
                "Melon Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Orange");
        numberOfIngredients.put("Orange", 1);
        fruit = Fruit.fruits.get("Orange");

        produce = new Produce(
                "Orange Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Peach");
        numberOfIngredients.put("Peach", 1);
        fruit = Fruit.fruits.get("Peach");

        produce = new Produce(
                "Peach Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Pineapple");
        numberOfIngredients.put("Pineapple", 1);
        fruit = Fruit.fruits.get("Pineapple");

        produce = new Produce(
                "Pineapple Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Pomegranate");
        numberOfIngredients.put("Pomegranate", 1);
        fruit = Fruit.fruits.get("Pomegranate");

        produce = new Produce(
                "Pomegranate Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Powdermelon");
        numberOfIngredients.put("Powdermelon", 1);
        fruit = Fruit.fruits.get("Powdermelon");

        produce = new Produce(
                "Powdermelon Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Qi Fruit");
        numberOfIngredients.put("Qi Fruit", 1);
        fruit = Fruit.fruits.get("Qi Fruit");

        produce = new Produce(
                "Qi Fruit Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Rhubarb");
        numberOfIngredients.put("Rhubarb", 1);
        fruit = Fruit.fruits.get("Rhubarb");

        produce = new Produce(
                "Rhubarb Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Salmonberry");
        numberOfIngredients.put("Salmonberry", 1);
        fruit = Fruit.fruits.get("Salmonberry");

        produce = new Produce(
                "Salmonberry Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);

        ingredients.add("Spice Berry");
        numberOfIngredients.put("Spice Berry", 1);
        fruit = Fruit.fruits.get("Spice Berry");

        produce = new Produce(
                "Spice Berry Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Starfruit");
        numberOfIngredients.put("Starfruit", 1);
        fruit = Fruit.fruits.get("Starfruit");

        produce = new Produce(
                "Starfruit Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Strawberry");
        numberOfIngredients.put("Strawberry", 1);
        fruit = Fruit.fruits.get("Strawberry");

        produce = new Produce(
                "Strawberry Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Wild Plum");
        numberOfIngredients.put("Wild Plum", 1);
        fruit = Fruit.fruits.get("Wild Plum");

        produce = new Produce(
                "Wild Plum Wine",
                3 * fruit.getBaseSellPrice(),
                true,
                (int)(1.75 * fruit.getEnergy()),
                7 * dayHours,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);

        //Dehydrator
        final int numberOfFruitsToDry = 5;
        // Dried Mushrooms

        ingredients.add("Chanterelle");
        numberOfIngredients.put("Chanterelle", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Chanterelle");

        produce = new Produce(
                "Dried Chanterelle",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                50,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Common Mushroom");
        numberOfIngredients.put("Common Mushroom", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Common Mushroom");

        produce = new Produce(
                "Dried Common Mushroom",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                50,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Magma Cap");
        numberOfIngredients.put("Magma Cap", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Magma Cap");

        produce = new Produce(
                "Dried Magma Cap",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                50,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Morel");
        numberOfIngredients.put("Morel", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Morel");

        produce = new Produce(
                "Dried Morel",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                50,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Purple Mushroom");
        numberOfIngredients.put("Purple Mushroom", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Purple Mushroom");

        produce = new Produce(
                "Dried Purple Mushroom",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                50,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Red Mushroom");
        numberOfIngredients.put("Red Mushroom", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Red Mushroom");

        produce = new Produce(
                "Dried Red Mushroom",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                50,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);

        // Dried Fruits

        ingredients.add("Ancient Fruit");
        numberOfIngredients.put("Ancient Fruit", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Ancient Fruit");

        produce = new Produce(
                "Dried Ancient Fruit",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Apple");
        numberOfIngredients.put("Apple", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Apple");

        produce = new Produce(
                "Dried Apple",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Apricot");
        numberOfIngredients.put("Apricot", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Apricot");

        produce = new Produce(
                "Dried Apricot",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Banana");
        numberOfIngredients.put("Banana", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Banana");

        produce = new Produce(
                "Dried Banana",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Blackberry");
        numberOfIngredients.put("Blackberry", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Blackberry");

        produce = new Produce(
                "Dried Blackberry",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Blueberry");
        numberOfIngredients.put("Blueberry", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Blueberry");

        produce = new Produce(
                "Dried Blueberry",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Cactus Fruit");
        numberOfIngredients.put("Cactus Fruit", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Cactus Fruit");

        produce = new Produce(
                "Dried Cactus Fruit",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Cherry");
        numberOfIngredients.put("Cherry", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Cherry");

        produce = new Produce(
                "Dried Cherry",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Coconut");
        numberOfIngredients.put("Coconut", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Cocomut");

        produce = new Produce(
                "Dried Coconut",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Cranberries");
        numberOfIngredients.put("Cranberries", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Cranberries");

        produce = new Produce(
                "Dried Cranberries",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Crystal Fruit");
        numberOfIngredients.put("Crystal Fruit", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Crystal Fruit");

        produce = new Produce(
                "Dried Crystal Fruit",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Hot Pepper");
        numberOfIngredients.put("Hot Pepper", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Hot Pepper");

        produce = new Produce(
                "Dried Hot Pepper",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Mango");
        numberOfIngredients.put("Mango", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Mango");

        produce = new Produce(
                "Dried Mango",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Melon");
        numberOfIngredients.put("Melon", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Melon");

        produce = new Produce(
                "Dried Melon",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Orange");
        numberOfIngredients.put("Orange", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Orange");

        produce = new Produce(
                "Dried Orange",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Peach");
        numberOfIngredients.put("Peach", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Peach");

        produce = new Produce(
                "Dried Peach",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Pineapple");
        numberOfIngredients.put("Pineapple", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Pineapple");

        produce = new Produce(
                "Dried Pineapple",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("pomegranate");
        numberOfIngredients.put("Pomegranate", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Pomegranate");

        produce = new Produce(
                "Dried pomegranate",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Powdermelon");
        numberOfIngredients.put("Powdermelon", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Powdermelon");

        produce = new Produce(
                "Dried Powdermelon",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);

        ingredients.add("Qi Fruit");
        numberOfIngredients.put("Qi Fruit", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Qi Fruit");

        produce = new Produce(
                "Dried Qi Fruit",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Rhubarb");
        numberOfIngredients.put("Rhubarb", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Rhubarb");

        produce = new Produce(
                "Dried Rhubarb",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Salmonberry");
        numberOfIngredients.put("Salmonberry", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Salmonberry");

        produce = new Produce(
                "Dried Salmonberry",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Spice Berry");
        numberOfIngredients.put("Spice Berry", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Spice Berry");

        produce = new Produce(
                "Dried Spice Berry",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Starfruit");
        numberOfIngredients.put("Starfruit", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Starfruit");

        produce = new Produce(
                "Dried Starfruit",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Strawberry");
        numberOfIngredients.put("Strawberry", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Strawberry");

        produce = new Produce(
                "Dried Strawberry",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Wild Plum");
        numberOfIngredients.put("Wild Plum", numberOfFruitsToDry);
        fruit = Fruit.fruits.get("Wild Plum");

        produce = new Produce(
                "Dried Wild Plum",
                (int)(7.5 * fruit.getBaseSellPrice()),
                true,
                75,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);


        ingredients.add("Grapes");
        numberOfIngredients.put("Grapes", numberOfFruitsToDry);

        produce = new Produce(
                "Raisins",
                600,
                true,
                125,
                0,
                1,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);

        // Coal

        ingredients.add("Wood");
        numberOfIngredients.put("Wood", 10);

        produce = new Produce(
                "Coal",
                50,
                false,
                0,
                1,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);

        //Cloth

        ingredients.add("Wool");
        numberOfIngredients.put("Wool", 1);

        produce = new Produce(
                "Cloth",
                470,
                false,
                0,
                4,
                0,
                ingredients,
                numberOfIngredients
        );
        produceTypes.put(produce.getName(), produce);

        // Mayonnaise

        //Oil

        // Jelly And Pickles

        // Smoked Fish

        // Material Bar

    }

}
