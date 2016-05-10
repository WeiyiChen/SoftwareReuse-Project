package team.eleven.record;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//import packedDao.TextDao;


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
			System.out.println("record thread stop");
		}
	}

	private ClientRecordController() {
		counterDao = new TextDao();
		saveRecordThread = new SaveRecordThread(this);
	}

	
	/**
	 * 鑾峰彇涓�涓狢lientRecordController绫荤殑瀹炰緥锛岃繖閲岀敤鍒皊ingleton鐨勬ā寮�
	 * @return
	 */
	public static ClientRecordController getInstance(){
		if(clientRecordController == null){

			clientRecordController = new ClientRecordController();
		}
		return clientRecordController;
	}

	
	/**
	 * 寮�濮嬭褰曟棩蹇楋紝骞朵笖璁剧疆璁板綍鏃ュ織鐨勫懆鏈�
	 * @param saveCycle 锛� 璁板綍鏃ュ織鐨勫懆鏈燂紙鍗曚綅绉掞級
	 */
	public void setAndStart(int saveCycle){
		this.saveCycle = saveCycle;
		saveRecordThread.start();
	}
	
	/**
	 * 灏嗙櫥褰曞け璐ユ鏁帮紝鐧诲綍鎴愬姛娆℃暟锛屾帴鍙楁秷鎭鏁帮紝鍙戦�佹秷鎭鏁伴噸鏂扮疆0
	 */
	public void reset(){

		this.loginFailedCount = 0;
		this.loginSucceedCount = 0;
		this.receiveNum = 0;
		this.sendNum = 0;
		this.user = "";
	}

	
	
	public void setUser(String user){
		this.user = user;
	}
	
	public void loginFailedCountAdd(){
		this.loginFailedCount++;
	}

	public void loginSucceedCountAdd(){
		this.loginSucceedCount++;
	}
	
	
	public void receiveNumAdd(){
		this.receiveNum++;
	}
	
	
	public void sendNumAdd(){

		this.sendNum++;
	}

	public int getSaveCycle(){
		return this.saveCycle;
	}
	
	
	public void save(){

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String strToSave = format.format(cal.getTime()) + " [ user : " + user
				+ " , loginFailedCount : " + loginFailedCount
				+ " , loginSucceedCount : " + loginSucceedCount
				+ " , sendNum : " + sendNum + " , receiveNum : " + receiveNum
				+ " ]";
		counterDao.append(strToSave);
	}

	
	
	public void quit(){
		saveRecordThread.setStop();
		this.save();
		counterDao.quit();
	}
}
