package io.github.stardewmini.model.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.model.enums.Symbol;
import io.github.stardewmini.model.lives.NPC;

public class NPCHouse extends Building {
    private final NPC npc;

    public NPCHouse(NPC npc, Area area) {
        super(area.upperLeftLocation(), new Map(area.numberOfRows(), area.numberOfColumns()));
        this.npc = npc;

        this.getTileAt(getRandomLocation()).setThingOnTile(npc);
    }

    public NPC getNpc() {
        return npc;
    }
}
