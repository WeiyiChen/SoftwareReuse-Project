package server.DAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import sun.util.resources.CalendarData;

public class CounterDAO extends FileDAO <String>{

	FileAccess fa;
	String fileName = "";
	boolean fileOpened;
	
	public CounterDAO(){
		super();
		checkOrCreateFile();
		
		Calendar cal =  Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		fileName =  "record" + format.format(cal.getTime()) + ".txt";
		
		fa = new FileAccess();
		fileOpened = fa.createAppendFile(getPathName());
	}
	
	@Override
	public boolean save(String str) {
		if(!fileOpened){
			return false;
		}
		return fa.append(str);
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
		return "recoder.txt";
	}

	@Override
	protected String getBasicString() {
		return fileName;
	}

}
