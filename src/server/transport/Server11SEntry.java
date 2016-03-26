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
		List<SocketController> socketList = new ArrayList<SocketController>();
		Socket socket = null;
		while (true) {
			socket = serverSocket.accept();
			SocketController socketWS = new SocketController(socket);
			System.out.println("Unknown clinet connected to the server!"
					+ socket.getLocalAddress().toString());
			// add the socket to all socket list
			socketList.add(socketWS);
			// allocate a server for new socket
			new Server11S(socketWS, socketList).start();
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
