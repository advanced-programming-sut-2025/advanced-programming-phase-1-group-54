package io.github.stardewmini.server.model;

import io.github.stardewmini.common.model.User;

import java.util.ArrayList;

public class Lobby {
    private final String name;
    private final int id;
    private final String password;
    private final boolean visible;
    private final ArrayList<User> users = new ArrayList<>();

    public Lobby(String name, int id, String password, boolean visible) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.visible = visible;
    }
}
