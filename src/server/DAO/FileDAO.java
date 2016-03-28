package server.DAO;

import java.io.File;
import java.io.IOException;

public abstract class FileDAO {

	protected String dirPath = "./data";
	
	protected String getPathName(){
		return dirPath + "/" + getFileName();
	}
	
	public FileDAO() {
		super();
	}
	
	protected void checkOrMk(){
		this.chechOrMkDir();
		this.checkOrMkFile();
	}
	
	private void chechOrMkDir(){
		File dataFolder = new File(dirPath);
		if (!dataFolder.exists() || !dataFolder.isDirectory()) {
			dataFolder.mkdir();
		}
	}
	
	private void checkOrMkFile(){
		try {
			String fileName = getFileName();
			File dataFile = new File(fileName);
			if(!dataFile.exists() || !dataFile.isFile()){
				dataFile.createNewFile();
				FileAccess.fileOverWrite(fileName, getBasicString());
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	protected abstract void checkOrCreateFile();
	
	protected abstract String getFileName();
	
	protected abstract String getBasicString();

}
