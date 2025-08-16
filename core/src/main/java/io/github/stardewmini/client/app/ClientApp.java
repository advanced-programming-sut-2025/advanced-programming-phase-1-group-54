package io.github.stardewmini.client.app;

import io.github.stardewmini.client.controllers.ClientConnectionController;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.Result;

import java.net.Socket;

public class ClientApp {
    public static final int TIMEOUT_MILLIS = 20000;

    private static String ip;
    private static int port;
    private static ServerConnectionThread serverConnectionThread;

    private static boolean exitFlag = false;

    public static boolean isEnded() {
        return exitFlag;
    }

    public static void initFromArgs(String[] args) throws Exception {
        String[] selfAddress = args[0].split(":");
        String[] serverAddress = args[1].split(":");

        ip = selfAddress[0];
        port = Integer.parseInt(selfAddress[1]);

        serverConnectionThread = new ServerConnectionThread(
                new Socket(serverAddress[0], Integer.parseInt(serverAddress[1]))
        );
    }

    public static String getIp() {
        return ip;
    }

    public static int getPort() {
        return port;
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

    public static Message sendMessageAndGetResponse(Message message) {
        return serverConnectionThread.sendAndWaitForResponse(message,
            ClientApp.TIMEOUT_MILLIS);
    }

    public static void sendUpdate(Message message) {
        serverConnectionThread.sendMessage(message);
    }
}
