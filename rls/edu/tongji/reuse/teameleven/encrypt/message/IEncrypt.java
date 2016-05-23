package edu.tongji.reuse.teameleven.encrypt.message;

public interface IEncrypt {

	public abstract String encrypt(String plainText);

	public abstract String decryptToTMD5(String secretText);

	public abstract String getTMD5(String pwd);

}
