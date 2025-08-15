package io.github.stardewmini.common.model;

import java.util.Arrays;
import java.util.Objects;

public record GameData(String[] playerNames, int[] playerFarms, int seed){
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GameData gameData)) return false;
        return seed == gameData.seed && Objects.deepEquals(playerFarms, gameData.playerFarms) && Objects.deepEquals(playerNames, gameData.playerNames);
    }

}
