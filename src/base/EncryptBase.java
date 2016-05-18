package base;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// md5 encrypt for password

public class EncryptBase {
	protected static final int lengthOfMd5 =32;
	
	public String getMd5String(String pwd) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md5Byte = md5.digest(pwd.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < md5Byte.length; i++) {
				String hexString = String.format("%02x", Byte.toUnsignedInt(md5Byte[i]));
				sb.append(hexString);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException nsae) {
			return "";
		}
	}
	
	public String sumOfTwoMd5(String md51,String md52){
		char[] c1=md51.toCharArray();
		char[] c2= md52.toCharArray();
		char[] c3 = new char[lengthOfMd5];
		for(int i=0;i<lengthOfMd5;i++){
			int tempValue = Integer.parseInt(String.valueOf(c1[i]),16) 
					+ Integer.parseInt(String.valueOf(c2[i]),16);
			tempValue = tempValue %16;
			c3[i]=  Integer.toHexString(tempValue).toCharArray()[0];
		}
		return String.valueOf(c3);
	}

}
