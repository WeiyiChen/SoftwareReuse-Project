package base;

import org.json.JSONException;
import org.json.JSONObject;

// analyze Json message

public class JsonAnalizerBase {

	public static String getMessageType(String jsonString) {
		return getValue(jsonString, JsonBuilderBase.type);
	}

	protected static String getValue(String jsonString, String key) {
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			return jsonObj.getString(key);
		} catch (JSONException je) {
			return "";
		}
	}

	public static String getUser(String jsonString) {
		return getValue(jsonString, JsonBuilderBase.user);
	}

	public JsonAnalizerBase() {
		super();
	}

}