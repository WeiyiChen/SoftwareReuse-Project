package server.DAO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonDao extends FileDAO<Map<String,String>>{
	protected String fileName = "";
	private Map<String, String> defaultContentMap;
	
	/*
	 * 
	 * 
	 * */
	public JsonDao(String fileName){
		super();
		this.fileName = fileName.endsWith(".json") ? fileName : fileName + ".json";
		this.defaultContentMap = new HashMap<String, String>();
		checkOrCreateFile();
	}
	
	public JsonDao(String fileName, Map<String, String> defaultContentMap){
		super();
		this.fileName = fileName.endsWith(".json") ? fileName : fileName + ".json";
		this.defaultContentMap = new HashMap<String, String>(defaultContentMap);
		checkOrCreateFile();
	}
	
	@Override
	public boolean save(Map<String,String> userMap){
		JSONObject jsonObj= new JSONObject(userMap);
		checkOrCreateFile();
		return FileAccess.fileOverWrite(getPathName(), jsonObj.toString());
	}
	
	@Override
	public Map<String,String> read(){
		String jsonString = FileAccess.readFile(getPathName());
		Map<String,String> userMap = new HashMap<String,String>();
		if(jsonString.equals("")){
			return userMap;
		}
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			@SuppressWarnings("unchecked")
			Iterator<String> it = jsonObj.keys();
			while(it.hasNext()){
				String key = it.next();
				userMap.put(key, jsonObj.getString(key));
				//System.out.println(key + ":" + jsonObj.getString(key));
			}
			return userMap;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return userMap;
	}
	
	@Override
	protected String getBasicString() {
		JSONObject jo = new JSONObject(defaultContentMap);
		return jo.toString();
	}

	@Override
	public void checkOrCreateFile(){
		super.checkOrMk();
	}

	@Override
	public String getFileName() {
		return fileName;
	}
	
}
