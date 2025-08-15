package io.github.stardewmini.server.app;

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

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVisible() {
        return visible;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }
}
