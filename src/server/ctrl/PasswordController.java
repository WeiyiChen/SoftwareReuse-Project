package server.ctrl;

import server.DAO.UserDAO;
import server.intf.IPasswordController;
import server.json.JsonAnalizerServer;
import server.json.JsonBuilderServer;
import sun.usagetracker.UsageTrackerClient;


public class PasswordController implements IPasswordController{

	private static UserDAO  userDao = new UserDAO();
	@Override
	public boolean passwordCheck(String jsonString) {
		// TODO Auto-generated method stub
		String user = JsonAnalizerServer.getUser(jsonString);
		String password = JsonAnalizerServer.getPassword(jsonString);
		String pwdInDB = userDao.getPassword(user);
		return pwdInDB.equals(password);
	}

	
}
