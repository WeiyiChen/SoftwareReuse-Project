package server.json;

import base.JsonAnalizerBase;
import base.JsonBuilderBase;

public class JsonAnalizerServer extends JsonAnalizerBase {
	public static String getUserEncryptedPassword(String jsonString) {
		return getValue(jsonString, JsonBuilderBase.password);
	}

	public static String getUser(String jsonString){
		return getValue(jsonString, JsonBuilderBase.user);
	}
	
	public static String getPassword(String jsonString){
		return getValue(jsonString, JsonBuilderBase.content);
	}

}
