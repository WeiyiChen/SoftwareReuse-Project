package team.eleven.encrypt.file;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FileEncryptTest {

	String inPath = "/Users/d/Project/Eclipse/SoftwareReuse/clientTmp/logclient/test.log";
	String enCryptPath = "/Users/d/Project/Eclipse/SoftwareReuse/clientTmp/logclient/zencrypt";
	String outPath = "/Users/d/Project/Eclipse/SoftwareReuse/clientTmp/logclient/testout.log";
	FileEncrypt fe;
	@Before
	public void before(){
		fe = new FileEncrypt("test");
		
		
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
		fe.encrypt(inPath, enCryptPath);
		fe.decrypt(enCryptPath, outPath);
	}

}
