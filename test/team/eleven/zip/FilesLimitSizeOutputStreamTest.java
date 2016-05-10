package team.eleven.zip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringBufferInputStream;

import org.junit.Before;
import org.junit.Test;

public class FilesLimitSizeOutputStreamTest {
	FilesLimitSizeOutputStream flsos = null;
	StringBufferInputStream sbis = null;
	private	final static int BUFF_LEN = 256;
	@Before
	public void before() throws FileNotFoundException{
		flsos = new FilesLimitSizeOutputStream("/Users/d/Desktop/tmp/testdir6/b.txt",517L);
		String t = "HelloworldHelloWorld";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 200; i++){
			sb.append(t);
		}
		sbis = new StringBufferInputStream(sb.toString());
		
	}
	
//	@Test
	public void testWrite3() throws IOException{
		int readNum = 0;
		byte[] buff = new byte[BUFF_LEN];
		while((readNum = sbis.read(buff))>0){
			flsos.write(buff,0,readNum);
		}
		
	}
	
	@Test
	public void testWrite1() throws IOException{
		byte[] buff = new byte[BUFF_LEN];
		while((sbis.read(buff))>0){
			flsos.write(buff);
		}
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
	}

}
