package edu.tongji.reuse.teameleven.model;

/**
 * Created by daidongyang on 5/21/16.
 */
public class User {
    private String user = "";
    private String group = "";

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
