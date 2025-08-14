package io.github.stardewmini.server;

import io.github.stardewmini.server.app.ListenerThread;
import io.github.stardewmini.server.app.ServerApp;

import java.util.Scanner;

public class ServerMain {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: java ServerMain <port>");
			return;
		}

		try {
			int port = Integer.parseInt(args[0]);
			ServerApp.setListenerThread(new ListenerThread(port));
			ServerApp.startListening();
		} catch (Exception e) {
			System.err.println("Error starting server: " + e.getMessage());
		}
	}
}
