package server.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;

import team.eleven.zip.Zip;

public class ZipLogController {

	private ZipRecordThread zipRecordThread;

	private class ZipRecordThread extends Thread {

		private boolean continueFlag;
		private String originParentLogForder;
		private String zipFolder = "dayziplog";
		private int saveCycle;
		private int startSave = 100;

		public void setStartSave(int startSave) {
			this.startSave = startSave;
		}

		public ZipRecordThread(String originLogParentForder) {
			super();
			this.originParentLogForder = originLogParentForder;
			continueFlag = true;
		}

		public void setStop() {
			continueFlag = false;
		}

		@Override
		public void run() {
			int i = startSave;
			do {
				try {
					while (continueFlag && i > 0) {
						Thread.sleep(1000);
						i--;
					}
					
					executeZip();
					i = this.saveCycle;
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				// rec.save();

			} while (continueFlag);

			System.out.println("zip thread stop");
		}

		@SuppressWarnings("unused")
		public int getSaveCycle() {
			return saveCycle;
		}

		public void setSaveCycle(int saveCycle) {
			this.saveCycle = saveCycle;
		}
		
		public void executeZip(){
			try{
				Calendar cal = Calendar.getInstance();
				java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
						"yyyyMMddhhmmss");

				String folderOfZippedLog = this.originParentLogForder
						+ File.separator + zipFolder;
				File outputFolder = new File(folderOfZippedLog);
				// check if folder exist
				if (!outputFolder.exists() || !outputFolder.isDirectory()) {
					outputFolder.mkdir();
				}

				String logForder = this.originParentLogForder
						+ File.separator + "log";
				File inputFolder = new File(logForder);
				if (!inputFolder.exists() || !inputFolder.isDirectory()) {
					return;
				}

				String destFile = folderOfZippedLog + File.separator
						+ "log" + format.format(cal.getTime());
				Zip.zip(logForder, destFile);
				FileUtils.deleteDirectory(new File(logForder));
			}catch (IOException e) {
				new RuntimeException(e);
			}			
		}
	}

	public ZipLogController() {
		zipRecordThread = new ZipRecordThread(System.getProperty("user.dir"));
//		throw new RuntimeException("hello");
	}

	public void setAndStart(int saveCycle) {
		this.zipRecordThread.setSaveCycle(saveCycle);
		zipRecordThread.start();
//		throw new RuntimeException("hello");
	}

	public void quit() {
		this.zipRecordThread.setStop();
	}
}
