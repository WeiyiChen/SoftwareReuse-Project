package client.transport;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import teamEleven.configController.ConfigController;
/**
 * keep a global socket for the client
 * @author Dai
 *
 */
public class ClientSocket {
	private static Socket socket = null;
	
	/**
	 * create a socket according the configure file
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private synchronized static Socket createSocket() throws UnknownHostException, IOException{
//		ClientConfig config = (ClientConfig) ParseClientConfig.getConfig("ClientConfig.xml");
		ConfigController cc = new ConfigController("clientconfig.json");
		String ip = cc.getString("ip", "127.0.0.1");
		int port = cc.getInt("port", 2345);
		socket = new Socket(ip, port);
		System.out.println("create socket\n");
		return socket;
	}
	
	/**
	 * 
	 * @return - get the socket used by the client, if the socket haven't been created, create it
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static Socket getSocket() throws UnknownHostException, IOException{
		if(socket == null){
			
				return createSocket();
			
			
		}
		return socket;
	}
}
