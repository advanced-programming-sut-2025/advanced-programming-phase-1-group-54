package io.github.stardewmini.common.model.lives;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.stardewmini.common.model.DailyUpdate;
import io.github.stardewmini.common.model.Quest;
import io.github.stardewmini.common.model.map.Location;
import io.github.stardewmini.common.model.map.Tile;

import java.util.ArrayList;
import java.util.Random;

public class NPC extends Live implements DailyUpdate {
    private static final Random rand = new Random();
    public static final int dialogTiming = 2 * 60;

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
        newQuestCounter = rand.nextInt(30) + 30;
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
    public void nextDayUpdate() {
        if (newQuestCounter > 0) {
            newQuestCounter--;
            if (newQuestCounter <= 0) {
                allQuests.get(2).setActive(true);
            }
        }
    }
}
