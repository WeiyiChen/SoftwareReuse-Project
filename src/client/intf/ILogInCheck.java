package client.intf;

import java.io.IOException;
import java.net.UnknownHostException;

public interface ILogInCheck {
	boolean check(String usrName, String pwd)throws UnknownHostException, IOException;
	
}
