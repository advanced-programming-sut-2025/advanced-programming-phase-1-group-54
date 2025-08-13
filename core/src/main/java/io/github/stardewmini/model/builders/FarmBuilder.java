package io.github.stardewmini.model.builders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.stardewmini.model.DateTime;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.enums.Feature;
import io.github.stardewmini.model.enums.Symbol;
import io.github.stardewmini.model.items.Material;
import io.github.stardewmini.model.map.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FarmBuilder {
    private static FarmBuilder instance;

    private FarmBuilder() {
    }

    public static FarmBuilder getInstance() {
        if (instance == null)
            instance = new FarmBuilder();
        return instance;
    }

    private final int NUMBER_OF_FORAGING_MATERIAL = 2;

    private Location location;

    private Location cabinLocation;
    private Location greenHouseLocation;
    private Area[] lakeAreas;
    private Area quarryArea;
    private DateTime dateTime;

    private Tile[][] tiles = new Tile[Farm.getNumberOfRows()][Farm.getNumberOfColumns()];

    public void reset() {
        location = null;
        cabinLocation = null;
        greenHouseLocation = null;
        lakeAreas = null;
        quarryArea = null;
        dateTime = null;
        tiles = new Tile[Farm.getNumberOfRows()][Farm.getNumberOfColumns()];
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCabinLocation(Location cabinLocation) {
        this.cabinLocation = cabinLocation;
    }

    public void setGreenHouseLocation(Location greenHouseLocation) {
        this.greenHouseLocation = greenHouseLocation;
    }

    public void setLakeAreas(Area[] lakeAreas) {
        this.lakeAreas = lakeAreas;
    }

    public void setQuarryArea(Area quarryArea) {
        this.quarryArea = quarryArea;
    }


    public void setFarmNumber(int number) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("farms.json"); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
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

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getNumberOfFarms() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("farms.json"); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return JsonParser.parseReader(reader).getAsJsonArray().size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Cabin buildCabin() {
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        Cabin cabin = new Cabin(cabinLocation);
        for (int x = 0; x < cabin.getNumberOfRows(); x++) {
            for (int y = 0; y < cabin.getNumberOfColumns(); y++) {
                Location location = cabinLocation.add(new Location(x, y));
                tiles[location.row()][location.column()].setThingOnTile(cabin);
            }
        }
        return cabin;
    }

    private GreenHouse buildGreenHouse() {
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        GreenHouse greenHouse = new GreenHouse(greenHouseLocation);
        for (int x = 0; x < greenHouse.getNumberOfRows(); x++) {
            for (int y = 0; y < greenHouse.getNumberOfColumns(); y++) {
                Location locationInGreenHouse = new Location(x, y);
                dateTime.addDailyUpdateListener(greenHouse.getTileAt(locationInGreenHouse));

                Location location = greenHouseLocation.add(locationInGreenHouse);
                tiles[location.row()][location.column()].setThingOnTile(greenHouse);
            }
        }
        return greenHouse;
    }

    private GenericWall[] buildLakes() {
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        GenericWall[] lakes = new GenericWall[lakeAreas.length];
        for (int t = 0; t < lakeAreas.length; t++) {
            lakes[t] = new GenericWall(lakeAreas[t], GameAssetManager.getInstance().getBuilding("lake"));
            for (int row = lakeAreas[t].upperLeftLocation().row(); row <= lakeAreas[t].lowerRightLocation().row(); row++) {
                for (int column = lakeAreas[t].upperLeftLocation().column(); column <= lakeAreas[t].lowerRightLocation().column(); column++) {
                    tiles[row][column].setThingOnTile(lakes[t]);
                    tiles[row][column].addFeature(Feature.WATER);
                    tiles[row][column].getTop().getSprite().setRegion(gameAssetManager.getBuilding("lake"));
                }
            }
        }
        return lakes;
    }


    private Quarry buildQuarry() {
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        Quarry quarry = new Quarry(quarryArea);
        for (int row = quarryArea.upperLeftLocation().row(); row <= quarryArea.lowerRightLocation().row(); row++) {
            for (int column = quarryArea.upperLeftLocation().column(); column <= quarryArea.lowerRightLocation().column(); column++) {
                tiles[row][column].setThingOnTile(quarry);
                tiles[row][column].getTop().getSprite().setRegion(gameAssetManager.getBuilding("Quarry Floor"));
            }
        }

        for (int i = 1; i <= NUMBER_OF_FORAGING_MATERIAL; i++) {
            quarry.foragingMaterial();
        }

        dateTime.addDailyUpdateListener(quarry);
        return quarry;
    }

    public void placeRandomStuff(Farm farm) {
        for (int i = 0; i < Farm.getNumberOfRows(); i++) {
            for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                if ((int) (Math.random() * 100) < 5) {
                    if (tiles[i][j].getThingOnTile() == null)
                        tiles[i][j].setThingOnTile(Material.getMaterial("Wood"));
                }
            }
        }
        for (int i = 0; i < Farm.getNumberOfRows(); i++) {
            for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                if ((int) (Math.random() * 100) < 5) {
                    if (tiles[i][j].getThingOnTile() == null)
                        tiles[i][j].setThingOnTile(Material.getMaterial("Stone"));
                }
            }
        }
    }

    public Farm getResult() {
        for (int i = 0; i < Farm.getNumberOfRows(); i++) {
            for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                tiles[i][j] = new Tile(new Location(i, j));
                dateTime.addDailyUpdateListener(tiles[i][j]);
            }
        }

        Cabin cabin = buildCabin();
        GreenHouse greenHouse = buildGreenHouse();
        GenericWall[] lakes = buildLakes();
        Quarry quarry = buildQuarry();

        Farm farm = new Farm(location, greenHouse, cabin, quarry, lakes, new Map(Farm.getNumberOfRows(), Farm.getNumberOfColumns(), tiles));
        placeRandomStuff(farm);
        dateTime.addDailyUpdateListener(farm);

        this.reset();
        return farm;
    }

}
