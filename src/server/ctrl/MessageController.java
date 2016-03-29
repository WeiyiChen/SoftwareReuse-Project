package server.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import base.JsonBuilderBase;
import server.json.JsonAnalizerServer;
import server.json.JsonBuilderServer;

public class MessageController {
	private int maxMessagePerLogin = 10;
	private int maxMessagePerSecond = 5;

	private String UserID;
	private int remainMessageCount;
	private List<Timer> timers = new ArrayList<Timer>();
	static private PasswordController passwordCtrl = new PasswordController();
	static private RecordController recordController = new RecordController();

	public MessageController() {
		UserID = "";
		remainMessageCount = 0;
	}

	public String dealWithMessage(String jsonString) {
		String type = JsonAnalizerServer.getMessageType(jsonString);
		if (type.equals(JsonBuilderBase.message)) {
			return this.dealWithTextMessage(jsonString);
		}
		if (type.equals(JsonBuilderBase.password)) {
			return this.dealWithPassword(jsonString);
		}
		return JsonBuilderServer.getTypeNoFoundError();
	}

	private String dealWithTextMessage(String jsonString) {
		if (timers.size() >= this.maxMessagePerSecond) {
			return JsonBuilderServer.getMessageBusyError();
		}
		if (remainMessageCount <= 0) {
			return JsonBuilderServer.getNeedReloginError();
		}
		if(!JsonAnalizerServer.getUser(jsonString).equals(UserID)){
			//how can this happens, I don't know.
			return JsonBuilderServer.getNeedReloginError();
		}
		--remainMessageCount;
		Timer t = new Timer();
		timers.add(t);
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				timers.remove(t);
			}
		}, 1000);
		return jsonString;
	}

	private String dealWithPassword(String jsonString) {
		if (passwordCtrl.passwordCheck(jsonString)) {
			this.remainMessageCount = this.maxMessagePerLogin;
			this.UserID = JsonAnalizerServer.getUser(jsonString);
			return JsonBuilderServer.getLoginSucceedJson();
		}
		return JsonBuilderServer.getLoginFailedJson();
	}
	
	public void quit(){
		
	}
}
