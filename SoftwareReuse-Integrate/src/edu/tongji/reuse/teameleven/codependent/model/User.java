package edu.tongji.reuse.teameleven.codependent.model;

/**
 * Created by daidongyang on 5/21/16.
 */
public class User{
    private String userId = "";
    private String group = "";
    private String pwd = "";
    private boolean isOnLine = false;
    private long loginTime;

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

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (isOnLine != user.isOnLine) return false;
        if (loginTime != user.loginTime) return false;
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (group != null ? !group.equals(user.group) : user.group != null) return false;
        return pwd != null ? pwd.equals(user.pwd) : user.pwd == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (isOnLine ? 1 : 0);
        result = 31 * result + (int) (loginTime ^ (loginTime >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", group='" + group + '\'' +
                ", pwd='" + pwd + '\'' +
                ", isOnLine=" + isOnLine +
                ", loginTime=" + loginTime +
                '}';
    }
}
