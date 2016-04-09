package junitTest.server;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.BeforeClass;






import client.ctl.JsonBuilderClient;
import packedController.PasswordController;
import packedEncrypt.EncryptImpl;
import packedEncrypt.IEncrypt;

public class PasswordControllerTest {

	private static Map<String,String> defMap = new HashMap<String,String>();
	@BeforeClass
	public void initDefMap(){
		IEncrypt ie = new EncryptImpl();
		defMap.put("qyd", ie.getTMD5("1252865"));
	}
	
	@Test
	public void testPasswordCheck() {
		PasswordController pwdCtrl = new PasswordController(defMap);
		
		System.out.println(JsonBuilderClient.getPasswordJson("qyd", "1252865"));
		System.out.println(pwdCtrl.passwordCheck("qyd", "1252865"));

	}
	
	@Test
	public void testaddUser() {
		PasswordController pwdCtrl = new PasswordController(defMap);
		System.out.println(pwdCtrl.addUser("qyd", "1252865"));
	}

}
