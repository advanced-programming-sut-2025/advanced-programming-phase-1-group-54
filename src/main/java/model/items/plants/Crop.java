package model.items.plants;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.enums.Season;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;

public class Crop extends Plant {
    public static HashMap<String,Crop> crops;

    static{
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("crops.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String,Crop>>(){}.getType();
        crops = gson.fromJson(file,type);
        System.out.println(crops.size());
    }

    private final boolean oneTime;
    private final boolean canBecomeGiant;

    public Crop(String name, String source, String fruit, int[] stages, int regrowthTime, Season[] seasons,
                boolean oneTime, boolean canBecomeGiant) {
        super(name, source, fruit, stages, regrowthTime, seasons);
        this.oneTime = oneTime;
        this.canBecomeGiant = canBecomeGiant;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public boolean isCanBecomeGiant() {
        return canBecomeGiant;
    }

}
