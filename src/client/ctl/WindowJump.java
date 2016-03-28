package client.ctl;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.UnknownHostException;

import client.intf.IMsgWindow;
import client.intf.IWindowJump;
import client.transport.Client11SReciever;
import client.transport.ClientSocket;
import client.ui.MsgWindow;

public class WindowJump implements IWindowJump {

	@Override
	public void startMsgWindow(String usr) {
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				IMsgWindow imw = new MsgWindow();
				imw.showMsgWindow();
				imw.setUsr(usr);
				try {
					new Client11SReciever(ClientSocket.getSocket(),imw.getMsgHandle()).start();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
	}

}
