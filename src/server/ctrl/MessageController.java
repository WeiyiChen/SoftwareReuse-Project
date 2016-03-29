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
	static private PasswordController passwordController = new PasswordController();
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
			recordController.ignoredNumberAdd();
			return JsonBuilderServer.getMessageBusyError();
		}
		if (remainMessageCount <= 0) {
			recordController.ignoredNumberAdd();
			return JsonBuilderServer.getNeedReloginError();
		}
		if(!JsonAnalizerServer.getUser(jsonString).equals(UserID)){
			recordController.logfailedNumberAdd();
			//how can this happens, I don't know.
			remainMessageCount = 0;
			return JsonBuilderServer.getNeedReloginError();
		}
		recordController.receivedNumberAdd();
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
		if (passwordController.passwordCheck(jsonString)) {
			this.remainMessageCount = this.maxMessagePerLogin;
			this.UserID = JsonAnalizerServer.getUser(jsonString);
			recordController.logsucceedNumberAdd();
			return JsonBuilderServer.getLoginSucceedJson();
		}
		recordController.logfailedNumberAdd();
		return JsonBuilderServer.getLoginFailedJson();
	}
	
	public void addSendRecord(){
		recordController.forwardedNumberAdd();
	}
	
	public void quit(){
		recordController.quit();
		passwordController.quit();
	}
}
