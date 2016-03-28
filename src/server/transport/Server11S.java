package server.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import server.ctrl.MessageController;
import server.json.JsonAnalizerServer;
import server.json.JsonBuilderServer;

class Server11S extends Thread {
	private SocketController socketController;
	private MessageController messageController;
	private List<SocketController> socketControllerList;
	
	public Server11S(SocketController socketP, List<SocketController> socketList) {
		this.socketController = socketP;
		this.socketControllerList = socketList;
	}

	public void run() {
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = socketController.getBufferedReader();

			String message = null;
			while (true) {
				message = reader.readLine();
				if (message == null) {
					break;
				}
				String result = messageController.dealWithMessage(message);
				if (message.equals("bye")) {
					socketController.sendText("bye");
					break;
				}
				if (JsonAnalizerServer.getMessageType(result).equals(
						JsonBuilderServer.message)) {
					// send information to all user
					for (int i = 0; i < socketControllerList.size(); i++) {
						socketControllerList.get(i).sendText(result);
					}
				}else{
					socketController.sendText(result);
				}

			}
			socketControllerList.remove(socketController);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}