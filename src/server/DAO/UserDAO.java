package server.DAO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;


public class UserDAO extends FileDAO {
	protected String fileName = "users.json";
	
	private Map<String, String> userMap = new HashMap<String, String>();
		
	public UserDAO(){
		super();
		checkOrCreateFile();
		read();
	}
	
	@Override
	public void finalize(){
		save();
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
		save();
		return true;
	}
	
	public boolean save(){
		JSONObject jsonObj= new JSONObject(userMap);
		return FileAccess.fileOverWrite(getPathName(), jsonObj.toString());
	}
	
	private void read(){
		String jsonString = FileAccess.readFile(getPathName());
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
	
	@Override
	protected String getBasicString() {
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

	@Override
	public void checkOrCreateFile(){
		super.checkOrMk();
	}

	@Override
	public String getFileName() {
		return fileName;
	}
	
}
