package io.github.stardewmini.client.Renderers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.common.model.items.Material;

public class MaterialRenderer {
    private final Material material;
    private Sprite sprite;

    public MaterialRenderer(Material material) {
        this.material = material;
        this.sprite = new Sprite(GameAssetManager.getInstance().getMaterials(material.getName()));
    }

    public Material getMaterial() {
        return material;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
