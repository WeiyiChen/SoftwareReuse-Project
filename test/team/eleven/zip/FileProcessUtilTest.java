package team.eleven.zip;

import static org.junit.Assert.*;

import org.junit.Test;

import team.eleven.file.limit.FileProcessUtil;

public class FileProcessUtilTest {
	
	@Test
	public void testGetRelatedFile(){
		String originFilePath = "/Users/d/Desktop/tmp/testdir/bar.zip";
		String[] results = FileProcessUtil.getRelatedFiles(originFilePath);
		System.out.println(results.length);
		printStringArray(results);
		
	}

//	@Test
	public void test() {
//		fail("Not yet implemented");
		boolean result = "hello".contains("");
		System.out.println(result);
	}
	
//	@Test
	public void testGetFileNameAndEx(){
		String[] test1 = FileProcessUtil.getFilePreNameAndEx("test");
		printStringArray(test1);
		String[] test2 = FileProcessUtil.getFilePreNameAndEx("test.test");
//		System.out.println(test2);
		printStringArray(test2);
		String[] test3 = FileProcessUtil.getFilePreNameAndEx("test.test.test");
//		System.out.println(test3);
		printStringArray(test3);
		
		try{
			String[] test4 = FileProcessUtil.getFilePreNameAndEx("");
//			System.out.println(test4);
			printStringArray(test4);
		}catch(RuntimeException e){
			e.printStackTrace(System.out);
		}
		
		try{
			String[] test5 = FileProcessUtil.getFilePreNameAndEx(null);
//			System.out.println(test5);
			printStringArray(test5);
		}catch(RuntimeException e){
			e.printStackTrace(System.out);
		}
	}
	
	public void printStringArray(String[] args){
		System.out.println();
		for(String s : args){
			if(s == null){
				System.out.println("null");
			}
			
			System.out.println("\"" +s + "\"");
		}
		System.out.println();
	}

}
