package server.ctrl;

import java.io.IOException;

import base.JsonBuilderBase;
import octoteam.tahiti.config.ConfigManager;
import octoteam.tahiti.config.loader.JsonAdapter;
import packedEncrypt.EncryptImpl;
import packedEncrypt.IEncrypt;
import server.json.JsonAnalizerServer;
import server.json.JsonBuilderServer;
import teamEleven.licenseCtrl.LicenseController;
import teamEleven.pwdCtrl.PasswordController;
import teamEleven.record.RecordController;

;

public class MessageController {

	static private PasswordController passwordController = new PasswordController(
			ServerConfigEnum.defaultUserPwdMap);
	static private RecordController recordController = RecordController.getInstance();
//	static private ConfigController configController = new ConfigController(
//			ServerConfigEnum.defaultConfigMap);
	
	static ConfigManager configManager = new ConfigManager(new JsonAdapter(), "data/config.json");
	static ServerConfigBean configBean;
	
//	static ServerConfigBean 

	private static IEncrypt encrypt = new EncryptImpl();
	
	private LicenseController licenseController;

//	private static int maxMessagePerLogin = configController
//			.getInt(ServerConfigEnum.maxMsgsPerLogin.getKey(),
//					Integer.valueOf(ServerConfigEnum.maxMsgsPerLogin
//							.getDefaultValue()));
//	private static int maxMessagePerSecond = configController
//			.getInt(ServerConfigEnum.maxMsgsPerSecond.getKey(),
//					Integer.valueOf(ServerConfigEnum.maxMsgsPerSecond
//							.getDefaultValue()));
	
	private static int maxMessagePerLogin;
	private static int maxMessagePerSecond;
	private static int saveCycle;
	
	static{
		try{
			configBean = configManager.loadToBean(ServerConfigBean.class);
			maxMessagePerLogin = configBean.getMaxMessagesPerLogin();
			maxMessagePerSecond = configBean.getMaxMessagesPerSecond();
			saveCycle = configBean.getSaveCycle();
			LicenseController.setLimit(maxMessagePerLogin, maxMessagePerSecond);
		}catch(IOException ioe){
			throw new RuntimeException(ioe);
		}
		
	}
	public MessageController() {
		licenseController = new LicenseController();
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
//		recordController.setAndStart(configController
//				.getInt(ServerConfigEnum.saveCycle.getKey(),
//						Integer.valueOf(ServerConfigEnum.saveCycle.getDefaultValue())));
		recordController.setAndStart(saveCycle);
	}

	private String dealWithTextMessage(String jsonString) {
		String user = JsonAnalizerServer.getUser(jsonString);
		int licenseResult = licenseController.receivedMessage(user);
		if (licenseResult != 0) {
			recordController.ignoredNumberAdd();
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

		recordController.receivedNumberAdd();
		return jsonString;
	}

	private String dealWithPassword(String jsonString) {
		String user = JsonAnalizerServer.getUser(jsonString);
		String password = encrypt.decryptToTMD5(JsonAnalizerServer.getPassword(jsonString));

		if (passwordController.passwordCheck(user, password)) {
			licenseController.reset(user);
			recordController.logsucceedNumberAdd();
			return JsonBuilderServer.getLoginSucceedJson();
		}
		recordController.logfailedNumberAdd();
		return JsonBuilderServer.getLoginFailedJson();
	}

	public void addSendRecord() {
		recordController.forwardedNumberAdd();
	}

	public static void quit() {
		recordController.quit();
		//recordController.notify();
		passwordController.quit();
	}
}
