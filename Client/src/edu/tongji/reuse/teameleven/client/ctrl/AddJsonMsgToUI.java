package edu.tongji.reuse.teameleven.client.ctrl;

import edu.tongji.reuse.teameleven.client.intf.IAddMsgToUI;
import edu.tongji.reuse.teameleven.client.intf.IMsgWindow;

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

		String jsonString = (String)msg;
		String sender = JsonAnalizerClient.getUser(jsonString);
		String content = JsonAnalizerClient.getMessageContent(jsonString);
		Date d = new Date(JsonAnalizerClient.getTime(jsonString));
		SimpleDateFormat dateFormat = new SimpleDateFormat("K:mm a, yyyy-MM-dd");
		String time = dateFormat.format(d);
		imw.appendMsgRecord(sender + "\t" + time);
		imw.appendMsgRecord(content);
	}

}
