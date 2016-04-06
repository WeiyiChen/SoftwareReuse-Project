package server.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import server.json.JsonAnalizerServer;
import server.json.JsonBuilderServer;

public class LicenseController {

	private static int maxMessagePerLogin;
	private static int maxMessagePerSecond;
	
	private String UserID;
	private int remainMessageCount;
	
	private List<Timer> timers = new ArrayList<Timer>();
	
	public  LicenseController(){
		UserID = "";
		remainMessageCount = 0;
		
	}
	
	/**
	 * @param user Stirng of user's ID
	 * @return
	 * 0 for receive the massage normally. <br>
	 * 1 for reaching the limit of max number of message in one second. <br>
	 * 2 for reaching the limit of max number of message of one author authorization.
	 */
	public int receivedMessage(String user){
		if (timers.size() >= LicenseController.maxMessagePerSecond) {
			return 1;
		}
		if (remainMessageCount <= 0) {
			return 2;
		}
		if (!user.equals(UserID)) {
			// how can this happens, I don't know.
			remainMessageCount = 0;
			return 3;
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
		return 0;
	}
	
	public void reset(String user){
		this.UserID =user;
		remainMessageCount = maxMessagePerLogin;
	}
	
	public static void setLimit(int msgsPerLogin, int msgsPerSecong){
		maxMessagePerLogin = msgsPerLogin;
		maxMessagePerSecond = msgsPerSecong;
	}
	
	public void stopCounting(){
		remainMessageCount = 0;
	}
	
}
