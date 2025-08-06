package io.github.stardewmini.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class FishingGame {
    private static final int screenWidth = Gdx.graphics.getWidth();
    private static final int screenHeight = Gdx.graphics.getHeight();
    public static final int max = 3 * screenHeight/4;
    public static final int min = screenHeight/4;


    public FishingGame() {
        greenPart = new Rectangle(screenWidth/2f,screenHeight/2f,50,150);
        fish = new Rectangle(screenWidth/2f,screenHeight/2f,50,50);
        map = new Rectangle(screenWidth/2f, min,50,max - min);
        this.perfect = true;
    }

    private final Rectangle fish;
    private final Rectangle greenPart;
    private boolean greenPartDirection;
    private final Rectangle map;
    private boolean perfect;

    public Rectangle getFish() {
        return fish;
    }

    public Rectangle getGreenPart() {
        return greenPart;
    }

    public boolean isGreenPartDirection() {
        return greenPartDirection;
    }

    public Rectangle getMap() {
        return map;
    }

    public boolean isPerfect() {
        return perfect;
    }

    public void setGreenPartDirection(boolean greenPartDirection) {
        this.greenPartDirection = greenPartDirection;
    }

    public void setPerfect(boolean perfect) {
        this.perfect = perfect;
    }
}
