package server.ctrl;

import java.io.IOException;

import packedZip.Zip;

public class ZipLogController {

	private class ZipRecordThread extends Thread {

		private boolean continueFlag;
		private String originLogForder;
		private String zipedLogZip;
		
		public ZipRecordThread(String originLogForder, String zipedLogFile) {
			super();
			this.originLogForder = originLogForder;
			this.zipedLogZip = zipedLogFile;
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
						int i = 86400;
						while (continueFlag && i > 0) {
							Thread.sleep(1000);
							i--;
						}
						Zip.zip(this.originLogForder,this.zipedLogZip);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (IOException e) {
						
					}
					//rec.save();
					
				} while (continueFlag);
			}
			System.out.println("record thread stop");
		}

	}
}
