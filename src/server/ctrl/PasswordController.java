package server.ctrl;

import java.util.HashMap;
import java.util.Map;

import packedDao.JsonDao;
import server.intf.IPasswordController;
import server.json.JsonAnalizerServer;


public class PasswordController implements IPasswordController{

	private JsonDao userDao;
	
	private Map<String, String> userMap = new HashMap<String, String>();
	@SuppressWarnings("serial")
	private static final Map<String, String> defaultUserMap = new HashMap<String, String>(){
	{
		put("qyd","1252865");
		put("cwy","1252874");
	}};
	
	public PasswordController(){
		userDao = new JsonDao("user.json", defaultUserMap);
		userMap = userDao.read();
	}
	
	
	@Override
	public boolean passwordCheck(String jsonString) {
		// TODO Auto-generated method stub
		String user = JsonAnalizerServer.getUser(jsonString);
		String password = JsonAnalizerServer.getPassword(jsonString);
		String pwdInDB = userMap.get(user);
		if(pwdInDB==null){
			return false;
		}
		return pwdInDB.equals(password);
	}
	
	public boolean addUser(String user, String password) {
		if(userMap.containsKey(user)){
			return false;
		}
		userMap.put(user, password);
		userDao.save(userMap);
		return true;
	}
	
	public void quit(){
		userDao.save(userMap);
	}
	
}
