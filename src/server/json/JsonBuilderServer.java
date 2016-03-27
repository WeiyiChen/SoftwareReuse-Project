package server.json;

import base.JsonBuilderBase;


public class JsonBuilderServer extends JsonBuilderBase {
	public static final String messageBusyOneSecond = "MessageBusy";
	public static final String typeNotFound  = "TypeNotFound";
	
	public static final String serverError = "ServerError";
	
	public static String getLoginSucceedJson() {
		return getTypeContentJson(JsonBuilderBase.authorization, JsonBuilderBase.loginSucceed);
	}

	public static String getLoginFailedJson() {
		return getTypeContentJson(JsonBuilderBase.authorization, JsonBuilderBase.loginFailed);
	}
	
	public static String getReloginRequestJson(){
		return getTypeContentJson(JsonBuilderBase.authorization, JsonBuilderBase.relogin);
	}
	
	public static String getMessageBusyError(){
		return getTypeContentJson(JsonBuilderServer.serverError, JsonBuilderServer.messageBusyOneSecond);
	}
	
	public static String getNeedReloginError(){
		return getTypeContentJson(JsonBuilderServer.serverError, JsonBuilderServer.relogin);
	}
	
	public static String getTypeNoFoundError(){
		return getTypeContentJson(JsonBuilderServer.serverError, JsonBuilderServer.typeNotFound);
	}
}
