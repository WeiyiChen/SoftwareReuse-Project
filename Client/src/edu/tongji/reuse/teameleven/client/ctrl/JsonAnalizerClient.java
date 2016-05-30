package edu.tongji.reuse.teameleven.client.ctrl;

import edu.tongji.reuse.teameleven.codependent.base.JsonAnalizerBase;
import edu.tongji.reuse.teameleven.codependent.base.JsonBuilderBase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonAnalizerClient extends JsonAnalizerBase {
	public static String getMessageContent(String jsonString) {
		return getValue(jsonString, JsonBuilderClient.content);
	}

	/**
	 * Get a contact list to beforeLoop
	 * @param jsonString - json message
	 * @return - a contact list to beforeLoop
     */
	public static List<String> getInitContacts(String jsonString){
		List<String> strings = new ArrayList<String>();
		try{
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(JsonBuilderBase.content);
			for(int i = 0; i < jsonArray.length(); i++){
				String str = jsonArray.getString(i);
				strings.add(str);
			}
			return strings;
		}catch(JSONException e){
			e.printStackTrace();
			return null;
		}
	}
}
