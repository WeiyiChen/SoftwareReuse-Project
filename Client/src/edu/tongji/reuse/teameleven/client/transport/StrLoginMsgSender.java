package edu.tongji.reuse.teameleven.client.transport;

import edu.tongji.reuse.teameleven.client.intf.IMsgSender;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Send json type message by socket
 * @author Dai
 *
 */
public class StrLoginMsgSender implements IMsgSender {

	@Override
	public boolean send(Object msg) {

		PrintWriter writer;
		try {
			Socket socket = ClientLoginSocket.getSocket();
			writer = new PrintWriter(socket.getOutputStream());
			String jsonString = (String)msg;
			writer.println(jsonString);
			writer.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
