package edu.tongji.reuse.teameleven.codependent.base;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.Date;

//generate json string

// define these constants in several inner class may be better.

public class JsonBuilderBase {
	//schema
	public static final String type = "Type";
	public static final String content = "Content";
	public static final String user = "User";
	public static final String time = "Time";
    public static final String op = "Op";
	
	//types
	public static final String authorization = "Authorization";
	public static final String message = "Message";
	public static final String password = "Password";
    public static final String contacts = "Contacts";
	public static final String firstMsg = "FirstMsg";
	
	//content
	public static final String loginFailed = "LoginFailed";
	public static final String loginSucceed = "LoginSucceed";
	public static final String relogin = "Relogin";

    //op
    public static final String add = "Add";
    public static final String remove = "Remove";
    public static final String init = "Init";

	protected static String getTypeContentJson(String type, String content, Date date) {
		try {
			JSONObject jsonObject = new JSONObject();
            jsonObject.put(JsonBuilderBase.type, type);
            jsonObject.put(JsonBuilderBase.content, content);
            jsonObject.put(JsonBuilderBase.time, date.getTime());
            return jsonObject.toString();
		} catch (JSONException je) {
			System.out.println(je.getMessage());
			System.out.println("[type=" + type +",content=" + content+"]");
		}
		return "{\"Type\":\"Error\"}";
	}

    protected static String getTypeContentJson(String type, String content){
        try {
            String jsonStringer = new JSONStringer().object()
                    .key(JsonBuilderBase.type).value(type)
                    .key(JsonBuilderBase.content).value(content)
                    .endObject().toString();
            return jsonStringer;
        } catch (JSONException je) {
            System.out.println(je.getMessage());
            System.out.println("[type=" + type +",content=" + content+"]");
        }
        return "{\"Type\":\"Error\"}";
    }



    protected static String getTypeUserContentJson(String type, String user, String content){
        return getTypeUserContentJson(type, user, content, new Date());
    }



	protected static String getTypeUserContentJson(String type, String user,
			String content, Date date) {
				try {
					String jsonStringer = new JSONStringer().object()
							.key(JsonBuilderBase.type).value(type)
							.key(JsonBuilderBase.user).value(user)
							.key(JsonBuilderBase.content).value(content)
							.key(JsonBuilderBase.time).value(date.getTime())
							.endObject().toString();
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

	public static String getFirstMsgJson(String userId){
		try{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(JsonBuilderBase.type, JsonBuilderBase.firstMsg);
            jsonObject.put(JsonBuilderBase.user, userId);
            return jsonObject.toString();
		}catch(JSONException e){
            e.printStackTrace();
        }
        return "{\"Type\":\"Error\"}";
	}


	public JsonBuilderBase() {
		super();
	}

}