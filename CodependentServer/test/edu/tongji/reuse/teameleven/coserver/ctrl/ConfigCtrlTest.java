package edu.tongji.reuse.teameleven.coserver.ctrl;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by daidongyang on 5/31/16.
 */
public class ConfigCtrlTest {
    @Test
    public void test(){
        System.out.println(ConfigCtrl.getConfig().toString());
    }

}