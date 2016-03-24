package base;

import org.json.JSONException;
import org.json.JSONStringer;

import server.json.JsonBuilderServer;

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
					.key(JsonBuilderServer.type).value(type)
					.key(JsonBuilderServer.content).value(content).
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
							.key(JsonBuilderServer.type).value(type)
							.key(JsonBuilderServer.user).value(user)
							.key(JsonBuilderServer.content).value(content).
							endObject().toString();
					return jsonStringer;
				} catch (JSONException je) {
					System.out.println(je.getMessage());
					System.out.println("[type=" + type + ",user=" + user +",content=" + content+"]");
				}
				return "{\"Type\":\"Error\"}";
			}

	public JsonBuilderBase() {
		super();
	}

}