package junitTest.server;

import org.junit.Before;
import org.junit.Test;

import client.ctl.JsonBuilderClient;
import server.ctrl.MessageController;

public class MessageControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDealWithMessage() {
		MessageController msgs = new MessageController();
		System.out.println(msgs.dealWithMessage(JsonBuilderClient.getPasswordJson("qyd", "12525")));
		System.out.println(msgs.dealWithMessage(JsonBuilderClient.getPasswordJson("qyd", "1252865")));
		try{
			for(int i =0 ;i<31;i++){
				System.out.println(msgs.dealWithMessage(JsonBuilderClient.getMessageJson("qyd", "message"+i)));
				Thread.sleep(100);
			}
				System.out.println(msgs.dealWithMessage(JsonBuilderClient.getMessageJson("ssd", "messagessd")));
		}catch(InterruptedException ie){
			
		}
		System.out.println(msgs.dealWithMessage(JsonBuilderClient.getPasswordJson("qyd", "1252865")));
		try{
			for(int i =0 ;i<31;i++){
				System.out.println(msgs.dealWithMessage(JsonBuilderClient.getMessageJson("qyd", "message"+i)));
				Thread.sleep(100);
			}
				System.out.println(msgs.dealWithMessage(JsonBuilderClient.getMessageJson("ssd", "messagessd")));
		}catch(InterruptedException ie){
			
		}
		msgs.quit();
	}

}
