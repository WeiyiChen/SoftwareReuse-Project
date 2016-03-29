package junitTest.server;

import static org.junit.Assert.*;

import org.junit.Test;

import client.ctl.JsonBuilderClient;
import server.ctrl.PasswordController;

public class PasswordControllerTest {

	@Test
	public void testPasswordCheck() {
		PasswordController pwdCtrl = new PasswordController();
		
		System.out.println(JsonBuilderClient.getPasswordJson("qyd", "1252865"));
		System.out.println(pwdCtrl.passwordCheck(JsonBuilderClient.getPasswordJson("qyd", "1252865")));

	}

}
