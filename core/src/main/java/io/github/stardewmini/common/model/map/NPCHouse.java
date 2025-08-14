package io.github.stardewmini.common.model.map;

import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.lives.NPC;

public class NPCHouse extends Building {

    private final NPC npc;

    public NPCHouse(NPC npc, Area area) {
        super(area.upperLeftLocation(), new Map(area.numberOfRows(), area.numberOfColumns()));
        this.npc = npc;

        this.getTileAt(getRandomLocation()).setThingOnTile(npc);

        this.getSprite().setRegion(GameAssetManager.getInstance().getBuilding(npc.getName() + "House"));
    }

    public NPC getNpc() {
        return npc;
    }
}
