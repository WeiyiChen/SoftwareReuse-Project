package junitTest.client;

import org.junit.Before;
import org.junit.Test;

import client.ctl.JsonBuilderClient;

public class JsonBuilderClientTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPasswordJson() {
		System.out.println(JsonBuilderClient.getPasswordJson("qyd", "1252865"));
	}

	@Test
	public void testGetMessageJson() {
		System.out.println(JsonBuilderClient.getMessageJson("qyd", "1234"));
	}

}
