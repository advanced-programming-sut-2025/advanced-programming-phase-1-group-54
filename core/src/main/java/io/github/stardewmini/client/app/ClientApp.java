package io.github.stardewmini.client.app;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientApp {
    public static final int TIMEOUT_MILLIS = 500;

    private static String clientIP;
    private static int clientPort;
    private static final Map<String, List<String>> sentFiles = new HashMap<>();
    private static final Map<String, List<String>> receivedFiles = new HashMap<>();
    private static ServerConnectionThread serverConnectionThread;

    private static boolean exitFlag = false;

    public static boolean isEnded() {
        return exitFlag;
    }

    public static void initFromArgs(String[] args) throws Exception {
        String[] selfAddress = args[0].split(":");
        String[] serverAddress = args[1].split(":");

        clientIP = selfAddress[0];
        clientPort = Integer.parseInt(selfAddress[1]);

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
}
