package io.github.stardewmini.client.app;

import io.github.stardewmini.client.controllers.ClientConnectionController;
import io.github.stardewmini.common.ConnectionThread;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.server.app.ClientConnectionThread;

import java.io.IOException;
import java.net.Socket;

public class ServerConnectionThread extends ConnectionThread {

    protected ServerConnectionThread(Socket socket) throws IOException {
        super(socket);
    }


    @Override
    protected boolean handleMessage(Message message) {
        if (message.getType() == Message.Type.command) {
            sendMessage(ClientConnectionController.handleCommand(message));
            return true;
        } else if (message.getType() == Message.Type.update) {
            ClientConnectionController.handleUpdate(message);
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        super.run();
        ClientApp.endAll();
        System.exit(0);
    }
}
