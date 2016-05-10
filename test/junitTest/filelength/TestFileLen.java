package junitTest.filelength;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Test;

public class TestFileLen {
	
	
	public void testWriteb(){
		int i = 1 + 2 + 2*2 + 2*2*2 + 2*2*2*2 + 2*2*2*2*2 + 2*2*2*2*2*2;
		i = 2*i + 1;
		i = 2*i + 1;
		i = 2*i + 1;
		System.out.println(i);
		System.out.println(Integer.toBinaryString(i));
		ByteArrayOutputStream baos = new ByteArrayOutputStream(10);
		byte[] bs = baos.toByteArray();
		System.out.println(bs.length);
		baos.write(i);
		bs = baos.toByteArray();
		System.out.println(bs.length);
		printByteArray(baos.toByteArray());
	}
	
	@Test
	public void testFileLen() throws FileNotFoundException{
//		File file = new File("/Users/d/Desktop/");
//		System.out.println(file.length());
		PrintWriter pw = new PrintWriter("/Users/d/Desktop/tmp/test.out");
		File file = new File("/Users/d/Desktop/tmp/test.out");
		String tStr = "helloworldhelloworldhelloworldhelloworldhelloworldhelloworldhelloworld";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i <= 100; i++){
			sb.append(tStr);
		}
		String testStr = tStr.toString();
		for(int i = 0; i <= 5; i++){
			pw.write(testStr);
			pw.flush();
			System.out.println(file.length());
		}
		System.out.println(file.length());
		pw.close();
		System.out.println(file.length());
	}
	
	public void printByteArray(byte[] bs){
		System.out.println(bs.length);
		for(byte b : bs){
			System.out.print(b);
		}
		System.out.println();
	}
}
