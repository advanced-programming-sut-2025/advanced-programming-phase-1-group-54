package model.alive;

import model.Placeable;

public class Human implements Placeable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
