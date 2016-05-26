package edu.tongji.reuse.teameleven.rls.encrypt.stream;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

/**
 * With methods encrypt and decrypt a stream or file.
 * @author Dai
 *
 */
public class EncryptCtrl {
	private final static int BUFF_LEN = 1024;
	private static AlgorithmParameterSpec paramSpec = new IvParameterSpec(new byte[]{

			  (byte)0xB2, (byte)0x12, (byte)0xD5, (byte)0xB2,

			  (byte)0x44, (byte)0x21, (byte)0xC3, (byte)0xC3
			    });
	
	private Key key = null;
	
	/**
	 * Default Constructor.
	 */
	public EncryptCtrl(){
		this("default");
	}
	
	/**
	 * Construct a FileEncrypt instance with a specific string.
	 * @param keyStr - a string to specific the encrypt and decrypt a stream or file.
	 */
	public EncryptCtrl(String keyStr){
		super();
		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(new SecureRandom());
			key = keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}			
	}
	
	/**
	 * return a CipherInputStream(FilterInputStream) for encrypt
	 * @param in
	 * @return
	 */
	public CipherInputStream encryptInputStream(InputStream in){
		CipherInputStream cis = null;
		try {
			Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			enCipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			cis = new CipherInputStream(in, enCipher);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return cis;
	}
	
	/**
	 * return a CipherInputStream(FilterInputStream) for decrypt
	 * @param in
	 * @return
	 */
	public CipherInputStream decryptInputStream(InputStream in){
		CipherInputStream cis = null;
		try {
			Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			deCipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			cis = new CipherInputStream(in, deCipher);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return cis;
	}
	
	/**
	 * return a CipherOutputStream(FilterOutputStream) for encrypt
	 * @param out
	 * @return
	 */
	public CipherOutputStream encryptOutputStream(OutputStream out){
		CipherOutputStream cos = null;
		try {
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			cos = new CipherOutputStream(out, cipher);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return cos;
	}
	
	/**
	 * return a CipherOutputStream(FilterOutputStream) for decrypt
	 * @param out
	 * @return
	 */
	public CipherOutputStream decryptOutputStream(OutputStream out){
		CipherOutputStream cos = null;
		try {
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			cos = new CipherOutputStream(out, cipher);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return cos;
	}
	
	/**
	 * To encrypt a stream. The InputStream and OutputStream won't be closed in this function.
	 * @param is - the InputStream to encrypt.
	 * @param os - the output of the encrypt stream.
	 */
	public void encrypt(InputStream is, OutputStream os){
		CipherOutputStream cos = null;
		try {
			
			Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			enCipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);			
			cos = new CipherOutputStream(os, enCipher);
			byte[] buff = new byte[BUFF_LEN];
			int numRead = 0;
			while((numRead = is.read(buff)) > 0){
				cos.write(buff, 0, numRead);
			}
			cos.flush();
		} catch (NoSuchAlgorithmException | NoSuchPaddingException 
				| InvalidKeyException | InvalidAlgorithmParameterException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(cos != null){
				try{
					cos.close();
//					System.out.println(os);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 
	 To decrypt a stream. The InputStream and OutputStream won't be closed in this function.
	 * @param is - the InputStream to decrypt.
	 * @param os - the output of the decrypt stream.
	 */
	public void decrypt(InputStream is, OutputStream os){
		CipherInputStream cis = null;
		try {
			Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			deCipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			cis = new CipherInputStream(is, deCipher);
			byte[] buff = new byte[BUFF_LEN];
			int numRead = 0;
			while((numRead = cis.read(buff)) > 0){
				os.write(buff, 0, numRead);
			}
			os.flush();
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
				InvalidAlgorithmParameterException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(cis != null){
				try{
					cis.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * encrypt a file according to the file path.
	 * @param inFilePath - input file path
	 * @param outFilePath - output file path
	 */
	public void encrypt(String inFilePath, String outFilePath){
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try{
			fis = new FileInputStream(inFilePath);
			fos = new FileOutputStream(outFilePath);
			encrypt(fis, fos);
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(fis != null){
				try{
					fis.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(fos != null){
				try{
					fos.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * decrypt a file according to the file path.
	 * @param inFilePath - input file path
	 * @param outFilePath - output file path
	 */
	public void decrypt(String inFilePath, String outFilePath){
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try{
			fis = new FileInputStream(inFilePath);
			fos = new FileOutputStream(outFilePath);
			decrypt(fis, fos);
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(fis != null){
				try{
					fis.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(fos != null){
				try{
					fos.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
}
