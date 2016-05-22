package edu.tongji.reuse.teameleven.model;

/**
 * Created by daidongyang on 5/21/16.
 */
public class User {
    private String userId = "";
    private String group = "";
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

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", group='" + group + '\'' +
                ", isOnLine=" + isOnLine +
                '}';
    }
}
