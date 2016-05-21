package simple.junitTest.server;

import org.junit.Before;
import org.junit.Test;

import edu.tongji.reuse.teameleven.server.json.JsonBuilderServer;

public class JsonBuilderServerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMessageBusyError() {
		System.out.println(JsonBuilderServer.getMessageBusyError());
	}

	@Test
	public void testGetNeedReloginError() {
		System.out.println(JsonBuilderServer.getNeedReloginError());
	}

	@Test
	public void testGetTypeNoFoundError() {
		System.out.println(JsonBuilderServer.getTypeNoFoundError());
	}

}
