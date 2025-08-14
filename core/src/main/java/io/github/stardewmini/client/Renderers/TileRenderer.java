package io.github.stardewmini.client.Renderers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.common.model.map.Tile;

public class TileRenderer {
    private final Tile tile;
    private Sprite sprite;

    public TileRenderer(Tile tile) {
        this.tile = tile;
        this.sprite = new Sprite();
        this.sprite.setSize(Tile.getSize(),Tile.getSize());
    }

    public Tile getTile() {
        return tile;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSpritePosition(float x, float y) {
        sprite.setPosition(x, y);
    }
}
