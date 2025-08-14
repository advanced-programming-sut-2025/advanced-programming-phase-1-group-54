package io.github.stardewmini.client.Renderers.Lives;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.common.model.lives.NPC;

public class NPCRenderer {
    private final NPC npc;
    private Sprite sprite;

    public NPCRenderer(NPC npc) {
        this.npc = npc;
        this.sprite = new Sprite();
    }

    public NPC getNpc() {
        return npc;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
