package model.map;

import model.Building.Building;
import model.Placeable;
import model.enums.Symbol;
import model.enums.Feature;

import java.util.ArrayList;

public class Tile {
    private Symbol symbol = Symbol.EMPTY;

    private Placeable thingOnTile;
    private ArrayList<Feature> features;

    public boolean isWalkable() {
        if (thingOnTile == null)
            return true;

        if (thingOnTile instanceof Building building)
            return building.canEnter();

        return false;
    }

    public Placeable getThingOnTile() {
        return thingOnTile;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setThingOnTile(Placeable thingOnTile) {
        this.thingOnTile = thingOnTile;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol.toString();
    }
}
