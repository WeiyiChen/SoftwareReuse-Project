package packedZip;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ZipTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		String path = System.getProperty("user.dir");
		String sourceName = path +"dsdf" + File.separator + "log";
		String destinationName = path + File.separator + "log";
		try {
//			Zip.zip(sourceName, destinationName);
			Zip.zip("log", "test.zip");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
