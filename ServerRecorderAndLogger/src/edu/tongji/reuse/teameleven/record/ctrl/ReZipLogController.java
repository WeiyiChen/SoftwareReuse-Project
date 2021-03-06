package edu.tongji.reuse.teameleven.record.ctrl;

import edu.tongji.reuse.teameleven.rls.filelimit.FileProcessUtil;
import edu.tongji.reuse.teameleven.rls.zip.Zip2;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class ReZipLogController {

	private ZipRecordThread zipRecordThread;
	private Logger logger = LoggerFactory.getLogger(ReZipLogController.class);

	private class ZipRecordThread extends Thread {

		private boolean continueFlag;
		private String originParentLogForder;
		private String zipFolder = "weekziplog";
		private int saveCycle;
		private int startSave = 300;
		
		private String appendIdentifier ="-app-";
		private int fileLimit = 5*1024;

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
			logger.info("start rezip");
			int i = startSave;
			do {
				try {
					while (continueFlag && i > 0) {
						Thread.sleep(1000);
						i--;
					}
					executeUnZip(System.getProperty("user.dir") + File.separator + "dayziplog");
					executeZip();
					i = this.saveCycle;
					logger.info("reZip once");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// rec.save();

			} while (continueFlag);

			logger.info("zip thread stop");
		}

		@SuppressWarnings("unused")
		public int getSaveCycle() {
			return saveCycle;
		}

		public void setSaveCycle(int saveCycle) {
			this.saveCycle = saveCycle;
		}

		public String getAppendIdentifier() {
			return appendIdentifier;
		}

		public void setAppendIdentifier(String appendIdentifier) {
			this.appendIdentifier = appendIdentifier;
		}

		public void executeUnZip(String folderPath) {
			File folder = new File(folderPath);
			String[] list = folder.list(FileProcessUtil.originFilter(appendIdentifier));
			try {
				for (String filePath : list) {
					if (filePath.contains(".zip")) {
						Zip2.unZip(folderPath + File.separator + filePath, "tmp");
					}
				}

				FileUtils.deleteDirectory(new File(folderPath));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		public void executeZip() {
			try {
				Calendar cal = Calendar.getInstance();
				java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMddhhmmss");

				String tmpFolder = System.getProperty("user.dir") + File.separator + "tmp";
				String destFile = System.getProperty("user.dir") + File.separator + zipFolder + File.separator + "log" + format.format(cal.getTime());
				Zip2.zip(tmpFolder, destFile,fileLimit);
				FileUtils.deleteDirectory(new File(tmpFolder));
				
				
			} catch (IOException e) {
				new RuntimeException(e);
			}
		}
	}

	public ReZipLogController() {
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
