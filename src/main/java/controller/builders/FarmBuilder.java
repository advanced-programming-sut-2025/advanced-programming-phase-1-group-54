package controller.builders;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Building.Cabin;
import model.Building.GreenHouse;
import model.alive.Player;
import model.enums.Feature;
import model.enums.Symbol;
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
    private static Location lakeUpperLeftLocation;
    private static Location lakeLowerRightLocation;
    private static Location quarryUpperLeftLocation;
    private static Location quarryLowerRightLocation;

    public static void reset() {
        location = null;
        owner = null;
        cabinLocation = null;
        greenHouseLocation = null;
        lakeUpperLeftLocation = null;
        lakeLowerRightLocation = null;
        quarryUpperLeftLocation = null;
        quarryLowerRightLocation = null;
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

    public static void setLakeUpperLeftLocation(Location lakeUpperLeftLocation) {
        FarmBuilder.lakeUpperLeftLocation = lakeUpperLeftLocation;
    }

    public static void setLakeLowerRightLocation(Location lakeLowerRightLocation) {
        FarmBuilder.lakeLowerRightLocation = lakeLowerRightLocation;
    }

    public static void setQuarryUpperLeftLocation(Location quarryUpperLeftLocation) {
        FarmBuilder.quarryUpperLeftLocation = quarryUpperLeftLocation;
    }

    public static void setQuarryLowerRightLocation(Location quarryLowerRightLocation) {
        FarmBuilder.quarryLowerRightLocation = quarryLowerRightLocation;
    }

    public static void setFarmNumber(int number) {
        try (InputStream inputStream = FarmBuilder.class.getClassLoader().getResourceAsStream("farms.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Gson gson = new Gson();

            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonArray().get(number - 1).getAsJsonObject();
            setCabinLocation(gson.fromJson(jsonObject.get("cabinLocation"), Location.class));
            setGreenHouseLocation(gson.fromJson(jsonObject.get("greenHouseLocation"), Location.class));
            setLakeUpperLeftLocation(gson.fromJson(jsonObject.get("lakeUpperLeftLocation"), Location.class));
            setLakeLowerRightLocation(gson.fromJson(jsonObject.get("lakeLowerRightLocation"), Location.class));
            setQuarryUpperLeftLocation(gson.fromJson(jsonObject.get("quarryUpperLeftLocation"), Location.class));
            setQuarryLowerRightLocation(gson.fromJson(jsonObject.get("quarryLowerRightLocation"), Location.class));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getNumberOfFarms() {
        try (InputStream inputStream = FarmBuilder.class.getClassLoader().getResourceAsStream("farms.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return JsonParser.parseReader(reader).getAsJsonArray().size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Farm getResult() {
        Tile[][] tiles = new Tile[Farm.getNumberOfRows()][Farm.getNumberOfColumns()];
        for (int i = 0; i < Farm.getNumberOfRows(); i++) {
            for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                tiles[i][j] = new Tile();
            }
        }

        Cabin cabin = new Cabin(cabinLocation);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {

                Location location = new Location(cabinLocation.row() + x, cabinLocation.column() + y);
                tiles[location.row()][location.column()].setThingOnTile(cabin);
            }
        }

        GreenHouse greenHouse = new GreenHouse(greenHouseLocation);
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 6; y++) {
                Location location = new Location(greenHouseLocation.row() + x, greenHouseLocation.column() + y);
                tiles[location.row()][location.column()].setThingOnTile(greenHouse);

                greenHouse.getTileAt(new Location(x, y)).addFeature(Feature.PROTECTED);
            }
        }

        Lake lake = new Lake(lakeUpperLeftLocation, lakeLowerRightLocation);
        for (int row = lakeUpperLeftLocation.row(); row <= lakeLowerRightLocation.row(); row++) {
            for (int column = lakeUpperLeftLocation.column(); column <= lakeLowerRightLocation.column(); column++) {
                tiles[row][column].setThingOnTile(lake);
            }
        }

        Quarry quarry = new Quarry(quarryUpperLeftLocation, quarryLowerRightLocation);
        for (int row = quarryUpperLeftLocation.row(); row <= quarryLowerRightLocation.row(); row++) {
            for (int column = quarryUpperLeftLocation.column(); column <= quarryLowerRightLocation.column(); column++) {
                tiles[row][column].setThingOnTile(quarry);
            }
        }

        // TODO tree, foraging, random stuff

        Farm farm = new Farm(owner, location, greenHouse, cabin, quarry, lake, new Map(Farm.getNumberOfRows(), Farm.getNumberOfColumns(), tiles));
        FarmBuilder.reset();
        return farm;
    }
}
