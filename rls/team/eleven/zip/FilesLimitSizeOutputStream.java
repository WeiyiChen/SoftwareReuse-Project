package team.eleven.zip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * extends an OutputStream to achieve the function of file limit
 * when the file's size up to the limit, this OutputStream will create a new file
 * @author daidongyang
 *	May 10, 2016
 *
 */
public class FilesLimitSizeOutputStream extends OutputStream {
	private FileOutputStream fos;
	private File f;
	private String preNamePath;
	private String exName;
	private int appendCount;
	private String appendIdentifier;
	
	// file size limit with the unit of byte
	// 1M means fileSizeLimit == 1024*1024
	private long fileSizeLimit;

	/**
	 * 
	 * @param originFilePath - the file to output
	 * @param fileSizeLimit - the file size limit
	 * @param appendIdentifier - the identifier to demonstrate the appended file
	 * @throws FileNotFoundException
	 */
	FilesLimitSizeOutputStream(String originFilePath, long fileSizeLimit, String appendIdentifier)
			throws FileNotFoundException {
		f = new File(originFilePath);
		f.getParentFile().mkdirs();
		fos = new FileOutputStream(originFilePath);
		String[] preAndEx = FileProcessUtil.getFilePreNameAndEx(originFilePath);
		preNamePath = preAndEx[0];
		exName = preAndEx[1];
		this.fileSizeLimit = fileSizeLimit;
		this.appendIdentifier = appendIdentifier;
		appendCount = 0;
	}
	
	/**
	 * 
	 * @param originFilePath - the origin file to output
	 * @param fileSizeLimit - the file size limit
	 * @throws FileNotFoundException
	 */
	FilesLimitSizeOutputStream(String originFilePath, long fileSizeLimit) throws FileNotFoundException{
		this(originFilePath, fileSizeLimit, "-app-");
	}

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		if(f.length() >= fileSizeLimit - 1){
			updateOutputFile();
		}
		fos.write(b);
		fos.flush();
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		// TODO Auto-generated method stub
//		super.write(b);
		if(f.length() >= fileSizeLimit - b.length){
			updateOutputFile();
		}
		fos.write(b);
		fos.flush();
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
//		super.write(b, off, len);
		if(f.length() >= fileSizeLimit - len){
			updateOutputFile();
		}
		fos.write(b, off, len);
		fos.flush();
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		fos.flush();
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		fos.close();
	}

	/**
	 * update the OutputStream link to a new file
	 * @return
	 * @throws IOException
	 */
	public FileOutputStream updateOutputFile() throws IOException{
		fos.close();
		String newFilePath = generateNewFilePath();
		fos = new FileOutputStream(newFilePath);
		f = new File(newFilePath);
		return fos;
	}

	
	/**
	 * when the file size is up to limit, this method will generate a new file name according
	 * to the origin file name
	 * @return  - the filename generated
	 */
	private String generateNewFilePath() {
		appendCount++;
		return preNamePath + appendIdentifier + appendCount + exName;
	}
	
	/**
	 * 
	 * @return - the file path this OuputStream is writting on.
	 */
	public String getCurrentFilePath(){
		if(appendCount == 0){
			return preNamePath + exName;
		}
		else{
			return preNamePath + appendIdentifier + appendCount + exName;
		}
	}

}
