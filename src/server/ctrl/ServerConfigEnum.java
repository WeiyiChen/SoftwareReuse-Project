package server.ctrl;

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
		{
			put(maxMsgsPerSecond.key, maxMsgsPerSecond.defaultValue);
			put(maxMsgsPerLogin.key, maxMsgsPerLogin.defaultValue);
			put(saveCycle.key, saveCycle.defaultValue);
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
