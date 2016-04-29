package teamEleven.groupCtrl;

import java.util.Map;

import teamEleven.userKeyValueCtrl.KeyValueController;;

public class GroupController extends KeyValueController {

	public GroupController(Map<String, String> defaultUserMap) {
		super(defaultUserMap, "usergroup.json");
	}
	
	public GroupController() {
		super("usergroup.json");
	}
	
	public String getGroup(String user){
		return super.getValue(user);
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

}
