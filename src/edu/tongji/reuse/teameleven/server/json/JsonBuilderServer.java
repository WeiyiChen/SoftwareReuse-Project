package edu.tongji.reuse.teameleven.server.json;

import edu.tongji.reuse.teameleven.base.JsonBuilderBase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class JsonBuilderServer extends JsonBuilderBase {
	public static final String messageBusyOneSecond = "MessageBusy";
	public static final String typeNotFound  = "TypeNotFound";
	
	public static final String serverError = "ServerError";
	
	public static String getMessageBusyError(){
		return getTypeContentJson(JsonBuilderServer.serverError, JsonBuilderServer.messageBusyOneSecond);
	}
	
	public static String getNeedReloginError(){
		return getTypeContentJson(JsonBuilderServer.serverError, JsonBuilderBase.relogin);
	}
	
	public static String getTypeNoFoundError(){
		return getTypeContentJson(JsonBuilderServer.serverError, JsonBuilderServer.typeNotFound);
	}

	/**
	 * Generate json message contains a string arrays which means the online users
	 * This json message will be used to init client contact list
	 * @param contacts - online users
	 * @return - a json message contains online users
     */
	public static String getInitContactsJson(List<String> contacts){
		JSONArray jsonArray = new JSONArray(contacts);
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put(JsonBuilderBase.type, JsonBuilderBase.contacts);
			jsonObject.put(JsonBuilderBase.content, jsonArray);
			jsonObject.put(JsonBuilderBase.op, JsonBuilderBase.init);
			return jsonObject.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "{\"Type\":\"Error\"}";
	}


	/**
	 * Generate a message to operate client contact list
	 * @param user - the user to be operated(add or remove)
	 * @param op - the operation method add or remove
     * @return - a json message to operate client contact list
     */
	public static String getOpContactsJson(String user, String op){
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put(JsonBuilderBase.type, JsonBuilderBase.contacts);
			jsonObject.put(JsonBuilderBase.content, user);
			jsonObject.put(JsonBuilderBase.op, op);
			return jsonObject.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "{\"Type\":\"Error\"}";
	}

	/**
	 * Generate a json message to add a user to client contact list
	 * @param user - the user to be added
	 * @return - a json message to operate client contact list
     */
	public static String getAddContactsJson(String user){
		return getOpContactsJson(user, JsonBuilderBase.add);
	}

	/**
	 * Generate a json message to remove a user from client contact list
	 * @param user - the user to be removed
	 * @return - a json message to operate client contact list
     */
	public static String getRmContactsJson(String user){
		return getOpContactsJson(user, JsonBuilderBase.remove);
	}
}
