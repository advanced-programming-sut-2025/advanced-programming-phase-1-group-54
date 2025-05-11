package model.map;

import model.Placeable;
import model.Walkable;
import model.enums.Symbol;

public class Tile {
    private Symbol symbol = Symbol.EMPTY;
    private final Character owner;

    private Placeable thingOnTile;
    private boolean isProtected = false;

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

    public boolean isProtected() {
        return isProtected;
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
