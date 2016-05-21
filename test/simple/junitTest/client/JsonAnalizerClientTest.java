package simple.junitTest.client;

import org.junit.Before;
import org.junit.Test;

import edu.tongji.reuse.teameleven.client.ctl.JsonAnalizerClient;

public class JsonAnalizerClientTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMessageContent() {
		System.out.println(JsonAnalizerClient.getMessageContent("{\"Type\":\"Message\",\"User\":\"qyd\",\"Content\":\"1jjjj\"}"));
	}

}
