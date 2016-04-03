package server.DAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//import sun.util.resources.CalendarData;

public class TextDAO extends FileDAO <String>{

	private String fileName = "";
		
	public TextDAO(){
		super();
	}
	
	public void quit(){
	}
	
	@Override
	public boolean save(String str) {
		Calendar cal =  Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");
		fileName =  "record" + format.format(cal.getTime()) + ".txt";
		checkOrCreateFile();
		return FileAccess.fileOverWrite(getPathName(), str);
	}

	@Override
	public String read() {
		System.out.println("record file can not be read");
		return null;
	}

	@Override
	protected void checkOrCreateFile() {
		super.checkOrMk();
	}

	@Override
	protected String getFileName() {
		return fileName;
	}

	@Override
	protected String getBasicString() {
		return "";
	}

}
