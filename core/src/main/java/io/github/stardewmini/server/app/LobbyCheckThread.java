package io.github.stardewmini.server.app;

import io.github.stardewmini.common.ConnectionThread;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.server.controllers.UpdateController;
import io.github.stardewmini.server.model.Lobby;

import java.util.concurrent.atomic.AtomicBoolean;

public class LobbyCheckThread extends Thread {
    private static final int TICK_RATE = 3000;

    private AtomicBoolean end;

    public LobbyCheckThread() {
        this.end = new AtomicBoolean(false);
    }

    @Override
    public void run() {
        while (!end.get()) {
            for (Lobby lobby : App.getLobbies()) {
                if (System.currentTimeMillis() - lobby.getLastJoinedTime() >= 2*60_000) {
                    App.removeLobby(lobby);
                }
            }
            try {
                Thread.sleep(TICK_RATE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        end();
    }

    public void end() {
        end.set(true);
    }
}
