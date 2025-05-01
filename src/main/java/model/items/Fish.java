package model.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.enums.ProduceQuality;
import model.enums.Season;
import model.items.plants.Tree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Fish extends Item {
    static final HashMap<String ,Fish> fishes;
    static final HashMap<Season,ArrayList<Fish>> seasonFishes ;
    static final HashMap<Season,ArrayList<Fish>> seasonLegendaryFishes ;

    static {
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("fishes.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, Fish>>(){}.getType();
        fishes = gson.fromJson(file,type);

        try {
            file = new FileReader("seasonFishes.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        type = new TypeToken<HashMap<Season,ArrayList<Fish>>>(){}.getType();
        seasonFishes = gson.fromJson(file,type);

        try {
            file = new FileReader("seasonLegendaryFishes.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        type = new TypeToken<HashMap<Season,ArrayList<Fish>>>(){}.getType();
        seasonLegendaryFishes = gson.fromJson(file,type);

    }

    private final int baseSellPrice;
    private ProduceQuality quality;

    public Fish(String name, int baseSellPrice) {
        super(name);
        this.baseSellPrice = baseSellPrice;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public static void writeToJson(){

        HashMap<String,Fish> fishesType = new HashMap<String,Fish>() ;

        Fish fish;

        HashMap<Season,ArrayList<Fish>> seasonFishesType  = new HashMap<Season,ArrayList<Fish>>() ;


        ArrayList<Fish> springFishes = new ArrayList<Fish>() ;

        fish = new Fish("Flounder",100);
        fishesType.put("Flounder",fish);
        springFishes.add(fish);

        fish = new Fish("Lionfish",100);
        fishesType.put("Lionfish",fish);
        springFishes.add(fish);

        fish = new Fish("Herring",30);
        fishesType.put("Herring",fish);
        springFishes.add(fish);

        fish = new Fish("Ghostfish",45);
        fishesType.put("Ghostfish",fish);
        springFishes.add(fish);

        seasonFishesType.put(Season.SPRING,springFishes);


        ArrayList<Fish> summerFishes = new ArrayList<Fish>() ;

        fish = new Fish("Tilapia",75);
        fishesType.put("Tilapia",fish);
        summerFishes.add(fish);

        fish = new Fish("Dorado",100);
        fishesType.put("Dorado",fish);
        summerFishes.add(fish);

        fish = new Fish("Sunfish",30);
        fishesType.put("Sunfish",fish);
        summerFishes.add(fish);

        fish = new Fish("Rainbow Trout",65);
        fishesType.put("Rainbow Trout",fish);
        summerFishes.add(fish);

        seasonFishesType.put(Season.SUMMER,summerFishes );


        ArrayList<Fish> fallFishes = new ArrayList<Fish>() ;

        fish = new Fish("Salmon",75);
        fishesType.put("Salmon",fish);
        fallFishes.add(fish);

        fish = new Fish("Sardine",40);
        fishesType.put("Sardine",fish);
        fallFishes.add(fish);

        fish = new Fish("Shad",60);
        fishesType.put("Shad",fish);
        fallFishes.add(fish);

        fish = new Fish("Blue Discus",120);
        fishesType.put("Blue Discus",fish);
        fallFishes.add(fish);

        seasonFishesType.put(Season.FALL,fallFishes );


        ArrayList<Fish> winterFishes = new ArrayList<Fish>() ;

        fish = new Fish("Midnight Carp",150);
        fishesType.put("Midnight Carp",fish);
        winterFishes.add(fish);

        fish = new Fish("Squid",80);
        fishesType.put("Squid",fish);
        winterFishes.add(fish);

        fish = new Fish("Tuna",100);
        fishesType.put("Tuna",fish);
        winterFishes.add(fish);

        fish = new Fish("Perch",55);
        fishesType.put("Perch",fish);
        winterFishes.add(fish);

        seasonFishesType.put(Season.WINTER,winterFishes );




        HashMap<Season,ArrayList<Fish>> seasonLegendaryFishesType = new HashMap<Season,ArrayList<Fish>>();

        ArrayList<Fish> springLegendaryFishes = new ArrayList<Fish>();

        fish = new Fish("Legend",5000);
        fishesType.put("Legend",fish);
        springLegendaryFishes.add(fish);

        seasonLegendaryFishesType.put(Season.SPRING,springLegendaryFishes );


        ArrayList<Fish> summerLegendaryFishes = new ArrayList<Fish>();

        fish = new Fish("Crimsonfish",1500);
        fishesType.put("Crimsonfish",fish);
        summerLegendaryFishes.add(fish);

        seasonLegendaryFishesType.put(Season.SUMMER,summerLegendaryFishes );


        ArrayList<Fish> fallLegendaryFishes = new ArrayList<Fish>();

        fish = new Fish("Angler",900);
        fishesType.put("Angler",fish);
        fallLegendaryFishes.add(fish);

        seasonLegendaryFishesType.put(Season.FALL,fallLegendaryFishes );


        ArrayList<Fish> winterLegendaryFishes = new ArrayList<Fish>();

        fish = new Fish("Glacierfish",1000);
        fishesType.put("Glacierfish",fish);
        winterLegendaryFishes.add(fish);

        seasonLegendaryFishesType.put(Season.WINTER,winterLegendaryFishes );



        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("fishes.json")){
            gson.toJson(fishesType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter seasonFile = new FileWriter("seasonFishes.json")){
            gson.toJson(seasonFishesType, seasonFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter seasonLegendaryFile = new FileWriter("seasonLegendaryFishes.json")){
            gson.toJson(seasonLegendaryFishesType, seasonLegendaryFile );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
