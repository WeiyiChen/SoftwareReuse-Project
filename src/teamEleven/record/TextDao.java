package teamEleven.record;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//import sun.util.resources.CalendarData;

public class TextDao extends FileDao<String>{

	private String fileName = "";
		
	public TextDao(){
		super();
	}
	
	public void quit(){
	}
	
	@Override
	public boolean save(String str) {
		super.dirPath = System.getProperties().getProperty("user.dir")
		+ File.separator + "log";
		Calendar cal =  Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");
		fileName =  "record" + format.format(cal.getTime()) + ".txt";
		checkOrCreateFile();
		return FileAccess.fileOverWrite(getPathName(), str);
	}
    
    public boolean append(String str){
        boolean result = false;
        if("".equals(fileName) || fileName == null){
            return false;
        }
        FileAccess fa = new FileAccess();
        if(fa.createAppendFile(getPathName())){
            result = append(str);
        }
        fa.closeFile();
        return result;
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
