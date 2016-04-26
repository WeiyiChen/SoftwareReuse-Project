package client;

import java.awt.EventQueue;
import java.io.IOException;

import client.intf.ILoginWindow;
import client.transport.ClientSocket;
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
