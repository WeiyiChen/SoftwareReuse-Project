package server.DAO;

import java.io.File;
import java.io.IOException;

public abstract class FileDAO<T> {

	protected String dirPath = System.getProperties().getProperty("user.dir")
			+ File.separator + "data";

	protected String getPathName() {
		return dirPath + File.separator + getFileName();
	}

	public FileDAO() {
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
			if (!dataFile.exists() || !dataFile.isFile()) {
				dataFile.createNewFile();
				FileAccess.fileOverWrite(getPathName(), getBasicString());
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
