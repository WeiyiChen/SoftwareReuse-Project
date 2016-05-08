package team.eleven.encrypt.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * With methods encrypt and decrypt a stream or file.
 * @author Dai
 *
 */
public class FileEncrypt {
	private final static int BYTE_LEN = 1024;
	private static AlgorithmParameterSpec paramSpec = new IvParameterSpec(new byte[]{

			  (byte)0xB2, (byte)0x12, (byte)0xD5, (byte)0xB2,

			  (byte)0x44, (byte)0x21, (byte)0xC3, (byte)0xC3
			    });
	
	private static Key key = null;
	
	/**
	 * Default Constructor.
	 */
	public FileEncrypt(){
		this("default");
	}
	
	/**
	 * Construct a FileEncrypt instance with a specific string.
	 * @param keyStr - a string to specific the encrypt and decrypt a stream or file.
	 */
	public FileEncrypt(String keyStr){
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
			byte[] buff = new byte[BYTE_LEN];
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
			byte[] buff = new byte[BYTE_LEN];
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
