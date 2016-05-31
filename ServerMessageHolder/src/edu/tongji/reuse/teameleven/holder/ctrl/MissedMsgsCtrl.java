package edu.tongji.reuse.teameleven.holder.ctrl;

import edu.tongji.reuse.teameleven.codependent.model.User;
import edu.tongji.reuse.teameleven.holder.model.MessageList;


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

    public Map<String, Integer> getGroupSizes() {
        return groupSizes;
    }

    public void setGroupSizes(Map<String, Integer> groupSizes) {
        this.groupSizes = groupSizes;
    }

    public void setLogoutTime(String user, Long time){
        userLogout.put(user, time);
    }

    public void setLogoutTime(String user){
        setLogoutTime(user, new Date().getTime());
    }

    public void updateGroupSizes(String group, Integer size){
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
        // todo check is right below
        Long logoutTime = userLogout.remove(user.getUserId());
        if(logoutTime == null){
            return null;
        }
        return getMissedMsgs(user.getGroup(), logoutTime, user.getLoginTime());
    }

    public boolean addMessage(String group, String jsonMsg, int groupOnlineCount){
        MessageList messageList = groupMsgs.get(group);
        if(messageList == null){
            int groupSize = groupSizes.get(group);
            messageList = new MessageList(group, groupSize);
        }
        messageList.setGroupOnlineCount(groupOnlineCount);
        groupMsgs.put(group, messageList);
        return messageList.addMessage(jsonMsg);
    }

//    public void initMsgList(String group, int groupOnlineCount){
//        if(groupMsgs.containsKey(group)){
//            setGroupOnLineCount(group, groupOnlineCount);
//            return;
//        }
//        int groupSize = new GroupController().getGroupSize(group);
//        System.out.println("group size : " + groupSize);
//        MessageList messageList = new MessageList(group, groupSize);
//        messageList.setGroupOnlineCount(groupOnlineCount);
//        groupMsgs.put(group, messageList);
//        return;
//    }



//    public boolean addMessage(String group, String jsonMsg){
//        if(group == null){
//            System.err.println("group is null when add json msg for missedMsgsCtrl!");
//            return false;
//        }
//        MessageList messageList = groupMsgs.get(group);
////        System.out.println(group);
//        return messageList.addMessage(jsonMsg);
//    }
//
//    public void setGroupOnLineCount(String group, int groupOnlineCount){
//        MessageList messageList = groupMsgs.get(group);
//        messageList.setGroupOnlineCount(groupOnlineCount);
//    }
//
//    public void addGroupOnLineCount(String group){
//        MessageList messageList = groupMsgs.get(group);
//        messageList.addGroupOnLineCount();
//    }
//
//    public void subGroupOnLineCount(String group){
//        MessageList messageList = groupMsgs.get(group);
//        try{
//            messageList.subGroupConLineCount();
//        }catch(NullPointerException e){
//            // the messageList may be null, which means
//            // the client hasn't log in when exiting.
//            e.printStackTrace(System.out);
//        }
//    }


}
