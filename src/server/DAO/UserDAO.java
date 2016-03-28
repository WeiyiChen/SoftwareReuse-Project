package server.DAO;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;


public class UserDAO {
	private String dirPath = "./data";
	private String filePath = "./data/users.json";
	
	private Map<String, String> userMap = new HashMap<String, String>();
		
	public UserDAO(){
		checkOrCreateJsonFile();
		getUsersAndPassword();
	}
	
	@Override
	public void finalize(){
		saveUsers();
	}
	
	public String getPassword(String user) {
		String pwd = userMap.get(user);
		if(pwd == null){
			pwd = "";
		}
		return pwd;
	}
	
	public boolean addUser(String user, String password) {
		if(userMap.containsKey(user)){
			return false;
		}
		userMap.put(user, password);
		saveUsers();
		return true;
	}
	
	private String getBasicUserString() {
		String basicUserString = "";
		try {
			JSONStringer js = new JSONStringer();
			basicUserString = js.object().key("qyd").value("1252865")
					.key("cwy").value("1252874").endObject().toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return basicUserString;
	}
	
	private void getUsersAndPassword(){
		String jsonString = FileAccess.readFile(filePath);
		if(jsonString.equals("")){
			return;
		}
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			@SuppressWarnings("unchecked")
			Iterator<String> it = jsonObj.keys();
			userMap.clear();
			while(it.hasNext()){
				String key = it.next();
				userMap.put(key, jsonObj.getString(key));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
	public void checkOrCreateJsonFile(){
		
		try {
			File dataFolder = new File(dirPath);
			if (!dataFolder.exists() || !dataFolder.isDirectory()) {
				dataFolder.mkdir();
			}
			File dataFile = new File(filePath);
			if(!dataFile.exists() || !dataFile.isFile()){
				dataFile.createNewFile();
				FileAccess.fileOverWrite(filePath, getBasicUserString());
			}
		} catch (Exception e) {

		}
		
		
	}
	
	public boolean saveUsers(){
		JSONObject jsonObj= new JSONObject(userMap);
		System.out.println("save:"+jsonObj.toString());
		return FileAccess.fileOverWrite(filePath, jsonObj.toString());
	}
}
