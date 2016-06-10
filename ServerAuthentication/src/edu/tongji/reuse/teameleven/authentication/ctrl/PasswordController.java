package edu.tongji.reuse.teameleven.authentication.ctrl;

import edu.tongji.reuse.teameleven.coserver.dao.KeyValueController;
import edu.tongji.reuse.teameleven.coserver.util.ServerConfigEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * check if user and password combination is correct
 * notice: the password has been encryted by md5
 */
public class PasswordController extends KeyValueController {

    private static PasswordController passwordController;
    private Logger logger = LoggerFactory.getLogger(PasswordController.class.getName());
    public static PasswordController getInstance(){

        if(null == passwordController){
            synchronized (PasswordController.class){
                if(null == passwordController){
                    passwordController = new PasswordController(ServerConfigEnum.defaultUserPwdMap);
                }
            }
        }
        return passwordController;
    }

    public PasswordController(Map<String, String> defaultUserMap){
        super(defaultUserMap, "userpwd.json");
    }

    // todo add more default password, there is only one password encryted from '111' now
    public boolean passwordCheck(String user, String password) {
        logger.trace("in method passwordCheck");
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
        logger.trace("add user");
        return super.addUserKeyValue(user, password);
    }

    public void quit(){
        logger.trace("quit");
        super.quit();
    }

}
