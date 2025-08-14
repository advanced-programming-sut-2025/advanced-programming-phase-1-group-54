package io.github.stardewmini.client.Renderers.Lives;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.stardewmini.client.Renderers.GameAssetManager;
import io.github.stardewmini.common.model.lives.NPC;
import io.github.stardewmini.common.model.map.Tile;

public class NPCRenderer {
    private final NPC npc;
    private Sprite sprite;

    public NPCRenderer(NPC npc) {
        this.npc = npc;
        this.sprite = new Sprite();
        TextureRegion[][] textureRegion = GameAssetManager.getInstance().getNPCsFrames(npc.getName());
        if(textureRegion == null) {
            textureRegion = GameAssetManager.getInstance().getNPCsFrames("Robin");
        }
        this.sprite.setRegion(textureRegion[0][0]);
        this.sprite.setSize(Tile.getSize(), (int) Math.floor(Tile.getSize() * 1.7));
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
