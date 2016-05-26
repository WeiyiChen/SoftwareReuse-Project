package edu.tongji.reuse.teameleven.client.ctrl;

import edu.tongji.reuse.teameleven.client.intf.IAddMsgToUI;
import edu.tongji.reuse.teameleven.client.intf.IMsgWindow;

/**
 * update the UI with message type simple String
 * @author Dai
 *
 */
public class AddStrMsgToUI implements IAddMsgToUI{

	@Override
	public void addMsg(IMsgWindow imw, Object msg) throws ClassCastException{

		imw.appendMsgRecord((String)msg);
	}

}
