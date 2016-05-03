package server.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import teamEleven.zip.Zip;



public class ZipLogController {

	private ZipRecordThread zipRecordThread;

	private class ZipRecordThread extends Thread {

		private boolean continueFlag;
		private String originParentLogForder;
		private int saveCycle;

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
			do {
				try {
//					int i = this.saveCycle;
					int i = this.saveCycle - 10;
					while (continueFlag && i > 0) {
						Thread.sleep(1000);
						i--;
					}
					Calendar cal = Calendar.getInstance();
					java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
							"yyyyMMddhhmmss");

					String folderOfZippedLog = this.originParentLogForder
							+ File.separator + "ziplog";
					File outputFolder = new File(folderOfZippedLog);
					// check if folder exist
					if (!outputFolder.exists() || !outputFolder.isDirectory()) {
						outputFolder.mkdir();
					}

					String logForder = this.originParentLogForder
							+ File.separator + "log";
					File inputFolder = new File(logForder);
					if (!inputFolder.exists() || !inputFolder.isDirectory()) {
						continue;
					}

					String destFile = folderOfZippedLog + File.separator
							+ "log" + format.format(cal.getTime());
					Zip.zip(logForder, destFile);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {

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

	}

	public ZipLogController() {
		zipRecordThread = new ZipRecordThread(System.getProperty("user.dir"));
	}

	public void setAndStart(int saveCycle) {
		this.zipRecordThread.setSaveCycle(saveCycle);
		zipRecordThread.start();
	}

	public void quit() {
		this.zipRecordThread.setStop();
	}
}
