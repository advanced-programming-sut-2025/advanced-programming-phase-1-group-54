package io.github.stardewmini.client.Renderers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.common.model.map.Building;

public class BuildingRenderer {
    private final Building building;
    private Sprite sprite;

    public BuildingRenderer(Building building) {
        this.building = building;
        this.sprite = new Sprite();
    }

    public Building getBuilding() {
        return building;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
