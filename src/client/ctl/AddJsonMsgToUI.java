package client.ctl;

import client.intf.IAddMsgToUI;
import client.intf.IMsgWindow;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		Date d = JsonAnalizerClient.getTime(jsonString);
		SimpleDateFormat dateFormat = new SimpleDateFormat("K:mm a, yyyy-MM-dd");
		String time = dateFormat.format(d);
		imw.appendMsgRecord(sender + "\t\t" + time);
		imw.appendMsgRecord(content);
	}

}
