package client.transport;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import client.util.ClientConfig;
import client.util.ParseClientConfig;

public class ClientSocket {
	private static Socket socket;
	
	public static Socket createSocket() throws UnknownHostException, IOException{
		ClientConfig config = (ClientConfig) ParseClientConfig.getConfig("ClientConfig.xml");
		String ip = config.getIp();
		int port = config.getIntPort();
		socket = new Socket(ip, port);
		return socket;
	}
	
	public static Socket getSocket() throws UnknownHostException, IOException{
		if(socket == null){
			return createSocket();
		}
		return socket;
	}
}
