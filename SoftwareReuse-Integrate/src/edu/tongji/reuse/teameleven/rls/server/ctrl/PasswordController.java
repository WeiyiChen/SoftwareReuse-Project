package edu.tongji.reuse.teameleven.rls.server.ctrl;

import edu.tongji.reuse.teameleven.rls.dao.KeyValueController;

import java.util.Map;

public class PasswordController extends KeyValueController {

    public PasswordController(Map<String, String> defaultUserMap){
        super(defaultUserMap, "userpwd.json");
    }

    public boolean passwordCheck(String user, String password) {
        if(user == null || password ==null){
            return false;
        }
        String pwdInDB = super.getValue(user);
        if(pwdInDB==null){
            return false;
        }
        return pwdInDB.equals(password);
    }

    public boolean addUser(String user, String password) {
        return super.addUserKeyValue(user, password);
    }

    public void quit(){
        super.quit();
    }

}
