package client.ctl;

import base.JsonBuilderBase;

public class JsonBuilderClient extends JsonBuilderBase {
	public static String getPasswordJson(String user,String password){
		return getTypeUserContentJson(JsonBuilderBase.password, user, password);
	}
	
	public static String getMessageJson(String user, String msg){
		return getTypeUserContentJson(JsonBuilderBase.message, user, msg);
	}


}
