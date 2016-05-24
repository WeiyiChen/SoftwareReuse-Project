package edu.tongji.reuse.teameleven.model;

import edu.tongji.reuse.teameleven.base.Jsonable;
import org.json.JSONObject;

/**
 * Created by daidongyang on 5/21/16.
 */
public class User implements Jsonable {
    private String userId = "";
    private String group = "";
    private String pwd = "";
    private boolean isOnLine = false;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean isOnLine() {
        return isOnLine;
    }

    public void setOnLine(boolean onLine) {
        isOnLine = onLine;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", group='" + group + '\'' +
                ", pwd='" + pwd + '\'' +
                ", isOnLine=" + isOnLine +
                '}';
    }

    @Override
    public String toJsonString() {
        return null;
    }

    @Override
    public void setByJsonString(String jsonString) {

    }
}
