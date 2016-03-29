package server.ctrl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import server.DAO.CounterDAO;

public class RecordController {
	private int receivedNumber;
	private int ignoredNumber;
	private int forwardedNumber;
	private int logsucceedNumber;
	private int logfailedNumber;
	private CounterDAO counterDao;
	private SaveRecordThread saveRecordThread;

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
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (continueFlag) {
				rec.save();
				try {
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("record thread stop");
		}

	}

	public RecordController() {
		counterDao = new CounterDAO();
		saveRecordThread = new SaveRecordThread(this);
		saveRecordThread.start();
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
		this.save();
		counterDao.quit();
	}

}
