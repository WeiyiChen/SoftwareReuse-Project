package teamEleven.configController;

import java.util.Map;
import teamEleven.userKeyValueCtrl.KeyValueController;

public class ConfigController extends KeyValueController {

	public ConfigController() {
		super("config");
	}

	public ConfigController(Map<String, String> defaultConfigMap) {
		super(defaultConfigMap, "config11s");
	}

	public int getInt(String key, int defaultValue) {
		try {
			return Integer.valueOf(super.getValue(key));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public int getInt(String key) {
		try {
			return Integer.valueOf(super.getValue(key));
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}

	public String getString(String key, String defaultValue) {
		return super.getValue(key) != null ? super.getValue(key) : defaultValue;
	}

	public String getString(String key) {
		if (null == super.getValue(key)) {
			throw new RuntimeException("config doesn't contains " + key);
		}
		return super.getValue(key);
	}

	public void setString(String key, String value) {
		super.forceSetKeyValue(key, value);
	}

	public void setInt(String key, int value) {
		super.forceSetKeyValue(key, String.valueOf(value));
	}

	public void refresh() {
		super.refresh();
	}

}
