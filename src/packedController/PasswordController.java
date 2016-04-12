package packedController;

import java.util.HashMap;
import java.util.Map;

import packedDao.JsonDao;


public class PasswordController{

	private JsonDao userDao;

	private Map<String, String> userMap = new HashMap<String, String>();
	
	public PasswordController(Map<String, String> defaultUserMap){
		userDao = new JsonDao("user.json", defaultUserMap);
		userMap = userDao.read();
	}	
	
	public boolean passwordCheck(String user, String password) {
		// TODO Auto-generated method stub
		if(user == null || password ==null){
			return false;
		}
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
