package edu.tongji.reuse.teameleven.rls.encrypt.message;


import java.util.Random;

public class EncryptImpl extends EncryptBase implements IEncrypt {
	
	@Override
	public String encrypt(String plainText) {
		String basicMd5 = this.getTMD5(plainText);
		Random rand = new Random();
		int key = rand.nextInt(900) + 100;
		String num = String.valueOf(key);
		String offsetMd5 = super.multiOfMd5(super.getMd5String(num), key);
		return super.sumOfTwoMd5(basicMd5, offsetMd5) + num;
	}

	@Override
	public String decryptToTMD5(String secretText) {
		try {
			if (secretText.length() != lengthOfMd5 + 3) {
				return "";
			}
			String secretMd5 = secretText.substring(0, lengthOfMd5);
			String num = secretText.substring(lengthOfMd5);
			int key = Integer.valueOf(num);
			String offsetMd5 =  super.multiOfMd5(super.getMd5String(num), (16- (key%16)));
			return super.sumOfTwoMd5(secretMd5, offsetMd5);
		} catch (Exception e) {
			return "";
		}
	}
	
	@Override
	public String getTMD5(String pwd){
		return super.getTripleMd5(pwd);
	}
}