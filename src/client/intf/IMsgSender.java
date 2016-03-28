package client.intf;

import java.net.Socket;

public interface IMsgSender {
	boolean send(Socket socket, Object object);
}
