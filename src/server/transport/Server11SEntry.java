package server.transport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server11SEntry {
	
	private ServerSocket serverSocket;
	/**
	 * Description
	 * 
	 * @param args
	 */
	public void startWork() throws IOException {
		serverSocket = new ServerSocket(2345);
		List<Socket> socketList = new ArrayList<Socket>();
		Socket socket = null;
		int count = 0;
		while (true) {
			socket = serverSocket.accept();
			count++;
			System.out.println(count + " clinet connected to the server!");
			// 将每一个连接到该服务器的客户端，加到List中
			socketList.add(socket);
			// 每一个连接到服务器的客户端，服务器开启一个新的线程来处理
			new Server11S(count, socket, socketList).start();
		}
	}
	
	@Override
	public void finalize() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Server11SEntry server11SEntry = new Server11SEntry();
		server11SEntry.startWork();
	}
}
