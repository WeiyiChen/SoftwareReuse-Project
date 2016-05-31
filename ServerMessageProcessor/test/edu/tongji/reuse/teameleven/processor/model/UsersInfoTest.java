package edu.tongji.reuse.teameleven.processor.model;

import edu.tongji.reuse.teameleven.processor.ctrl.UsersInfoCtrl;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by daidongyang on 5/30/16.
 */
public class UsersInfoTest {
    @Test
    public void test(){
        UsersInfoCtrl usersInfoCtrl = new UsersInfoCtrl();
        Map<String, List<String>> groupOnlineUsers = usersInfoCtrl.getGroupOnLineUsers();
        Map<String, Integer> groupCounts = usersInfoCtrl.getGroupCounts();
        System.out.println(groupCounts);
        usersInfoCtrl.addGroupCount("backend");
        usersInfoCtrl.subGroupCount("frontend");
        System.out.println(groupCounts);
    }

}