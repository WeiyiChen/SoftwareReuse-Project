package client.transport;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client11SEntry {
	private String ipAdress;

	public Client11SEntry(String ipAString) {
		ipAdress = ipAString;
	}

	public void startWork() throws UnknownHostException, IOException {
		Socket socket = new Socket(ipAdress, 2345);
		new Client11SReciever(socket).start();
		new Client11SSender(socket).start();
		

	}

	/**
	 * Description
	 * 
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Client11SEntry chatClient = new Client11SEntry("127.0.0.1");
		chatClient.startWork();
	}
}
