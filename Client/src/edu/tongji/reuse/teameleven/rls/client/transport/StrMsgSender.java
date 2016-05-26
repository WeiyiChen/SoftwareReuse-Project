package edu.tongji.reuse.teameleven.rls.client.transport;

import edu.tongji.reuse.teameleven.rls.client.intf.IMsgSender;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Send json type message by socket
 * @author Dai
 *
 */
public class StrMsgSender implements IMsgSender {

	@Override
	public boolean send(Object msg) {
		// TODO Auto-generated method stub		
		PrintWriter writer;
		try {
			Socket socket = ClientLoginSocket.getSocket();
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
