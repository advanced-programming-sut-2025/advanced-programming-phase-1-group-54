package io.github.stardewmini.model;

public class SoundManager {
    // This Class is for managing audio and audio settings. (i think this makes it easier).

    private static SoundManager instance;

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    private SoundManager() {
    }

    public void playClick() {
        // TODO
    }
}
