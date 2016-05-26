package edu.tongji.reuse.teameleven.rls.server.ctrl;

import edu.tongji.reuse.teameleven.rls.dao.KeyValueController;

import java.util.Map;

public class GroupController extends KeyValueController {

    public GroupController(Map<String, String> defaultUserMap) {
        super(defaultUserMap, "usergroup.json");
    }

    public GroupController() {
        super("usergroup.json");
    }

    public String getGroup(String user) {
        if (super.getValue(user) != null) {
            return super.getValue(user);
        } else {
            return "";
        }
    }

    public boolean addUserKeyValue(String user, String group) {
        return super.addUserKeyValue(user, group);
    }

    public boolean updateUserKeyValue(String user, String group) {
        return super.updateUserKeyValue(user, group);
    }

    public void quit() {
        super.quit();
    }

    public void refresh() {
        super.refresh();
    }

    public int getGroupSize(String group){
        int groupSize = 0;
        for(String str : super.getUserMap().values()){
            if(group.equals(str)){
                groupSize++;
            }
        }
        return groupSize;
    }


}

