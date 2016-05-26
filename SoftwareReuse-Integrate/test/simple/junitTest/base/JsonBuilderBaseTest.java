package simple.junitTest.base;

import org.junit.Before;
import org.junit.Test;

import edu.tongji.reuse.teameleven.base.JsonBuilderBase;

public class JsonBuilderBaseTest extends JsonBuilderBase{

	JsonBuilderBase jbase = new JsonBuilderBase();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetTypeContentJson() {
		System.out.println(JsonBuilderBase.getTypeContentJson(authorization, loginSucceed));
	}

	@Test
	public void testGetTypeUserContentJson() {
		System.out.println(JsonBuilderBase.getTypeUserContentJson(password, "qyd", "1252865"));
	}

	@Test
	public void testGetLoginSucceedJson() {
		System.out.println(JsonBuilderBase.getLoginSucceedJson());
	}

	@Test
	public void testGetLoginFailedJson() {
		System.out.println(JsonBuilderBase.getLoginFailedJson());
	}

	@Test
	public void testGetReloginRequestJson() {
		System.out.println(JsonBuilderBase.getReloginRequestJson());
	}

}
