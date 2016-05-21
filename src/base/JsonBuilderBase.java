package base;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.Date;

//generate json string

public class JsonBuilderBase {
	//schema
	public static final String type = "Type";
	public static final String content = "Content";
	public static final String user = "User";
	public static final String time = "Time";
	
	//types
	public static final String authorization = "Authorization";
	public static final String message = "Message";
	public static final String password = "Password";
	
	//content
	public static final String loginFailed = "LoginFailed";
	public static final String loginSucceed = "LoginSucceed";
	public static final String relogin = "Relogin";

//	protected static String getTypeContentJson(String type, String content) {
//		try {
//			String jsonStringer = new JSONStringer().object()
//					.key(JsonBuilderBase.type).value(type)
//					.key(JsonBuilderBase.content).value(content)
//					.key(JsonBuilderBase.time).value(new Date())
//					.endObject().toString();
//			return jsonStringer;
//		} catch (JSONException je) {
//			System.out.println(je.getMessage());
//			System.out.println("[type=" + type +",content=" + content+"]");
//		}
//		return "{\"Type\":\"Error\"}";
//	}

    protected static String getTypeContentJson(String type, String content){
        return getTypeContentJson(type, content, new Date());
    }

    protected static String getTypeContentJson(String type, String content, Date d){
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(JsonBuilderBase.type, type);
            jsonObject.put(JsonBuilderBase.content, content);
            jsonObject.put(JsonBuilderBase.time, d);
            String test = jsonObject.toString();
            Date d3 = (Date)jsonObject.get(JsonBuilderBase.time);
            System.out.print(d3);
            JSONObject obj2 = new JSONObject(test);
            Date d2 = (Date)obj2.get(JsonBuilderBase.time);
            System.out.print(d2);
            System.out.print(d);
            return jsonObject.toString();
        }catch(JSONException e){
            e.printStackTrace();
        }
        return "{\"Type\":\"Error\"}";
    }

    protected static String getTypeUserContentJson(String type, String user, String content){
        return getTypeUserContentJson(type, user, content, new Date());
    }

    protected static String getTypeUserContentJson(String type, String user, String content, Date d){
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(JsonBuilderBase.type, type);
            jsonObject.put(JsonBuilderBase.user, user);
            jsonObject.put(JsonBuilderBase.content, content);
            jsonObject.put(JsonBuilderBase.time, d);
            return jsonObject.toString();
        }catch(JSONException e){
            e.printStackTrace();
        }
        return "{\"Type\":\"Error\"}";
    }

//	protected static String getTypeUserContentJson(String type, String user,
//			String content) {
//				try {
//					String jsonStringer = new JSONStringer().object()
//							.key(JsonBuilderBase.type).value(type)
//							.key(JsonBuilderBase.user).value(user)
//							.key(JsonBuilderBase.content).value(content)
//							.key(JsonBuilderBase.time).value(new Date())
//							.endObject().toString();
//					return jsonStringer;
//				} catch (JSONException je) {
//					System.out.println(je.getMessage());
//					System.out.println("[type=" + type + ",user=" + user +",content=" + content+"]");
//				}
//				return "{\"Type\":\"Error\"}";
//			}

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