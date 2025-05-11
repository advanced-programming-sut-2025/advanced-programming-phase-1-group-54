package controller.builders;

import model.map.Farm;
import model.map.Location;
import model.map.Tile;
import model.map.World;

public class WorldBuilder {
    static final Location[] farmLocation = {
            new Location(1, 1),
            new Location(World.getNumberOfRows() - Farm.getNumberOfRows() + 1, 1),
            new Location(World.getNumberOfRows() - Farm.getNumberOfRows() + 1, World.getNumberOfColumns() - Farm.getNumberOfColumns() + 1),
            new Location(1, World.getNumberOfColumns() - Farm.getNumberOfColumns() + 1)
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
                        Location location = new Location(1 + i, 1 + j);
                        tiles[farmLocation[t].row() + i - 1][farmLocation[t].column() + j - 1] = playerFarms[t].getTileAt(location);
                    }
                }
            }
        }

        // All Other Tiles
        for (int i = 1; i <= World.getNumberOfRows(); i++) {
            for (int j = 1; j <= World.getNumberOfColumns(); j++) {
                if (tiles[i - 1][j - 1] == null) {
                    tiles[i - 1][j - 1] = new Tile();
                }
            }
        }

        // TODO World is empty, add village

        World world = new World(tiles);
        WorldBuilder.reset();
        return world;
    }
}
