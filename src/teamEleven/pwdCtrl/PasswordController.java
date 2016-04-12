package teamEleven.pwdCtrl;

import java.util.HashMap;
import java.util.Map;

//import packedDao.JsonDao;

/**
 * 用于验证用户密码是否正确，以及向数据库中添加用户，
 * 这里的“数据库”指的是项目根目录下data文件下的user.json文件
 * @author Qin YiDan
 *
 */
public class PasswordController{

	private JsonDao userDao;

	private Map<String, String> userMap = new HashMap<String, String>();
	
	public PasswordController(Map<String, String> defaultUserMap){
		userDao = new JsonDao("user.json", defaultUserMap);
		userMap = userDao.read();
	}	
	
	/**
	 * 验证用户密码是否正确
	 * @param user
	 * @param password
	 * @return
	 */
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
	
	/**
	 * 向数据库中添加用户
	 * @param user
	 * @param password
	 * @return
	 */
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
