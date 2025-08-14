package io.github.stardewmini.client.Renderers.Lives;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.common.model.lives.Animal;

public class AnimalRenderer {
    private final Animal animal;
    private Sprite sprite;

    public AnimalRenderer(Animal animal) {
        this.animal = animal;
        this.sprite = new Sprite();
    }

    public Animal getAnimal() {
        return animal;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
