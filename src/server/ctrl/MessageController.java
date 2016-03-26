package server.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MessageController {
	private String UserID;
	private int remainMessageCount;
	private List<Timer> timers = new ArrayList<Timer>();
	
	
	public MessageController(){
		UserID = "";
		remainMessageCount = 0;
	}
	
	private String dealWithTextMessage(String jsonString){
		if(timers.size()>=5 || remainMessageCount>=0){
			return "";
		}
		--remainMessageCount;
		return jsonString;
	}
}
