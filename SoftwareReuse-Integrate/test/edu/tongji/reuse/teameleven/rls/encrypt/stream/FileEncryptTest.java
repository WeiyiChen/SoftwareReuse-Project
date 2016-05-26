package edu.tongji.reuse.teameleven.rls.encrypt.stream;

import org.junit.Before;
import org.junit.Test;

public class FileEncryptTest {

	String inPath = "/Users/d/Project/Eclipse/SoftwareReuse/clientTmp/logclient/test.log";
	String enCryptPath = "/Users/d/Project/Eclipse/SoftwareReuse/clientTmp/logclient/zencrypt";
	String outPath = "/Users/d/Project/Eclipse/SoftwareReuse/clientTmp/logclient/testout.log";
	EncryptCtrl fe;
	@Before
	public void before(){
		fe = new EncryptCtrl("test");
		
		
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
		fe.encrypt(inPath, enCryptPath);
		fe.decrypt(enCryptPath, outPath);
	}

}
