package client.ctl;

import base.JsonAnalizerBase;

public class JsonAnalizerClient extends JsonAnalizerBase {
	public static String getMessageContent(String jsonString) {
		return getValue(jsonString, JsonBuilderClient.content);
	}
}
