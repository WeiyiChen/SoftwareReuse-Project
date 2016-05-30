package edu.tongji.reuse.teameleven.processor.ctrl;



import edu.tongji.reuse.teameleven.coserver.dao.KeyValueController;
import edu.tongji.reuse.teameleven.coserver.util.ServerConfigEnum;

import java.util.Map;

public class GroupController extends KeyValueController {

    private static GroupController groupController;

    public static GroupController getInstance(){
        if(null == groupController){
            synchronized (GroupController.class){
                if(null == groupController){
                    groupController = new GroupController(ServerConfigEnum.defaultUserGroupMap);
                }
            }
        }
        return groupController;
    }

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

