package server.ctrl;

import java.util.HashMap;
import java.util.Map;

import packedDao.JsonDao;

public class ConfigController {
	private JsonDao configDao;
	private Map<String, String> confMap;
	public static final String maxMsgsPerSecond = "MaxMessagesPerSecond";
	public static final String maxMsgsPerLogin = "MaxMessagesPerLogin";
	public static final String saveCycle = "SaveCycle";

	@SuppressWarnings("serial")
	private static final Map<String, String> defaultConfigMap = new HashMap<String, String>() {
		{
			put(maxMsgsPerSecond, "5");
			put(maxMsgsPerLogin, "100");
			put(saveCycle, "60");
		}
	};

	public ConfigController() {
		configDao = new JsonDao("config", defaultConfigMap);
		confMap = configDao.read();
	}

	public int getInt(String key) {
		return Integer.valueOf(confMap.get(key));
	}

	public String getString(String key) {
		return confMap.get(key);
	}

	public void setString(String key, String value) {
		confMap.put(key, value);
	}

	public void setInt(String key, int value) {
		confMap.put(key, String.valueOf(value));
	}

}
