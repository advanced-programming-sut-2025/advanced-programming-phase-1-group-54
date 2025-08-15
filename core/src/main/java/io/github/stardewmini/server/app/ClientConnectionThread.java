package io.github.stardewmini.server.app;

import io.github.stardewmini.common.ConnectionThread;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.server.controllers.ServerConnectionController;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import static io.github.stardewmini.server.app.ServerApp.TIMEOUT_MILLIS;

public class ClientConnectionThread extends ConnectionThread {
    private String username;
    private int lobbyId;

    public ClientConnectionThread(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public boolean initialHandshake() {
        try {
            refreshStatus();
            ServerApp.addClientConnection(this);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void refreshStatus() {

        HashMap<String, Object> messageBody = new HashMap<>();
        messageBody.put("command", "status");
        Message statusCommand = new Message(messageBody, Message.Type.command);

        Message responseMessage = sendAndWaitForResponse(statusCommand, TIMEOUT_MILLIS);

        if (responseMessage.getType() == Message.Type.response) {
            String clientIp = responseMessage.getFromBody("ip");
            int clientPort = responseMessage.getIntFromBody("port");

            setOtherSideIP(clientIp);
            setOtherSidePort(clientPort);
        }
    }

    @Override
    protected boolean handleMessage(Message message) {
        if (message.getType() == Message.Type.command) {
            sendMessage(ServerConnectionController.handleCommand(username, getOtherSideIP(), getOtherSidePort(), message));
            return true;
        } else if (message.getType() == Message.Type.update) {
            ServerConnectionController.handleUpdate(username, message);
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        super.run();
        ServerApp.removeClientConnection(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }
}
