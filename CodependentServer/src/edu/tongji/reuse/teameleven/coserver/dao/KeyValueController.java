package edu.tongji.reuse.teameleven.coserver.dao;

import java.util.HashMap;
import java.util.Map;

//import packedDao.JsonDao;

/**
 * @author Qin YiDan: prototype is PasswordController
 * @author Chen Weiyi: change to KeyValueController
 */
public class KeyValueController{

    private JsonDao userDao;

    private Map<String, String> userMap = new HashMap<String, String>();

    public KeyValueController(Map<String, String> defaultUserMap, String dataFileName){
        userDao = new JsonDao(dataFileName, defaultUserMap);
        userMap = userDao.read();
    }

    public KeyValueController(String dataFileName){
        userDao = new JsonDao(dataFileName);
        userMap = userDao.read();
    }

    /**
     * @param key
     * @param value
     * @return
     */
    protected boolean addUserKeyValue(String key, String value) {
        if(userMap.containsKey(key)){
            return false;
        }
        userMap.put(key, value);
        userDao.save(userMap);
        return true;
    }

    protected boolean updateUserKeyValue(String key, String value){
        if(!userMap.containsKey(key)){
            return false;
        }
        userMap.put(key, value);
        userDao.save(userMap);
        return true;
    }

    protected void forceSetKeyValue(String key, String value){
        userMap.put(key, value);
        userDao.save(userMap);
    }

    protected String getValue(String key){
        return userMap.get(key);
    }

    public void quit(){
        userDao.save(userMap);
    }

    protected void refresh(){
        userMap = userDao.read();
    }

    public Map<String, String> getUserMap() {
        return userMap;
    }
}
