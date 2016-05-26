package edu.tongji.reuse.teameleven.rls.server.ctrl;

import wheellllll.license.License;
import wheellllll.license.License.LicenseType;

public class LicenseCtrl {
	private static int maxMessagePerLogin = 100;
	private static int maxMessagePerSecond = 5;
	
	private String userID;
	License license;
	
	public LicenseCtrl(){
		userID = "";
		license = new License(License.LicenseType.BOTH, 
				maxMessagePerLogin, maxMessagePerSecond);
	}
	
	/**
	 * @param user String of user's ID
	 * @return
	 * 0 for receive the massage normally. <br>
	 * 1 for reaching the limit of max number of message in one second. <br>
	 * 2 for reaching the limit of max number of message of one author authorization.
	 */
	public int receivedMessage(String user){
		License.Availability availability = license.use();
		if(availability == License.Availability.THROUGHPUTEXCEEDED){
			return 1;
		}
		if(availability == License.Availability.CAPACITYEXCEEDED ||
				availability == License.Availability.BOTHEXCEEDED){
			return 2;
		}
		if(!user.equals(userID)){
			license.reset(License.LicenseType.BOTH);
			return 3;
		}
		return 0;
		
	}
	
	public void reset(String user){
		this.userID = user;
		license.reset(LicenseType.BOTH);
	}
	
	public static void setLimit(int msgsPerLogin, int msgsPerSecond){
		maxMessagePerLogin = msgsPerLogin;
		maxMessagePerSecond = msgsPerSecond;
	}
	
	public void stopCounting(){
		
	}

}
