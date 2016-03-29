package client.ctl;

import client.intf.IAddMsgToUI;
import client.intf.IMsgWindow;

/**
 * update the ui with json type message
 * @author Dai
 *
 */
public class AddJsonMsgToUI implements IAddMsgToUI{

	@Override
	public void addMsg(IMsgWindow imw, Object msg) throws ClassCastException {
		// TODO Auto-generated method stub
		String jsonString = (String)msg;
		String sender = JsonAnalizerClient.getUser(jsonString);
		String content = JsonAnalizerClient.getMessageContent(jsonString);
		imw.appendMsgRecord(sender + " :");
		imw.appendMsgRecord(content);
	}

}
