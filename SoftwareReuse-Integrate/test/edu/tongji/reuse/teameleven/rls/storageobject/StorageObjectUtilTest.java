package edu.tongji.reuse.teameleven.rls.storageobject;

import java.util.Date;

import org.junit.Test;

public class StorageObjectUtilTest {

	@Test
	public void test() {
		Date d= new Date();
		System.out.println(d);
//		fail("Not yet implemented");
		
		StorageObjectUtil<Date> sou = new StorageObjectUtil<Date>();
		sou.storageObject(d, "/Users/d/Desktop/tmp/a/b/tt/dump");
		Date e = sou.getObjectFormDump("/Users/d/Desktop/tmp/a/b/tt/dump");
		System.out.println(e);
	}

}
