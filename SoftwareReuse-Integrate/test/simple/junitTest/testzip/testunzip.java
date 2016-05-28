package simple.junitTest.testzip;

import edu.tongji.reuse.teameleven.rls.zip.Zip;
import org.junit.Test;

import java.io.IOException;

public class testunzip {

	@Test
	public void test() {
//		fail("Not yet implemented");
		
		try {
			Zip.zip("tmp", "junittest/test.zip");
//			Zip.unZip("zipclient"+ File.separator +"bar-2016-04-27 01:27:26", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
