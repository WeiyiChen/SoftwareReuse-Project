package edu.tongji.reuse.teameleven.client;

import edu.tongji.reuse.teameleven.client.ctrl.ClientReZipLogController;
import edu.tongji.reuse.teameleven.client.intf.ILoginWindow;
import edu.tongji.reuse.teameleven.client.ctrl.ClientConfigBean;
import edu.tongji.reuse.teameleven.client.ctrl.ClientMonitorController;
import edu.tongji.reuse.teameleven.client.ctrl.ClientZipLogController;
import edu.tongji.reuse.teameleven.client.transport.ClientLoginSocket;
import edu.tongji.reuse.teameleven.client.ui.LoginWindow;
import octoteam.tahiti.config.ConfigManager;
import octoteam.tahiti.config.loader.JsonAdapter;

import java.awt.*;
import java.io.IOException;

//import teamEleven.record.ClientRecordController;

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
		
		// configuration variable values
		int logSaveCycle = 60;
		int beginCompressSecs = 12;
		int internalCompressSecs = 86400;
		String zipPrex = "zipclient/logzip-";
		 
		// read the configuration
		ConfigManager configManager = new ConfigManager(new JsonAdapter(),"data/clientconfig.json");
		
		try {
			ClientConfigBean configBean = configManager.loadToBean(ClientConfigBean.class);
			logSaveCycle = configBean.getLogSaveCycle();
			beginCompressSecs = configBean.getBeginCompressSeconds();
			internalCompressSecs = configBean.getInternalCompressSeconds();
			zipPrex = configBean.getCompressPathPrex();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ClientMonitorController.setSaveCycle(logSaveCycle);
		ClientMonitorController.startRecord();
		
		ClientZipLogController.getInstance().setCompressConfig(beginCompressSecs, internalCompressSecs);

//		System.out.println("before start client zip");
		ClientZipLogController.getInstance().setAndStart("logclient", zipPrex);
//		System.out.println("before start client rezip");
		ClientReZipLogController.getInstance().setAndStart("clientdayziplog", "clientweekziplog/bar--");
//		System.out.println("after start client rezip");


	}
}
