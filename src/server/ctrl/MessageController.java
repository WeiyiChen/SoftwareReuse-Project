package server.ctrl;

import base.JsonBuilderBase;
import server.json.JsonAnalizerServer;
import server.json.JsonBuilderServer;
import packedController.PasswordController;
import packedController.RecordController;
import packedController.ConfigController;
import packedController.LicenseController;
import packedEncrypt.EncryptImpl;
import packedEncrypt.IEncrypt;

;

public class MessageController {

	static private PasswordController passwordController = new PasswordController(
			ServerConfigEnum.defaultUserPwdMap);
	static private RecordController recordController = new RecordController();
	static private ConfigController configController = new ConfigController(
			ServerConfigEnum.defaultConfigMap);

	private static IEncrypt encrypt = new EncryptImpl();
	
	private LicenseController licenseController;

	private static int maxMessagePerLogin = configController
			.getInt(ServerConfigEnum.maxMsgsPerLogin.getKey(),
					Integer.valueOf(ServerConfigEnum.maxMsgsPerLogin
							.getDefaultValue()));
	private static int maxMessagePerSecond = configController
			.getInt(ServerConfigEnum.maxMsgsPerSecond.getKey(),
					Integer.valueOf(ServerConfigEnum.maxMsgsPerSecond
							.getDefaultValue()));
	static{
		LicenseController.setLimit(maxMessagePerLogin, maxMessagePerSecond);
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
		recordController.setAndStart(configController
				.getInt(ServerConfigEnum.saveCycle.getKey(),
						Integer.valueOf(ServerConfigEnum.saveCycle.getDefaultValue())));
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

	public void quit() {
		recordController.quit();
		passwordController.quit();
	}
}
