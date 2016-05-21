package edu.tongji.reuse.teameleven.server.json;

import edu.tongji.reuse.teameleven.base.JsonAnalizerBase;
import edu.tongji.reuse.teameleven.base.JsonBuilderBase;

public class JsonAnalizerServer extends JsonAnalizerBase {
	public static String getUserEncryptedPassword(String jsonString) {
		return getValue(jsonString, JsonBuilderBase.password);
	}

	public static String getPassword(String jsonString){
		return getValue(jsonString, JsonBuilderBase.content);
	}

}