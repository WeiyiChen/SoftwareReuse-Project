package junitTest.server;

import static org.junit.Assert.*;

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
		System.out.println(JsonBuilderClient.getMessageJson("qyd", "1252865"));
		System.out.println(JsonBuilderClient.getMessageJson(null, null));
	}

}
