package team.eleven.record;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//import packedDao.TextDao;


public class RecordController {
	private int receivedNumber;
	private int ignoredNumber;
	private int forwardedNumber;
	private int logsucceedNumber;
	private int logfailedNumber;
	private TextDao counterDao;
	private SaveRecordThread saveRecordThread;
	private int saveCycle;
	
	//private long maxServerLogSize = 1000;
	
	private static RecordController recordController;

	private class SaveRecordThread extends Thread {
		private RecordController rec;

		private boolean continueFlag;

		public SaveRecordThread(RecordController irec) {
			super();
			rec = irec;
			continueFlag = true;
		}

		public void setStop() {
			continueFlag = false;
		}

		@Override
		public void run() {
			while (continueFlag) {
				do {
					try {
						int i = rec.getSaveCycle();
						while (continueFlag && i > 0) {
							Thread.sleep(1000);
							i--;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					rec.save();

				} while (continueFlag);
			}
			System.out.println("record thread stop");
		}

	}

	private RecordController() {
		counterDao = new TextDao();
		saveRecordThread = new SaveRecordThread(this);
	}
	
	
	
	public static RecordController getInstance(){
		if(recordController == null){
			recordController = new RecordController();
		}
		return recordController;
	}
	
	
	public void setAndStart(int saveCycle){
		this.saveCycle = saveCycle;
		saveRecordThread.start();
	}
	
	
	
	public int getSaveCycle() {
		return saveCycle;
	}

	public void reset() {
		receivedNumber = 0;
		ignoredNumber = 0;
		forwardedNumber = 0;
		logsucceedNumber = 0;
		logfailedNumber = 0;
	}

	public void receivedNumberAdd() {
		this.receivedNumber++;
	}

	
	public void ignoredNumberAdd() {
		this.ignoredNumber++;
	}

	public void forwardedNumberAdd() {
		this.forwardedNumber++;
	}

	
	public void logsucceedNumberAdd() {
		this.logsucceedNumber++;
	}

	
	public void logfailedNumberAdd() {
		this.logfailedNumber++;
	}

	public void save() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		StringBuilder sb = new StringBuilder(format.format(cal.getTime()) + "[");
		sb.append("receivedNumber=").append(receivedNumber).append(";");
		sb.append("ignoredNumber=").append(ignoredNumber).append(";");
		sb.append("forwardedNumber=").append(forwardedNumber).append(";");
		sb.append("logsucceedNumber=").append(logsucceedNumber).append(";");
		sb.append("logfailedNumber=").append(logfailedNumber).append("]");
		this.reset();
		counterDao.save(sb.toString());

	}

	
	public void quit() {
		saveRecordThread.setStop();
		counterDao.quit();
	}
	
}
