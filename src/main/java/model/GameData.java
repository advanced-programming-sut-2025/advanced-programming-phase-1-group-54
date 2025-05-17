package model;

import java.util.Arrays;
import java.util.Objects;

public record GameData(String[] playerNames, int[] playerFarms){
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GameData(String[] names, int[] farms))) return false;
        return Objects.deepEquals(playerFarms, farms) && Objects.deepEquals(playerNames, names);
    }
}
