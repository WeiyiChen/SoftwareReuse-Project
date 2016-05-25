package edu.tongji.reuse.teameleven.server.ctrl;

import edu.tongji.reuse.teameleven.model.User;
import edu.tongji.reuse.teameleven.server.model.MessageList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daidongyang on 5/24/16.
 */
public class MissedMsgsCtrl {
    private Map<String, Long> userLogout;
    private Map<String, MessageList> groupMsgs;

    private static MissedMsgsCtrl lostMsgsCtrl = null;

    public static MissedMsgsCtrl getInstance(){
        if(null == lostMsgsCtrl){
            synchronized (MissedMsgsCtrl.class){
                if(null == lostMsgsCtrl){
                    lostMsgsCtrl = new MissedMsgsCtrl();
                }
            }
        }
        return lostMsgsCtrl;
    }

    private MissedMsgsCtrl(){
        userLogout = new HashMap<>();
        groupMsgs = new HashMap<>();
    }

    public Map<String, Long> getUserLogout() {
        return userLogout;
    }

    public void setUserLogout(Map<String, Long> userLogout) {
        this.userLogout = userLogout;
    }

    public Map<String, MessageList> getGroupMsgs() {
        return groupMsgs;
    }

    public void setGroupMsgs(Map<String, MessageList> groupMsgs) {
        this.groupMsgs = groupMsgs;
    }

    public void setLogoutTime(String user, Long time){
        userLogout.put(user, time);
    }

    public void setLogoutTime(String user){
        setLogoutTime(user, new Date().getTime());
    }

    public Long rmLogoutTime(String user){
        return userLogout.remove(user);
    }

    public Long getLogoutTime(String user){
        return userLogout.get(user);
    }

    public List<String> getMissedMsgs(String group, long logoutTime, long loginTime){
        MessageList messageList = groupMsgs.get(group);
        if(messageList == null){
            return null;
        }
        return messageList.getMissedMsgs(logoutTime, loginTime);
    }

    public List<String> getMissedMsgs(User user){
        Long logoutTime = userLogout.get(user.getUserId());
        if(logoutTime == null){
            return null;
        }
        return getMissedMsgs(user.getGroup(), logoutTime, user.getLoginTime());
    }

    public List<String> getMissedMsgsAndUpdateUser(User user){
        Long logoutTime = userLogout.get(user.getUserId());
        if(logoutTime == null){
            return null;
        }
        return getMissedMsgs(user.getGroup(), logoutTime, user.getLoginTime());
    }

    public boolean addMessage(String group, String jsonMsg, int groupOnlineCount){
        MessageList messageList = groupMsgs.get(group);
        if(messageList == null){
            int groupSize = new GroupController().getGroupSize(group);
            messageList = new MessageList(group, groupSize);
            messageList.setGroupOnlineCount(groupOnlineCount);
            groupMsgs.put(group, messageList);
        }
        return messageList.addMessage(jsonMsg);
    }

    public void setGroupOnLineCount(String group, int groupOnlineCount){
        MessageList messageList = groupMsgs.get(group);
        messageList.setGroupOnlineCount(groupOnlineCount);
    }
}
