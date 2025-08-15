package io.github.stardewmini.server.app;

import io.github.stardewmini.common.JSONUtils;
import io.github.stardewmini.common.Message;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class UpdateThread extends Thread {
    private int TICK_RATE = 50;

    private AtomicBoolean end;


    @Override
    public void run() {
        while (!end.get()) {
            // TODO

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
