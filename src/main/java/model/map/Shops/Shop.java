package model.map.Shops;

import model.DailyUpdate;
import model.DateTime;
import model.HourUpdate;
import model.lives.NPC;
import model.map.Building;
import model.enums.Symbol;
import model.map.Area;
import model.map.Map;

public class Shop extends Building implements DailyUpdate, HourUpdate {
    /* TODO shops don't need subclasses
        save each shop to files
    */
    private final NPC owner;
    private final int openingHours;
    private final int closingHours;

    private boolean open;
    private int hoursToOpenOrClose;

    public Shop(NPC owner, int openingHours, int closingHours, Area area) {
        super(area.upperLeftLocation(), new Map(area.lowerRightLocation().row() - area.upperLeftLocation().row(),
                area.lowerRightLocation().column() - area.upperLeftLocation().column()));
        this.owner = owner;
        this.openingHours = openingHours;
        this.closingHours = closingHours;

        this.getTileAt(getRandomLocation()).setThingOnTile(owner);

        hoursToOpenOrClose = openingHours - DateTime.getStartHour();
        if (hoursToOpenOrClose <= 0) {
            openOrClose();
        }
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

    private void openOrClose() {
        if (!open) {
            open = true;
            hoursToOpenOrClose = closingHours - openingHours;
        }
        else {
            open = false;
            hoursToOpenOrClose = DateTime.getHoursInDay() + openingHours - closingHours;
        }
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
    public void nextDayUpdate() {
        hoursToOpenOrClose -= DateTime.getNightTime();
        if (hoursToOpenOrClose <= 0) {
            openOrClose();
        }
    }

    @Override
    public void nextHourUpdate() {
        hoursToOpenOrClose--;
        if (hoursToOpenOrClose <= 0)
            openOrClose();
    }
}
