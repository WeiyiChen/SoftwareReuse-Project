package server.transport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import server.ctrl.MessageController;
import server.ctrl.SocketController;

public class Server11Entry extends Thread {

	private ServerSocket serverSocket;

	private List<SocketController> socketList;

	private boolean continueToRun;

	@Override
	public void run() {
		continueToRun = true;
		MessageController.startRecordThread();
		try {
			serverSocket = new ServerSocket(2345);
			socketList = new ArrayList<SocketController>();
			Socket socket = null;
			while (continueToRun) {
				socket = serverSocket.accept();
				SocketController socketWS = new SocketController(socket);
				System.out.println("Clinet connected to the server!"
						+ socket.getLocalAddress().toString());
				// add the socket to all socket list
				socketList.add(socketWS);
				// allocate a server for new socket
				new Server11Entity(socketWS, socketList).start();
			}
		} catch (IOException e) {
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

	public void quit() {
		this.continueToRun = false;
		for (SocketController sc : socketList) {
			try {
				sc.quit();
			} catch (IOException e) {
			}
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
		}
	}

}
