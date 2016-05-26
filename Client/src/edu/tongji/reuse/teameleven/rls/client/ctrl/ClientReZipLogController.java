package edu.tongji.reuse.teameleven.rls.client.ctrl;

import edu.tongji.reuse.teameleven.file.limit.FileProcessUtil;
import edu.tongji.reuse.teameleven.zip.Zip2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClientReZipLogController {
	
	private class ZipRecordThread extends Thread {

		private boolean continueFlag;
		private String originLogForder;
		private String zipedLogZipPrex = "clientweekziplog/bar--";
		private String appendIdentifier = "-app-";
		private long fileLimit = 5*1024;
		private int beginCompressSecs = 3;
		private int internalCompressSecs = 86400 * 7;
		
		private final String tmpFolderPath = System.getProperty("user.dir") + File.separator + "clientTmp";
		
		public ZipRecordThread() {
			super();
			continueFlag = true;
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
			System.out.println("start client rezip");
			
			int i = beginCompressSecs;
			while (continueFlag) {
				do {
					try {
						while (continueFlag && i > 0) {
							Thread.sleep(1000);
							i--;
						}
						executeUnZip("clientdayziplog");
//						executeUnZip("clientdayziplog");
						executeZip();					
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
		
		public void executeUnZip(String srcFolderPath) throws IOException{
			File srcFolder = new File(srcFolderPath);
			String[] srcFileNames = srcFolder.list(FileProcessUtil.originFilter(appendIdentifier));
			for(String srcFileName : srcFileNames){
				if(srcFileName.contains(".zip")){
					Zip2.unZip(srcFolderPath + File.separator + srcFileName, tmpFolderPath);
				}
			}
			try{
				FileUtils.deleteDirectory(new File(srcFolderPath));
			}catch(IOException e){
				e.printStackTrace(System.out);
			}

			
		}
		
		public void executeZip() throws IOException{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			mkdir();
			Zip2.zip(tmpFolderPath,this.zipedLogZipPrex + format.format(cal.getTime()), fileLimit);
			File oriFolder  = new File(this.originLogForder);
			synchronized(oriFolder){
				try{
					FileUtils.deleteDirectory(oriFolder);
				}catch(IOException e){
					e.printStackTrace(System.out);
				}

			}
			
			FileUtils.deleteDirectory(new File(tmpFolderPath));
		}

	}
	
	private ZipRecordThread zipRecordThread;
	
	private static ClientReZipLogController clientReZipLogController;
	
	private ClientReZipLogController(){
		super();
		zipRecordThread = new ZipRecordThread();
	}
	
	public static ClientReZipLogController getInstance(){
		if(clientReZipLogController == null){
			synchronized(ClientReZipLogController.class){
				if(clientReZipLogController == null){
					clientReZipLogController = new ClientReZipLogController();
				}
			}
			
		}
		return clientReZipLogController;
	}
	
	public void setAndStart(String originLogForder, String zipedLogFilePrex){
//		System.out.println("set and start clinet re zip thread");
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
