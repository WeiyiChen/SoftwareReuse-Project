package edu.tongji.reuse.teameleven.client.transport;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import edu.tongji.reuse.teameleven.client.intf.IMsgSender;

/**
 * Send json type message by socket
 * @author Dai
 *
 */
public class JsonMsgSender implements IMsgSender {

	@Override
	public boolean send(Object msg) {
		// TODO Auto-generated method stub		
		PrintWriter writer;
		try {
			Socket socket = ClientSocket.getSocket();
			writer = new PrintWriter(socket.getOutputStream());
			String jsonString = (String)msg;
			writer.println(jsonString);
			writer.flush();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
