package client.ui;

import java.awt.EventQueue;

import client.intf.ILoginWindow;

public class ClientStart {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ILoginWindow window = new LoginWindow();
					window.showLoginWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
