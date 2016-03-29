package server.ctrl;

import java.util.HashMap;
import java.util.Map;

import server.DAO.UserDAO;
import server.intf.IPasswordController;
import server.json.JsonAnalizerServer;


public class PasswordController implements IPasswordController{

	private UserDAO userDao;
	
	private Map<String, String> userMap = new HashMap<String, String>();
	
	public PasswordController(){
		userDao = new UserDAO();
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
