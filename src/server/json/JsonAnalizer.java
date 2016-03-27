package server.json;

import org.json.JSONException;
import org.json.JSONObject;
import base.JsonBuilderBase;

public class JsonAnalizer {
	public static String getUserEncryptedPassword(String jsonString) {
		return getValue(jsonString, JsonBuilderBase.password);
	}

	public static String getMessageType(String jsonString) {
		return getValue(jsonString, JsonBuilderBase.type);
	}
	
	public static String getUser(String jsonString){
		return getValue(jsonString, JsonBuilderBase.user);
	}
	
	public static String getPassword(String jsonString){
		return getValue(jsonString, JsonBuilderBase.password);
	}

	protected static String getValue(String jsonString, String key) {
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			return jsonObj.getString(key);
		} catch (JSONException je) {
			return "";
		}
	}

}
