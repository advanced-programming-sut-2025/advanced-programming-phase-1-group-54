package model.lives;

import model.Placeable;

public abstract class Live implements Placeable {
    private String name;

    public Live() {
    }

    public Live(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
