package teamEleven.record;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//import packedDao.TextDao;

/**
 * 用于服务端日志，日志保存在项目根目录下的log文件夹中
 * @author Qin Yidan
 *
 */
public class RecordController {
	private int receivedNumber;
	private int ignoredNumber;
	private int forwardedNumber;
	private int logsucceedNumber;
	private int logfailedNumber;
	private TextDao counterDao;
	private SaveRecordThread saveRecordThread;
	private int saveCycle;
	
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
			try {
				Thread.sleep(rec.getSaveCycle() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (continueFlag) {
				rec.save();
				try {
					Thread.sleep(rec.getSaveCycle() * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("record thread stop");
		}

	}

	private RecordController() {
		counterDao = new TextDao();
		saveRecordThread = new SaveRecordThread(this);
	}
	
	
	/**
	 * 获取一个RecordController类的实例，这里用到singleton的模式
	 * @return
	 */
	public static RecordController getInstance(){
		if(recordController == null){
			recordController = new RecordController();
		}
		return recordController;
	}
	
	
	/**
	 * 开始记录日志，并且设置记录日志的周期
	 * @param saveCycle － 记录日志的周期（单位秒）
	 */
	public void setAndStart(int saveCycle){
		this.saveCycle = saveCycle;
		saveRecordThread.start();
	}
	
	
	
	public int getSaveCycle() {
		return saveCycle;
	}

	/**
	 * 将登录失败次数，登录成功次数，转发消息次数，接受消息次数，忽略消息次数重新置0
	 */
	public void reset() {
		receivedNumber = 0;
		ignoredNumber = 0;
		forwardedNumber = 0;
		logsucceedNumber = 0;
		logfailedNumber = 0;
	}

	/**
	 * 接受消息次数加1
	 */
	public void receivedNumberAdd() {
		this.receivedNumber++;
	}

	/**
	 * 忽略消息次数加1
	 */
	public void ignoredNumberAdd() {
		this.ignoredNumber++;
	}

	/**
	 * 转发消息次数加1
	 */
	public void forwardedNumberAdd() {
		this.forwardedNumber++;
	}

	/**
	 * 登录成功次数加1 
	 */
	public void logsucceedNumberAdd() {
		this.logsucceedNumber++;
	}

	/**
	 * 登录失败次数加1
	 */
	public void logfailedNumberAdd() {
		this.logfailedNumber++;
	}

	/**
	 * 把接收消息次数，忽略消息次数，转发消息次数，登录成功次数，登录失败次数写进日志文件中
	 * 调用setAndStart()方法后，会有一个线程周期性地调用该方法
	 */
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

	/**
	 * 停止周期性写日志的线程
	 */
	public void quit() {
		saveRecordThread.setStop();
		this.save();
		counterDao.quit();
	}
	
}
