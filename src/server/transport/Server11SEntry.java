package server.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server11SEntry {

	private ServerSocket serverSocket;
	
	private List<SocketController> socketList; 

	/**
	 * Description
	 * 
	 * @param args
	 */
	public void startWork() throws IOException {
		serverSocket = new ServerSocket(2345);
		socketList = new ArrayList<SocketController>();
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
	
	public void quit() throws IOException{
		serverSocket.close();
	}

	/**
	 * Description
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		try {
			BufferedReader input = null;
			Server11SEntry server11SEntry = new Server11SEntry();
			server11SEntry.startWork();
			input = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				String cmd = input.readLine();
				if (cmd.equals("quit")) {
					server11SEntry.quit();
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
