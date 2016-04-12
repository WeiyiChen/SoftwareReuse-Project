package junitTest.reuse;

import java.util.Map;

import org.junit.Test;

import packedDao.JsonDao;

public class JsonDaoTest {

	@Test
	public void test() {
		JsonDao jsonDao = new JsonDao("config");
		Map<String, String> kv = jsonDao.read();
		for(String s : kv.keySet()){
			System.out.println(s+":" + kv.get(s));
		}
		
		kv.put("hello", "world2");
		System.out.println();
		jsonDao.save(kv);
		Map<String, String> kv2 = jsonDao.read();
		for(String s : kv2.keySet()){
			System.out.println(s+":" + kv2.get(s));
		}
		//fail("Not yet implemented");
	}
	

}
