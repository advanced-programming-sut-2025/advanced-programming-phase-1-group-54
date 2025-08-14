package io.github.stardewmini.client.Renderers.Lives;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.common.model.lives.Player;

public class PlayerRenderer {
    private final Player player;
    private Sprite sprite;

    public PlayerRenderer(Player player) {
        this.player = player;
        this.sprite = new Sprite();
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
