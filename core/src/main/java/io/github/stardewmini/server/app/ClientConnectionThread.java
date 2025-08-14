package io.github.stardewmini.server.app;

import io.github.stardewmini.client.controllers.ClientConnectionController;
import io.github.stardewmini.common.ConnectionThread;
import io.github.stardewmini.common.Message;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionThread extends ConnectionThread {
	public ClientConnectionThread(Socket socket) throws IOException {
		super(socket);
	}

	@Override
	protected boolean handleMessage(Message message) {
		if (message.getType() == Message.Type.command) {
			sendMessage(ClientConnectionController.handleCommand(message));
			return true;
		}
        else if (message.getType() == Message.Type.update) {
            ClientConnectionController.handleUpdate(message);
        }
		return false;
	}

	@Override
	public void run() {
        ServerApp.addClientConnection(this);
		super.run();
		ServerApp.removeClientConnection(this);
	}
}
