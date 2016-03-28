package junitTest.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import client.ctl.JsonBuilderClient;
import base.JsonBuilderBase;

public class JsonBuilderClientTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPasswordJson() {
		System.out.println(JsonBuilderClient.getPasswordJson("qyd", "1234"));
	}

	@Test
	public void testGetMessageJson() {
		System.out.println(JsonBuilderClient.getMessageJson("qyd", "1234"));
	}

}
