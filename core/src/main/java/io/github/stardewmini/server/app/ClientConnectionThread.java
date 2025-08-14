package io.github.stardewmini.server.app;

import io.github.stardewmini.client.controllers.ClientConnectionController;
import io.github.stardewmini.common.ConnectionThread;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.server.controllers.ServerConnectionController;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionThread extends ConnectionThread {
    private String username;
    private int lobbyId;

	public ClientConnectionThread(Socket socket) throws IOException {
		super(socket);
	}

	@Override
	protected boolean handleMessage(Message message) {
		if (message.getType() == Message.Type.command) {
			sendMessage(ServerConnectionController.handleCommand(username, message));
			return true;
		}
        else if (message.getType() == Message.Type.update) {
            ServerConnectionController.handleUpdate(username, message);
        }
		return false;
	}

	@Override
	public void run() {
        ServerApp.addClientConnection(this);
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
