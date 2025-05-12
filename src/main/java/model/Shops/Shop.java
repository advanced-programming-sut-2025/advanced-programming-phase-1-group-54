package model.Shops;

import model.Building.Building;
import model.alive.Human;
import model.enums.Symbol;
import model.map.Location;
import model.map.Map;

public class Shop extends Building {
    /* TODO shops don't need subclasses
        save each shop to files
    */
    protected Human owner;
    protected int openingHours;
    protected int closingHours;

    public Shop(Human owner, int openingHours, int closingHours, Location upperLeftLocation, Location lowerRightLocation) {
        super(upperLeftLocation, new Map(lowerRightLocation.row() - upperLeftLocation.row(),
                lowerRightLocation.column() - upperLeftLocation.column()));
        this.owner = owner;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
    }
    public Human getOwner() {
        return owner;
    }
    public void setOwner(Human owner) {
        this.owner = owner;
    }
    public int getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(int openingHours) {
        this.openingHours = openingHours;
    }

    public int getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(int closingHours) {
        this.closingHours = closingHours;
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.SHOP;
    }
}
