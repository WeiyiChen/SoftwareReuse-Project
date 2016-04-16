package junitTest.server;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.BeforeClass;
import client.ctl.JsonBuilderClient;
import teamEleven.pwdCtrl.PasswordController;
import packedEncrypt.EncryptImpl;
import packedEncrypt.IEncrypt;

public class PasswordControllerTest {

	private static Map<String,String> defMap = new HashMap<String,String>();
	private static IEncrypt ie;
	@BeforeClass
	public static void initDefMap(){
		ie = new EncryptImpl();
		defMap.put("qyd", ie.getTMD5("1252865"));
		defMap.put("cwy", ie.getTMD5("1252874"));
	}
	
	@Test
	public void testPasswordCheck() {
		PasswordController pwdCtrl = new PasswordController(defMap);
		String str =ie.encrypt("1252865");
		System.out.println(JsonBuilderClient.getPasswordJson("qyd",str ));
		System.out.println(pwdCtrl.passwordCheck("qyd", ie.decryptToTMD5(str)));

	}
	
	//@Test
	public void testaddUser() {
		PasswordController pwdCtrl = new PasswordController(defMap);
		System.out.println(pwdCtrl.addUser("qyd", "1252865"));
	}

}
