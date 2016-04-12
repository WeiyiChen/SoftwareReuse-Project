package packedDao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TextDao extends FileDao<String>{
	private String fileName = "";
	private String fileNameTag = "";
	
	public TextDao(){
		super();
	}
	
	public TextDao(String fileNameTag){
		this.fileNameTag = fileNameTag;
	}
	
	
	public void quit(){}
	
	
	@Override
	public boolean save(String str) {
		super.dirPath = System.getProperties().getProperty("user.dir")
		+ File.separator + "log";
		Calendar cal =  Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");
		fileName =  "record" + fileNameTag + format.format(cal.getTime()) + ".txt";
		checkOrCreateFile();
		return FileAccess.fileOverWrite(getPathName(), str);
	}

	@Override
	public String read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void checkOrCreateFile() {
		// TODO Auto-generated method stub
		super.checkOrmk();
		
	}

	@Override
	protected String getFileName() {
		// TODO Auto-generated method stub
		return fileName;
	}

	@Override
	protected String getBasicString() {
		// TODO Auto-generated method stub
		return "";
	}

}
