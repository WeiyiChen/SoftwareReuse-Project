package edu.tongji.reuse.teameleven.client.intf;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * check the user and password when login
 * @author Dai
 *
 */
public interface ILogInCheck {
	/**
	 * 
	 * @param usrName - user name
	 * @param pwd - password
	 * @return true - log in successfully, false - failed to log in.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	boolean check(String usrName, String pwd)throws UnknownHostException, IOException;
	
}
