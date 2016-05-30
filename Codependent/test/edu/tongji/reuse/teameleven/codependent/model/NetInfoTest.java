package edu.tongji.reuse.teameleven.codependent.model;

import static org.junit.Assert.*;

/**
 * Created by daidongyang on 5/29/16.
 */
public class NetInfoTest {
    @org.junit.Test
    public void test(){
        NetInfo netInfo = new NetInfo();
        netInfo.setIp("127.0.0.1");
        netInfo.setPort(12345);
        System.out.println(netInfo);
        String jsonString = netInfo.toJsonString();
        NetInfo netInfo1 = new NetInfo();
        netInfo1.setByJsonString(jsonString);
        System.out.println(netInfo1);
    }

    @org.junit.Test
    public void toJsonString() throws Exception {

    }

    @org.junit.Test
    public void setByJsonString() throws Exception {

    }

}