package edu.tongji.reuse.teameleven.coserver.util;

import edu.tongji.reuse.teameleven.rls.encrypt.message.EncryptImpl;
import edu.tongji.reuse.teameleven.rls.encrypt.message.IEncrypt;

import java.util.HashMap;
import java.util.Map;


public class ServerConfigEnum {

	public static ServerConfigEnum maxMsgsPerSecond = new ServerConfigEnum(
			"MaxMessagesPerSecond", "5");
	public static ServerConfigEnum maxMsgsPerLogin = new ServerConfigEnum(
			"MaxMessagesPerLogin", "100");
	public static ServerConfigEnum saveCycle = new ServerConfigEnum(
			"SaveCycle", "60");
	public static Map<String, String> defaultConfigMap = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			put(maxMsgsPerSecond.key, maxMsgsPerSecond.defaultValue);
			put(maxMsgsPerLogin.key, maxMsgsPerLogin.defaultValue);
			put(saveCycle.key, saveCycle.defaultValue);
		}
	};
	public static IEncrypt encrypt = new EncryptImpl();
	
	public static Map<String, String> defaultUserPwdMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("qyd", encrypt.getTMD5("111"));
			put("cwy", encrypt.getTMD5("111"));
			put("ddy", encrypt.getTMD5("111"));
			put("rkl", encrypt.getTMD5("111"));
		}
	};
	
	public static Map<String, String> defaultUserGroupMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("qyd", "backend");
			put("cwy", "backend");
			put("ddy", "frontend");
			put("rkl", "frontend");
		}
	};

	public ServerConfigEnum(String key, String value) {
		this.key = key;
		this.defaultValue = value;
	}

	private String key;

	private String defaultValue;

	public String getKey() {
		return key;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
}
