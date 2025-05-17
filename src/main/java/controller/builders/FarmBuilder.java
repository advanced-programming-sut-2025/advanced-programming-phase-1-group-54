package controller.builders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.enums.Symbol;
import model.items.Material;
import model.map.Cabin;
import model.map.GreenHouse;
import model.lives.Player;
import model.enums.Feature;
import model.map.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FarmBuilder {
    private static final int NUMBER_OF_FORAGING_MATERIAL = 2;
    private static final int NUMBER_OF_FORAGING_CROP = 10;

    private static Location location;

    private static Location cabinLocation;
    private static Location greenHouseLocation;
    private static Area[] lakeAreas;
    private static Area quarryArea;

    private static Tile[][] tiles = new Tile[Farm.getNumberOfRows()][Farm.getNumberOfColumns()];

    public static void reset() {
        location = null;
        cabinLocation = null;
        greenHouseLocation = null;
        lakeAreas = null;
        quarryArea = null;

        tiles = new Tile[Farm.getNumberOfRows()][Farm.getNumberOfColumns()];
    }

    public static void setLocation(Location location) {
        FarmBuilder.location = location;
    }

    public static void setCabinLocation(Location cabinLocation) {
        FarmBuilder.cabinLocation = cabinLocation;
    }

    public static void setGreenHouseLocation(Location greenHouseLocation) {
        FarmBuilder.greenHouseLocation = greenHouseLocation;
    }

    public static void setLakeAreas(Area[] lakeAreas) {
        FarmBuilder.lakeAreas = lakeAreas;
    }

    public static void setQuarryArea(Area quarryArea) {
        FarmBuilder.quarryArea = quarryArea;
    }


    public static void setFarmNumber(int index) {
        try (InputStream inputStream = FarmBuilder.class.getClassLoader().getResourceAsStream("farms.json"); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonArray().get(index).getAsJsonObject();
            setCabinLocation(gson.fromJson(jsonObject.get("cabinLocation"), Location.class));
            setGreenHouseLocation(gson.fromJson(jsonObject.get("greenHouseLocation"), Location.class));
            setLakeAreas(gson.fromJson(jsonObject.get("lakeAreas"), Area[].class));
            setQuarryArea(gson.fromJson(jsonObject.get("quarryArea"), Area.class));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getNumberOfFarms() {
        try (InputStream inputStream = FarmBuilder.class.getClassLoader().getResourceAsStream("farms.json"); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return JsonParser.parseReader(reader).getAsJsonArray().size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Cabin buildCabin() {
        Cabin cabin = new Cabin(cabinLocation);
        for (int x = 0; x < cabin.getNumberOfRows(); x++) {
            for (int y = 0; y < cabin.getNumberOfColumns(); y++) {
                Location location = cabinLocation.add(new Location(x, y));
                tiles[location.row()][location.column()].setThingOnTile(cabin);
            }
        }
        return cabin;
    }

    private static GreenHouse buildGreenHouse() {
        GreenHouse greenHouse = new GreenHouse(greenHouseLocation);
        for (int x = 0; x < greenHouse.getNumberOfRows(); x++) {
            for (int y = 0; y < greenHouse.getNumberOfColumns(); y++) {
                Location location = greenHouseLocation.add(new Location(x, y));
                tiles[location.row()][location.column()].setThingOnTile(greenHouse);
            }
        }
        return greenHouse;
    }

    private static GenericWall[] buildLakes() {
        GenericWall[] lakes = new GenericWall[lakeAreas.length];
        for (int t = 0; t < lakeAreas.length; t++) {
            lakes[t] = new GenericWall(lakeAreas[t], Symbol.LAKE);
            for (int row = lakeAreas[t].upperLeftLocation().row(); row <= lakeAreas[t].lowerRightLocation().row(); row++) {
                for (int column = lakeAreas[t].upperLeftLocation().column(); column <= lakeAreas[t].lowerRightLocation().column(); column++) {
                    tiles[row][column].setThingOnTile(lakes[t]);
                    tiles[row][column].addFeature(Feature.WATER);
                }
            }
        }
        return lakes;
    }


    private static Quarry buildQuarry() {
        Quarry quarry = new Quarry(quarryArea);
        for (int row = quarryArea.upperLeftLocation().row(); row <= quarryArea.lowerRightLocation().row(); row++) {
            for (int column = quarryArea.upperLeftLocation().column(); column <= quarryArea.lowerRightLocation().column(); column++) {
                tiles[row][column].setThingOnTile(quarry);
            }
        }

        for (int i = 1; i <= NUMBER_OF_FORAGING_MATERIAL; i++) {
            quarry.foragingMaterial();
        }

        return quarry;
    }

    public static void placeRandomStuff(Farm farm) {
        for (int i = 1; i <= NUMBER_OF_FORAGING_CROP; i++) {
            farm.foragingCrop();
        }

        for (int i = 0; i < Farm.getNumberOfRows(); i++) {
            for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                if ((int) (Math.random() * 100) < 5) {
                    tiles[i][j].setThingOnTile(Material.getMaterial("Wood"));
                }
            }
        }
    }

    public static Farm getResult() {
        for (int i = 0; i < Farm.getNumberOfRows(); i++) {
            for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                tiles[i][j] = new Tile();
            }
        }

        Cabin cabin = buildCabin();
        GreenHouse greenHouse = buildGreenHouse();
        GenericWall[] lakes = buildLakes();
        Quarry quarry = buildQuarry();

        Farm farm = new Farm(location, greenHouse, cabin, quarry, lakes, new Map(Farm.getNumberOfRows(), Farm.getNumberOfColumns(), tiles));
        placeRandomStuff(farm);

        FarmBuilder.reset();
        return farm;
    }
}
