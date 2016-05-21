package base;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

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

	public static Long getTime(String jsonString){
		try{
			JSONObject jsonObj = new JSONObject(jsonString);
			Long d = jsonObj.getLong(JsonBuilderBase.time);
			return d;
		}catch(JSONException e){
			e.printStackTrace();
			return null;
		}
	}

	public JsonAnalizerBase() {
		super();
	}

}