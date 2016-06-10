package edu.tongji.reuse.teameleven.holder.ctrl;

import edu.tongji.reuse.teameleven.codependent.model.User;
import edu.tongji.reuse.teameleven.holder.model.MessageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daidongyang on 5/24/16.
 */
public class MissedMsgsCtrl {

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

    // key:userId, value:logouttime
    private Map<String, Long> userLogout;

    // key: group name, value : a lost messageList related to group
    private Map<String, MessageList> groupMsgs;

    // key: group name, value : group size
    private Map<String, Integer> groupSizes;

    private Logger logger;

    private MissedMsgsCtrl(){
        userLogout = new HashMap<>();
        groupMsgs = new HashMap<>();
        logger = LoggerFactory.getLogger(this.getClass());
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

    public Map<String, Integer> getGroupSizes() {
        return groupSizes;
    }

    public void setGroupSizes(Map<String, Integer> groupSizes) {
        logger.trace("set group seze" + groupSizes);
        this.groupSizes = groupSizes;
    }

    public void setLogoutTime(String user, Long time){
        logger.trace("set logout time :" + user + "," + time);
        userLogout.put(user, time);
    }

    public void setLogoutTime(String user){
        setLogoutTime(user, new Date().getTime());
    }

    public void updateGroupSizes(String group, Integer size){
        logger.trace("updateGroupSize : " + group + "," + size);
        groupSizes.put(group, size);
    }

    /**
     *
     * @param user - userId
     * @return - logout time
     */
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
        logger.trace("missedMsgs" + messageList);
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
        Long logoutTime = userLogout.remove(user.getUserId());
        if(logoutTime == null){
            return null;
        }
        return getMissedMsgs(user.getGroup(), logoutTime, user.getLoginTime());
    }

    public boolean addMessage(String group, String jsonMsg, int groupOnlineCount){
        logger.trace("add message : " + jsonMsg);
        MessageList messageList = groupMsgs.get(group);
        if(messageList == null){
            int groupSize = groupSizes.get(group);
            messageList = new MessageList(group, groupSize);
        }
        messageList.setGroupOnlineCount(groupOnlineCount);
        groupMsgs.put(group, messageList);
        return messageList.addMessage(jsonMsg);
    }


}
