package io.github.stardewmini.common.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record GameData(String[] playerNames, int[] playerFarms, long seed){
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GameData gameData)) return false;
        return seed == gameData.seed && Objects.deepEquals(playerFarms, gameData.playerFarms) && Objects.deepEquals(playerNames, gameData.playerNames);
    }

}
