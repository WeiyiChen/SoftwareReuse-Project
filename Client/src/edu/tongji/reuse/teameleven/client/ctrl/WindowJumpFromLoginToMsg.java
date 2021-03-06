package edu.tongji.reuse.teameleven.client.ctrl;

import edu.tongji.reuse.teameleven.client.intf.IMsgWindow;
import edu.tongji.reuse.teameleven.client.intf.IWindowJump;
import edu.tongji.reuse.teameleven.client.intf.IClientWindow;
import edu.tongji.reuse.teameleven.client.transport.ClientLoginSocket;
import edu.tongji.reuse.teameleven.client.transport.ClientMsgSocket;
import edu.tongji.reuse.teameleven.client.transport.ClientReciever;
import edu.tongji.reuse.teameleven.client.transport.StrMsgSender;
import edu.tongji.reuse.teameleven.codependent.base.JsonBuilderBase;

import java.net.UnknownHostException;

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
		boolean result = false;
		try{
			IMsgWindow imw = (IMsgWindow)to;
			imw.toShowWindow();
			ClientRecievers.loginReceiver = new ClientReciever(ClientLoginSocket.getSocket(), imw.getMsgHandle());
			ClientRecievers.msgReceiver = new ClientReciever(ClientMsgSocket.getSocket(), imw.getMsgHandle());
			ClientRecievers.loginReceiver.start();
			ClientRecievers.msgReceiver.start();
//			new StrMsgSender().send(JsonBuilderBase.getFirstMsgJson());
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
