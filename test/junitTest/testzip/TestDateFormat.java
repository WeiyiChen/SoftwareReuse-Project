package junitTest.testzip;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TestDateFormat {

	@Test
	public void test() {
//		fail("Not yet implemented");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String result = sdf.format(date);
		System.out.println(result);
	}

}
