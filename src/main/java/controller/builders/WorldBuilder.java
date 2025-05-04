package controller.builders;

import model.map.Farm;
import model.map.Location;
import model.map.Tile;
import model.map.World;

public class WorldBuilder {
    private static Farm[] playerFarms = new Farm[4];

    public static void reset() {
        playerFarms = new Farm[4];
    }

    public static void setPlayerFarms(Farm[] playerFarms) {
        System.arraycopy(playerFarms, 0, WorldBuilder.playerFarms, 0, playerFarms.length);
    }

    public static World getResult() {
        Tile[][] tiles = new Tile[World.getNumberOfRows()][World.getNumberOfRows()];

        // Upper Left Farm
        if (playerFarms[0] != null) {
            for (int i = 1; i <= Farm.getNumberOfRows(); i++) {
                for (int j = 1; j <= Farm.getNumberOfColumns(); j++) {
                    Location location = new Location(i, j);
                    tiles[i - 1][j - 1] = playerFarms[0].getTileAt(location);
                }
            }
        }

        // Lower Left Farm
        if (playerFarms[2] != null) {
            for (int i = World.getNumberOfRows() - Farm.getNumberOfRows() + 1; i <= World.getNumberOfRows(); i++) {
                for (int j = 1; j <= Farm.getNumberOfColumns(); j++) {
                    Location location = new Location(i, j);
                    tiles[i - 1][j - 1] = playerFarms[2].getTileAt(location);
                }
            }
        }

        // Upper Right Farm
        if (playerFarms[3] != null) {
            for (int i = 1; i <= Farm.getNumberOfRows(); i++) {
                for (int j = World.getNumberOfColumns() - Farm.getNumberOfColumns() + 1; j <= World.getNumberOfColumns(); j++) {
                    Location location = new Location(i, j);
                    tiles[i - 1][j - 1] = playerFarms[3].getTileAt(location);
                }
            }
        }

        // Lower Right Farm
        if (playerFarms[1] == null) {
            for (int i = World.getNumberOfRows() - Farm.getNumberOfRows() + 1; i <= World.getNumberOfRows(); i++) {
                for (int j = World.getNumberOfColumns() - Farm.getNumberOfColumns() + 1; j <= World.getNumberOfColumns(); j++) {
                    Location location = new Location(i, j);
                    tiles[i - 1][j - 1] = playerFarms[1].getTileAt(location);
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
