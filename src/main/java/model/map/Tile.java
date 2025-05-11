package model.map;

import model.Placeable;
import model.Walkable;
import model.enums.Symbol;
import model.enums.Feature;

import java.util.ArrayList;

public class Tile {
    private Symbol symbol = Symbol.EMPTY;
    private final Character owner;

    private Placeable thingOnTile;
    private ArrayList<Feature> features;

    public Tile() {
        owner = null;
    }

    public Tile(Character owner) {
        this.owner = owner;
    }

    public Character getOwner() {
        return owner;
    }

    public boolean isWalkable() {
        return thingOnTile == null || thingOnTile instanceof Walkable;
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

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }

    @Override
    public String toString() {
        return symbol.toString();
    }
}
