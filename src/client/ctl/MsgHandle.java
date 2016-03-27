package client.ctl;

import client.intf.IMsgHandle;
import client.intf.IMsgWindow;

public class MsgHandle implements IMsgHandle{
	private IMsgWindow imw;
	
	MsgHandle(){}
	MsgHandle(IMsgWindow _imw){ imw = _imw;}
	
	@Override
	public void sendMessage(Object msg, String targetUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(Object msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
