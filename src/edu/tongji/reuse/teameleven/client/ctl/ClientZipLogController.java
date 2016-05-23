package edu.tongji.reuse.teameleven.client.ctl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

import edu.tongji.reuse.teameleven.zip.Zip2;

public class ClientZipLogController {
	
	private class ZipRecordThread extends Thread {

		private boolean continueFlag;
		private String originLogForder;
		private String zipedLogZipPrex;
		
		private int beginCompressSecs;
		private int internalCompressSecs;
		
		private int fileLimit;
		
		public ZipRecordThread() {
			super();
			continueFlag = true;
		}
		
		public int getFileLimit() {
			return fileLimit;
		}

		public void setFileLimit(int fileLimit) {
			this.fileLimit = fileLimit;
		}

		public int getBeginCompressSecs() {
			return beginCompressSecs;
		}

		public void setBeginCompressSecs(int beginCompressSecs) {
			this.beginCompressSecs = beginCompressSecs;
		}

		public int getInternalCompressSecs() {
			return internalCompressSecs;
		}

		public void setInternalCompressSecs(int internelCompressSecs) {
			this.internalCompressSecs = internelCompressSecs;
		}
		public String getOriginLogForder() {
			return originLogForder;
		}

		public void setOriginLogForder(String originLogForder) {
			this.originLogForder = originLogForder;
		}

		public String getZipedLogZipPrex() {
			return zipedLogZipPrex;
		}

		public void setZipedLogZipPrex(String zipedLogZipPrex) {
			this.zipedLogZipPrex = zipedLogZipPrex;
		}

		public void setStop() {
			continueFlag = false;
		}
		
		public void mkdir(){
			
			String[] files = zipedLogZipPrex.split(File.separator);
			String path = System.getProperties().getProperty("user.dir");
			
			for(int i = 0; i < files.length - 1; i++){
				
				path = path + File.separator + files[i];
				File Folder = new File(path);
				if (!Folder.exists() || !Folder.isDirectory()) {
					Folder.mkdir();
				}
			}
		}

		@Override
		public void run() {
			
			int i = beginCompressSecs;
			while (continueFlag) {
				do {
					try {
						while (continueFlag && i > 0) {
							Thread.sleep(1000);
							i--;
						}
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
						mkdir();
						Zip2.zip(this.originLogForder,this.zipedLogZipPrex + format.format(cal.getTime()), fileLimit);
						File oriFolder  = new File(this.originLogForder);
						synchronized(oriFolder){
							FileUtils.deleteDirectory(oriFolder);
						}
						
						
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					//rec.save();
					i = internalCompressSecs;
				} while (continueFlag);
			}
			System.out.println("record thread stop");
		}

	}
	
	private ZipRecordThread zipRecordThread;
	
	private static ClientZipLogController clientZipLogController;
	
	private ClientZipLogController(){
		super();
		zipRecordThread = new ZipRecordThread();
	}
	
	public static ClientZipLogController getInstance(){
		if(clientZipLogController == null){
			synchronized(ClientZipLogController.class){
				if(clientZipLogController == null){
					clientZipLogController = new ClientZipLogController();
				}
			}
			
		}
		return clientZipLogController;
	}
	
	public void setAndStart(String originLogForder, String zipedLogFilePrex){
		zipRecordThread.setOriginLogForder(originLogForder);
		zipRecordThread.setZipedLogZipPrex(zipedLogFilePrex);
		zipRecordThread.start();
	}
	
	public void setCompressConfig(int beginCompressSecs, int internalCompressSecs){
		zipRecordThread.setBeginCompressSecs(beginCompressSecs);
		zipRecordThread.setInternalCompressSecs(internalCompressSecs);
	}
	
	public void stop(){
		zipRecordThread.setStop();
	}
}
