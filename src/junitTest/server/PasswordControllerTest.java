package junitTest.server;

import org.junit.Test;


import client.ctl.JsonBuilderClient;
import packedController.PasswordController;

public class PasswordControllerTest {

	@Test
	public void testPasswordCheck() {
		PasswordController pwdCtrl = new PasswordController();
		
		System.out.println(JsonBuilderClient.getPasswordJson("qyd", "1252865"));
		System.out.println(pwdCtrl.passwordCheck("qyd", "1252865"));

	}
	
	@Test
	public void testaddUser() {
		PasswordController pwdCtrl = new PasswordController();
		System.out.println(pwdCtrl.addUser("qyd", "1252865"));
	}

}
