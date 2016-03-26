package server.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

class Server11S extends Thread {
	private SocketPlus socketP;
	private List<SocketPlus> socketWSList;
	
	public Server11S(SocketPlus socketP, List<SocketPlus> socketList) {
		this.socketP = socketP;
		this.socketWSList = socketList;
	}

	public void run() {
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = socketP.getBufferedReader();

			String message = null;
			while (true) {
				message = reader.readLine();
				// ���յ��ͻ��˵�bye��Ϣ���ͻ��˼����˳�������byeд�뵽�ÿͻ���
				if(message == null){
					break;
				}
				if (message.equals("bye")) {
					socketP.sendText("bye");
					break;
				}

				// send information to all user
				for (int i = 0; i < socketWSList.size(); i++) {
					socketWSList.get(i).sendText("say: " + message);
				}

			}
			socketWSList.remove(socketP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}