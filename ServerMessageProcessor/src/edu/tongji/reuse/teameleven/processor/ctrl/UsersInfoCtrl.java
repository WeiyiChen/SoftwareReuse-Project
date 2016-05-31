package edu.tongji.reuse.teameleven.processor.ctrl;

import edu.tongji.reuse.teameleven.coserver.ctrl.GroupController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by daidongyang on 5/30/16.
 */
public class UsersInfoCtrl {
    private Map<String, List<String>> groupOnLineUsers;
    private Map<String, Integer> groupCounts;

    public UsersInfoCtrl(){
        groupOnLineUsers = new HashMap<>();
        updateGroupCount();
    }

    public Map<String, List<String>> getGroupOnLineUsers() {
        return groupOnLineUsers;
    }

    public Map<String, Integer> getGroupCounts() {
        return groupCounts;
    }

    public void loginUser(String userId){
        String group = GroupController.getInstance().getGroup(userId);
        List<String> onLineUsers = groupOnLineUsers.get(group);
        if(onLineUsers == null){
            onLineUsers = new LinkedList<>();
            groupOnLineUsers.put(group, onLineUsers);
        }
        onLineUsers.add(userId);
    }

    public List<String> getOnlineGroupMates(String userId){
        String group = GroupController.getInstance().getGroup(userId);
        return groupOnLineUsers.get(group);
    }

    public void logoutUser(String userId){
        String group = GroupController.getInstance().getGroup(userId);
        List<String> onLineUsers = groupOnLineUsers.get(group);
        if(onLineUsers == null){
            System.out.println("cannot remove this online users, because it's group doesn't exit!");
            return;
        }
        onLineUsers.remove(userId);
    }

    public void updateGroupCount(){
        if(groupCounts == null){
            groupCounts = new HashMap<String, Integer>();
        }
        Map<String, String> userGroup = GroupController.getInstance().getUserMap();
        for(String group : userGroup.values()){
            addGroupCount(group);
        }

    }

    public void addGroupCount(String group){
        Integer count = groupCounts.get(group);
        if(count == null){
            count = 0;
        }
        count = count + 1;
        groupCounts.put(group, count);
    }

    public void subGroupCount(String group){
        Integer count = groupCounts.get(group);
        if(count == null){
            return;
        }
        count--;
        groupCounts.put(group, count);
    }
}
