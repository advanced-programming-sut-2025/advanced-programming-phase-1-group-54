package io.github.stardewmini.client.app;

import io.github.stardewmini.client.controllers.ClientConnectionController;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.Result;

import java.net.Socket;

public class ClientApp {
    public static final int TIMEOUT_MILLIS = 500;

    private static ServerConnectionThread serverConnectionThread;

    private static boolean exitFlag = false;

    public static boolean isEnded() {
        return exitFlag;
    }

    public static void initFromArgs(String[] args) throws Exception {
        String[] selfAddress = args[0].split(":");
        String[] serverAddress = args[1].split(":");

        serverConnectionThread = new ServerConnectionThread(
                new Socket(serverAddress[0], Integer.parseInt(serverAddress[1]))
        );
    }

    public static void endAll() {
        exitFlag = true;
        serverConnectionThread.end();
    }

    public static void connectServer() {
        if (serverConnectionThread != null && !serverConnectionThread.isAlive()) {
            serverConnectionThread.start();
        }
    }

    public static ServerConnectionThread getServerConnectionThread() {
        return serverConnectionThread;
    }

    public static Result sendRequest(Message message) {
        Message response = serverConnectionThread.sendAndWaitForResponse(message,
            ClientApp.TIMEOUT_MILLIS);
        return ClientConnectionController.getResultFromResponse(response);
    }

    public static void sendUpdate(Message message) {
        serverConnectionThread.sendMessage(message);
    }
}
