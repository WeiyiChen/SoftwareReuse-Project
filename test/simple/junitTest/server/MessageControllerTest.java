package simple.junitTest.server;

import org.junit.Before;
import org.junit.Test;

import edu.tongji.reuse.teameleven.client.ctl.JsonBuilderClient;
import edu.tongji.reuse.teameleven.server.ctrl.MessageControllerOld;

public class MessageControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDealWithMessage() {
		MessageControllerOld msgs = new MessageControllerOld();
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
