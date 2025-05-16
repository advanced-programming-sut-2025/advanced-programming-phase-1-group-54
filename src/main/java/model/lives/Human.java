package model.lives;

import model.Placeable;

public abstract class Human implements Placeable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
