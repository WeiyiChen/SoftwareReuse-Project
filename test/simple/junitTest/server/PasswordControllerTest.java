package simple.junitTest.server;

import java.util.HashMap;
import java.util.Map;

import edu.tongji.reuse.teameleven.server.ctrl.PasswordController;
import org.junit.Test;
import org.junit.BeforeClass;
import edu.tongji.reuse.teameleven.client.ctrl.JsonBuilderClient;
//import teamEleven.pwdCtrl.PasswordController;
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
		System.out.println(pwdCtrl.passwordCheck("qyd", ie.decryptToTMD5(
				str)));
		System.out.println(ie.decryptToTMD5("766a60c2153ea0ab9f2970b48134c63b248"));

	}

	//@Test
	public void testaddUser() {
		PasswordController pwdCtrl = new PasswordController(defMap);
		System.out.println(pwdCtrl.addUser("qyd", "1252865"));
	}

}
