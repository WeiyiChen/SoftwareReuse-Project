package client.ctl;

import java.net.UnknownHostException;

import client.intf.IClientWindow;
import client.intf.IMsgWindow;
import client.intf.IWidnowJump;
import client.transport.Client11SReciever;
import client.transport.ClientSocket;

public class WindowJumpFromLoginToMsg implements IWidnowJump {

	/**
	 * notice: login window hasn't been closed.
	 */
	@Override
	public boolean jump(IClientWindow from, IClientWindow to) {
		// TODO Auto-generated method stub
		boolean result = false;
		try{
			IMsgWindow imw = (IMsgWindow)to;
			imw.toShowWindow();
			new Client11SReciever(ClientSocket.getSocket(), imw.getMsgHandle());
			result = true;
		}catch(ClassCastException e1){
			e1.printStackTrace();
		}catch(UnknownHostException e2){
			e2.printStackTrace();
		}catch(Exception e3){
			e3.printStackTrace();
		}
		return result;
	}

}
