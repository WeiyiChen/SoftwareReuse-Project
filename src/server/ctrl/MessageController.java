package server.ctrl;

import java.io.IOException;

import base.JsonBuilderBase;
import octoteam.tahiti.config.ConfigManager;
import octoteam.tahiti.config.loader.JsonAdapter;
import packedEncrypt.EncryptImpl;
import packedEncrypt.IEncrypt;
import server.json.JsonAnalizerServer;
import server.json.JsonBuilderServer;

import teamEleven.pwdCtrl.PasswordController;


public class MessageController {

	static private PasswordController passwordController = new PasswordController(
			ServerConfigEnum.defaultUserPwdMap);

	
	static ConfigManager configManager = new ConfigManager(new JsonAdapter(), "data/config.json");
	static ServerConfigBean configBean;
	

	private static IEncrypt encrypt = new EncryptImpl();
	
	private LicenseCtrl licenseController;

	
	private static int maxMessagePerLogin;
	private static int maxMessagePerSecond;
	private static int saveCycle;
	
	static{
		try{
			configBean = configManager.loadToBean(ServerConfigBean.class);
			maxMessagePerLogin = configBean.getMaxMessagesPerLogin();
			maxMessagePerSecond = configBean.getMaxMessagesPerSecond();
			saveCycle = configBean.getSaveCycle();
			ServerMonitorController.setSaveCycle(saveCycle);
//			license = new License(License.LicenseType.BOTH, maxMessagePerLogin, maxMessagePerSecond);
			LicenseCtrl.setLimit(maxMessagePerLogin, maxMessagePerSecond);
		}catch(IOException ioe){
			throw new RuntimeException(ioe);
		}
		
	}
	public MessageController() {
//		licenseController = new LicenseController();
		licenseController = new LicenseCtrl();
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

	public static void startRecordThread() {

		ServerMonitorController.startRecord();
	}

	private String dealWithTextMessage(String jsonString) {
		String user = JsonAnalizerServer.getUser(jsonString);
		int licenseResult = licenseController.receivedMessage(user);
		if (licenseResult != 0) {
//			recordController.ignoredNumberAdd();
			ServerMonitorController.increaseIgnoredNumber();
			if (licenseResult == 1) {
				return JsonBuilderServer.getMessageBusyError();
			}
			if (licenseResult == 2) {
				return JsonBuilderServer.getNeedReloginError();
			}
			if (licenseResult == 3) {
				// how can this happens, I don't know.
				licenseController.stopCounting();
				return JsonBuilderServer.getNeedReloginError();
			}
		}
		ServerMonitorController.increaseReceivedNumber();
		return jsonString;
	}

	private String dealWithPassword(String jsonString) {
		String user = JsonAnalizerServer.getUser(jsonString);
		String password = encrypt.decryptToTMD5(JsonAnalizerServer.getPassword(jsonString));

		if (passwordController.passwordCheck(user, password)) {
			licenseController.reset(user);
			ServerMonitorController.increaseLogfailedNumber();
			return JsonBuilderServer.getLoginSucceedJson();
		}
		ServerMonitorController.increaseLogfailedNumber();

		return JsonBuilderServer.getLoginFailedJson();
	}

	public void addSendRecord() {
		ServerMonitorController.increaseForwardedNumber();
	}

	public static void quit() {

		ServerMonitorController.getMonitor().stop();
		passwordController.quit();
	}
}
