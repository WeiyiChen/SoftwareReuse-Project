package edu.tongji.reuse.teameleven.processor.model;

import edu.tongji.reuse.teameleven.processor.ctrl.GroupController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daidongyang on 5/30/16.
 */
public class UsersInfo {
    private Map<String, List<String>> groupOnLineUsers;
    private Map<String, Integer> groupCounts;

    public UsersInfo(){
        groupOnLineUsers = new HashMap<>();
        updateGroupCount();
    }

    public Map<String, List<String>> getGroupOnLineUsers() {
        return groupOnLineUsers;
    }

    public Map<String, Integer> getGroupCounts() {
        return groupCounts;
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
