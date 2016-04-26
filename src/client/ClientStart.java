package client;

import java.awt.EventQueue;

import client.intf.ILoginWindow;
import client.ui.LoginWindow;
import client.util.ClientMonitorController;
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

		ClientMonitorController.startRecord();
	}
}
