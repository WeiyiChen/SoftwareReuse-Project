package edu.tongji.reuse.teameleven.processor.model;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.Assert.*;

/**
 * Created by daidongyang on 5/30/16.
 */
public class UsersInfoTest {
    @Test
    public void test(){
        UsersInfo usersInfo = new UsersInfo();
        Map<String, List<String>> groupOnlineUsers = usersInfo.getGroupOnLineUsers();
        Map<String, Integer> groupCounts = usersInfo.getGroupCounts();
        System.out.println(groupCounts);
        usersInfo.addGroupCount("backend");
        usersInfo.subGroupCount("frontend");
        System.out.println(groupCounts);
    }

}