package controller.builders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controller.Game.PlantsController;
import model.map.Cabin;
import model.map.GreenHouse;
import model.alive.Player;
import model.enums.Feature;
import model.map.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FarmBuilder {
    private static Location location;
    private static Player owner;

    private static Location cabinLocation;
    private static Location greenHouseLocation;
    private static Area[] lakeAreas;
    private static Area quarryArea;

    private static Tile[][] tiles = new Tile[Farm.getNumberOfRows()][Farm.getNumberOfColumns()];

    public static void reset() {
        location = null;
        owner = null;
        cabinLocation = null;
        greenHouseLocation = null;
        lakeAreas = null;
        quarryArea = null;

        tiles = new Tile[Farm.getNumberOfRows()][Farm.getNumberOfColumns()];
    }

    public static void setLocation(Location location) {
        FarmBuilder.location = location;
    }

    public static void setOwner(Player owner) {
        FarmBuilder.owner = owner;
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


    public static void setFarmNumber(int number) {
        try (InputStream inputStream = FarmBuilder.class.getClassLoader().getResourceAsStream("farms.json"); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonArray().get(number - 1).getAsJsonObject();
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
        for (int x = 0; x < Cabin.getNumberOfRows(); x++) {
            for (int y = 0; y < Cabin.getNumberOfColumns(); y++) {
                Location location = cabinLocation.add(new Location(x, y));
                tiles[location.row()][location.column()].setThingOnTile(cabin);
            }
        }
        return cabin;
    }

    private static GreenHouse buildGreenHouse() {
        GreenHouse greenHouse = new GreenHouse(greenHouseLocation);
        for (int x = 0; x < GreenHouse.getNumberOfRows(); x++) {
            for (int y = 0; y < GreenHouse.getNumberOfColumns(); y++) {
                Location location = greenHouseLocation.add(new Location(x, y));
                tiles[location.row()][location.column()].setThingOnTile(greenHouse);
            }
        }
        return greenHouse;
    }

    private static Lake[] buildLakes() {
        Lake[] lakes = new Lake[lakeAreas.length];
        for (int t = 0; t < lakeAreas.length; t++) {
            lakes[t] = new Lake(lakeAreas[t]);
            for (int row = lakeAreas[t].upperLeftLocation().row(); row <= lakeAreas[t].lowerRightLocation().row(); row++) {
                for (int column = lakeAreas[t].upperLeftLocation().column(); column <= lakeAreas[t].lowerRightLocation().column(); column++) {
                    tiles[row][column].setThingOnTile(lakes[t]);
                    tiles[row][column].addFeature(Feature.WATERED);
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

        return quarry;
    }

    public static Farm getResult() {
        for (int i = 0; i < Farm.getNumberOfRows(); i++) {
            for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                tiles[i][j] = new Tile();
            }
        }

        Cabin cabin = buildCabin();
        GreenHouse greenHouse = buildGreenHouse();
        Lake[] lakes = buildLakes();
        Quarry quarry = buildQuarry();

        Farm farm = new Farm(location, greenHouse, cabin, quarry, lakes, new Map(Farm.getNumberOfRows(), Farm.getNumberOfColumns(), tiles));
        FarmBuilder.reset();
        return farm;
    }
}
