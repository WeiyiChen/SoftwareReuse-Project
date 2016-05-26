package edu.tongji.reuse.teameleven.client.ctrl;

import java.net.UnknownHostException;

import edu.tongji.reuse.teameleven.client.intf.IClientWindow;
import edu.tongji.reuse.teameleven.client.intf.IMsgWindow;
import edu.tongji.reuse.teameleven.client.intf.IWindowJump;
import edu.tongji.reuse.teameleven.client.transport.ClientReciever;
import edu.tongji.reuse.teameleven.client.transport.ClientLoginSocket;

/**
 * jump from login window to message window
 * @author Dai
 *
 */
public class WindowJumpFromLoginToMsg implements IWindowJump {

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
			new ClientReciever(ClientLoginSocket.getSocket(), imw.getMsgHandle()).start();
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
