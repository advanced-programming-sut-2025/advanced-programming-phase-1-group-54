package io.github.stardewmini.common.model.lives;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.stardewmini.common.model.DailyUpdate;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Quest;
import io.github.stardewmini.common.model.map.Location;
import io.github.stardewmini.common.model.map.Tile;

import java.util.ArrayList;
import java.util.Random;

public class NPC extends Live implements DailyUpdate {
    public static final int dialogTiming = 2 * 60;

    private Sprite sprite;
    private String job;
    private float animationTime;
    private Location location;
    private float dialogTime;

    private final ArrayList<String> favoriteItems = new ArrayList<>();

    private final ArrayList<Quest> allQuests = new ArrayList<>();

    private int newQuestCounter;

    public NPC(String job, String name) {
        super(name);
        this.job = job;
        newQuestCounter = 30;
        this.sprite = new Sprite();
        TextureRegion[][] textureRegion = GameAssetManager.getInstance().getNPCsFrames(name);
        if(textureRegion == null) {
            textureRegion = GameAssetManager.getInstance().getNPCsFrames("Robin");
        }
        this.sprite.setRegion(textureRegion[0][0]);
        this.sprite.setSize(Tile.getSize(), (int) Math.floor(Tile.getSize() * 1.7));
        this.dialogTime = dialogTiming;
    }

    public void activateQuest(Quest quest) {
        if (allQuests.contains(quest)) {
            quest.setCompleted(true);
        }
    }

    public String getJob() {
        return job;
    }

    public float getAnimationTime() {
        return animationTime;
    }

    public Location getLocation() {
        return location;
    }

    public float getDialogTime() {
        return dialogTime;
    }


    public void setJob(String job) {
        this.job = job;
    }

    public void setAnimationTime(float animationTime) {
        this.animationTime = animationTime;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setDialogTime(float dialogTime) {
        this.dialogTime = dialogTime;
    }

    public ArrayList<String> getFavoriteItems() {
        return favoriteItems;
    }

    public ArrayList<Quest> getAllQuests() {
        return allQuests;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void nextDayUpdate() {
        if (newQuestCounter > 0) {
            newQuestCounter--;
            if (newQuestCounter <= 0) {
                allQuests.get(2).setActive(true);
            }
        }
    }
}
