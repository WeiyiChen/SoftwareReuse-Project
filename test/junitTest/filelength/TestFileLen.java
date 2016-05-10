package junitTest.filelength;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Test;

public class TestFileLen {
	@Test
	public void testFileLen() throws FileNotFoundException{
//		File file = new File("/Users/d/Desktop/");
//		System.out.println(file.length());
		PrintWriter pw = new PrintWriter("/Users/d/Desktop/tmp/test.out");
		File file = new File("/Users/d/Desktop/tmp/test.out");
		String testStr = "helloworldhelloworldhelloworldhelloworldhelloworldhelloworldhelloworld";
		for(int i = 0; i <= 100; i++){
			pw.write(testStr);
//			pw.flush();
			System.out.println(file.length());
		}
		pw.close();
		System.out.println(file.length());
	}
}
