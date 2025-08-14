package io.github.stardewmini.client.Renderers.Plants;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.common.model.items.plants.Fruit;

public class FruitRenderer {
    private final Fruit fruit;
    private Sprite sprite;

    public FruitRenderer(Fruit fruit) {
        this.fruit = fruit;
        this.sprite = new Sprite();
    }

    public Fruit getFruit() {
        return fruit;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
