package edu.tongji.reuse.teameleven.client.transport;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.tongji.reuse.teameleven.client.ctl.ClientConfigBean;
import octoteam.tahiti.config.ConfigManager;
import octoteam.tahiti.config.loader.JsonAdapter;
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
		ConfigManager configManager = new ConfigManager(new JsonAdapter(),"data/clientconfig.json");
		ClientConfigBean configBean = configManager.loadToBean(ClientConfigBean.class);
		String ip = configBean.getHost();
		int port = configBean.getPort();
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
