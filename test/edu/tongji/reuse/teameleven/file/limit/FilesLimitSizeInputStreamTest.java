package edu.tongji.reuse.teameleven.file.limit;

import java.io.BufferedInputStream;
import java.io.IOException;

import org.junit.Test;

public class FilesLimitSizeInputStreamTest {
	private final int BUFF_SIZE = 1024;

	@Test
	public void test() throws IOException {
//		fail("Not yet implemented");
		String path = "/Users/d/Desktop/tmp/testdir2/a.txt";
		BufferedInputStream bis = new BufferedInputStream(new FilesLimitSizeInputStream(path));
		byte[] buff = new byte[BUFF_SIZE];
		int readNum = 0;
		while((readNum = bis.read(buff))>=0){
			System.out.write(buff, 0, readNum);
		}
		
		System.out.flush();
	}

}
