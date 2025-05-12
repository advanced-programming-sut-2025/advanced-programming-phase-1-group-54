package model.Shops;

import model.Building.Building;
import model.alive.Human;

public abstract class Shop extends Building {
    protected Human owner;
    protected int openingHours;
    protected int closingHours;
    public Shop(Human owner, int openingHours, int closingHours,) {
        super()
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
}
