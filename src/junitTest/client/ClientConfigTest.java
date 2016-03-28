package junitTest.client;

import org.junit.Test;

import client.util.ClientConfig;

public class ClientConfigTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		String ip = "10.60.41.1";
		String port = "2345";
		ClientConfig clientConfig1 = new ClientConfig(ip, port);
		System.out.println(clientConfig1.getIp());
		System.out.println(clientConfig1.getPort());
		int intPort = clientConfig1.getIntPort();
		System.out.println(intPort);
	}

}
