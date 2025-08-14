package io.github.stardewmini.client.Renderers.Plants;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.common.model.items.plants.Seed;

public class SeedRenderer {
    private final Seed seed;
    private Sprite sprite;

    public SeedRenderer(Seed seed) {
        this.seed = seed;
        this.sprite = new Sprite();
    }

    public Seed getSeed() {
        return seed;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
