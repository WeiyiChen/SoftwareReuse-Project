package junitTest.reuse;

import org.junit.Test;

import teamEleven.record.TextDao;

public class TestTextDao {

	@Test
	public void test() {
		//fail("Not yet implemented");
		TextDao td = new TextDao();
		boolean result = td.append("test");
		System.out.println(result + " in junit");
	}

}
