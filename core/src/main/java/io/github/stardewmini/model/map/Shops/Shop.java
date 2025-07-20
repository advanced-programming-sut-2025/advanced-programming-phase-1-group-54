package io.github.stardewmini.model.map.Shops;

import io.github.stardewmini.model.HourCheck;
import io.github.stardewmini.model.enums.Symbol;
import io.github.stardewmini.model.lives.NPC;
import io.github.stardewmini.model.map.Area;
import io.github.stardewmini.model.map.Building;
import io.github.stardewmini.model.map.Map;

public class Shop extends Building implements HourCheck {
    /* TODO shops don't need subclasses
        save each shop to files
    */
    private final NPC owner;
    private final int openingHours;
    private final int closingHours;

    private boolean open;

    public Shop(NPC owner, int openingHours, int closingHours, Area area) {
        super(area.upperLeftLocation(), new Map(area.lowerRightLocation().row() - area.upperLeftLocation().row(),
                area.lowerRightLocation().column() - area.upperLeftLocation().column()));
        this.owner = owner;
        this.openingHours = openingHours;
        this.closingHours = closingHours;

        this.getTileAt(getRandomLocation()).setThingOnTile(owner);
    }
    public NPC getOwner() {
        return owner;
    }

    public int getOpeningHours() {
        return openingHours;
    }

    public int getClosingHours() {
        return closingHours;
    }

    public boolean isOpen() {
        return open;
    }

    @Override
    public boolean canEnter() {
        return isOpen();
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.SHOP;
    }

    @Override
    public void checkHour(int time) {
        open = (openingHours <= time) && (time < closingHours);
    }
}
