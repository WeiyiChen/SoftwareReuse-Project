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
				// ���յ��ͻ��˵�bye��Ϣ���ͻ��˼����˳�������byeд�뵽�ÿͻ���
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