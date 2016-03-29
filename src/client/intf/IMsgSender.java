package client.intf;

//import java.net.Socket;

/**
 * Abstract and encapsulate the method to send message.
 * The specific methods to send message (such as socket) is implements by its subclass
 * @author Dai
 *
 */
public interface IMsgSender {
	
	/**
	 * 
	 * @param msg - message to sender
	 * @return true - send successfully, false - failed to send.
	 */
	boolean send(Object msg);
}
