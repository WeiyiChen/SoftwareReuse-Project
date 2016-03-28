package base;

import org.json.JSONException;
import org.json.JSONStringer;

public class JsonBuilderBase {
	//schema
	public static final String type = "Type";
	public static final String content = "Content";
	public static final String user = "User";
	
	//types
	public static final String authorization = "Authorization";
	public static final String message = "Message";
	public static final String password = "Password";
	
	//content
	public static final String loginFailed = "LoginFailed";
	public static final String loginSucceed = "LoginSucceed";
	public static final String relogin = "Relogin";

	protected static String getTypeContentJson(String type, String content) {
		try {
			String jsonStringer = new JSONStringer().object()
					.key(JsonBuilderBase.type).value(type)
					.key(JsonBuilderBase.content).value(content).
					endObject().toString();
			return jsonStringer;
		} catch (JSONException je) {
			System.out.println(je.getMessage());
			System.out.println("[type=" + type +",content=" + content+"]");
		}
		return "{\"Type\":\"Error\"}";
	}

	protected static String getTypeUserContentJson(String type, String user,
			String content) {
				try {
					String jsonStringer = new JSONStringer().object()
							.key(JsonBuilderBase.type).value(type)
							.key(JsonBuilderBase.user).value(user)
							.key(JsonBuilderBase.content).value(content).
							endObject().toString();
					return jsonStringer;
				} catch (JSONException je) {
					System.out.println(je.getMessage());
					System.out.println("[type=" + type + ",user=" + user +",content=" + content+"]");
				}
				return "{\"Type\":\"Error\"}";
			}

	public static String getLoginSucceedJson() {
		return getTypeContentJson(JsonBuilderBase.authorization, JsonBuilderBase.loginSucceed);
	}

	public static String getLoginFailedJson() {
		return getTypeContentJson(JsonBuilderBase.authorization, JsonBuilderBase.loginFailed);
	}

	public static String getReloginRequestJson() {
		return getTypeContentJson(JsonBuilderBase.authorization, JsonBuilderBase.relogin);
	}

	public JsonBuilderBase() {
		super();
	}

}