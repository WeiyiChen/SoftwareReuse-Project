package junitTest.reuse;

import static org.junit.Assert.*;

import org.junit.Test;

import packedDao.FileAccess;

public class FileAccessTest {

	@Test
	public void test() {
//		testReadFile();
//		testFileOverWrite();
		testAppend();
		//fail("Not yet implemented");
	}
	
	public void testReadFile(){
		String result = FileAccess.readFile("data/test.txt", "utf8");
		System.out.println(result);
	}
	
	public void testFileOverWrite(){
		boolean result = FileAccess.fileOverWrite("data/test.txt", "overrite");
	}
	
	public void testAppend(){
		FileAccess fileAccess = new FileAccess();
		boolean create_result = fileAccess.createAppendFile("data/test.txt");
		System.out.println(create_result);
		boolean append_result = fileAccess.append("hello");
		System.out.println(append_result);
		fileAccess.closeFile();
	}

}
