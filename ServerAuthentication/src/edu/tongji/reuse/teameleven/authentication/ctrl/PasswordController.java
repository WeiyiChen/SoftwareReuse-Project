package edu.tongji.reuse.teameleven.authentication.ctrl;

import edu.tongji.reuse.teameleven.coserver.dao.KeyValueController;

import java.util.Map;

/**
 * check if user and password combination is correct
 * notice: the password has been encryted by md5
 */
public class PasswordController extends KeyValueController {

    public PasswordController(Map<String, String> defaultUserMap){
        super(defaultUserMap, "userpwd.json");
    }

    // todo add more default password, there is only one password encryted from '111' now
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
