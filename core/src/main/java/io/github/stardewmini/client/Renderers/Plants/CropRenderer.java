package io.github.stardewmini.client.Renderers.Plants;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.common.model.items.plants.Crop;

public class CropRenderer {
    private final Crop crop;
    private Sprite sprite;

    public CropRenderer(Crop crop) {
        this.crop = crop;
        this.sprite = new Sprite();
    }

    public Crop getCrop() {
        return crop;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
