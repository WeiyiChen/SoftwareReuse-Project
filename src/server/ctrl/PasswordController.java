package server.ctrl;

import server.intf.IPasswordController;
import server.json.JsonAnalizer;
import server.json.JsonBuilderServer;


public class PasswordController implements IPasswordController{

	@Override
	public boolean passwordCheck(String jsonString) {
		// TODO Auto-generated method stub
		String user = JsonAnalizer.getUser(jsonString);
		String password = JsonAnalizer.getPassword(jsonString);
		return false;
	}

	
}
