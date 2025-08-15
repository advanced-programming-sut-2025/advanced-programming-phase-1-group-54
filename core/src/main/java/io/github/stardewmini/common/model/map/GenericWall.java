package io.github.stardewmini.common.model.map;

import com.badlogic.gdx.graphics.Texture;

public class GenericWall extends Building {
    public GenericWall(Area area, Texture texture) { // TODO texture
        super(area.upperLeftLocation(), new Map(area.numberOfRows(), area.numberOfColumns()));
        this.getSprite().setRegion(texture);
    }

    @Override
    public boolean canEnter() {
        return false;
    }
}
