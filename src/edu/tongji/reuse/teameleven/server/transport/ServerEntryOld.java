package edu.tongji.reuse.teameleven.server.transport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import edu.tongji.reuse.teameleven.server.ctrl.MessageController;

@Deprecated
public class ServerEntryOld extends Thread {

	private int port = 2345;

	private ServerSocket serverSocket;

	private List<ServerEntityOld> socketServerList;

	private boolean continueToRun = false;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		continueToRun = true;
		MessageController.startRecordThread();
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.setSoTimeout(1000);
			socketServerList = new ArrayList<ServerEntityOld>();
			Socket socket = null;
			while (continueToRun) {
				try {
					socket = serverSocket.accept();
					SocketWrapper socketWrapper = new SocketWrapper(socket);
					System.out.println("Client connected to the server!"
							+ socket.getLocalAddress().toString());

					// allocate a server for new socket
					ServerEntityOld severEntity = new ServerEntityOld(socketWrapper, socketServerList);
					severEntity.start();

					// add the socket to all socket list
					socketServerList.add(severEntity);
				} catch (SocketTimeoutException ste) {
                    ste.printStackTrace();
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
		for (ServerEntityOld serverEntity : socketServerList) {
			serverEntity.quit();
		}
		MessageController.quit();
		System.out.println("server socket exit");
	}

}
