package io.github.stardewmini.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import io.github.stardewmini.model.items.Fish;
import io.github.stardewmini.model.lives.Player;

import java.util.Random;

public class FishingGame {
    private static final int screenWidth = Gdx.graphics.getWidth();
    private static final int screenHeight = Gdx.graphics.getHeight();
    public static final int max = 3 * screenHeight/4;
    public static final int min = screenHeight/4;
    public static final Random random = new Random();

    public FishingGame(Fish fish, int fishType) {
        greenPart = new Rectangle(screenWidth/2f,screenHeight/2f,50,150);
        fishRectangle = new Rectangle(screenWidth/2f,screenHeight/2f,50,50);
        map = new Rectangle(screenWidth/2f, min,50,max - min);
        this.perfect = true;
        this.fishType = fishType;
        this.lastMoveDirection = 0;
        this.fish = fish;
    }

    private final Rectangle fishRectangle;
    private final Rectangle greenPart;
    private boolean greenPartDirection;
    private final Rectangle map;
    private boolean perfect;
    private final int fishType;
    private int lastMoveDirection;
    public final Fish fish;

    public Rectangle getFishRectangle() {
        return fishRectangle;
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

    public int getFishType() {
        return fishType;
    }

    public int getLastMoveDirection() {
        return lastMoveDirection;
    }

    public Fish getFish() {
        return fish;
    }

    public void setGreenPartDirection(boolean greenPartDirection) {
        this.greenPartDirection = greenPartDirection;
    }

    public void setPerfect(boolean perfect) {
        this.perfect = perfect;
    }

    public void setLastMoveDirection(int lastMoveDirection) {
        this.lastMoveDirection = lastMoveDirection;
    }
}
