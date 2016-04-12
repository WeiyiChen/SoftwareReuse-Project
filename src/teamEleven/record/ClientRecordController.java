package teamEleven.record;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//import packedDao.TextDao;

/**
 * 用于客户端日志，日志保存在项目根目录下的log文件夹中
 * @author Dai
 *
 */
public class ClientRecordController {
	
	private int loginSucceedCount = 0;
	private int loginFailedCount = 0;
	private int receiveNum = 0;
	private int sendNum = 0;
	private String user = "";
	private int saveCycle;
	private SaveRecordThread saveRecordThread;
	private TextDao counterDao;
	
	private static ClientRecordController clientRecordController;
	
	private class SaveRecordThread extends Thread {
		private ClientRecordController rec;

		private boolean continueFlag;

		public SaveRecordThread(ClientRecordController irec) {
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
				Thread.sleep(rec.saveCycle * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (continueFlag) {
				rec.save();
				try {
					Thread.sleep(rec.saveCycle * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("record thread stop");
		}

	}
	
	private ClientRecordController(){
		counterDao = new TextDao();
		saveRecordThread = new SaveRecordThread(this);
	}
	
	/**
	 * 获取一个ClientRecordController类的实例，这里用到singleton的模式
	 * @return
	 */
	public static ClientRecordController getInstance(){
		if(clientRecordController == null){
			clientRecordController = new ClientRecordController();
		}
		return clientRecordController;
	}
	
	/**
	 * 开始记录日志，并且设置记录日志的周期
	 * @param saveCycle － 记录日志的周期（单位秒）
	 */
	public void setAndStart(int saveCycle){
		this.saveCycle = saveCycle;
		saveRecordThread.start();
	}
	
	/**
	 * 将登录失败次数，登录成功次数，接受消息次数，发送消息次数重新置0
	 */
	public void reset(){
		this.loginFailedCount = 0;
		this.loginSucceedCount = 0;
		this.receiveNum = 0;
		this.sendNum = 0;
		this.user = "";
	}
	
	/**
	 * 设置用户名
	 * @param user
	 */
	public void setUser(String user){
		this.user = user;
	}
	
	/**
	 * 登录失败次数加1
	 */
	public void loginFailedCountAdd(){
		this.loginFailedCount++;
	}
	
	/**
	 * 登录成功次数加1
	 */
	public void loginSucceedCountAdd(){
		this.loginSucceedCount++;
	}
	
	/**
	 * 接受消息次数加1
	 */
	public void receiveNumAdd(){
		this.receiveNum++;
	}
	
	/**
	 * 发送消息次数加1
	 */
	public void sendNumAdd(){
		this.sendNum++;
	}
	
	/**
	 * 把当前时间，用户名，接受消息次数，发送消息次数，登录成功次数，登录失败次数写进日志中
	 * 调用setAndStart()方法后，会有一个线程周期性地调用该方法
	 */
	public void save(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String strToSave = format.format(cal.getTime()) 
				+ " [ user : " + user + " , loginFailedCount : " + loginFailedCount 
				+ " , loginSucceedCount : " + loginSucceedCount + " , sendNum : " + sendNum
				+ " , receiveNum : " + receiveNum + " ]";
		counterDao.append(strToSave);
	}
	
	/**
	 * 停止周期性写日志的线程
	 */
	public void quit(){
		saveRecordThread.setStop();
		this.save();
		counterDao.quit();
	}
}















