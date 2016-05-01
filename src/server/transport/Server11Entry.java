package server.transport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import server.ctrl.MessageController;
import server.ctrl.SocketController;

public class Server11Entry extends Thread {

	private ServerSocket serverSocket;

	private List<Server11Entity> socketServerList;

	private boolean continueToRun;

	@Override
	public void run() {
		continueToRun = true;
		MessageController.startRecordThread();
		try {
			serverSocket = new ServerSocket(2345);
			serverSocket.setSoTimeout(1000);
			socketServerList = new ArrayList<Server11Entity>();
			Socket socket = null;
			while (continueToRun) {
				try {
					socket = serverSocket.accept();
					SocketController socketWS = new SocketController(socket);
					System.out.println("Clinet connected to the server!"
							+ socket.getLocalAddress().toString());
					// add the socket to all socket list
					
					// allocate a server for new socket
					Server11Entity s11 = new Server11Entity(socketWS, socketServerList);
					s11.start();
					
					socketServerList.add(s11);
				} catch (SocketTimeoutException ste) {
					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
				MessageController.quit();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("server socket stop running");
		}
	}

	public void quit() throws IOException {
		this.continueToRun = false;
		for (Server11Entity s11 : socketServerList) {
			s11.quit();
		}
		MessageController.quit();
		System.out.println("server socket exit");
	}

}
