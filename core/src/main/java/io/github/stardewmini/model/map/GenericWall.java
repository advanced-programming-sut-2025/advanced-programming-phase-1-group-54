package io.github.stardewmini.model.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.model.enums.Symbol;

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
