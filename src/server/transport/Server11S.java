package server.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

class Server11S extends Thread {
	private Socket socket;
	private List<Socket> socketList;
	private int count;

	public Server11S(int count, Socket socket, List<Socket> socketList) {
		this.count = count;
		this.socket = socket;
		this.socketList = socketList;
	}

	public void run() {
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			String message = null;
			while (true) {
				message = reader.readLine();
				// 接收到客户端的bye信息，客户端即将退出，并将bye写入到该客户端
				if(message == null){
					break;
				}
				if (message.equals("bye")) {
					writer = new PrintWriter(socket.getOutputStream());
					writer.println("bye");
					writer.flush();
					break;
				}

				// 向所有的客户端发送接收到信息，实现群聊
				for (int i = 0; i < socketList.size(); i++) {
					writer = new PrintWriter(socketList.get(i)
							.getOutputStream());
					writer.println(count + " say: " + message);
					writer.flush();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}