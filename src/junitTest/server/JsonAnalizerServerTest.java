package junitTest.server;

import org.junit.Before;
import org.junit.Test;

import base.JsonAnalizerBase;
import server.json.JsonAnalizerServer;

public class JsonAnalizerServerTest extends JsonAnalizerBase{

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPassword() {
		System.out.println(JsonAnalizerServer.getPassword("{\"Type\":\"Password\",\"User\":\"qyd\",\"Content\":\"1252865\"}"));
	}

	@Test
	public void testGetMessageType() {
		System.out.println(JsonAnalizerServer.getMessageType("{\"Type\":\"Password\",\"User\":\"qyd\",\"Content\":\"1252865\"}"));
		System.out.println(JsonAnalizerServer.getMessageType("{\"Type\":\"Message\",\"User\":\"qyd\",\"Content\":\"1jjjj\"}"));
	}

	@Test
	public void testGetValue() {
		System.out.println(JsonAnalizerServer.getValue("{\"Type\":\"Password\",\"User\":\"qyd\",\"Content\":\"1252865\"}", "User"));
	}

	@Test
	public void testGetUser() {
		System.out.println(JsonAnalizerServer.getUser("{\"Type\":\"Message\",\"User\":\"qyd\",\"Content\":\"1jjjj\"}"));
	}


}
