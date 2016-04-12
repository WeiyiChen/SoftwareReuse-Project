package junitTest.packedController;

import static org.junit.Assert.*;

import org.junit.Test;

import teamEleven.configController.ConfigController;

public class ConfigControllerTest {

	@Test
	public void test() {
//		testGetInt();
		testGetString();
		//fail("Not yet implemented");
	}
	
	void testGetInt(){
		ConfigController configController= new ConfigController();
		try{
			int result1 = configController.getInt("jjj");
			System.out.println(result1);
					}
		catch(RuntimeException e){
			e.printStackTrace();;
		}
		int result2 = configController.getInt("a", -1);
		
		System.out.println(result2);
		
		int result3 = configController.getInt("b", 2);
		System.out.println("result3: " + result3);
		
		try{
			int result4 = configController.getInt("b");
			System.out.println("result4: " + result4);
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		
		
		
		
	}
	
	void testGetString(){
		ConfigController configController= new ConfigController("config");
		try{
			String result1 = configController.getString("ab");
			System.out.println(result1);
					}
		catch(RuntimeException e){
			e.printStackTrace();;
		}
		String result2 = configController.getString("ab", "hello world");
		
		System.out.println(result2);
		
		String result3 = configController.getString("ba", "hl");
		System.out.println("result3: " + result3);
		
		try{
			String result4 = configController.getString("ba");
			System.out.println("result4: " + result4);
		}catch(RuntimeException e){
			e.printStackTrace();
		}
	}

}
