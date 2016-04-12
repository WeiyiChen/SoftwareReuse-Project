package junitTest.testJar;

import org.junit.Test;

import teamEleven.configController.ConfigController;

public class TestConfig {

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	public TestConfig(){
		ConfigController cc = new ConfigController();
		try{
			int result = cc.getInt("port2", 2);
//			String result = cc.getString("ip1");
			System.out.println(result);
			System.out.println("end");
		}catch(RuntimeException e){
			e.printStackTrace(System.out);
		}
		
	}

}
