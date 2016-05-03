package junitTest.testzip;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import teamEleven.zip.Zip;

public class testunzip {

	@Test
	public void test() {
//		fail("Not yet implemented");
		
		try {
			Zip.zip("tmp", "junittest/test.zip");
//			Zip.unZip("zipclient"+ File.separator +"bar-2016-04-27 01:27:26", "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
