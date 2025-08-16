package io.github.stardewmini.server.model;

import io.github.stardewmini.common.model.LobbyInfo;
import io.github.stardewmini.common.model.User;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private final String name;
    private final int id;
    private final String password;
    private final boolean visible;
    private final ArrayList<User> users = new ArrayList<>();
    private long lastJoinedTime;
    private boolean activeGame;

    public Lobby(String name, int id, String password, boolean visible) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.visible = visible;
        this.lastJoinedTime = System.currentTimeMillis();
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

    public boolean isPrivate() {
        return !password.isEmpty();
    }

    public List<User> getUsers() {
        return List.copyOf(users);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public User getAdmin() {
        return users.get(0);
    }

    public LobbyInfo getLobbyInfo() {
        return new LobbyInfo(name, id, isPrivate());
    }

    public int getNumberOfUsers() {
        return users.size();
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }

    public void setLastJoinedTime(long lastJoinedTime) {
        this.lastJoinedTime = lastJoinedTime;
    }

    public long getLastJoinedTime() {
        return lastJoinedTime;
    }
}
