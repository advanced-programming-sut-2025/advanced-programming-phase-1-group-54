package io.github.stardewmini.client.Renderers.Lives;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.client.Renderers.GameAssetManager;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.Tile;

public class PlayerRenderer {
    private final Player player;
    private Sprite sprite;

    public PlayerRenderer(Player player) {
        this.player = player;
        this.sprite = new Sprite(GameAssetManager.getInstance().getPlayerWalkRight().getKeyFrame(0));
        this.sprite.setSize(Tile.getSize(), (int) Math.floor(Tile.getSize() * 1.7));
    }

    public Player getPlayer() {
        return player;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
