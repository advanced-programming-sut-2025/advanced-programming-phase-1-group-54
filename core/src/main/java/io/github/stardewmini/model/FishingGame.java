package io.github.stardewmini.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class FishingGame {
    private static final int max = 3 * Gdx.graphics.getHeight()/4;
    private static final int min = Gdx.graphics.getWidth()/4;

    private Rectangle fish;
    private int fishLocation;
    private Rectangle greenPart;
    private int greenPartLocation;
    private boolean greenPartDirection;
    private Rectangle map;


}
