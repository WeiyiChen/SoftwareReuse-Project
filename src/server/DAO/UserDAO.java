package server.DAO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;


public class UserDAO extends FileDAO <Map<String,String>>{
	protected String fileName = "users.json";
			
	public UserDAO(){
		checkOrCreateFile();
		read();
	}
	
	@Override
	public boolean save(Map<String,String> userMap){
		JSONObject jsonObj= new JSONObject(userMap);
		return FileAccess.fileOverWrite(getPathName(), jsonObj.toString());
	}
	
	@Override
	public Map<String,String> read(){
		String jsonString = FileAccess.readFile(getPathName());
		if(jsonString.equals("")){
			return null;
		}
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			@SuppressWarnings("unchecked")
			Iterator<String> it = jsonObj.keys();
			Map<String,String> userMap = new HashMap<String,String>();
			while(it.hasNext()){
				String key = it.next();
				userMap.put(key, jsonObj.getString(key));
				//System.out.println(key + ":" + jsonObj.getString(key));
			}
			return userMap;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;

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
