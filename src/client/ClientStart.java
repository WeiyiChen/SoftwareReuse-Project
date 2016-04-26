package client;

import java.awt.EventQueue;
import java.io.IOException;

import client.intf.ILoginWindow;
import client.transport.ClientConfigBean;
import client.transport.ClientSocket;
import client.ui.LoginWindow;
import client.util.ClientMonitorController;
import client.util.ClientZipLogController;
//import teamEleven.record.ClientRecordController;
import octoteam.tahiti.config.ConfigManager;
import octoteam.tahiti.config.loader.JsonAdapter;

public class ClientStart {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ILoginWindow window = new LoginWindow();
					window.toShowWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		int logSaveCycle = 60;
		int beginCompressSecs = 1;
		int internalCompressSecs = 86400;
		 
		ConfigManager configManager = new ConfigManager(new JsonAdapter(),"data/clientconfig.json");
		
		try {
			ClientConfigBean configBean = configManager.loadToBean(ClientConfigBean.class);
			logSaveCycle = configBean.getLogSaveCycle();
			beginCompressSecs = configBean.getBeginCompressSeconds();
			internalCompressSecs = configBean.getInternalCompressSeconds();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ClientMonitorController.setSaveCycle(logSaveCycle);
		ClientMonitorController.startRecord();
		
		ClientZipLogController.getInstance().setCompressConfig(beginCompressSecs, internalCompressSecs);
		ClientZipLogController.getInstance().setAndStart("logclient", "zipclient/bar-");
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            try {
					ClientSocket.getSocket().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }, "Shutdown-thread"));
	}
}
