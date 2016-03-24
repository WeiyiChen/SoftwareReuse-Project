package client.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client11SReciever extends Thread {
	private Socket socket;

	public Client11SReciever(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String message = null;
			while (true) {
				message = reader.readLine();
				System.out.println(message);
				if(message.equals("bye")){
					break;
				}
			}
		} catch (IOException e) {
			if (!socket.isClosed()) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
