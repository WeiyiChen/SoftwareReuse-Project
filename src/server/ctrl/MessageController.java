package server.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import server.json.JsonAnalizerServer;
import server.json.JsonBuilderServer;

public class MessageController {
	private int maxMessagePerLogin = 500;
	private int maxMessagePerSecond = 5;

	private String UserID;
	private int remainMessageCount;
	private List<Timer> timers = new ArrayList<Timer>();
	private PasswordController pwdCtrl = new PasswordController();

	public MessageController() {
		UserID = "";
		remainMessageCount = 0;
	}

	public String dealWithMessage(String jsonString) {
		String type = JsonAnalizerServer.getMessageType(jsonString);
		if (type.equals(JsonBuilderServer.message)) {
			return this.dealWithTextMessage(jsonString);
		}
		if (type.equals(JsonBuilderServer.password)) {
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
		if (pwdCtrl.passwordCheck(jsonString)) {
			this.remainMessageCount = this.maxMessagePerLogin;
			this.UserID = JsonAnalizerServer.getUser(jsonString);
			return JsonBuilderServer.getLoginSucceedJson();
		}
		return JsonBuilderServer.getLoginFailedJson();
	}
}
