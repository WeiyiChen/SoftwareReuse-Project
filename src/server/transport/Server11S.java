package server.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

class Server11S extends Thread {
	private SocketController socketController;
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
				// 接收到客户端的bye信息，客户端即将退出，并将bye写入到该客户端
				if(message == null){
					break;
				}
				if (message.equals("bye")) {
					socketController.sendText("bye");
					break;
				}

				// send information to all user
				for (int i = 0; i < socketControllerList.size(); i++) {
					socketControllerList.get(i).sendText("say: " + message);
				}

			}
			socketControllerList.remove(socketController);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}