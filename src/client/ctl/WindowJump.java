package client.ctl;

import java.awt.EventQueue;

import client.intf.IMsgWindow;
import client.intf.IWindowJump;
import client.ui.MsgWindow;

public class WindowJump implements IWindowJump {

	@Override
	public void startMsgWindow() {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				IMsgWindow imw = new MsgWindow();
				imw.showMsgWindow();
			}
		});
	}

}
