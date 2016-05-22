package edu.tongji.reuse.teameleven.server.ctrl;

import java.io.IOException;

import edu.tongji.reuse.teameleven.base.JsonBuilderBase;
import edu.tongji.reuse.teameleven.server.json.JsonAnalizerServer;
import octoteam.tahiti.config.ConfigManager;
import octoteam.tahiti.config.loader.JsonAdapter;
import packedEncrypt.EncryptImpl;
import packedEncrypt.IEncrypt;
import edu.tongji.reuse.teameleven.server.json.JsonBuilderServer;

import teamEleven.pwdCtrl.PasswordController;
import teamEleven.groupCtrl.GroupController;

@Deprecated
public class MessageControllerOld {

	private static PasswordController passwordController = new PasswordController(
			ServerConfigEnum.defaultUserPwdMap);
	private static GroupController groupController = new GroupController(
			ServerConfigEnum.defaultUserGroupMap);

	private static int maxMessagePerLogin;
	private static int maxMessagePerSecond;
	private static int saveCycle;
	private static ConfigManager configManager = new ConfigManager(
			new JsonAdapter(), "data/config.json");
	private static ServerConfigBean configBean;
	private static ZipLogController zipLogController = new ZipLogController();
	private static ReZipLogController reZipLogController = new ReZipLogController();

	private static IEncrypt encrypt = new EncryptImpl();

	private LicenseCtrl licenseController;
	private String currentUser;

	static {
		try {
			configBean = configManager.loadToBean(ServerConfigBean.class);
			maxMessagePerLogin = configBean.getMaxMessagesPerLogin();
			maxMessagePerSecond = configBean.getMaxMessagesPerSecond();
			saveCycle = configBean.getSaveCycle();
			ServerMonitorController.setSaveCycle(saveCycle);
			// license = new License(License.LicenseType.BOTH,
			// maxMessagePerLogin, maxMessagePerSecond);
			LicenseCtrl.setLimit(maxMessagePerLogin, maxMessagePerSecond);
			zipLogController.setAndStart(86400);
			reZipLogController.setAndStart(86400*7);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}

	}

	public MessageControllerOld() {
		// licenseController = new LicenseController();
		licenseController = new LicenseCtrl();
		this.currentUser = "";
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
			// recordController.ignoredNumberAdd();
			currentUser = "";
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
		String password = encrypt.decryptToTMD5(JsonAnalizerServer
				.getPassword(jsonString));

		if (passwordController.passwordCheck(user, password)) {
			licenseController.reset(user);
			ServerMonitorController.increaseLogfailedNumber();
			this.currentUser = user;
			return JsonBuilderServer.getLoginSucceedJson();
		}
		ServerMonitorController.increaseLogfailedNumber();
		return JsonBuilderServer.getLoginFailedJson();
	}

	public void addSendRecord() {
		ServerMonitorController.increaseForwardedNumber();
	}

	public String getGroup() {
		return groupController.getGroup(this.currentUser);
	}

	public boolean hasCurrentUser() {
		return this.currentUser != "";
	}

	public static void quit() {
		ServerMonitorController.getMonitor().stop();
		passwordController.quit();
		groupController.quit();
		zipLogController.quit();
	}
}
