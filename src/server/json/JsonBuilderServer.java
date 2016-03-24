package server.json;

import base.JsonBuilderBase;


public class JsonBuilderServer extends JsonBuilderBase {
	public static String getLoginSucceedJson() {
		return getTypeContentJson(JsonBuilderBase.authorization, JsonBuilderBase.loginSucceed);
	}

	public static String getLoginFailedJson() {
		return getTypeContentJson(JsonBuilderBase.authorization, JsonBuilderBase.loginFailed);
	}
	
	public static String getReloginRequestJson(){
		return getTypeContentJson(JsonBuilderBase.authorization, JsonBuilderBase.relogin);
	}
}
