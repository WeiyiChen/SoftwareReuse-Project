package client.ctl;

import client.intf.IAddMsgToUI;
import client.intf.IMsgWindow;

public class AddStrMsgToUI implements IAddMsgToUI{

	@Override
	public void addMsg(IMsgWindow imw, Object msg) throws ClassCastException{
		// TODO Auto-generated method stub
		imw.appendMsgRecord((String)msg);
	}

}
