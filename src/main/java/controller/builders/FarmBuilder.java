package controller.builders;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Building.Cabin;
import model.Building.GreenHouse;
import model.map.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FarmBuilder {
    private static Location cabinLocation;
    private static Location greenHouseLocation;
    private static Location lakeUpperLeftLocation;
    private static Location lakeLowerRightLocation;
    private static Location quarryUpperLeftLocation;
    private static Location quarryLowerRightLocation;

    public static void reset() {
        cabinLocation = null;
        greenHouseLocation = null;
        lakeUpperLeftLocation = null;
        lakeLowerRightLocation = null;
        quarryUpperLeftLocation = null;
        quarryLowerRightLocation = null;
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

        Cabin cabin = new Cabin();
        for (int x = -1; x <= 4; x++) {
            for (int y = -1; y <= 4; y++) {
                boolean onBorder = x == -1 || y == -1 || x == 4 || y == 4;

                Location location = new Location(cabinLocation.row() + x, cabinLocation.column() + y);
                if (onBorder)
                    tiles[location.row() - 1][location.column() - 1].setThingOnTile(new Wall());
                else
                    tiles[location.row() - 1][location.column() - 1].setThingOnTile(cabin);
            }
        }

        GreenHouse greenHouse = new GreenHouse();
        for (int x = -1; x <= 5; x++) {
            for (int y = -1; y <= 6; y++) {
                boolean onBorder = x == -1 || y == -1 || x == 5 || y == 6;
                Location location = new Location(greenHouseLocation.row() + x, greenHouseLocation.column() + y);

                if (onBorder)
                    tiles[location.row() - 1][location.column() - 1].setThingOnTile(new Wall());

                else
                    tiles[location.row() - 1][location.column() - 1].setThingOnTile(greenHouse);
            }
        }

        Lake lake = new Lake();
        for (int row = lakeUpperLeftLocation.row() - 1; row <= lakeLowerRightLocation.row(); row++) {
            for (int column = lakeUpperLeftLocation.column(); column <= lakeLowerRightLocation.column(); column++) {
                tiles[row - 1][column - 1].setThingOnTile(lake);
            }
        }

        Quarry quarry = new Quarry();
        for (int row = quarryUpperLeftLocation.row(); row <= quarryLowerRightLocation.row(); row++) {
            for (int column = quarryUpperLeftLocation.column(); column <= quarryLowerRightLocation.column(); column++) {
                tiles[row - 1][column - 1].setThingOnTile(quarry);
            }
        }

        // TODO tree, foraging, random stuff

        Farm farm = new Farm(tiles);
        FarmBuilder.reset();
        return farm;
    }
}
