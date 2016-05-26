package edu.tongji.reuse.teameleven.rls.filelimit;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class FileProcessUtilTest {
	
//	@Test
	public void testGetLenth(){
		
		long size = FileProcessUtil.getLength("/Users/d/Desktop/tmp");
		System.out.println(size);
		double result = size/1024.0;
		System.out.println(result);
		System.out.println(result/1024);
		long size2 = FileUtils.sizeOfDirectory(new File("/Users/d/Desktop/tmp"));
		System.out.println(size2);
	}
	
	

	@Test
	public void test() {
//		fail("Not yet implemented");
		
	}

}
