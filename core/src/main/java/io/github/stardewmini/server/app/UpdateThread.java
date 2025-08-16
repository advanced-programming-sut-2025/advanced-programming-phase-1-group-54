package io.github.stardewmini.server.app;

import io.github.stardewmini.common.ConnectionThread;
import io.github.stardewmini.common.JSONUtils;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.server.controllers.UpdateController;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class UpdateThread extends Thread {
    private int TICK_RATE = 100;
    private int lobbyId;
    private Queue<Message> diffs;
    private AtomicBoolean end;

    public UpdateThread(int lobbyId) {
        this.lobbyId = lobbyId;
        this.end = new AtomicBoolean(false);
        this.diffs = new ConcurrentLinkedDeque<>();
    }

    private void sendDiff(Message diff) {
        for (ConnectionThread connectionThread : ServerApp.getConnectionsByLobbyId(lobbyId)) {
            connectionThread.sendMessage(diff);
        }
    }

    public void addDiff(Message message) {
        diffs.add(message);
    }

    @Override
    public void run() {
        int timer = 0;
        while (!end.get()) {
            if (timer >= 60_000) {
                App.getCurrentGame().getDateTime().increaseHour(1);
                diffs.add(UpdateController.createAdvanceTime("1"));
                timer = 0;
            }

            while (!diffs.isEmpty()) {
                sendDiff(diffs.poll());
            }

            try {
                Thread.sleep(TICK_RATE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            timer += TICK_RATE;
        }

        end();
    }

    public void end() {
        end.set(true);
    }
}
