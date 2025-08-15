package io.github.stardewmini.server.app;

import io.github.stardewmini.common.JSONUtils;
import io.github.stardewmini.common.Message;
import java.util.concurrent.atomic.AtomicBoolean;

public class UpdateThread extends Thread {
    private int TICK_RATE = 100;

    private AtomicBoolean end;

    public UpdateThread() {
        this.end = new AtomicBoolean(false);
    }

    private void sendDiff() {
        for (ClientConnectionThread connectionThread : ServerApp.getConnections()) {
            Message message = new Message(ServerApp.getDiff(), Message.Type.update);
            connectionThread.sendMessage(message);
        }
    }

    @Override
    public void run() {
        int timer = 0;
        while (!end.get()) {
            if (timer >= 60_000) {
                App.getCurrentGame().getDateTime().increaseHour(1);
                ServerApp.addDiff("advance_time", 1);
                timer = 0;
            }

            if (!ServerApp.getDiff().isEmpty()) {
                sendDiff();
                ServerApp.clearDiff();
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
