package io.github.stardewmini.common.model.map;

import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.lives.NPC;

public class NPCHouse extends Building {

    private final NPC npc;

    public NPCHouse(NPC npc, Area area) {
        super(area.upperLeftLocation(), new Map(area.numberOfRows(), area.numberOfColumns()));
        this.npc = npc;

        Location location = getRandomLocation();
        this.getTileAt(location).setThingOnTile(npc);
        npc.setLocation(this.getLocation().add(location));
        npc.setLocation2(location);

        this.getSprite().setRegion(GameAssetManager.getInstance().getBuilding(npc.getName() + "House"));
    }

    public NPC getNpc() {
        return npc;
    }
}
