package team.eleven.record;

import java.io.File;
import java.io.IOException;

public abstract class FileDao<T> {
    private long maxFileSize = 100000;//max file size 100kb

	protected String dirPath = System.getProperties().getProperty("user.dir")
			+ File.separator + "data";

	protected String getPathName() {
		return dirPath + File.separator + getFileName();
	}

	public FileDao() {
		super();
	}

	protected void checkOrMk() {
		System.out.println("checking file:" + getPathName());
		this.chechOrMkDir();
		this.checkOrMkFile();
	}

	private void chechOrMkDir() {
		File dataFolder = new File(dirPath);
		if (!dataFolder.exists() || !dataFolder.isDirectory()) {
			dataFolder.mkdir();
		}
	}

	private void checkOrMkFile() {
		try {
			File dataFile = new File(getPathName());
			if(dataFile.length() >= maxFileSize) {
			    if (!dataFile.exists() || !dataFile.isFile()) {
	                dataFile.createNewFile();
	                FileAccess.fileOverWrite(getPathName(), getBasicString());
	            }
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public abstract boolean save(T obj);

	public abstract T read();

	protected abstract void checkOrCreateFile();

	protected abstract String getFileName();

	protected abstract String getBasicString();

}
