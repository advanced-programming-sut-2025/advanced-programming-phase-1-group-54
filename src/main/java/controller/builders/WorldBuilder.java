package controller.builders;

import model.map.*;

public class WorldBuilder {
    static final Location[] farmLocation = {
            new Location(0, 0),
            new Location(World.getNumberOfRows() - Farm.getNumberOfRows(), 0),
            new Location(World.getNumberOfRows() - Farm.getNumberOfRows(), World.getNumberOfColumns() - Farm.getNumberOfColumns()),
            new Location(0, World.getNumberOfColumns() - Farm.getNumberOfColumns())
    };

    private static Farm[] playerFarms = new Farm[4];

    public static void reset() {
        playerFarms = new Farm[4];
    }

    public static void setPlayerFarms(Farm[] playerFarms) {
        System.arraycopy(playerFarms, 0, WorldBuilder.playerFarms, 0, playerFarms.length);
    }

    public static World getResult() {
        Tile[][] tiles = new Tile[World.getNumberOfRows()][World.getNumberOfColumns()];

        // put farms in corners\

        for (int t = 0; t < 4; t++) {
            if (playerFarms[t] != null) {
                for (int i = 0; i < Farm.getNumberOfRows(); i++) {
                    for (int j = 0; j < Farm.getNumberOfColumns(); j++) {
                        Location location = new Location(i, j);
                        tiles[farmLocation[t].row() + i][farmLocation[t].column() + j] = playerFarms[t].getTileAt(location);
                    }
                }
            }
        }

        // All Other Tiles
        for (int i = 0; i < World.getNumberOfRows(); i++) {
            for (int j = 0; j < World.getNumberOfColumns(); j++) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = new Tile();
                }
                tiles[i][j].setLocation(new Location(i, j));
            }
        }

        // TODO World is empty, add village

        World world = new World(playerFarms, new Map(World.getNumberOfRows(), World.getNumberOfColumns(), tiles));
        WorldBuilder.reset();
        return world;
    }
}
