package io.github.stardewmini.model.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.enums.Symbol;
import io.github.stardewmini.model.lives.NPC;

public class NPCHouse extends Building {

    private final NPC npc;

    public NPCHouse(NPC npc, Area area) {
        super(area.upperLeftLocation(), new Map(area.numberOfRows(), area.numberOfColumns()));
        this.npc = npc;

        this.getTileAt(getRandomLocation()).setThingOnTile(npc);

        System.out.println(npc.getName());
        this.getSprite().setRegion(GameAssetManager.getInstance().getBuilding(npc.getName() + "House"));

        for(int x = 0; x < area.numberOfRows(); x++) {
            for(int y = 0; y < area.numberOfColumns(); y++) {
                this.getTileAt(new Location(x,y)).getSprite().setRegion(GameAssetManager.getInstance().getBuilding("floor"));
            }
        }
    }

    public NPC getNpc() {
        return npc;
    }
}
