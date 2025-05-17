package model.map;

import model.enums.Symbol;
import model.lives.NPC;

public class NPCHouse extends Building {
    private final NPC npc;

    public NPCHouse(NPC npc, Area area) {
        super(area.upperLeftLocation(), new Map(area.numberOfRows(), area.numberOfColumns()));
        this.npc = npc;

        this.getTileAt(getRandomLocation()).setThingOnTile(npc);
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.HOUSE;
    }

    public NPC getNpc() {
        return npc;
    }
}
